package data;

import model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public List<Curso> listarCursos() {
        List<Curso> lista = new ArrayList<>();
        String sql = """
            SELECT id_curso, codigo, nombre, creditos
            FROM curso
            ORDER BY nombre
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getInt("creditos")
                );
                lista.add(curso);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar cursos: " + e.getMessage());
        }

        return lista;
    }
}