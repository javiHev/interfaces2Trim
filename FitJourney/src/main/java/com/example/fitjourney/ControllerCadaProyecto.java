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
        controllerVistaProyecto.setObjectProyecto(this.proyecto);
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
