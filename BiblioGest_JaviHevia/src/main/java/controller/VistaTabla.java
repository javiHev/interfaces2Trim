package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.LenguageManager;
import model.Libro;
import model.LibrosCreados;


import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class VistaTabla{
    @FXML
    private TableView<Libro> tablaLibros;
    @FXML
    private Button btnVer;
    @FXML
    private Button btnBorrar;
    @FXML
    private TextField buscador;
    @FXML
    private Button buscar;


    private PagPrincipal pagPrincipal;
    private LibrosCreados librosCreados;
    public void recibirData(LibrosCreados librosCreados){
        this.librosCreados = librosCreados;
    }

    public void initialize() {

        TableColumn<Libro, String> columnaISBN = new TableColumn<>("ISBN");
        columnaISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<Libro, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Libro, String> columnaAutor= new TableColumn<>("Autor");
        columnaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

        TableColumn<Libro, String> columnaYear = new TableColumn<>("Año");
        columnaYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        // Ajustar las columnas para que ocupen todo el espacio disponible
        columnaISBN.prefWidthProperty().bind(tablaLibros.widthProperty().multiply(0.25));
        columnaNombre.prefWidthProperty().bind(tablaLibros.widthProperty().multiply(0.25));
        columnaAutor.prefWidthProperty().bind(tablaLibros.widthProperty().multiply(0.25));
        columnaYear.prefWidthProperty().bind(tablaLibros.widthProperty().multiply(0.25));

        tablaLibros.getColumns().addAll(columnaISBN, columnaNombre, columnaAutor, columnaYear);
           tablaLibros.setItems(LibrosCreados.getLibros());


    }



    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    @FXML
    public void cambiarVista() {

        try {
            Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
            if(!(libroSeleccionado ==null)) {
                ResourceBundle bundle = LenguageManager.getInstance().getBundle();

                // Carga el archivo FXML y pasa el ResourceBundle
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliogest_javihevia/vistalibro.fxml"), bundle);
                Parent root = loader.load();
                VistaLibro vistaLibro = loader.getController();
                vistaLibro.establecerDatos(this.librosCreados);
                vistaLibro.setVistaOrigen("VistaTabla");
                vistaLibro.setTituloLibro(libroSeleccionado.getNombre());
                vistaLibro.setAutorLibro(libroSeleccionado.getAutor());
                vistaLibro.setPagPrincipalController(this.pagPrincipal);

                Stage stage = (Stage) tablaLibros.getScene().getWindow();
                stage.setTitle("BiblioGest");
                stage.setScene(new Scene(root));
            }else{
                mostrarAlertaError("Tienes que seleccionar algún libro");
            }
        } catch (IOException e) {
            mostrarAlertaError("Error al cargar la vista del libro");
            e.printStackTrace();
        }
    }
    @FXML
    public void eliminarLibroSeleccionado() {
        Libro libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (libroSeleccionado != null) {
            librosCreados.borrarLibro(libroSeleccionado);
            mostrarAlertaAceptar("Libro eliminado","Libro "+libroSeleccionado.getNombre()+" eliminado","Se ha eliminado el libro con exito");
        } else {
            mostrarAlertaError("No se ha seleccionado ningún libro");
        }
    }
    public void mostrarAlertaAceptar(String titulo,String head,String contenido){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setHeaderText(head);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    private ResourceBundle resourceBundle;



    public void setPagPrincipalController(PagPrincipal pagPrincipal) {
        this.pagPrincipal=pagPrincipal;
    }




}
