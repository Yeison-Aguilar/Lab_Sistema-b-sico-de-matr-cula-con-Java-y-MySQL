package data;

import java.util.List;
import model.Estudiante;


public interface EstudianteRepository {

    List<Estudiante> listarEstudiantes();

    void insertar(Estudiante estudiante);

    boolean existePorCedula(String cedula);
    
}