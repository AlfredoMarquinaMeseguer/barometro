package es.alfredoysergio.barometros;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.stage.WindowEvent;
import java.util.prefs.Preferences;
/**
 * JavaFX App
 */
public class App extends Application {

    public static String RUTA_BUNDLE = "es/alfredoysergio/barometros/internacionalizacion/MessageBundle";
    
    private static Scene scene;
    
    
    //Las preferncias no funcionan, pero yo te la pongo igual
    Preferences preferencias;

    @Override
    public void start(Stage stage) throws IOException {
        preferencias = Preferences.userRoot();
        String lang = preferencias.get("LANG", "ES");
    
        Locale locale ;
        
        // Esto tendría sentido si las preferencia funcionaran        
        if(lang.equals("EN")){
            locale = Locale.UK;
        }else if (lang.equals("FR")){
            locale = Locale.FRANCE;
        }else{
            locale = new Locale("es", "ES");
        }
        
        ResourceBundle rb = ResourceBundle.getBundle(RUTA_BUNDLE, 
                locale);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(
                "barometro.fxml"), rb);
        scene = new Scene(fxmlLoader.load());
        
        stage.setScene(scene);
        stage.setTitle("Barometro");
        
        Controlador controlador = fxmlLoader.getController();
        
        stage.setOnCloseRequest((WindowEvent event) -> {
            // Aqui abría guardado las preferencias si funcionaran
            preferencias.put("LANG", controlador.getPreferencias());
            Modelo.guardarModelo(controlador.getModelo(), Controlador.RUTA_JSON);
            System.out.println("Saliendo...");
        });

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // Creo que se carga tan rapido que no llama a la pantalla de  carga
        System.setProperty("javafx.proloader", PantallaDeCarga.class.getName());
        launch();
        //Application.launch(App.class, args);
    }

}