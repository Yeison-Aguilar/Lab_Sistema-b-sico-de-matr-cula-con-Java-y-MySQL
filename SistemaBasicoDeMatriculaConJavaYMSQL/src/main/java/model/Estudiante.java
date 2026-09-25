package model;

public class Estudiante extends Persona {
    private int idEstudiante;
    private int idCarrera;

    public Estudiante() {
        super();
    }

    public Estudiante(String cedula, String nombre, String apellido, String correo, int idCarrera) {
        super(cedula, nombre, apellido, correo);
        this.idCarrera = idCarrera;
    }

    public Estudiante(int idEstudiante, String cedula, String nombre, String apellido, String correo, int idCarrera) {
        super(cedula, nombre, apellido, correo);
        this.idEstudiante = idEstudiante;
        this.idCarrera = idCarrera;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
}