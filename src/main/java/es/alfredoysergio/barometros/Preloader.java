
package es.alfredoysergio.barometros;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Alfre
 */
public class Preloader extends javafx.application.Preloader {
    private ProgressBar progressBar;
    private Stage stage;

    private Scene createPreloaderScene() {
        progressBar = new ProgressBar();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(progressBar);
        return new Scene(borderPane, 800, 600);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("dñashfñasdhfjahfaskdhfklsdfhklhasldfkj");
        this.stage = stage;
        stage.setTitle("Barometro");
        stage.setScene(createPreloaderScene());
        stage.show();        
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        progressBar.setProgress(pn.getProgress());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
}