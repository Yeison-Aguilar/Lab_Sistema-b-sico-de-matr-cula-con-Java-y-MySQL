package data;

import model.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO implements EstudianteRepository {

    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = """
            SELECT e.id_estudiante, e.cedula, e.nombre, e.apellido, e.correo, e.id_carrera
            FROM lab_estudiante e
            ORDER BY e.apellido, e.nombre
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id_estudiante"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("id_carrera")
                ));
            }

        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo leer la lista de estudiantes", e);
        }

        return lista;
    }

    @Override
    public void insertar(Estudiante est) {
        String sql = """
            INSERT INTO lab_estudiante (cedula, nombre, apellido, correo, id_carrera)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, est.getCedula());
            ps.setString(2, est.getNombre());
            ps.setString(3, est.getApellido());
            ps.setString(4, est.getCorreo());
            ps.setInt(5, est.getIdCarrera());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo guardar el estudiante", e);
        }
    }

    @Override
    public boolean existePorCedula(String cedula) {
        String sql = "SELECT 1 FROM lab_estudiante WHERE cedula = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo consultar la cédula", e);
        }
    }
}