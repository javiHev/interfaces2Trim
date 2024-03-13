package com.example.fitjourney;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Proyectos {
    private String nombre;
    private LocalDate fecha; // Usar LocalDate en lugar de Date
    private List<String> tareas = new ArrayList<>();


    public Proyectos(String nombre, LocalDate fecha, List<String> tareas) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tareas = tareas;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() { // Retorna un LocalDate
        return fecha;
    }

    public List<String> getTareas() {
        return tareas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) { // Acepta un LocalDate
        this.fecha = fecha;
    }

    public void setTareas(List<String> tareas) {
        this.tareas = tareas;
    }
}
