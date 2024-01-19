package model;

import java.util.Objects;

public class Libro {
    private String isbn;
    private String nombre;
    private String autor;
    private String year;

    public Libro(String isbn, String nombre, String autor, String year) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.year = year;
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

    public void setAño(String year) {
        this.year = year;
    }
}
