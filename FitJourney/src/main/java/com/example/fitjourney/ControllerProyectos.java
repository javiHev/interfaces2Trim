package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerProyectos {




    public void recibirData(DatosProyectos creados) {
        this.datosProyectos=creados;
    }
    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Label pagina;

    @FXML
    private GridPane rellenar;
    private int numeroPagina;
    private int paginasTotales;
    private int proyectosXpagina = 6;

    /*static List<Proyectos> listaProyectos=new ArrayList<>();*/
    private DatosProyectos datosProyectos=PagPrincipal.getCreados();


/*    public static void addProyectToList(Proyectos proyecto) {
        listaProyectos.add(proyecto);
        System.out.println("Proyecto: " + proyecto.getNombre() );
        int i=1;
        for(String tarea:proyecto.getTareas()){
            System.out.println("Tareas"+i+": "+tarea);
            i++;
        }

    }*/

    public void crearProyectos() throws IOException {
        int y = 0;
        this.rellenar.setHgap(10);
        this.rellenar.setVgap(10);

        for(int i = proyectosXpagina * numeroPagina; i < proyectosXpagina * (numeroPagina + 1) && i < DatosProyectos.getListaProyectos().size(); i++){
            System.out.println(i);
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId(DatosProyectos.getListaProyectos().get(i).getNombre());


            anchorPane.getStyleClass().add("cadaAnchor");

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/cada-proyecto.fxml"));
            Parent root = fxmlLoader.load();
            anchorPane.getChildren().setAll(root);


            int fila = y / 4;
            int columna = y % 4;
            Insets margin = new Insets(10, 10, 10, 10);
            GridPane.setMargin(anchorPane, margin);
            GridPane.setFillHeight(anchorPane,false);
            GridPane.setFillWidth(anchorPane,false);
            this.rellenar.setPrefSize(800,600);

            GridPane.setConstraints(anchorPane,columna,fila);
            this.rellenar.getChildren().add(anchorPane);
            y++;
        }
        this.rellenar.setLayoutX(70);
        this.rellenar.setLayoutY(70);

    }



    @FXML
    public void cambiarVista(Proyectos proyectoSeleccionado) {
        try {
            if (proyectoSeleccionado != null) {
                // Carga el archivo FXML y pasa el ResourceBundle
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("com/example/fitjourney/img/fxml/view-proyecto.fxml"));
                Parent root = fxmlLoader.load();
                VistaProyecto vistaProyecto=fxmlLoader.getController();
                vistaProyecto.setVistaOrigen("/com/example/fitjourney/img/fxml/proyectos.fxml");
                vistaProyecto.recibirData(this.datosProyectos);
                DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);
            } else {
                mostrarAlertaError("Tienes que seleccionar algún proyecto");
            }
        } catch (IOException e) {
            mostrarAlertaError("Error al cargar la vista del libro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void mostrarAlertaAceptar(String titulo,String head,String contenido){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setHeaderText(head);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    @FXML
    void anterior(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/proyectos.fxml"));
        Parent root = fxmlLoader.load();
        ControllerProyectos controllerProyectos = fxmlLoader.getController();
        controllerProyectos.establecerDatos(this.datosProyectos,this.numeroPagina - 1);
        DatosProyectos.getControllers().getControllerPagPrincipal().cambiarContenido(root);
    }

    @FXML
    void siguiente(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/proyectos.fxml"));
        Parent root = fxmlLoader.load();
        ControllerProyectos controllerProyectos = fxmlLoader.getController();
        controllerProyectos.establecerDatos(this.datosProyectos,this.numeroPagina + 1);
        DatosProyectos.getControllers().getControllerPagPrincipal().cambiarContenido(root);
    }
    public void establecerDatos(DatosProyectos creados, int pagina) throws IOException {
        this.datosProyectos = creados;
        this.numeroPagina = pagina;
        this.paginasTotales = (int) (double) (DatosProyectos.getListaProyectos().size() / this.proyectosXpagina);
        if((double) this.numeroPagina <= 0){
            this.btnAnterior.setDisable(true);
        }
        if((double) this.numeroPagina>= this.paginasTotales){
            this.btnSiguiente.setDisable(true);
        }
        System.out.println(this.numeroPagina);
        System.out.println(this.paginasTotales);
        this.crearProyectos();

    }
}
