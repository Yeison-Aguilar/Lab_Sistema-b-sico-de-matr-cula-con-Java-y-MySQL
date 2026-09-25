package data;

import model.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = """
            SELECT e.id_estudiante, e.cedula, e.nombre, e.apellido, e.correo, e.id_carrera
            FROM estudiante e
            ORDER BY e.apellido, e.nombre
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estudiante est = new Estudiante(
                        rs.getInt("id_estudiante"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("id_carrera")
                );
                lista.add(est);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar estudiantes: " + e.getMessage());
        }

        return lista;
    }

    public List<Estudiante> listarEstudiantesConCarrera() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = """
            SELECT e.id_estudiante, e.cedula, e.nombre, e.apellido, e.correo, e.id_carrera, c.nombre AS nombre_carrera
            FROM estudiante e
            JOIN carrera c ON e.id_carrera = c.id_carrera
            ORDER BY e.apellido, e.nombre
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estudiante est = new Estudiante(
                        rs.getInt("id_estudiante"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("id_carrera")
                );
                lista.add(est);
                System.out.println(est.getNombre() + " " + est.getApellido() + " - " + rs.getString("nombre_carrera"));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar estudiantes con carrera: " + e.getMessage());
        }

        return lista;
    }
}