package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControllerCadaProyecto {
    @FXML
    private Button proyectoBtn;
    @FXML
    private ImageView imageView;
    private DatosProyectos datosProyectos;
    private Proyectos proyecto;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /*void cambiarVista(MouseEvent event) {
        try {
            if (this.proyecto != null) {


                // Carga el archivo FXML y pasa el ResourceBundle
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/view-proyecto.fxml"));
                Parent root = fxmlLoader.load();
                VistaProyecto vistaLibro = fxmlLoader.getController();
                vistaLibro.setNombreProyecto(this.proyecto.getNombre());
                vistaLibro.setTareas(this.proyecto.getTareas());

                vistaLibro.setVistaOrigen("libros.fxml");
                vistaLibro.recibirData(this.datosProyectos);
                this.datosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);
            } else {
                mostrarAlertaError("Tienes que seleccionar algún libro");
            }
        } catch (IOException e) {
            mostrarAlertaError("Error al cargar la vista del libro: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
    @FXML
    void ver(ActionEvent event) {
        this.datosProyectos.setProyectoSeleccionado(this.proyecto);
        this.datosProyectos.setVistaAnterior(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/view-proyecto.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
        VistaProyecto controllerVistaProyecto =fxmlLoader.getController();
        System.out.println("Proyecto escogido: "+this.proyecto.getNombre());
        controllerVistaProyecto.setNombreProyecto(this.proyecto.getNombre());
        LocalDate date=this.proyecto.getFecha();
        String fecha=date.format(formatter);
        controllerVistaProyecto.setDateProyect(fecha);
        controllerVistaProyecto.setTareas(this.proyecto.getTareas());
        controllerVistaProyecto.establecerDatos(this.datosProyectos);
        controllerVistaProyecto.setVistaOrigen("/com/example/fitjourney/img/fxml/proyectos.fxml");
        this.datosProyectos.getControllers().getControllerPagPrincipal().cambiarContenido(root);
    }
    public void recibirData(DatosProyectos creados, Proyectos proyecto){
        this.datosProyectos=creados;
        this.proyecto = proyecto;
        this.proyectoBtn.setText(this.proyecto.getNombre());
        this.imageView.setMouseTransparent(true);
    }

}
