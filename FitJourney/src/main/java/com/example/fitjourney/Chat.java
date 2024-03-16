package com.example.fitjourney;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Chat {
    @FXML
    private TextArea messageArea;

    @FXML
    private TextField inputBox;

    private String host = "localhost";
    // puerto del server
    private int puerto = 4321;

    @FXML
    public void iniciarChat() throws IOException {
        Chat cliente = new Chat();
        cliente.iniciarCliente();
    }
    public void iniciarCliente() throws IOException {

        try (Socket socket = new Socket(host, puerto);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {

            System.out.println("Conectado al servidor. \nEscribe tu pregunta:");

                if(inputBox!=null) {
                    String pregunta = inputBox.getText();

                    // Enviar pregunta al servidor
                    output.writeUTF(pregunta);

                    // Recibir respuesta del servidor
                    String respuesta = input.readUTF();
                    messageArea.setText(respuesta);

                }else {
                    inputBox.clear();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void returnToController(ActionEvent event) throws IOException {

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/pagPrincipal.fxml"));
        Parent root = loader.load();
        DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);

    }

    public void setMensajeBot(String mensaje){
        this.messageArea.setText("hola");
    }
}
