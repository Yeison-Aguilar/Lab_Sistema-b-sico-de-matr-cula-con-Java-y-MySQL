package logic;

import data.EstudianteRepository;
import java.util.List;
import model.Estudiante;

public class EstudianteService {

    private final EstudianteRepository repositorio;

    public EstudianteService(EstudianteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void registrar(Estudiante est) throws ValidacionException {
        validar(est);
        if (repositorio.existePorCedula(est.getCedula())) {
            throw new ValidacionException("Ya existe un estudiante con la cédula " + est.getCedula());
        }
        repositorio.insertar(est);
    }

    public List<Estudiante> listar() {
        return repositorio.listarEstudiantes();
    }

    private void validar(Estudiante est) throws ValidacionException {
        if (est.getCedula() == null || est.getCedula().isBlank()) {
            throw new ValidacionException("La cédula es obligatoria");
        }
        if (est.getNombre() == null || est.getNombre().isBlank()) {
            throw new ValidacionException("El nombre es obligatorio");
        }
        if (est.getApellido() == null || est.getApellido().isBlank()) {
            throw new ValidacionException("El apellido es obligatorio");
        }
        if (est.getCorreo() == null || !est.getCorreo().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new ValidacionException("El correo no tiene un formato válido");
        }
        if (est.getIdCarrera() <= 0) {
            throw new ValidacionException("Debe indicar una carrera válida");
        }
    }
}