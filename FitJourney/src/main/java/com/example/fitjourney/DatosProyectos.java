package com.example.fitjourney;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatosProyectos {


    private Proyectos proyectoSeleccionado;
    private Boolean vistaAnteriorTabla;

    public DatosProyectos() throws ParseException {

        // Creando listas de tareas para cada proyecto
        List<String> tareasProyecto1 = Arrays.asList("Correr 5km tres veces a semana", "10 minutos de estiramiento diario", "Registro diario de progreso");
        List<String> tareasProyecto2 = Arrays.asList("Inscribirse en el gimnasio", "Asistir al gimnasio 4 veces a la semana", "Planificación semanal de rutinas");
        List<String> tareasProyecto3 = Arrays.asList("Comprar equipo de natación", "Nadar 30 minutos tres veces a semana", "Asistir a clases de natación");
        List<String> tareasProyecto4 = Arrays.asList("Comprar bicicleta", "Bicicleta al trabajo dos veces a semana", "Mantenimiento semanal de la bicicleta");
        List<String> tareasProyecto5 = Arrays.asList("Iniciar una dieta balanceada", "Preparar comidas saludables para la semana", "Seguimiento diario de la alimentación");
        List<String> tareasProyecto6 = Arrays.asList("Meditar 10 minutos diarios", "Practicar yoga cada fin de semana", "Leer libros sobre bienestar mental");

        // Creando instancias de DatosProyectos usando LocalDate
        Proyectos proyecto1 = new Proyectos("Correr regularmente", LocalDate.of(2024, 3, 5), tareasProyecto1);
        Proyectos proyecto2 = new Proyectos("Gimnasio constante", LocalDate.of(2024, 4, 4), tareasProyecto2);
        Proyectos proyecto3 = new Proyectos("Natación semanal", LocalDate.of(2024, 5, 4), tareasProyecto3);
        Proyectos proyecto4 = new Proyectos("Bicicleta como transporte", LocalDate.of(2024, 6, 3), tareasProyecto4);
        Proyectos proyecto5 = new Proyectos("Dieta Saludable", LocalDate.of(2024, 7, 3), tareasProyecto5);
        Proyectos proyecto6 = new Proyectos("Bienestar mental", LocalDate.of(2024, 8, 2), tareasProyecto6);
        Proyectos proyecto7 = new Proyectos("Nadar", LocalDate.of(2024, 6, 3), tareasProyecto4);
        Proyectos proyecto8 = new Proyectos("Dieta hipocalorica", LocalDate.of(2024, 7, 3), tareasProyecto5);
        Proyectos proyecto9 = new Proyectos("Meditacion", LocalDate.of(2024, 8, 2), tareasProyecto6);

        DatosProyectos.addProyectToList(proyecto1);
        DatosProyectos.addProyectToList(proyecto2);
        DatosProyectos.addProyectToList(proyecto3);
        DatosProyectos.addProyectToList(proyecto4);
        DatosProyectos.addProyectToList(proyecto5);
        DatosProyectos.addProyectToList(proyecto6);
        DatosProyectos.addProyectToList(proyecto7);
        DatosProyectos.addProyectToList(proyecto8);
        DatosProyectos.addProyectToList(proyecto9);


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

    public Proyectos getProyectoSeleccionado(){return this.proyectoSeleccionado;}
    public void setProyectoSeleccionado(Proyectos proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }
    public void setVistaAnterior(Boolean vistaAnterior) {
        this.vistaAnteriorTabla = vistaAnterior;
    }

}
