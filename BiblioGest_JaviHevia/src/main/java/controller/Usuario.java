package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LenguageManager;
import model.LibrosCreados;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {

    @FXML
    private TextField apellidoText;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField edadText;

    @FXML
    private TextField emailText;

    @FXML
    private Button logOut;

    @FXML
    private TextField nombreText;

   private PagPrincipal pagPrincipalController;
    private LibrosCreados librosCreados;


    public void setPagPrincipalController(PagPrincipal pagPrincipalController) {
        this.pagPrincipalController = pagPrincipalController;
    }

    @FXML
    void guardarUsuario(ActionEvent event) throws IOException {
        String newName=nombreText.getText();
        String newApellido=apellidoText.getText();
        String newEdad=edadText.getText();
        String newEmail=emailText.getText();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@(gmail|yahoo|hotmail|outlook)\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(newEmail);

        // Comprueba si el email coincide con la expresión regular
        if (matcher.matches()) {
            System.out.println(newName);
            if(!newName.isEmpty()&&!newApellido.isEmpty()&&!newEdad.isEmpty()) {
                if(!(pagPrincipalController ==null)) {
                    System.out.println("Pagina principal: "+pagPrincipalController);
                    pagPrincipalController.setUserName("@" + newName);
                    System.out.println("Nombre cambiado");
                    nombreText.clear();
                    apellidoText.clear();
                    edadText.clear();
                    emailText.clear();
                }else {
                    System.out.println("Error");
                }
            }else{
                mostrarAlertaError("Debes rellenar todos los campos");
            }
        } else {
           mostrarAlertaError("El email no es valido, debe contener @gmail.com,@hotmail.com...");
        }



    }

    @FXML
    void volverLogin(ActionEvent event) {
        try {
            Button boton=(Button) event.getSource();
            ResourceBundle bundle = LenguageManager.getInstance().getBundle();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliogest_javihevia/login-view.fxml"), bundle);
            Parent root = loader.load();

            // Obtener el Stage actual desde un control (por ejemplo, un botón)
            Stage stageActual = (Stage) boton.getScene().getWindow();

            // Establecer la nueva escena en el Stage actual
            stageActual.setScene(new Scene(root));

            // Opcional: Actualizar el título del Stage
            stageActual.setTitle("Login");

            // Mostrar el Stage actual con la nueva escena
            stageActual.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void mostrarAlertaAceptar(String titulo,String mensaje){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private ResourceBundle resourceBundle;


    public void recibirData(LibrosCreados creados) {
        this.librosCreados=creados;
    }
}
