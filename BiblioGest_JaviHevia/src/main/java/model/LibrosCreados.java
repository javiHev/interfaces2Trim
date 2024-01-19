package model;

import controller.Libros;
import controller.PagPrincipal;
import controller.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.Locale;
import java.util.ResourceBundle;

public class LibrosCreados {





        public LibrosCreados() {
            this.libros.add(new Libro("9780307474728", "Cien Años de Soledad", "Gabriel García Márquez", "1967"));
            this.libros.add(new Libro("9780451524935", "1984", "George Orwell", "1949"));
            this.libros.add(new Libro("9780156012195", "El Principito", "Antoine de Saint-Exupéry", "1943"));
            this.libros.add(new Libro("9788423353385", "Don Quijote de la Mancha", "Miguel de Cervantes", "1605"));
            this.libros.add(new Libro("9780061120084", "To Kill a Mockingbird", "Harper Lee", "1960"));
            this.libros.add(new Libro("9780345339683", "El Hobbit", "J.R.R. Tolkien", "1937"));
            this.libros.add(new Libro("9780679783268", "Orgullo y Prejuicio", "Jane Austen", "1813"));
            this.libros.add(new Libro("9788491392255", "Matar a un Ruiseñor", "Harper Lee", "1960"));
            this.libros.add(new Libro("9780743273565", "El Gran Gatsby", "F. Scott Fitzgerald", "1925"));
            this.libros.add(new Libro("9780747532743", "Harry Potter y la Piedra Filosofal", "J.K. Rowling", "1997"));
            this.libros.add(new Libro("9780307474278", "El Código Da Vinci", "Dan Brown", "2003"));

            Libros.addBookToList(new Libro("9780307474728", "Cien Años de Soledad", "Gabriel García Márquez", "1967"));
            Libros.addBookToList(new Libro("9780451524935", "1984", "George Orwell", "1949"));
            Libros.addBookToList(new Libro("9780156012195", "El Principito", "Antoine de Saint-Exupéry", "1943"));
            Libros.addBookToList(new Libro("9788423353385", "Don Quijote de la Mancha", "Miguel de Cervantes", "1605"));
            Libros.addBookToList(new Libro("9780061120084", "To Kill a Mockingbird", "Harper Lee", "1960"));
            Libros.addBookToList(new Libro("9780345339683", "El Hobbit", "J.R.R. Tolkien", "1937"));
            Libros.addBookToList(new Libro("9780679783268", "Orgullo y Prejuicio", "Jane Austen", "1813"));
            Libros.addBookToList(new Libro("9788491392255", "Matar a un Ruiseñor", "Harper Lee", "1960"));
            Libros.addBookToList(new Libro("9780743273565", "El Gran Gatsby", "F. Scott Fitzgerald", "1925"));
            Libros.addBookToList(new Libro("9780747532743", "Harry Potter y la Piedra Filosofal", "J.K. Rowling", "1997"));
            Libros.addBookToList(new Libro("9780307474278", "El Código Da Vinci", "Dan Brown", "2003"));

        }


        private Usuario currentUser;
        private static ObservableList<Libro> libros = FXCollections.observableArrayList();
        private ObservableList<Libro> librosFiltrados;
        private Boolean vistaAnteriorTabla;
        private boolean filtrar = false;
        private AnchorPane main;
        private Controller controllers = new Controller();
        private Libro libroSeleccionado;
        private boolean oscuro = false;
        private boolean español = true;

        public void setCurrentUser(Usuario currentUser) {
            this.currentUser = currentUser;
        }

        public Usuario getCurrentUser() {
            return currentUser;
        }

        public static ObservableList<Libro> getLibros() {
            return libros;
        }

        public Boolean getVistaAnterior() {
            return vistaAnteriorTabla;
        }


        public boolean isOscuro() {
            return oscuro;
        }

        public void setOscuro(boolean oscuro) {
            this.oscuro = oscuro;
        }

        public void setLibros(ObservableList<Libro> libros) {
            this.libros = libros;
        }

        public void setVistaAnterior(Boolean vistaAnterior) {
            this.vistaAnteriorTabla = vistaAnterior;
        }


        public boolean isFiltrar() {
            return filtrar;
        }

        public void setFiltrar(boolean filtrar) {
            this.filtrar = filtrar;
        }

        public Libro getLibroSeleccionado() {
            return libroSeleccionado;
        }

        public void setLibroSeleccionado(Libro libroSeleccionado) {
            this.libroSeleccionado = libroSeleccionado;
        }

        public ObservableList<Libro> getLibrosFiltrados() {
            return librosFiltrados;
        }

        public void setLibrosFiltrados(ObservableList<Libro> librosFiltrados) {
            this.librosFiltrados = librosFiltrados;
        }

        public Boolean getVistaAnteriorTabla() {
            return vistaAnteriorTabla;
        }
        public void setMain(AnchorPane anchorPane){
            main = anchorPane;
        }

        public AnchorPane getMain() {
            return main;
        }

        public Controller getControllers() {
            return controllers;
        }

        public void setVistaAnteriorTabla(Boolean vistaAnteriorTabla) {
            this.vistaAnteriorTabla = vistaAnteriorTabla;
        }

        public void setControllers(Controller controllers) {
            this.controllers = controllers;
        }

        public boolean isEspañol() {
            return español;
        }

        public void setEspañol(boolean español) {
            this.español = español;
        }

        public void borrarLibro(Libro libro){
            libros.remove(libro);
        }
    private String ultimaVistaFXML;
    public void setUltimaVistaFXML(String ultimaVistaFXML) {
        this.ultimaVistaFXML = ultimaVistaFXML;
    }
    private static String color_selected = "Gris Claro";
    public static String getColor(){
        String colorHex ="";
        switch (color_selected) {
            case "Amarillo":
                colorHex = "FFFF00"; // Amarillo
                break;
            case "Azul":
                colorHex = "0000FF"; // Azul
                break;
            case "Rojo":
                colorHex = "FF0000"; // Rojo
                break;
            case "Verde":
                colorHex = "008000"; // Verde
                break;
            case "Naranja":
                colorHex = "FFA500"; // Naranja
                break;
            case "Morado":
                colorHex = "800080"; // Morado
                break;
            case "Cyan":
                colorHex = "00FFFF"; // Cyan
                break;
            case "Rosa":
                colorHex = "FFC0CB"; // Rosa
                break;
            case "Marrón":
                colorHex = "8B4513"; // Marrón
                break;
            case "Gris":
                colorHex = "808080"; // Gris
                break;
            case "Gris Claro":
                colorHex = "D4D4D4";
        }
        return colorHex;
    }

    public String getUltimaVistaFXML() {
        return ultimaVistaFXML;
    }
}


