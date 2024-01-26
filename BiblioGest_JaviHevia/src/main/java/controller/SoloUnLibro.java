package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.LenguageManager;
import model.Libro;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;

import java.io.IOException;

public class SoloUnLibro {

    @FXML
    private Label autorLibro;

    @FXML
    private Button btnVer;

    @FXML
    private Label nombreLibro;
    private Libro libro;
    private LibrosCreados librosCreados;

    @FXML
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
    }

}
