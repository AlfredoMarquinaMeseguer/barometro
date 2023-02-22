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

    private Controlador controlador;

    @Override
    public void start(Stage stage) throws IOException {
        Preferences preferencias = Preferences.userRoot();
        String lang = preferencias.get("LANG", "ES");

        Locale locale;

        if (null == lang) {
            locale = new Locale("es", "ES");
        } else {
            switch (lang) {
                case "EN":
                    locale = Locale.UK;
                    break;
                case "FR":
                    locale = Locale.FRANCE;
                    break;
                default:
                    locale = new Locale("es", "ES");
                    break;
            }
        }

        ResourceBundle rb = ResourceBundle.getBundle(RUTA_BUNDLE,
                locale);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(
                "barometro.fxml"), rb);
        scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Barometro");

        controlador = fxmlLoader.getController();

        stage.setOnCloseRequest((WindowEvent event) -> {
            // Aqui abría guardado las preferencias si funcionaran
            preferencias.put("LANG", controlador.getPreferencias());
            Modelo.guardarModelo(controlador.getModelo(), Controlador.RUTA_JSON);
            System.out.println("Saliendo...");
        });

        stage.show();
    }

    public Controlador getControlador() {
        return controlador;
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
