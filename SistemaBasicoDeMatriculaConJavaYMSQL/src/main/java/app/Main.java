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

public class Main extends Application {

private final EstudianteDAO estudianteDAO = new EstudianteDAO();

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

    Button botonActualizar = new Button("Actualizar");

    botonActualizar.setOnAction(event -> {
        cargarEstudiantes(tabla);
    });

    Button botonSalir = new Button("Salir");

    botonSalir.setOnAction(event -> {
        stage.close();
    });

    HBox botones = new HBox(10);
    botones.getChildren().addAll(
            botonActualizar,
            botonSalir
    );

    VBox encabezado = new VBox(5);
    encabezado.getChildren().addAll(
            titulo,
            subtitulo
    );

    VBox contenido = new VBox(15);
    contenido.setStyle("-fx-padding: 20;");
    contenido.getChildren().addAll(
            encabezado,
            tabla,
            botones
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

    tabla.setItems(
            FXCollections.observableArrayList(
                    estudianteDAO.listarEstudiantes()
            )
    );
}

public static void main(String[] args) {
    launch(args);
}


}