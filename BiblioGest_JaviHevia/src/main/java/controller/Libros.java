package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.LenguageManager;
import model.Libro;
import model.LibrosCreados;
import org.example.bibliogest_javihevia.HelloApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Libros {

    @FXML private Button btnAnterior;
    @FXML private Button btnSiguiente;
    @FXML private GridPane rellenar;

    private int numeroPagina = 0; // Inicializa la página inicial
    private int paginasTotales;
    private int librosXpagina = 6;
    private static List<Libro> listaLibros=new ArrayList<>();

    private LibrosCreados librosCreados = PagPrincipal.getCreados(); // Asegúrate de que esto se inicialice correctamente

    @FXML
    public void initialize() {
        inicializarVista();
        try {
            establecerDatos(librosCreados, numeroPagina); // Asume que librosCreados ya está inicializado
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlertaError("Error al inicializar la vista de libros.");
        }
    }

    private void inicializarVista() {
        rellenar.setHgap(10);
        rellenar.setVgap(10);
        rellenar.setLayoutX(70);
        rellenar.setLayoutY(70);
    }

    public void establecerDatos(LibrosCreados creados, int pagina) throws IOException {
        this.librosCreados = creados;
        this.numeroPagina = pagina;
        this.paginasTotales = (int) Math.ceil((double) creados.getLibros().size() / librosXpagina);
        btnAnterior.setDisable(numeroPagina <= 0);
        btnSiguiente.setDisable(numeroPagina >= paginasTotales - 1);
        crearLibros();
    }

    public void crearLibros() throws IOException {
        rellenar.getChildren().clear();
        for (int i = librosXpagina * numeroPagina, y = 0; i < librosXpagina * (numeroPagina + 1) && i < librosCreados.getLibros().size(); i++, y++) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cadalibro.fxml"), LenguageManager.getInstance().getBundle());
            Parent root = fxmlLoader.load();
            SoloUnLibro controllerCadaLibro = fxmlLoader.getController();
            controllerCadaLibro.recibirData(librosCreados, librosCreados.getLibros().get(i));

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().setAll(root);
            anchorPane.getStyleClass().add("cadaAnchor");
            GridPane.setMargin(anchorPane, new Insets(10));
            GridPane.setConstraints(anchorPane, y % 4, y / 4);
            rellenar.getChildren().add(anchorPane);
        }
    }

    @FXML
    void anterior() throws IOException {
        if (numeroPagina > 0) {
            establecerDatos(librosCreados, numeroPagina - 1);
        }
    }

    @FXML
    void siguiente() throws IOException {
        if (numeroPagina < paginasTotales - 1) {
            establecerDatos(librosCreados, numeroPagina + 1);
        }
    }

    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void recibirData(LibrosCreados creados) {
        this.librosCreados=creados;
    }
    public static void addBookToList(Libro libro) {
        listaLibros.add(libro);
        System.out.println("Libro: " + libro.getNombre() + "\nAutor: " + libro.getAutor());
    }
}
