package app;

import data.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// VERSIÓN MALA: el botón valida y hace el SQL él mismo. Solo para la comparación.
public class MainMalo extends Application {

    @Override
    public void start(Stage stage) {
        TextField txtCedula = new TextField();
        TextField txtNombre = new TextField();
        TextField txtApellido = new TextField();
        TextField txtCorreo = new TextField();
        TextField txtCarrera = new TextField();

        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(8);
        formulario.addRow(0, new Label("Cédula:"), txtCedula);
        formulario.addRow(1, new Label("Nombre:"), txtNombre);
        formulario.addRow(2, new Label("Apellido:"), txtApellido);
        formulario.addRow(3, new Label("Correo:"), txtCorreo);
        formulario.addRow(4, new Label("ID Carrera:"), txtCarrera);

        Button botonGuardar = new Button("Guardar");
        botonGuardar.setOnAction(event -> {
            // 1. Validaciones (reglas de negocio dentro del botón)
            if (txtCedula.getText().isBlank()) {
                new Alert(Alert.AlertType.WARNING, "La cédula es obligatoria").showAndWait();
                return;
            }
            if (!txtCorreo.getText().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                new Alert(Alert.AlertType.WARNING, "El correo no tiene un formato válido").showAndWait();
                return;
            }
            int idCarrera;
            try {
                idCarrera = Integer.parseInt(txtCarrera.getText().trim());
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.WARNING, "El ID de carrera debe ser un número").showAndWait();
                return;
            }

            // 2. SQL directo desde el evento (persistencia dentro del botón)
            String sql = "INSERT INTO lab_estudiante (cedula, nombre, apellido, correo, id_carrera) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = ConexionBD.conectar();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, txtCedula.getText().trim());
                ps.setString(2, txtNombre.getText().trim());
                ps.setString(3, txtApellido.getText().trim());
                ps.setString(4, txtCorreo.getText().trim());
                ps.setInt(5, idCarrera);
                ps.executeUpdate();
                new Alert(Alert.AlertType.INFORMATION, "Estudiante registrado").showAndWait();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "No se pudo guardar").showAndWait();
            }
        });

        VBox contenido = new VBox(15, formulario, botonGuardar);
        contenido.setStyle("-fx-padding: 20;");
        stage.setScene(new Scene(contenido, 400, 300));
        stage.setTitle("Versión mala - Guardar");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}