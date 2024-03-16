package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerCadaContacto {
    private DatosContactos datosContactos;

    @FXML
    private ImageView imgPerfil;
    private Contactos contacto;
    @FXML
    private Button contactoBtn;

    @FXML
    void edit(ActionEvent event) {
        Node source = (Node) event.getSource();
        String idBtn = source.getId();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/add-contactos-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            AddContactos controllerAddContactos = fxmlLoader.getController();

            // Aquí es donde pasarías los datos del proyecto a modificar a la vista AddNotas
            // Necesitas obtener los datos del proyecto actual y pasárselos
            String nombre = this.contacto.getNombre(); // Si estos son Labels, obtendrás el texto directamente
            String apellido = this.contacto.getApellido();
            String email = this.contacto.getEmail();
            String telefono = this.contacto.getTelefono();
            String urlImagen = this.contacto.getUrlImagen();
            controllerAddContactos.setNombreContacto(nombre);
            controllerAddContactos.setApellidoContacto(apellido);
            controllerAddContactos.iniciar(idBtn);
            controllerAddContactos.setIdBoton(idBtn);
            controllerAddContactos.setEmailContacto(email);
            controllerAddContactos.setTelefonoContacto(telefono);
            controllerAddContactos.setContactoSeleccionado(contacto);

            Scene scene = new Scene(root);
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene);
            nuevoStage.setTitle("Modificar Nota");
            nuevoStage.initStyle(StageStyle.UNDECORATED);
            nuevoStage.show();

        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public void recibirData(DatosContactos datosContactos, Contactos contactos, String url) {
        System.out.println("URL:" + url);
        Image image;
        if (url == null) {
            image = new Image(getClass().getClassLoader().getResourceAsStream("/com/example/fitjourney/img/logo_redondo.png"));
        } else {
            image = new Image("file:///"+url);
        }
        this.datosContactos = datosContactos;
        this.contacto = contactos;
        this.imgPerfil.setImage(image);
        this.contactoBtn.setText(contactos.getNombre());
    }
}
