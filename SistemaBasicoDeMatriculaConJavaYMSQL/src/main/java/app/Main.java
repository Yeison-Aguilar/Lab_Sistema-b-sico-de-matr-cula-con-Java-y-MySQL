package app;

import data.EstudianteDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Estudiante;
import data.PersistenciaException;
import javafx.scene.control.Alert;
import data.PersistenciaException;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.EstudianteService;
import logic.ValidacionException;

public class Main extends Application {

private final EstudianteService servicio = new EstudianteService(new EstudianteDAO());

@Override
public void start(Stage stage) {

    Label titulo = new Label("Sistema de Matrícula");
    titulo.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;"
    );

    Label subtitulo = new Label("Lista de estudiantes");
    subtitulo.setStyle("-fx-font-size: 18px;");

    TableView<Estudiante> tabla = new TableView<>();
    
    TableColumn<Estudiante, Integer> columnaId =
            new TableColumn<>("ID");

    columnaId.setCellValueFactory(
            new PropertyValueFactory<>("idEstudiante")
    );

    TableColumn<Estudiante, String> columnaCedula =
            new TableColumn<>("Cédula");

    columnaCedula.setCellValueFactory(
            new PropertyValueFactory<>("cedula")
    );

    TableColumn<Estudiante, String> columnaNombre =
            new TableColumn<>("Nombre");

    columnaNombre.setCellValueFactory(
            new PropertyValueFactory<>("nombre")
    );

    TableColumn<Estudiante, String> columnaApellido =
            new TableColumn<>("Apellido");

    columnaApellido.setCellValueFactory(
            new PropertyValueFactory<>("apellido")
    );

    TableColumn<Estudiante, String> columnaCorreo =
            new TableColumn<>("Correo");

    columnaCorreo.setCellValueFactory(
            new PropertyValueFactory<>("correo")
    );

    TableColumn<Estudiante, Integer> columnaCarrera =
            new TableColumn<>("ID Carrera");

    columnaCarrera.setCellValueFactory(
            new PropertyValueFactory<>("idCarrera")
    );

    tabla.getColumns().addAll(
            columnaId,
            columnaCedula,
            columnaNombre,
            columnaApellido,
            columnaCorreo,
            columnaCarrera
    );

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
    botonGuardar.setOnAction(event ->
        guardar(txtCedula, txtNombre, txtApellido, txtCorreo, txtCarrera, tabla));
    
    
    Button botonActualizar = new Button("Actualizar");

    botonActualizar.setOnAction(event -> {
        cargarEstudiantes(tabla);
    });

    Button botonSalir = new Button("Salir");

    botonSalir.setOnAction(event -> {
        stage.close();
    });

    HBox botones = new HBox(10);
    botones.getChildren().addAll(botonGuardar, botonActualizar, botonSalir
    );

    VBox encabezado = new VBox(5);
    encabezado.getChildren().addAll(
            titulo,
            subtitulo
    );

    VBox contenido = new VBox(15);
    contenido.setStyle("-fx-padding: 20;");
    contenido.getChildren().addAll(encabezado, tabla, formulario, botones
    );

    BorderPane root = new BorderPane();
    root.setCenter(contenido);

    Scene scene = new Scene(root, 900, 550);

    stage.setTitle("Sistema de Matrícula");
    stage.setScene(scene);
    stage.show();

    cargarEstudiantes(tabla);
}

private void cargarEstudiantes(TableView<Estudiante> tabla) {
    try {
        tabla.setItems(
                FXCollections.observableArrayList(servicio.listar())
        );
    } catch (PersistenciaException ex) {
        new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
    }
}
private void guardar(TextField cedula, TextField nombre, TextField apellido,
                     TextField correo, TextField carrera, TableView<Estudiante> tabla) {
    int idCarrera;
    try {
        idCarrera = Integer.parseInt(carrera.getText().trim());
    } catch (NumberFormatException ex) {
        mostrar(Alert.AlertType.WARNING, "El ID de carrera debe ser un número entero");
        return;
    }

    Estudiante est = new Estudiante(cedula.getText().trim(), nombre.getText().trim(),
            apellido.getText().trim(), correo.getText().trim(), idCarrera);

    try {
        servicio.registrar(est);
        mostrar(Alert.AlertType.INFORMATION, "Estudiante registrado");
        cargarEstudiantes(tabla);
        cedula.clear();
        nombre.clear();
        apellido.clear();
        correo.clear();
        carrera.clear();
    } catch (ValidacionException ex) {
        mostrar(Alert.AlertType.WARNING, ex.getMessage());
    } catch (PersistenciaException ex) {
        mostrar(Alert.AlertType.ERROR, ex.getMessage());
    }
}

private void mostrar(Alert.AlertType tipo, String mensaje) {
    new Alert(tipo, mensaje).showAndWait();
}
public static void main(String[] args) {
    launch(args);
}


}