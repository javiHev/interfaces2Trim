package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerProyectos {

    @FXML
    private ImageView addProyect;

    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;

    @FXML
    private AnchorPane pagPrincipal;



    @FXML
    private GridPane rellenar;
    private int numeroPagina=0;
    private int paginasTotales;
    private int proyectosXpagina = 6;
    private DatosProyectos datosProyectos = PagPrincipal.getCreados();



    @FXML
    public void initialize() {
        inicializarVista();
        try {
            establecerDatos(datosProyectos, numeroPagina); // datosProyectos ya está inicializado
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlertaError("Error al inicializar la vista de Proyectos.");
        }
    }
    private void inicializarVista() {
        rellenar.setHgap(10);
        rellenar.setVgap(10);
        rellenar.setLayoutX(70);
        rellenar.setLayoutY(70);
    }
    public void crearProyectos() throws IOException {
        rellenar.getChildren().clear();

        for (int i = proyectosXpagina * numeroPagina, y = 0; i < proyectosXpagina * (numeroPagina + 1) && i < datosProyectos.getListaProyectos().size(); i++, y++) {
            System.out.println(i);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/cada-proyecto.fxml"));
            Parent root = fxmlLoader.load();
            ControllerCadaProyecto controllerCadaProyecto = fxmlLoader.getController();
            controllerCadaProyecto.recibirData(this.datosProyectos, this.datosProyectos.getListaProyectos().get(i));

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefSize(234.0, 189.0); // Estos valores deben coincidir con los del FXML de cada proyecto
            anchorPane.getChildren().setAll(root);
            anchorPane.getStyleClass().add("cadaAnchor");

            // Ajusta los márgenes y la posición en el GridPane
            GridPane.setMargin(anchorPane, new Insets(10)); // Ajusta el margen según necesites
            GridPane.setConstraints(anchorPane, y % 3, y / 3); // Asegúrate de que esta configuración evite la superposición

            rellenar.getChildren().add(anchorPane);
        }


    }


    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    @FXML
    void anterior() throws IOException {
        if (numeroPagina > 0) {
            establecerDatos(datosProyectos, numeroPagina - 1);
        }
    }

    @FXML
    void siguiente() throws IOException {
        if (numeroPagina < paginasTotales - 1) {
            establecerDatos(datosProyectos, numeroPagina + 1);
        }
    }


    public void establecerDatos(DatosProyectos creados, int pagina) throws IOException {
        this.datosProyectos = creados;
        this.numeroPagina = pagina;
        this.paginasTotales = (int) (double) (DatosProyectos.getListaProyectos().size() / this.proyectosXpagina);
        if ((double) this.numeroPagina <= 0) {
            this.btnAnterior.setDisable(true);
        }
        if ((double) this.numeroPagina >= this.paginasTotales) {
            this.btnSiguiente.setDisable(true);
        }
        System.out.println(this.numeroPagina);
        System.out.println(this.paginasTotales);
        this.crearProyectos();

    }

    public void recibirData(DatosProyectos creados) {
        this.datosProyectos=creados;
    }
}



