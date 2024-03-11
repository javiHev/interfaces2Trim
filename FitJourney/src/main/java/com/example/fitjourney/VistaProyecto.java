package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VistaProyecto {
    @FXML
    private Label nombreProyecto;

    @FXML
    private Label tarea1;

    @FXML
    private Label tarea2;

    @FXML
    private Label tarea3;
    private DatosProyectos datosProyectos;
    private String vistaOrigen;

    public void setVistaOrigen(String origen) {
        this.vistaOrigen = origen;
    }
    @FXML
    void returnToController(ActionEvent event) throws IOException {
        if (vistaOrigen != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaOrigen));
            Parent root = loader.load();
            DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);
        }
    }
    public void establecerDatos(DatosProyectos creados){
        this.datosProyectos = creados;
    }
    public void recibirData(DatosProyectos listaProyectos) {
        this.datosProyectos=listaProyectos;
    }

    public void setNombreProyecto(String nombre) {
        this.nombreProyecto.setText(nombre);
    }

    public void setTareas(List<String> tareasSet) {
        // Convierte el conjunto a una lista para poder acceder a los elementos por índice
        List<String> tareas = new ArrayList<>(tareasSet);
        // Asigna los elementos del conjunto a las variables tarea1, tarea2 y tarea3
        if (tareas.size() >= 1) {
            tarea1.setText(tareas.get(0));
        }
        if (tareas.size() >= 2) {
            tarea2.setText(tareas.get(1));
        }
        if (tareas.size() >= 3) {
            tarea3.setText(tareas.get(2));
        }
    }
}
