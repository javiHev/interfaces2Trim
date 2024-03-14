package com.example.fitjourney;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DatosNotas {


    public DatosNotas () throws ParseException{
        addNotasToList(new Nota("Correr en la mañana", "Correr 30 minutos cada mañana mejora la cardio"));
        addNotasToList(new Nota("Rutina de fuerza", "Realizar rutinas de fuerza mejora la resistencia muscular"));
        addNotasToList(new Nota("Yoga para principiantes", "El yoga ayuda a mejorar la flexibilidad y reduce el estrés"));
        addNotasToList(new Nota("Alimentación saludable", "Incorporar frutas y verduras en cada comida"));
        addNotasToList(new Nota("Hidratación", "Beber al menos 2 litros de agua al día"));
        addNotasToList(new Nota("Descanso adecuado", "Dormir al menos 7 horas por noche es crucial para la recuperación"));
    }
    static List<Nota> listaNotas=new ArrayList<>();
    private static final Controller controllers = new Controller();
    public static void addNotasToList(Nota nota) {
        listaNotas.add(nota);
        System.out.println("Nota: " + nota.getTitulo()+"\n Descripcion: "+nota.getDescripcion() );
    }

    public static List<Nota> getListaNotas() {
        return listaNotas;
    }
}
