package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddNotas {
    @FXML
    private Button deleteBtn;
    @FXML
    private TextArea detallesTxt;

    @FXML
    private TextField tituloTxt;
    private List<Nota> listaNotas = DatosNotas.getListaNotas();
    private String idBoton;
    private Nota notaSeleccionada;


    public void iniciar(String id) {
        switch (id){
            case "notaBtn":
                deleteBtn.setVisible(true);
                deleteBtn.setManaged(true);
                break;
            case "addNota":
                //Oculta la opcion de borrado
                deleteBtn.setVisible(false);
                deleteBtn.setManaged(false);
                break;
            default:
                System.out.println("Error al obtener el id del boton");
                break;
        }
    }

    @FXML
    void deleteNota(ActionEvent event){
        int id=notaSeleccionada.getId();
        boolean resultado = borrarNota(
                id

        );
        if (resultado) {
            tituloTxt.clear();
            detallesTxt.clear();
            mostrarAlertaAceptar("Nota Borrada","Nota "+this.notaSeleccionada.getTitulo()+" borrada con éxito");
        } else {
            mostrarAlertaError("No se pudo borrar la nota");
        }
    }
    public boolean borrarNota(int id){
        for(Nota nota : listaNotas){
            if(nota.getId()==id){
                listaNotas.remove(nota);
                return true;
            }
        }
        return false;
    }
    @FXML
    void determinaFuncion(ActionEvent event){
        switch (idBoton){
            case "notaBtn":
                modifyNota();
                break;

            case "addNota":
                addNota();
                break;
            default:
                System.out.println("Error al obtener el id del boton");
                break;
        }
    }

    private void addNota() {
        String titulo=tituloTxt.getText();
        String detalles=detallesTxt.getText();
        if (!titulo.isEmpty() && !detalles.isEmpty()) {
            Nota newNota=new Nota(titulo,detalles);
            listaNotas.add(newNota);
            mostrarAlertaAceptar("Nota añadida", "La nueva nota se añadio con exito");
            tituloTxt.clear();
            detallesTxt.clear();
        }else{
            mostrarAlertaError("Debes rellenar todos los campos.");
        }
    }


    void modifyNota() {
        String titulo = tituloTxt.getText();
        String detalles = detallesTxt.getText();
        int nota=notaSeleccionada.getId();
        // Asumiendo que Proyecto es tu clase para manejar la lógica de proyectos
        // y que tiene un constructor adecuado para estos parámetros
        if (!titulo.isEmpty() && !detalles.isEmpty()) {
            boolean resultado = actualizarNota(
                    nota,
                    titulo,
                    detalles

            );
            if (resultado) {
                mostrarAlertaAceptar("Nota Actualizada","Proyecto "+this.notaSeleccionada.getTitulo()+" actualizado con éxito");
            } else {
                mostrarAlertaError("No se pudo actualizar la nota");
            }
        } else {
            mostrarAlertaError("Debes rellenar todos los campos.");
        }
    }
    /*
    * Método para actualizar la nota seleccionada
    * */
    public boolean actualizarNota(int id, String titulo, String descripcion) {
        for (Nota nota : listaNotas) {
            if (nota.getId() == id) {
                nota.setTitulo(titulo);
                nota.setDescripcion(descripcion);
                return true; // Nota encontrado y actualizada
            }
        }
        return false; // Nota no encontrada
    }
    private void mostrarAlertaAceptar(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
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

    @FXML
    void returnToController(ActionEvent event) throws IOException {

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/notas.fxml"));
        Parent root = loader.load();
        DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);

    }
    public void setNotaSeleccionada(Nota nota){
        this.notaSeleccionada=nota;
    }
    public void setIdBoton(String origen) {
        this.idBoton = origen;
    }

    public void setTituloNota(String titulo) {
        this.tituloTxt.setText(titulo);
    }

    public void setDescripcionNota(String descripcion) {
        this.detallesTxt.setText(descripcion);
    }
}
