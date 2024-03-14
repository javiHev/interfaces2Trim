package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerNotas {
    private DatosNotas datosNotas=PagPrincipal.getNotasCreadas();
    public void recibirData(DatosNotas datosNotas) {
        this.datosNotas=datosNotas;
    }

    @FXML
    private ScrollPane rellenar; // Usamos ScrollPane en lugar de ScrollBar
    private VBox contenedorNotas = new VBox(); // Contenedor para las notas
    private int numeroPagina = 0;

    @FXML
    public void initialize() {
        //contenedorNotas.setAlignment(Pos.CENTER);
        inicializarVista();
        try {
            crearNotas(); // datosProyectos ya está inicializado
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarVista() {
        // Configuración inicial del ScrollPane
        rellenar.setContent(contenedorNotas); // Añade el VBox al ScrollPane
        rellenar.setPannable(true);
        rellenar.setFitToWidth(true); // Asegúrate de que el ScrollPane se ajuste al ancho de los contenidos

    }

    public void crearNotas() throws IOException {
        contenedorNotas.getChildren().clear(); // Limpia el VBox antes de añadir nuevas notas

        for (int i = 0; i < datosNotas.getListaNotas().size(); i++) {
            System.out.println("Hola");
            System.out.println(datosNotas.getListaNotas().get(i).getTitulo());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/cada-nota.fxml"));
            AnchorPane anchorPane = fxmlLoader.load(); // Carga el AnchorPane desde el FXML
            ControllerCadaNota controllerCadaNota = fxmlLoader.getController();
            controllerCadaNota.recibirData(datosNotas, datosNotas.getListaNotas().get(i));

            // Configura el AnchorPane si es necesario (por ejemplo, añadiendo estilo)
            anchorPane.getStyleClass().add("cadaAnchor");

            contenedorNotas.getChildren().add(anchorPane); // Añade directamente el AnchorPane al VBox
        }
    }

    @FXML
    void addNota(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/add-notas-view.fxml"));
        try {
            // Cargar el root desde el FXML
            Parent root = fxmlLoader.load();

            // Crear una nueva escena con el root
            Scene scene = new Scene(root);
            AddNotas contrrollerAddNota = fxmlLoader.getController();
            contrrollerAddNota.setVistaOrigen("com/example/fitjourney/img/fxml/notas.fxml");
            // Crear un nuevo stage (ventana) para la nueva escena
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene); // Establecer la escena en el stage
            nuevoStage.setTitle("Añadir Nota"); // Opcional: establecer un título para el stage

            // Mostrar el nuevo stage
            nuevoStage.show();


        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


}
