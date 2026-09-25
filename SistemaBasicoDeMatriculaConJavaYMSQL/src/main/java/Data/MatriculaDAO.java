package data;

import model.Matricula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public List<Matricula> listarMatriculas() {
        List<Matricula> lista = new ArrayList<>();
        String sql = """
            SELECT id_matricula, id_estudiante, id_curso, ciclo, anio, nota_final
            FROM matricula
            ORDER BY anio, ciclo
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Matricula mat = new Matricula(
                        rs.getInt("id_matricula"),
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_curso"),
                        rs.getString("ciclo"),
                        rs.getInt("anio"),
                        rs.getBigDecimal("nota_final")
                );
                lista.add(mat);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar matriculas: " + e.getMessage());
        }

        return lista;
    }

    public void listarMatriculasDetalle() {
        String sql = """
            SELECT m.id_matricula,
                   CONCAT(e.nombre, ' ', e.apellido) AS estudiante,
                   cu.nombre AS curso,
                   m.ciclo,
                   m.anio,
                   m.nota_final
            FROM matricula m
            JOIN estudiante e ON m.id_estudiante = e.id_estudiante
            JOIN curso cu ON m.id_curso = cu.id_curso
            ORDER BY m.anio, m.ciclo, estudiante
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("estudiante") + " - " +
                        rs.getString("curso") + " - " +
                        rs.getString("ciclo") + " " + rs.getInt("anio") +
                        " - Nota: " + rs.getBigDecimal("nota_final")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al listar matriculas con detalle: " + e.getMessage());
        }
    }

    public void cantidadCursosPorEstudiante() {
        String sql = """
            SELECT e.nombre, e.apellido, COUNT(m.id_matricula) AS total_cursos
            FROM estudiante e
            LEFT JOIN matricula m ON e.id_estudiante = m.id_estudiante
            GROUP BY e.id_estudiante, e.nombre, e.apellido
            ORDER BY total_cursos DESC, e.apellido, e.nombre
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("nombre") + " " + rs.getString("apellido") +
                        " - Total cursos: " + rs.getInt("total_cursos")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al contar cursos por estudiante: " + e.getMessage());
        }
    }
}