package model;

import java.math.BigDecimal;

public class Matricula {
    private int idMatricula;
    private int idEstudiante;
    private int idCurso;
    private String ciclo;
    private int anio;
    private BigDecimal notaFinal;

    public Matricula() {
    }

    public Matricula(int idEstudiante, int idCurso, String ciclo, int anio, BigDecimal notaFinal) {
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
        this.ciclo = ciclo;
        this.anio = anio;
        this.notaFinal = notaFinal;
    }

    public Matricula(int idMatricula, int idEstudiante, int idCurso, String ciclo, int anio, BigDecimal notaFinal) {
        this.idMatricula = idMatricula;
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
        this.ciclo = ciclo;
        this.anio = anio;
        this.notaFinal = notaFinal;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }
}