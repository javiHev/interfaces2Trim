package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AddNotas {

    @FXML
    private TextArea detallesTxt;

    @FXML
    private TextField tituloTxt;
    private List<Nota> listaNotas=DatosNotas.getListaNotas();
    private String vistaOrigen;
    private Proyectos notaSeleccionada;


    @FXML
    void returnToController(ActionEvent event) throws IOException {

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/notas.fxml"));
        Parent root = loader.load();
        DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);

    }
    public void setVistaOrigen(String origen) {
        this.vistaOrigen = origen;
    }

    public void setTituloNota(String titulo) {
        this.tituloTxt.setText(titulo);
    }

    public void setDescripcionNota(String descripcion) {
        this.detallesTxt.setText(descripcion);
    }
}
