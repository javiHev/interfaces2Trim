package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.LenguageManager;
import model.Libro;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;

import java.io.IOException;
import java.util.ResourceBundle;

public class SoloUnLibro {

    @FXML
    private Label autorLibro;

    @FXML
    private Button btnVer;

    @FXML
    private Label nombreLibro;
    @FXML
    private ImageView meterImagen;
    private Libro libro;
    private LibrosCreados librosCreados;

    void ver(MouseEvent event) {
        this.librosCreados.setLibroSeleccionado(this.libro);
        this.librosCreados.setVistaAnterior(true);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistalibro.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = null;
        try {
            root = fxmlLoader.load();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
        VistaLibro controllerVistaLibro =fxmlLoader.getController();
        controllerVistaLibro.establecerDatos(this.librosCreados);
        this.librosCreados.getControllers().getControllerPagPrincipal().cambiarContenido(root);
    }
    public void recibirData(LibrosCreados creados, Libro libro){
        this.librosCreados=creados;
        this.libro = libro;
        this.nombreLibro.setText(this.libro.getNombre());
        this.autorLibro.setText(this.libro.getAutor());
        this.meterImagen.setImage(new Image("file:"+this.libro.getImagen()));
    }

    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void mostrarAlertaAceptar(String titulo,String head,String contenido){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setHeaderText(head);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public void cambiarVista(MouseEvent mouseEvent) {
        try {
            if (this.libro != null) {
                // Obtiene el ResourceBundle del LenguageManager
                ResourceBundle bundle = LenguageManager.getInstance().getBundle();

                // Carga el archivo FXML y pasa el ResourceBundle
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistalibro.fxml"), LenguageManager.getInstance().getBundle());
                Parent root = fxmlLoader.load();
                VistaLibro vistaLibro = fxmlLoader.getController();
                vistaLibro.setTituloLibro(this.libro.getNombre());
                vistaLibro.setAutorLibro(this.libro.getAutor());

                vistaLibro.setVistaOrigen("libros.fxml");
                vistaLibro.recibirData(this.librosCreados);
                this.librosCreados.getControllers().getControllerPagPrincipal().cambiarStage(root);
            } else {
                mostrarAlertaError("Tienes que seleccionar algún libro");
            }
        } catch (IOException e) {
            mostrarAlertaError("Error al cargar la vista del libro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
