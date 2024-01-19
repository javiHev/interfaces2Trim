package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Controller;
import model.LenguageManager;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
public class Configuracion {

    @FXML
    private ComboBox<String> colorBox;

    @FXML
    private Button guardarCambios;

    @FXML
    private ComboBox<String> idiomaBox;
    @FXML
    private Label cambiarColor;

    @FXML
    private Label cambiarIdioma;
    private LibrosCreados creado;
    private LibrosCreados librosCreados;


    @FXML
    void guardar(ActionEvent event) throws IOException {
        //this.cambiarColor();
        this.cambiarIdioma();
    }

    public void recibirData(LibrosCreados creado) {
        this.creado = creado;
        this.idiomaBox.getItems().setAll(LenguageManager.getInstance().getBundle().getString("idioma1"),
                LenguageManager.getInstance().getBundle().getString("idioma2"));

        this.colorBox.getItems().setAll(LenguageManager.getInstance().getBundle().getString("color1"),
                LenguageManager.getInstance().getBundle().getString("color2"));

        if (this.creado.isOscuro()) {
            this.colorBox.getSelectionModel().selectFirst();
        } else {
            this.colorBox.getSelectionModel().selectLast();
        }

        if(this.creado.isEspañol()){
            this.idiomaBox.getSelectionModel().selectFirst();
        }else {
            this.idiomaBox.getSelectionModel().selectLast();
        }


    }

    public void cambiarIdioma() throws IOException {

        if (this.idiomaBox.getValue() == null) {
            return;
        } else if (this.idiomaBox.getValue().equalsIgnoreCase("English") || this.idiomaBox.getValue().equalsIgnoreCase("Inglés")) {
            LenguageManager.getInstance().cargarIdioma("en", "US");
            this.creado.setEspañol(false);
        } else if (this.idiomaBox.getValue().equalsIgnoreCase("Spanish") || this.idiomaBox.getValue().equalsIgnoreCase("Español")) {
            LenguageManager.getInstance().cargarIdioma("es", "ES");
            this.creado.setEspañol(true);
        }

        // Recargar la vista actual con el nuevo idioma
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("configuracion.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();

        Configuracion controllerConfiguracion = fxmlLoader.getController();
        controllerConfiguracion.recibirData(this.creado);

        this.creado.getControllers().getControllerPagPrincipal().cambiarContenido(root);
        recargarVistaActual();

    }
    private void recargarVistaActual() throws IOException {
        // Cargar la vista de configuración con el nuevo idioma
        cargarVista("/org/example/bibliogest_javihevia/configuracion.fxml");

    }

    private void cargarVista(String rutaFXML) throws IOException {
        ResourceBundle bundle = LenguageManager.getInstance().getBundle();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML), bundle);
        Parent newRoot = fxmlLoader.load();

        if (rutaFXML.equals("/org/example/bibliogest_javihevia/configuracion.fxml")) {
            Configuracion controllerConfiguracion = fxmlLoader.getController();
            controllerConfiguracion.recibirData(this.creado);
            this.creado.getControllers().getControllerPagPrincipal().actualizarIdioma(bundle);
        }
    }


    public void cambiarColor() {

        PagPrincipal controllerPagPrincipal = this.creado.getControllers().getControllerPagPrincipal();

        if (this.colorBox.getValue().equalsIgnoreCase("Grey") || this.colorBox.getValue().equalsIgnoreCase("Gris")) {
            controllerPagPrincipal.meterEstilo("/styles/gris.css");
            this.creado.setOscuro(true);
        } else {
            controllerPagPrincipal.meterEstilo("/styles/claro/azul.css");
            this.creado.setOscuro(false);
        }

    }



}

