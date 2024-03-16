package com.example.fitjourney;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerContactos {
    public ImageView addContacto;
    private DatosContactos datosContactos;

    public void recibirData(DatosContactos datosContactos) {
        this.datosContactos = datosContactos;
    }

    @FXML
    private ScrollPane scroll1, scroll2, scroll3;
    private VBox contenedorContactos1 = new VBox();
    private VBox contenedorContactos2 = new VBox();
    private VBox contenedorContactos3 = new VBox();

    @FXML
    public void initialize() {
        inicializarVista();
        try {
            crearContactos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicializarVista() {
        scroll1.setContent(contenedorContactos1);
        scroll2.setContent(contenedorContactos2);
        scroll3.setContent(contenedorContactos3);


        scroll1.setPannable(true);
        scroll2.setPannable(true);
        scroll3.setPannable(true);

        scroll1.setFitToWidth(true);
        scroll2.setFitToWidth(true);
        scroll3.setFitToWidth(true);
    }

    public void crearContactos() throws IOException {

        for (Contactos contacto : datosContactos.getListaContactos()) {
            System.out.println("Contacto Proyect: "+contacto.getProyecto());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/cada-contacto.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            ControllerCadaContacto controllerCadaContacto = fxmlLoader.getController();
            controllerCadaContacto.recibirData(datosContactos, contacto,contacto.getUrlImagen());

            anchorPane.getStyleClass().add("cadaAnchor");

            switch (contacto.getProyecto()) {
                case "proyecto1":
                    contenedorContactos1.getChildren().add(anchorPane);
                    break;
                case "proyecto2":
                    contenedorContactos2.getChildren().add(anchorPane);
                    break;
                case "proyecto3":
                    contenedorContactos3.getChildren().add(anchorPane);
                    break;
                default:
                    System.out.println("Ese proyecto no existe");
                    break;
            }
        }
    }

    @FXML
    void addContacto(MouseEvent event) {
        Node source = (Node) event.getSource();
        String idBtn = source.getId();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/add-contactos-view.fxml"));
        try {
            // Cargar el root desde el FXML
            Parent root = fxmlLoader.load();

            // Crear una nueva escena con el root
            Scene scene = new Scene(root);
            AddContactos controllerAddContactos = fxmlLoader.getController();
            controllerAddContactos.setIdBoton(idBtn);
            controllerAddContactos.iniciar(idBtn);
            // Crear un nuevo stage (ventana) para la nueva escena
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene); // Establecer la escena en el stage
            nuevoStage.setTitle("Añadir Contacto");
            nuevoStage.initStyle(StageStyle.UNDECORATED);

            // Mostrar el nuevo stage
            nuevoStage.show();


        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
