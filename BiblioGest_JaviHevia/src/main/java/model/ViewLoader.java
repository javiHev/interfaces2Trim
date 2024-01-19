package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ViewLoader {
    public static Parent loadFXML(String fxmlFile) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Messages", Locale.getDefault());
            FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(fxmlFile), bundle);
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo FXML: " + fxmlFile, e);
        }
    }
}
