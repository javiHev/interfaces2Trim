package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerProyectos {

    @FXML
    private ImageView addProyect;

    @FXML
    private AnchorPane pagPrincipal;

    @FXML
    private ScrollPane rellenar; // Usamos ScrollPane en lugar de ScrollBar
    private VBox contenedorProyectos = new VBox(22); // Contenedor principal para los HBox
    private HBox filaArriba = new HBox(22); // Primer HBox para la fila superior
    private HBox filaAbajo = new HBox(22); // Segundo HBox para la fila inferior

    private int numeroPagina = 0;
    private DatosProyectos datosProyectos = PagPrincipal.getCreados();

    @FXML
    public void initialize() {
        inicializarVista();
        try {
            crearProyectos(); // datosProyectos ya está inicializado
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarVista() {
        // Configuración inicial del ScrollPane
        rellenar.setContent(contenedorProyectos); // Añade el VBox al ScrollPane
        rellenar.setPannable(true);
        rellenar.setFitToWidth(true); // Asegúrate de que el ScrollPane se ajuste al ancho de los contenidos

        contenedorProyectos.getChildren().addAll(filaArriba, filaAbajo); // Añade los dos HBox al VBox
    }

    public void crearProyectos() throws IOException {
        // Limpiar los contenedores antes de añadir nuevos proyectos
        filaArriba.getChildren().clear();
        filaAbajo.getChildren().clear();

        // Suponiendo que datosProyectos.getListaProyectos() siempre tiene exactamente 6 proyectos
        for (int i = 0; i < datosProyectos.getListaProyectos().size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/cada-proyecto.fxml"));
            Parent root = fxmlLoader.load();
            ControllerCadaProyecto controllerCadaProyecto = fxmlLoader.getController();
            controllerCadaProyecto.recibirData(datosProyectos, datosProyectos.getListaProyectos().get(i));

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE); // Valores de acuerdo con el FXML de cada proyecto
            anchorPane.getChildren().setAll(root);
            anchorPane.getStyleClass().add("cadaAnchor");

            // Decide en qué HBox colocar el proyecto basado en el índice
            if (i%2==0) {
                filaArriba.getChildren().add(anchorPane);
            } else {
                filaAbajo.getChildren().add(anchorPane);
            }
        }
    }

    @FXML
    void addProyect(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/add-proyect-view.fxml"));
        try {
            // Cargar el root desde el FXML
            Parent root = fxmlLoader.load();

            // Crear una nueva escena con el root
            Scene scene = new Scene(root);
            AddProyecto controllerProyecto = fxmlLoader.getController();
            controllerProyecto.setVistaOrigen("/com/example/fitjourney/img/fxml/proyectos.fxml");

            // Crear un nuevo stage (ventana) para la nueva escena
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene); // Establecer la escena en el stage
            nuevoStage.setTitle("Añadir Proyecto"); // Opcional: establecer un título para el stage
            nuevoStage.initStyle(StageStyle.UNDECORATED);

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


    public void establecerDatos(DatosProyectos creados, int pagina) throws IOException {
        this.datosProyectos = creados;
        this.numeroPagina = pagina;
        System.out.println(this.numeroPagina);
        this.crearProyectos();

    }

    public void recibirData(DatosProyectos creados) {
        this.datosProyectos=creados;
    }
}



