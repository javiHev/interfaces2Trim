package model;

import java.util.Objects;

public class Libro {
    private String isbn;
    private String nombre;
    private String autor;
    private String imagen;
    private String year;

    public Libro(String isbn, String nombre, String autor, String year, String imagen) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.year = year;
        this.imagen=imagen;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Libro libro = (Libro) obj;
        return isbn.equals(libro.isbn); // Compara basándose en el ISBN, por ejemplo
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn); // Usa el ISBN para el código hash
    }
    public String getIsbn() {
        return isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getYear() {
        return year;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImagen() {return imagen;}
}
