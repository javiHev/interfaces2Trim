package com.example.fitjourney;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Proyectos {
    private String nombre;
    private Date fecha;
    private List<String> tareas=new ArrayList<>();

    public Proyectos(String nombre, Date fecha, List<String> tareas) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tareas = tareas;
    }
    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<String> getTareas() {
        return tareas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTareas(List<String> tareas) {
        this.tareas = tareas;
    }
}
