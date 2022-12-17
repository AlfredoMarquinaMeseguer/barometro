package es.alfmarmes.javafxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class controles {

    @FXML
    private Button Boton;

    @FXML
    private Label lTexto;

    @FXML
    private TextField tfTexto;

    private static final String MSGS[] = {
        "Patata",
        "Potato",
        "Kartoffel"
    };

    @FXML
    void elPerroDeJuanVicenteComePienso(ActionEvent event) {
        int msg = (int) (Math.round(Math.random() * (MSGS.length - 1)));
        tfTexto.setText(MSGS[msg]);
    }

}
