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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


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
    private Label bpmDia;

    @FXML
    private Label kcalDiarias;


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
    public static DatosNotas getNotasCreadas(){
        return  datosNotas;
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
        controllerProyectos.recibirData(datosProyectos);

        cambiarStage(root);

    }

    @FXML
    void handlerCalendario() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/calendario.fxml"));
        Parent root = fxmlLoader.load();
        ControllerCalendario controllerCalendario = fxmlLoader.getController();
        controllerCalendario.recibirData(datosProyectos);
        cambiarStage(root);
    }

    @FXML
    void handlerNotas() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/notas.fxml"));
        Parent root = fxmlLoader.load();
        ControllerNotas controllerNotas = fxmlLoader.getController();
        controllerNotas.recibirData(datosNotas);
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
        String originalBMP=bpmDia.getText();
        String originalKcal=kcalDiarias.getText();
        controllerPagPrincipal.setUserName(cleanedText);
        controllerPagPrincipal.setKcalDiarias(originalKcal);
        controllerPagPrincipal.setBpmDia(originalBMP);
        cambiarStage(root);
    }

    //Preguntar chat
    @FXML
    void openChat(MouseEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/chat.fxml"));
        try {
            // Cargar el root desde el FXML
            Parent root = fxmlLoader.load();

            // Crear una nueva escena con el root
            Scene scene = new Scene(root);
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene); // Establecer la escena en el stage
            nuevoStage.setTitle("Chat"); // Opcional: establecer un título para el stage
            nuevoStage.initStyle(StageStyle.UNDECORATED);

            // Mostrar el nuevo stage
            nuevoStage.show();


        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public void setUserName(String s) {
        this.userName.setText("Bienvenido "+s+"!!");
    }

    public void establecerDatos(DatosProyectos datosProyectos,DatosNotas datosNotas,DatosContactos datosContactos) {
        PagPrincipal.datosProyectos = datosProyectos;
        PagPrincipal.datosNotas=datosNotas;
        PagPrincipal.datosContactos=datosContactos;
        DatosProyectos.getControllers().setControllerPanelPrincipal(this);
    }

    public static DatosProyectos getDatosProyectos(){
        return datosProyectos;
    }

    public void setBpmDia(String bpmDia) {

        this.bpmDia.setText(bpmDia);
    }

    public void setKcalDiarias(String  kcalDiarias) {
        this.kcalDiarias.setText(kcalDiarias);
    }
}
