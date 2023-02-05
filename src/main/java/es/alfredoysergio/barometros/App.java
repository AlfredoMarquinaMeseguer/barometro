package es.alfredoysergio.barometros;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.WindowEvent;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(
                "barometro.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        
        Controlador controlador = fxmlLoader.getController();
        
        stage.setOnCloseRequest((WindowEvent event) -> {
            Modelo.guardarModelo(controlador.getModelo());
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
        System.setProperty("javafx.proloader", PantallaDeCarga.class.getName());
        launch();
        //Application.launch(App.class, args);
    }

}