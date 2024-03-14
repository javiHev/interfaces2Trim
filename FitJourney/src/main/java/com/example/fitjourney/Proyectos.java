package com.example.fitjourney;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proyectos {
    private static int contadorId = 0; // Variable estática para generar IDs
    private int id; // ID único para cada instancia
    private String nombre;
    private LocalDate fecha;
    private List<String> tareas = new ArrayList<>();

    public Proyectos(String nombre, LocalDate fecha, List<String> tareas) {
        this.id = ++contadorId; // Incrementa el contador y asigna el ID
        this.nombre = nombre;
        this.fecha = fecha;
        this.tareas = tareas;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<String> getTareas() {
        return tareas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTareas(List<String> tareas) {
        this.tareas = tareas;
    }
}
