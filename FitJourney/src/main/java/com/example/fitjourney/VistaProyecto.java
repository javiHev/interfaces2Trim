package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class VistaProyecto {
    @FXML
    private Label nombreProyecto;
    @FXML
    private Label fechaProyecto;

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
            System.out.println(vistaOrigen);
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

    public void setDateProyect(String fecha){this.fechaProyecto.setText(fecha);}

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


    @FXML
    void modifyProyect(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/add-proyect-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            AddProyecto controllerProyecto = fxmlLoader.getController();

            // Aquí es donde pasarías los datos del proyecto a modificar a la vista AddProyecto
            // Necesitas obtener los datos del proyecto actual y pasárselos
            // Ejemplo: Suponiendo que tienes un método para obtener los datos del proyecto actual:
            String nombre = this.nombreProyecto.getText(); // Si estos son Labels, obtendrás el texto directamente
            List<String> tareas = Arrays.asList(tarea1.getText(), tarea2.getText(), tarea3.getText());
            if (tareas.size() >= 1) {
                controllerProyecto.setTxt1Field(tareas.get(0)); // Actualiza el primer TextField
            }
            if (tareas.size() >= 2) {
                controllerProyecto.setTxt2Field(tareas.get(1)); // Actualiza el segundo TextField
            }
            if (tareas.size() >= 3) {
                controllerProyecto.setText3Field(tareas.get(2)); // Actualiza el tercer TextField
            }
            controllerProyecto.setNombreProyecto(nombre);
            controllerProyecto.setVistaOrigen("/com/example/fitjourney/img/fxml/view-proyecto.fxml");
            controllerProyecto.setFechaEscogida(fechaProyecto.getText());

            Scene scene = new Scene(root);
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene);
            nuevoStage.setTitle("Modificar Proyecto");
            nuevoStage.show();

        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
