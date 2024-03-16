package com.example.fitjourney;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddContactos extends Application {

    @FXML
    private Button deleteBtn;
    @FXML
    private TextField apellidoTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField nombreTxt;

    @FXML
    private CheckBox proyecto1;

    @FXML
    private CheckBox proyecto2;

    @FXML
    private CheckBox proyecto3;

    @FXML
    private TextField telefonoTxt;
    private Contactos contactoSeleccionado;
    private String idBoton;
    private List<Contactos>listaContactos=DatosContactos.getListaContactos();
    private String imagenSeleccionada;

    @Override
    public void start(Stage primaryStage) {

        // Escuchador para checkBox1
        proyecto1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            proyecto2.setDisable(newValue);
            proyecto3.setDisable(newValue);
        });

        // Escuchador para checkBox2
        proyecto2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            proyecto1.setDisable(newValue);
            proyecto3.setDisable(newValue);
        });

        // Escuchador para checkBox3
        proyecto3.selectedProperty().addListener((observable, oldValue, newValue) -> {
            proyecto1.setDisable(newValue);
            proyecto2.setDisable(newValue);
        });

    }

    public void iniciar(String id) {
        switch (id){
            case "contactoBtn":
                deleteBtn.setVisible(true);
                deleteBtn.setManaged(true);
                break;
            case "addContacto":
                //Oculta la opcion de borrado
                deleteBtn.setVisible(false);
                deleteBtn.setManaged(false);
                break;
            default:
                System.out.println("Error al obtener el id del boton");
                break;
        }
    }
    @FXML
    void deleteContacto(ActionEvent event){
        int id=contactoSeleccionado.getId();
        boolean resultado = borrarContacto(
                id

        );
        if (resultado) {
            nombreTxt.clear();
            apellidoTxt.clear();
            emailTxt.clear();
            telefonoTxt.clear();
            proyecto1.setSelected(false);
            proyecto2.setSelected(false);
            proyecto3.setSelected(false);
            mostrarAlertaAceptar("Contacto Borrado","Contacto "+this.contactoSeleccionado.getNombre()+" eliminado con éxito");
        } else {
            mostrarAlertaError("No se pudo borrar el contacto");
        }
    }
    public boolean borrarContacto(int id){
        for(Contactos contacto : listaContactos){
            if(contacto.getId()==id){
                listaContactos.remove(contacto);
                return true;
            }
        }
        return false;
    }

    @FXML
    void determinaFuncion(ActionEvent event){
        switch (idBoton){
            case "contactoBtn":
                modifyContacto();
                break;

            case "addContacto":
                addContacto();
                break;
            default:
                System.out.println("Error al obtener el id del boton");
                break;
        }
    }

    private void addContacto() {
        String nombre=nombreTxt.getText();
        String apellido=apellidoTxt.getText();
        String email=emailTxt.getText();
        String telefono=telefonoTxt.getText();
        String proyectoSelected;
        if(proyecto1.isSelected()){
            proyectoSelected=proyecto1.getId();
        } else if (proyecto2.isSelected()) {
            proyectoSelected=proyecto2.getId();
        }else {
            proyectoSelected=proyecto3.getId();
        }

        if (!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !imagenSeleccionada.isEmpty() && proyecto1.isSelected() || proyecto2.isSelected() || proyecto3.isSelected()) {
            Contactos newContacto=new Contactos(nombre,apellido,email,telefono,proyectoSelected,imagenSeleccionada);
            listaContactos.add(newContacto);
            mostrarAlertaAceptar("Contacto añadido", "El nuevo contacto se añadio con exito");
            proyecto1.setSelected(false);
            proyecto2.setSelected(false);
            proyecto3.setSelected(false);
            nombreTxt.clear();
            apellidoTxt.clear();
            emailTxt.clear();
            telefonoTxt.clear();
        }else{
            mostrarAlertaError("Debes rellenar todos los campos.");
        }
    }

    void modifyContacto() {
        String nombre = nombreTxt.getText();
        String apellido = apellidoTxt.getText();
        String email=emailTxt.getText();
        String telefono=telefonoTxt.getText();
        String proyectoSelected;
        if(proyecto1.isSelected()){
            proyectoSelected=proyecto1.getId();
        } else if (proyecto2.isSelected()) {
            proyectoSelected=proyecto2.getId();
        }else {
            proyectoSelected=proyecto3.getId();
        }
        int id=contactoSeleccionado.getId();
        // Asumiendo que Proyecto es tu clase para manejar la lógica de proyectos
        // y que tiene un constructor adecuado para estos parámetros
        if (!nombre.isEmpty() && !apellido.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !imagenSeleccionada.isEmpty() && proyecto1.isSelected() || proyecto2.isSelected() || proyecto3.isSelected()) {
            boolean resultado = actualizarContacto(
                    id,
                    nombre,
                    apellido,
                    email,
                    telefono,
                    imagenSeleccionada

            );
            if (resultado) {
                mostrarAlertaAceptar("Contacto Actualizado","Contacto "+this.contactoSeleccionado.getNombre()+" actualizado con éxito");
            } else {
                mostrarAlertaError("No se pudo actualizar el contacto");
            }
        } else {
            mostrarAlertaError("Debes rellenar todos los campos.");
        }
    }
    /*
     * Método para actualizar la nota seleccionada
     * */
    public boolean actualizarContacto(int id, String nombre, String apellido, String email,String telefono,String imagenSeleccionada) {
        for (Contactos contacto : listaContactos) {
            if (contacto.getId() == id) {
                contacto.setNombre(nombre);
                contacto.setApellido(apellido);
                contacto.setEmail(email);
                contacto.setTelefono(telefono);
                contacto.setUrlImagen(imagenSeleccionada);
                return true; // Nota encontrado y actualizada
            }
        }
        return false; // Nota no encontrada
    }







    @FXML
    void imagen(MouseEvent event){
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("imagen files", "*.png","*.jpg","*.jpeg"));
        File selectedFile=fileChooser.showOpenDialog(null);

        if(selectedFile!=null){
            String imagenPath=selectedFile.getAbsolutePath();
            this.imagenSeleccionada=imagenPath;
        }
    }



    @FXML
    void returnToController(ActionEvent event) throws IOException {
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitjourney/img/fxml/contactos.fxml"));
        Parent root = loader.load();
        DatosProyectos.getControllers().getControllerPagPrincipal().cambiarStage(root);
    }


    private void mostrarAlertaAceptar(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void mostrarAlertaError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public void setContactoSeleccionado(Contactos contacto){
        this.contactoSeleccionado=contacto;
    }
    public void setIdBoton(String origen) {
        this.idBoton = origen;
    }

    public void setNombreContacto(String nombre) {
        this.nombreTxt.setText(nombre);
    }

    public void setApellidoContacto(String apellido ) {
        this.apellidoTxt.setText(apellido);
    }

    public void setEmailContacto(String email) {
        this.emailTxt.setText(email);
    }

    public void setTelefonoContacto(String telefono) {
        this.telefonoTxt.setText(telefono);
    }

   /* public void setDatosContacto(Contactos contacto) {
        this.da
    }*/
}
