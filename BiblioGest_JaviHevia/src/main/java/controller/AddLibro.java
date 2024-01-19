package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import model.Libro;
import model.LibrosCreados;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddLibro  {
    @FXML
    private Label author_label;
    @FXML
    private Label isbn_label;
    @FXML
    private Label name_label;
    @FXML
    private Label year_label;

    @FXML
    private TextField autorText;

    @FXML
    private Button btnAñadir;

    @FXML
    private TextField isbnText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField yearText;

    private PagPrincipal pagPrincipal;
    private LibrosCreados librosCreados;






    @FXML
    void crearLibro(ActionEvent event) {
        String isbn=isbnText.getText();
        String name=nameText.getText();
        String year=yearText.getText();
        String autor=autorText.getText();

        if(!isbn.isEmpty()&&!name.isEmpty()&&!year.isEmpty()&&!autor.isEmpty()){
            Libro libro=new Libro(isbn,name,autor,year);
            this.librosCreados.getLibros().add(libro);
            Libros.addBookToList(libro);
            mostrarAlertaAceptar("Libro añadido","El libro ha sido añadido con exito");
            isbnText.clear();
            nameText.clear();
            yearText.clear();
            autorText.clear();

        }else{
            mostrarAlertaError("Tienes que reyenar todos los campos");
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


    public boolean validarContenido(String patron, String texto_buscar) {
        Pattern patronValidar = Pattern.compile(patron);
        Matcher matcher = patronValidar.matcher(texto_buscar);
        return matcher.matches();
    }
    private ResourceBundle resourceBundle;


    public void recibirData(LibrosCreados creados) {
        this.librosCreados=creados;
    }

    public void setPagPrincipalController(PagPrincipal pagPrincipal) {
        this.pagPrincipal=pagPrincipal;
    }
}
