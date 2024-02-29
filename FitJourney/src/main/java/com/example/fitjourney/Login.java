package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class Login {

    @FXML
    private Button btnInicio;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;



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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/principal.fxml"));
            Parent root = loader.load();
            PagPrincipal pagPrincipalController = loader.getController();
            pagPrincipalController.setUserName("@" + usuario);
//            pagPrincipalController.establecerDatos(this.creados);

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
    //public void establecerDatos(LibrosCreados creados){
    //    this.creados = creados;
   // }

}
