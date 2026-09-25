/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package app;

import data.ConexionBD;
import data.EstudianteDAO;

import java.sql.Connection;

/**
 *
 * @author Laboratorio_M
 */
public class Main {

    public static void main(String[] args) {

        
        try (Connection conn = ConexionBD.conectar()) {
            System.out.println("------------------------------------");
            System.out.println("   Conrxion exitosa");
            System.out.println("-------------------------------------");
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return;
        }

       
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        
        System.out.println("\n----------------------------------");
        System.out.println("   Lista de estudiantes");
        System.out.println("-------------------------------------");

        estudianteDAO.listarEstudiantes();
    }
}
