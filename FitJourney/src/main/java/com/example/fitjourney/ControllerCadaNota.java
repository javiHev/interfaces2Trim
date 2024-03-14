package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ControllerCadaNota {

    private DatosNotas datosNotas;
    private Nota nota;
    @FXML
    private Button notaBtn;

    @FXML
    void ver(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/add-notas-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            AddNotas controllerNotas = fxmlLoader.getController();

            // Aquí es donde pasarías los datos del proyecto a modificar a la vista AddNotas
            // Necesitas obtener los datos del proyecto actual y pasárselos
            String titulo = this.nota.getTitulo(); // Si estos son Labels, obtendrás el texto directamente
            String descripcion=this.nota.getDescripcion();

            controllerNotas.setTituloNota(titulo);
            controllerNotas.setVistaOrigen("/com/example/fitjourney/img/fxml/notas.fxml");
            controllerNotas.setDescripcionNota(descripcion);

            Scene scene = new Scene(root);
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene);
            nuevoStage.setTitle("Modificar Nota");
            nuevoStage.show();

        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }




    public void recibirData(DatosNotas datosNotas, Nota nota) {
        this.datosNotas=datosNotas;
        this.nota=nota;
        this.notaBtn.setText(nota.getTitulo());
    }
}
