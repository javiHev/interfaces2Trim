package com.example.fitjourney;
import javafx.fxml.FXMLLoader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private int puerto = 4321;

    public void iniciarServidor() throws IOException {
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor iniciado, esperando clientes...");


            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado");

            try (DataInputStream input = new DataInputStream(socket.getInputStream());
                 DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

                    String pregunta = input.readUTF();



                    System.out.println("Pregunta recibida: " + pregunta);
                    String respuesta = procesarPregunta(pregunta);
                    //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/fitjourney/img/fxml/chat.fxml"));
                    //Chat controllerChat = fxmlLoader.getController();
                    output.writeUTF(respuesta);
                    //controllerChat.setMensajeBot("Julia: "+respuesta);
                    serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }


    }

    private String procesarPregunta(String pregunta) {
        switch (pregunta.trim().toLowerCase()) {
            case "hola":
            case "hola, ¿cómo estás?":
                return "¡Hola! Estoy bien, gracias. ¿En qué puedo ayudarte hoy?";
            case "¿cómo te llamas?":
                return "Me llamo FitBot, tu asistente de fitness.";
            case "¿en qué me puedes ayudar?":
                return "Puedo ofrecerte consejos sobre ejercicios, nutrición, y cómo mantenerte saludable. ¿Tienes alguna pregunta específica?";
            // Preguntas sobre fitness
            case "¿cómo me mantengo en forma?":
                return "Mantenerse en forma requiere una combinación de ejercicio regular y una dieta balanceada.";
            case "¿qué ejercicios puedo hacer en casa?":
                return "Puedes hacer ejercicios de peso corporal como sentadillas, flexiones y abdominales.";
            case "¿cómo puedo perder peso?":
                return "Perder peso implica quemar más calorías de las que consumes. Considera aumentar tu actividad física y comer alimentos menos calóricos.";
            case "¿qué debo comer para ganar músculo?":
                return "Para ganar músculo, enfócate en alimentos ricos en proteínas como carnes magras, pescado, huevos, y legumbres, y asegúrate de combinarlo con entrenamiento de fuerza.";
            case "¿es importante el descanso en el fitness?":
                return "Sí, el descanso es crucial. Permite que tus músculos se recuperen y crezcan después del ejercicio.";
            case "¿cómo puedo mejorar mi resistencia?":
                return "Mejora tu resistencia con actividades cardiovasculares regulares como correr, nadar o andar en bicicleta, e intenta incrementar gradualmente la duración e intensidad.";
            // Agrega más casos según necesites
            default:
                return "Lo siento, no estoy seguro de cómo responder a eso. ¿Puedes hacer otra pregunta?";
        }
    }


}