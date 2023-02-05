/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

/**
 *
 * @author Alfre
 */
import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaDeCarga extends Preloader {
    private ProgressBar bar;
    private Label label;
    private Stage stage;
    
     public PantallaDeCarga() {
        bar = new ProgressBar();
        label = new Label("Loading...");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(bar, label);
        Scene scene = new Scene(root, 300, 100);
        stage = new Stage();
        stage.setScene(scene);
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == Type.BEFORE_START) {
            stage.hide();
        }
    }
    
    
      @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            bar.setProgress(((ProgressNotification) info).getProgress());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage.show();
    }
}