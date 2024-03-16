package com.example.fitjourney;

public class Contactos {
    private static int contadorId = 0; // Variable estática para llevar la cuenta de los IDs
    private int id; // ID único para cada contacto
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String proyecto;
    private String urlImagen; // Agregado campo para la URL de la imagen

    // Constructor actualizado para incluir urlImagen
    public Contactos(String nombre, String apellido, String email, String telefono, String proyecto, String urlImagen) {
        this.id = ++contadorId; // Incrementa el contador y asigna el valor a id
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.proyecto = proyecto;
        this.urlImagen = urlImagen; // Asigna el valor de urlImagen
    }

    // Getters y Setters incluyendo para urlImagen

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    // Getter y Setter para urlImagen
    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
