package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagPrincipal {

    private AddContactos addContactos = new AddContactos();
    private AddProyecto addProyectos=new AddProyecto();
    private static DatosContactos datosContactos;
    private static DatosProyectos datosProyectos;
    private static DatosNotas datosNotas;


    /*OBJETOS DENTRO DEL FXML*/
    /*###############################################################################*/
    @FXML
    private AnchorPane pagPrincipal;

    @FXML
    private Label userName;

    @FXML
    private Button btnCalendario;

    @FXML
    private ImageView btnChat;

    @FXML
    private Button btnContactos;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnNotas;

    @FXML
    private Button btnProyectos;

    @FXML
    private ImageView home; //Imagen principal =home
    /*###############################################################################*/

    public void cambiarStage(Object root) throws IOException {
        pagPrincipal.getChildren().setAll((Node) root);
    }

    public void cambiarContenido(Parent nuevaVista) {
        pagPrincipal.getChildren().setAll(nuevaVista); // Reemplaza el contenido actual con la nueva vista
    }


    @FXML
    void handlerProyectos() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/proyectos.fxml"));
        Parent root = fxmlLoader.load();
        ControllerProyectos controllerProyectos = fxmlLoader.getController();
        controllerProyectos.recibirData(this.datosProyectos);
        cambiarStage(root);

    }

    @FXML
    void handlerCalendario() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calendario.fxml"));
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
        URL url = getClass().getResource(s);
        if (url != null) {
            this.vboxPrincipal.getStylesheets().clear();
            this.vboxPrincipal.getStylesheets().add(url.toExternalForm());
        } else {
            System.out.println("No se encontró el archivo CSS: " + s);
        }
    }
    public static LibrosCreados getCreados(){
        return creados;
    }

    public VBox getVboxPrincipal() {
        return this.vboxPrincipal;
    }
}
