package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddProyecto {

    @FXML
    private DatePicker fechaEscogida;

    @FXML
    private TextField nombreProyecto;

    @FXML
    private TextField txt1Field;

    @FXML
    private TextField text3Field;

    @FXML
    private TextField txt2Field;
    private List<Proyectos> listaProyectos=DatosProyectos.getListaProyectos();
    private String vistaOrigen;

    // Asumiendo que tienes una estructura para almacenar tus proyectos
    // y un método para añadirlos a esa estructura, así como métodos para mostrar alertas

    @FXML
    void crearProyecto(ActionEvent event) {
        String nombre = nombreProyecto.getText();
        String tarea1 = txt1Field.getText();
        String tarea2 = txt2Field.getText();
        String tarea3 = text3Field.getText();
        LocalDate fecha = fechaEscogida.getValue();

        // Asumiendo que Proyecto es tu clase para manejar la lógica de proyectos
        // y que tiene un constructor adecuado para estos parámetros
        if (!nombre.isEmpty() && !tarea1.isEmpty() && !tarea2.isEmpty() && !tarea3.isEmpty() && fecha != null) {
            // Crear un nuevo proyecto con los datos recolectados
            Proyectos proyecto = new Proyectos(nombre, fecha,List.of(tarea1, tarea2, tarea3));
            // Aquí deberías añadir el proyecto a tu estructura de datos (no mostrada en tu código)
            listaProyectos.add(proyecto);
            mostrarAlertaAceptar("Proyecto añadido", "El proyecto ha sido añadido con éxito");

            // Limpiar campos
            nombreProyecto.clear();
            txt1Field.clear();
            txt2Field.clear();
            text3Field.clear();
            fechaEscogida.setValue(null);
        } else {
            mostrarAlertaError("Debes rellenar todos los campos.");
        }
    }

    @FXML
    void returnToController(ActionEvent event) throws IOException {

            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/proyectos.fxml"));
            Parent root = loader.load();
            DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);

    }

    private void mostrarAlertaAceptar(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void setVistaOrigen(String origen) {
        this.vistaOrigen = origen;
    }

    /*Modificar un proyecto*/

    public void setFechaEscogida(String fechaEnString) {
        // Convierte el String a LocalDate
        LocalDate fecha = LocalDate.parse(fechaEnString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // Establece la fecha en el DatePicker
        fechaEscogida.setValue(fecha);
        setColorFechaEscogida(fechaEscogida);
    }
    public void setColorFechaEscogida(DatePicker fechaEscogida) {
        // Cambia el color del texto del DatePicker a verde
        fechaEscogida.setStyle("-fx-text-fill: green; -fx-control-inner-background: #ccffcc;");
    }
    public void setNombreProyecto(String  nombreProyecto) {
        this.nombreProyecto.setText(nombreProyecto);
    }

    public void setTxt1Field(String txt1Field) {
        this.txt1Field.setText(txt1Field);
    }

    public void setText3Field(String text3Field) {
        this.text3Field.setText(text3Field);
    }

    public void setTxt2Field(String txt2Field) {
        this.txt2Field.setText(txt2Field);
    }
}
