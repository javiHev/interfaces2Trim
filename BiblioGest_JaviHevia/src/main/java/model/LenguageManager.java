package model;

import java.util.Locale;
import java.util.ResourceBundle;

public class LenguageManager {
    public static LenguageManager instance;
    private ResourceBundle resourceBundle;

    public LenguageManager() {
        cargarIdioma("es","ES");
    }
    public static LenguageManager getInstance(){
        if (instance == null){
            instance = new LenguageManager();
        }
        return instance;
    }
    public void cargarIdioma(String idioma, String pais){
        Locale locale = new Locale(idioma,pais);
        resourceBundle = ResourceBundle.getBundle("bundles.Messages",locale);
    }
    public ResourceBundle getBundle(){
        return resourceBundle;
    }
}
