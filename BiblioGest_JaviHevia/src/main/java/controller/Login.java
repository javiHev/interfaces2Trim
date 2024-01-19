package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.LenguageManager;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Login  {

    @FXML
    private Button btnInicio;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;
    @FXML
    private Label password_label;
    @FXML
    private Label user_label;
    private LibrosCreados creados=new LibrosCreados();


    @FXML
    void validarAcceso(ActionEvent event) throws IOException {
        //Variables para cambiar Stage
        Button button = (Button) event.getSource(); // Obtiene el botón que desencadenó el evento
        String fxmlResource = null; // Ruta al archivo FXML que se cargará
        String stageTitle = null;  // Título de la nueva ventana
        //____________________________________
        String usuario = userName.getText();
        String password = userPassword.getText();
        if (!usuario.isEmpty() && !password.isEmpty()) {
            ResourceBundle bundle = LenguageManager.getInstance().getBundle();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliogest_javihevia/principal.fxml"), bundle);
            Parent root = loader.load();
            PagPrincipal pagPrincipalController = loader.getController();
            pagPrincipalController.setUserName("@" + usuario);
            pagPrincipalController.establecerDatos(this.creados);

            Stage stage = (Stage) btnInicio.getScene().getWindow();
            stage.setTitle("BiblioGest");
            stage.setScene(new Scene(root));
        } else {
            String respuesta = (usuario.isEmpty() && password.isEmpty()) ? "Rellena los campos: usuario y contraseña" : (usuario.isEmpty()) ? "Rellena el campo: usuario" : "Rellena el campo: contraseña";
            mostrarAlertaError(respuesta);
        }

    }
    public String getNombreUsuario(String userName){
        return userName;
    }

    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void establecerDatos(LibrosCreados creados){
        this.creados = creados;
    }


}


