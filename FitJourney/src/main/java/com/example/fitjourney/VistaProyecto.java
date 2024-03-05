package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/"+vistaOrigen));
            Parent root = loader.load();
            DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);
        }
    }

    public void recibirData(DatosProyectos listaProyectos) {
        this.datosProyectos=listaProyectos;
    }
}
