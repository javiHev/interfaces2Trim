package com.example.fitjourney;

import java.util.ArrayList;
import java.util.List;

public class DatosContactos {
    private static List<Contactos> listaContactos = new ArrayList<>();
    private static final Controller controllers = new Controller();

    public DatosContactos() {

        addContactoToList(new Contactos("Juan", "Pérez", "juan.perez@example.com", "1234567890", "proyecto1", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Ana", "López", "ana.lopez@example.com", "0987654321", "proyecto2", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Ana", "López", "ana.lopez@example.com", "0987654321", "proyecto2", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Ana", "López", "ana.lopez@example.com", "0987654321", "proyecto2", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Luis", "Martínez", "luis.martinez@example.com", "1122334455", "proyecto3", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Luis", "Martínez", "luis.martinez@example.com", "1122334455", "proyecto3", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Luis", "Martínez", "luis.martinez@example.com", "1122334455", "proyecto3", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Sofía", "García", "sofia.garcia@example.com", "2233445566", "proyecto1", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Carlos", "Fernández", "carlos.fernandez@example.com", "3344556677", "proyecto2", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("María", "González", "maria.gonzalez@example.com", "4455667788", "proyecto3", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));
        addContactoToList(new Contactos("Pedro", "Rodríguez", "pedro.rodriguez@example.com", "5566778899", "proyecto1", "C:/Users/usuario/OneDrive/Escritorio/2DAM/2Trim/interfaces2Trim/FitJourney/src/main/resources/com/example/fitjourney/img/logo_redondo.png"));

    }

    public static void addContactoToList(Contactos contacto) {
        listaContactos.add(contacto);
        System.out.println("Contacto: " + contacto.getNombre() + " " + contacto.getApellido() + ", Proyecto: " + contacto.getProyecto());
    }

    public static List<Contactos> getListaContactos() {
        return listaContactos;
    }

    public static Controller getControllers() {
        return controllers;
    }

}
