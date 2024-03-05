package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PagPrincipal  {

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

    public static DatosProyectos getCreados() {
        return datosProyectos;
    }
    /*###############################################################################*/
   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnProyectos.setSelected(false); // Asegúrate de que el ToggleButton no esté seleccionado

    }*/

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

 /*   @FXML
    void handlerCalendario() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calendario.fxml"));
        Parent root = fxmlLoader.load();
        ControllerCalendario controllerCalendario = fxmlLoader.getController();
        controllerVitaTabla.recibirData(this.creados);
        cambiarStage(root);
    }*/

    @FXML
    void handlerNotas() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/notas.fxml"));
        Parent root = fxmlLoader.load();
        ControllerNotas controllerNotas = fxmlLoader.getController();
        controllerNotas.recibirData(this.datosNotas);
        cambiarStage(root);
    }

    @FXML
    void handlerContactos() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/contactos.fxml"));
        Parent root = fxmlLoader.load();
        ControllerContactos controllerContactos = fxmlLoader.getController();
        controllerContactos.recibirData(this.datosContactos);
        cambiarStage(root);
    }

    @FXML
    void handlerLogout() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
    }
    @FXML
    void handlerPrincipal() throws IOException {
        System.out.println("Carga");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/pagPrincipal.fxml"));
        Parent root = fxmlLoader.load();
        PagPrincipal controllerPagPrincipal = fxmlLoader.getController();
        String originalText = userName.getText(); // "Bienvenido userName!!"
// Elimina las partes no deseadas de la cadena
        String cleanedText = originalText.replace("Bienvenido ", "").replace("!!", "").trim();
        controllerPagPrincipal.setUserName(cleanedText);
        cambiarStage(root);
    }

    public void setUserName(String s) {
        this.userName.setText("Bienvenido "+s+"!!");
    }

    public void establecerDatos(DatosProyectos datosProyectos) {
        PagPrincipal.datosProyectos = datosProyectos;
        DatosProyectos.getControllers().setControllerPanelPrincipal(this);
    }


}
