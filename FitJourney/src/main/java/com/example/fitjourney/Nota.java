package com.example.fitjourney;

public class Nota {
    private static int contadorId = 0; // Contador estático para generar IDs únicos
    private int id; // ID único para cada nota
    private String titulo;
    private String descripcion;

    // Constructor que asigna un ID único y establece el título y la descripción
    public Nota(String titulo, String descripcion) {
        this.id = ++contadorId; // Incrementa el ID para cada nueva instancia
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

