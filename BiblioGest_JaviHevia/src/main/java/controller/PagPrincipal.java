package controller;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.LenguageManager;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Locale;

public class PagPrincipal {
    @FXML
    private AnchorPane pagPrincipal;
    @FXML
    private VBox main;
    @FXML
    private Label userName;

    private AddLibro addLibro = new AddLibro();

    private LibrosCreados creados;
    @FXML
    private Button addBook;

    @FXML
    private Button books;

    @FXML
    private Button detail_view;
    @FXML
    private Button user;

    @FXML
    private Button configuration;
    @FXML
    private Label welcome_message;
    @FXML
    private Label saludo;
    @FXML
    private VBox vboxPrincipal;

    public void cambiarStage(Object root) throws IOException {
        pagPrincipal.getChildren().setAll((Node) root);
    }

    public void cambiarContenido(Parent nuevaVista) {
        pagPrincipal.getChildren().setAll(nuevaVista); // Reemplaza el contenido actual con la nueva vista
    }


    @FXML
    void addLibro() throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addlibro.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        AddLibro controllerAddLibros = fxmlLoader.getController();
        controllerAddLibros.recibirData(this.creados);
        cambiarStage(root);

    }

    @FXML
    void vistaDetallada() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistatabla.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        VistaTabla controllerVitaTabla = fxmlLoader.getController();
        controllerVitaTabla.recibirData(this.creados);
        cambiarStage(root);
    }

    @FXML
    void usuario() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("usuario.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        Usuario controllerUsuarios = fxmlLoader.getController();
        controllerUsuarios.setPagPrincipalController(this);
        controllerUsuarios.recibirData(this.creados);
        cambiarStage(root);
    }

    @FXML
    void vistaLibros() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("libros.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        Libros controllerLibros = fxmlLoader.getController();
        controllerLibros.recibirData(this.creados);
        cambiarStage(root);
    }

    @FXML
    void configuracion() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("configuracion.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        Configuracion controllerConfiguracion = fxmlLoader.getController();
        controllerConfiguracion.recibirData(this.creados);
        cambiarStage(root);
    }

    public void setUserName(String s) {
        this.userName.setText(s);
    }

    /*public void eliminarEstilos(){
        this.main.getStylesheets().clear();
    }
    public void meterEstilo(String ruta){
        this.main.getStylesheets().add(getClass().getResource(ruta).toExternalForm());
    }*/
    public void establecerDatos(LibrosCreados creados) throws IOException {
        this.creados = creados;
        this.creados.getControllers().setControllerPanelPrincipal(this);

    }


    public void actualizarIdioma(ResourceBundle bundle) {
        // Actualizar los textos en esta instancia si no te deslogueas
        addBook.setText(bundle.getString("principal.add_book"));
        books.setText(bundle.getString("principal.books"));
        detail_view.setText(bundle.getString("principal.detailed_view"));
        user.setText(bundle.getString("principal.user"));
        configuration.setText(bundle.getString("principal.settings"));
        saludo.setText(bundle.getString("principal.welcome_user_message"));
        welcome_message.setText(bundle.getString("principal.welcome_message"));
    }

    public void meterEstilo(String s) {
        this.pagPrincipal.getStylesheets().add(getClass().getResource(s).toExternalForm());
    }
}

