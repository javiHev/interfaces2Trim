package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import atlantafx.base.controls.Calendar;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ControllerCalendario {

        @FXML
        private Calendar calendario;
        private DatosProyectos datosProyectos=PagPrincipal.getDatosProyectos();
        private List<Proyectos> listaProyectos=DatosProyectos.getListaProyectos();

    @FXML
    public void initialize( ) {
        cargarFechas();
    }


    public void cargarFechas() {
        calendario.setOnMouseClicked(event -> {
            LocalDate selectedDate = calendario.getValue();
            if (selectedDate != null) {
                mostrarDetallesProyectos(selectedDate, event);
            }
        });

        actualizarVisualizacionDias();
    }
    private void mostrarDetallesProyectos(LocalDate fecha, MouseEvent event) {
        if (fecha != null) {
            for (Proyectos proyecto : listaProyectos) {
                // Solo mostrar detalles si la fecha coincide
                if (proyecto.getFecha().equals(fecha)) {
                    this.datosProyectos.setProyectoSeleccionado(proyecto);
                    this.datosProyectos.setVistaAnterior(true);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/view-proyecto.fxml"));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException err) {
                        System.out.println(err.getMessage());
                        return; // Salir del método si hay un error
                    }
                    VistaProyecto controllerVistaProyecto = fxmlLoader.getController();
                    System.out.println("Proyecto escogido: " + proyecto.getNombre());
                    controllerVistaProyecto.setObjectProyecto(proyecto);
                    controllerVistaProyecto.setNombreProyecto(proyecto.getNombre());

                    // Formatea la fecha si es necesario, asumiendo que ya tienes un formatter definido
                    String fechaFormateada = proyecto.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    controllerVistaProyecto.setDateProyect(fechaFormateada);

                    controllerVistaProyecto.setTareas(proyecto.getTareas());
                    controllerVistaProyecto.establecerDatos(this.datosProyectos);
                    controllerVistaProyecto.setVistaOrigen("/com/example/fitjourney/img/fxml/calendario.fxml");

                    // Cambiar a la nueva vista
                    datosProyectos.getControllers().getControllerPagPrincipal().cambiarContenido(root);

                    break; // Rompe el bucle después de encontrar y procesar el proyecto deseado
                }
            }
        }
    }



    public void actualizarVisualizacionDias() {
        calendario.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                // Resetea el estilo para cada celda
                setStyle("");

                // Verifica si la fecha de la celda actual coincide con alguna fecha de los proyectos
                for (Proyectos proyecto : listaProyectos) {
                    if (proyecto.getFecha() != null && item.equals(proyecto.getFecha())) {
                        // Si hay un proyecto para esta fecha, establecer el estilo en rojo
                        setStyle("-fx-background-color: #4F6094; -fx-text-fill: #EEEEEE;");
                        break; // No es necesario verificar más proyectos si uno coincide
                    }
                }
            }
        });
    }
    /**
     * Método que se encarga de recibir información y comprobar las entregas
     *
     * @param datos clase con información
     */
    public void recibirData(DatosProyectos datos) {
        this.datosProyectos = datos;
        calendario.setValue(LocalDate.now());
    }
}
