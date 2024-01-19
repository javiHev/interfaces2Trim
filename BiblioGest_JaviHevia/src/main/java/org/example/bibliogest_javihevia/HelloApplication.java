package org.example.bibliogest_javihevia;

import controller.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LenguageManager;
import model.LibrosCreados;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        LenguageManager.getInstance().cargarIdioma("es","Es");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        Login controllerLogin = fxmlLoader.getController();
        controllerLogin.establecerDatos(new LibrosCreados());
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}