package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.LenguageManager;
import model.Libro;
import model.LibrosCreados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Libros {

    @FXML
    private ScrollPane scrollLibros;
    @FXML
    private Label autorLibro;

    @FXML
    private Label tituloLibro;

    static List<Libro> listaLibros=new ArrayList<>();
    private LibrosCreados librosCreados;
    private PagPrincipal pagPrincipal;

    public static void addBookToList(Libro libro) {
        listaLibros.add(libro);
        System.out.println("Libro: " + libro.getNombre() + "\nAutor: " + libro.getAutor());
    }

    public void initialize() {
        cargarLibros();
    }

    private void cargarLibros() {
        VBox content = new VBox(10); // Espacio vertical entre elementos
        for (Libro libro : listaLibros) {
            content.getChildren().add(crearVistaLibro(libro));
        }
        scrollLibros.setContent(content);
    }

    private AnchorPane crearVistaLibro(Libro libro) {
        AnchorPane pane = new AnchorPane();
        Label titulo = new Label(libro.getNombre());
        Label autor = new Label(libro.getAutor());
        Button botonVer = new Button("VER");

        titulo.setStyle("-fx-font-size:16px;font-family: 'Roboto Mono';\n" +
                "    src: url('fonts/RobotoMono-Bold.ttf'); font-weight: bold;");
        autor.setStyle("-fx-font-size:14px;font-family: 'Roboto Mono';\\n\" +\n" +
                "                \"    src: url('fonts/RobotoMono-Bold.ttf'); font-weight: regular;");
        botonVer.setStyle(" -fx-background-color: #4E7AC7;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-border-color: #3D5A80;\n" +
                "    -fx-border-radius: 5;\n" +
                "    -fx-background-radius: 5;");


        titulo.setLayoutX(20);
        titulo.setLayoutY(10);
        autor.setLayoutX(20);
        autor.setLayoutY(40);
        botonVer.setLayoutX(20);
        botonVer.setLayoutY(70);


        botonVer.setOnAction(event -> cambiarVista(libro));

        pane.getChildren().addAll(titulo, autor, botonVer);
        return pane;
    }
    @FXML
    public void cambiarVista(Libro libroSeleccionado) {
        try {
            if (libroSeleccionado != null) {
                // Obtiene el ResourceBundle del LenguageManager
                ResourceBundle bundle = LenguageManager.getInstance().getBundle();

                // Carga el archivo FXML y pasa el ResourceBundle
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/bibliogest_javihevia/vistalibro.fxml"), bundle);
                Parent root = loader.load();

                VistaLibro vistaLibro = loader.getController();
                vistaLibro.establecerDatos(this.librosCreados);
                vistaLibro.setVistaOrigen("Libros");
                vistaLibro.setTituloLibro(libroSeleccionado.getNombre());
                vistaLibro.setAutorLibro(libroSeleccionado.getAutor());
                vistaLibro.setPagPrincipalController(this.pagPrincipal);

                Stage stage = (Stage) scrollLibros.getScene().getWindow();
                stage.setTitle("BiblioGest");
                stage.setScene(new Scene(root));
                stage.show();
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




    public void setPagPrincipalController(PagPrincipal pagPrincipal) {
        this.pagPrincipal=pagPrincipal;
    }

    public void recibirData(LibrosCreados creados) {
        this.librosCreados=creados;
    }
}
