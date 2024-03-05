package com.example.fitjourney;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatosProyectos {


    public DatosProyectos() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Creando listas de tareas para cada proyecto
        List<String> tareasProyecto1 = Arrays.asList("Correr 5km tres veces a semana", "10 minutos de estiramiento diario", "Registro diario de progreso");
        List<String> tareasProyecto2 = Arrays.asList("Inscribirse en el gimnasio", "Asistir al gimnasio 4 veces a la semana", "Planificación semanal de rutinas");
        List<String> tareasProyecto3 = Arrays.asList("Comprar equipo de natación", "Nadar 30 minutos tres veces a semana", "Asistir a clases de natación");
        List<String> tareasProyecto4 = Arrays.asList("Comprar bicicleta", "Bicicleta al trabajo dos veces a semana", "Mantenimiento semanal de la bicicleta");
        List<String> tareasProyecto5 = Arrays.asList("Iniciar una dieta balanceada", "Preparar comidas saludables para la semana", "Seguimiento diario de la alimentación");
        List<String> tareasProyecto6 = Arrays.asList("Meditar 10 minutos diarios", "Practicar yoga cada fin de semana", "Leer libros sobre bienestar mental");


        // Creando instancias de DatosProyectos
        Proyectos proyecto1 = new Proyectos("Correr regularmente", sdf.parse("2024-03-05"), tareasProyecto1);
        Proyectos proyecto2 = new Proyectos("Gimnasio constante", sdf.parse("2024-04-04"), tareasProyecto2);
        Proyectos proyecto3 = new Proyectos("Natación semanal", sdf.parse("2024-05-04"), tareasProyecto3);
        Proyectos proyecto4 = new Proyectos("Bicicleta como transporte", sdf.parse("2024-06-03"), tareasProyecto4);
        Proyectos proyecto5 = new Proyectos("Dieta Saludable", sdf.parse("2024-07-03"), tareasProyecto5);
        Proyectos proyecto6 = new Proyectos("Bienestar mental", sdf.parse("2024-08-02"), tareasProyecto6);

        DatosProyectos.addProyectToList(proyecto1);
        DatosProyectos.addProyectToList(proyecto2);
        DatosProyectos.addProyectToList(proyecto3);
        DatosProyectos.addProyectToList(proyecto4);
        DatosProyectos.addProyectToList(proyecto5);
        DatosProyectos.addProyectToList(proyecto6);

    }
    static List<Proyectos> listaProyectos=new ArrayList<>();
    private static final Controller controllers = new Controller();
    public static void addProyectToList(Proyectos proyecto) {
        listaProyectos.add(proyecto);
        System.out.println("Proyecto: " + proyecto.getNombre() );
        int i=1;
        for(String tarea:proyecto.getTareas()){
            System.out.println("Tareas"+i+": "+tarea);
            i++;
        }

    }

    public static List<Proyectos> getListaProyectos() {
        return listaProyectos;
    }
    public static Controller getControllers() {
        return controllers;
    }
}
