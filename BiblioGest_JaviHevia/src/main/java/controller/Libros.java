package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.LenguageManager;
import model.Libro;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Libros {

    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Label pagina;

    @FXML
    private GridPane rellenar;
    private int numeroPagina;
    private int paginasTotales;
    private int librosXpagina = 6;

    static List<Libro> listaLibros=new ArrayList<>();
    private LibrosCreados librosCreados=PagPrincipal.getCreados();


    public static void addBookToList(Libro libro) {
        listaLibros.add(libro);
        System.out.println("Libro: " + libro.getNombre() + "\nAutor: " + libro.getAutor());
    }

    public void crearLibros() throws IOException {
        int y = 0;
        this.rellenar.setHgap(10);
        this.rellenar.setVgap(10);

        for(int i = librosXpagina * numeroPagina; i < librosXpagina * (numeroPagina + 1) && i < this.librosCreados.getLibros().size(); i++){
            System.out.println(i);
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId(this.librosCreados.getLibros().get(i).getNombre());


            anchorPane.getStyleClass().add("cadaAnchor");

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cadalibro.fxml"), LenguageManager.getInstance().getBundle());
            Parent root = fxmlLoader.load();

            SoloUnLibro controllerCadaLibro = fxmlLoader.getController();
            controllerCadaLibro.recibirData(this.librosCreados,this.librosCreados.getLibros().get(i));
            anchorPane.getChildren().setAll(root);


            int fila = y / 4;
            int columna = y % 4;
            Insets margin = new Insets(10, 10, 10, 10);
            GridPane.setMargin(anchorPane, margin);
            GridPane.setFillHeight(anchorPane,false);
            GridPane.setFillWidth(anchorPane,false);
            this.rellenar.setPrefSize(800,600);

            GridPane.setConstraints(anchorPane,columna,fila);
            this.rellenar.getChildren().add(anchorPane);
            y++;
        }
        this.rellenar.setLayoutX(70);
        this.rellenar.setLayoutY(70);

    }



    @FXML
    public void cambiarVista(Libro libroSeleccionado) {
        try {
            if (libroSeleccionado != null) {
                // Obtiene el ResourceBundle del LenguageManager
                ResourceBundle bundle = LenguageManager.getInstance().getBundle();

                // Carga el archivo FXML y pasa el ResourceBundle
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("vistalibro.fxml"), LenguageManager.getInstance().getBundle());
                Parent root = fxmlLoader.load();
                VistaLibro vistaLibro = fxmlLoader.getController();
                vistaLibro.setVistaOrigen("libros.fxml");
                vistaLibro.recibirData(this.librosCreados);
                this.librosCreados.getControllers().getControllerPagPrincipal().cambiarStage(root);
            } else {
                mostrarAlertaError("Tienes que seleccionar algún libro");
            }
        } catch (IOException e) {
            mostrarAlertaError("Error al cargar la vista del libro: " + e.getMessage());
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
    public void mostrarAlertaAceptar(String titulo,String head,String contenido){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setHeaderText(head);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    @FXML
    void anterior(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("libros.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        Libros controllerLibros = fxmlLoader.getController();
        controllerLibros.establecerDatos(this.librosCreados,this.numeroPagina - 1);
        this.librosCreados.getControllers().getControllerPagPrincipal().cambiarContenido(root);
    }

    @FXML
    void siguiente(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("libros.fxml"), LenguageManager.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        Libros controllerLibros = fxmlLoader.getController();
        controllerLibros.establecerDatos(this.librosCreados,this.numeroPagina + 1);
        this.librosCreados.getControllers().getControllerPagPrincipal().cambiarContenido(root);
    }
    public void establecerDatos(LibrosCreados creados, int pagina) throws IOException {
        this.librosCreados = creados;
        this.numeroPagina = pagina;
        this.paginasTotales = (int) (double) (this.librosCreados.getLibros().size() / this.librosXpagina);
        if((double) this.numeroPagina <= 0){
            this.btnAnterior.setDisable(true);
        }
        if((double) this.numeroPagina>= this.paginasTotales){
            this.btnSiguiente.setDisable(true);
        }
        System.out.println(this.numeroPagina);
        System.out.println(this.paginasTotales);
        this.crearLibros();

    }

    public void recibirData(LibrosCreados creados) {
        this.librosCreados=creados;
    }
}
