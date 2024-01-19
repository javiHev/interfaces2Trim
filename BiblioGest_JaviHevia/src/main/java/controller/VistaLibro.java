package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import model.LenguageManager;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;


import java.io.IOException;
import java.util.ResourceBundle;

public class VistaLibro {
    @FXML
    private Label autorLibro;
    @FXML
    private Label tituloLibro;
    @FXML
    private Button volver;
    private String vistaOrigen;


    private PagPrincipal pagPrincipalController;
    private LibrosCreados creados;

    public void setVistaOrigen(String origen) {
        this.vistaOrigen = origen;
    }

    public void setPagPrincipalController(PagPrincipal controller) {
        this.pagPrincipalController = controller;
    }



    public void setTituloLibro(String s) {
        tituloLibro.setText(s);
    }

    public void setAutorLibro(String s) {
        autorLibro.setText(s);
    }

    @FXML
    void returnToController(ActionEvent event) throws IOException {
        Button btnVolver = (Button) event.getSource();
        ResourceBundle bundle = LenguageManager.getInstance().getBundle();
        String ultimaVista = this.creados.getUltimaVistaFXML();



            // Si no hay una "última vista" definida, carga una vista por defecto
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliogest_javihevia/libros.fxml"), bundle);
            Parent root = loader.load();
            this.creados.getControllers().getControllerPagPrincipal().cambiarStage(root);
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setTitle("BiblioGest");
            stage.setScene(root.getScene());

    }

    private ResourceBundle resourceBundle;

    public void establecerDatos(LibrosCreados creados){
        this.creados = creados;
    }

    public void recibirData(LibrosCreados creado) {
        this.creados=creado;
    }
}
