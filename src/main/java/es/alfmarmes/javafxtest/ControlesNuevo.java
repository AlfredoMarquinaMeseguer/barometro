/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.alfmarmes.javafxtest;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import test.es.testnew.Vista;
import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
/**
 * La clase Controles representa el controlador de la interfaz de usuario JavaFX.
 * Maneja la lógica de los elementos de la interfaz de usuario y se comunica con la clase Vista para realizar acciones.
 * @author Sergio
 */
public class ControlesNuevo implements Initializable {
    
    /**
     * El objeto Vista asociado a este objeto Controles.
     */
    
    Vista vista;
    
    /**
     * El TextArea utilizado para mostrar el historial de mediciones.
     */
    @FXML
    private TextArea taHistorial;
    
    /**
     * Etiqueta utilizada para mostrar la presión calibrada en mmHg.
     */
    @FXML
    private Label labbelPresionMmhg;
    
    /**
     * El TextField utilizado para introducir la altitud para la calibración.
     */
    @FXML
    private TextField tfAltura;
    
    /**
     * Botón utilizado para iniciar el proceso de calibración.
     */
    @FXML
    private Button btCalibrar;
    
    
    /**
     * El DatePicker utilizado para seleccionar la fecha de una medida.
     */
    @FXML
    private DatePicker datePickerFecha;
    
    
    /**
     * El TextField utilizado para introducir la hora de una medición.
     */
    @FXML
    private TextField tfHora;
    
    /**
     * Botón utilizado para iniciar el proceso de actualización de la medición.
     */
    @FXML
    private Button btActualizar;
    
    
    /**
     * El RadioButton utilizado para seleccionar hPa como unidad de presión.
     */
    @FXML
    private RadioButton bthpa;
    
    /**
     * El ToggleGroup utilizado para asegurar que sólo se selecciona una unidad de presión a la vez.
     */
    @FXML
    private ToggleGroup tgMedida;
    
    /**
     * RadioButton utilizado para seleccionar mmHg como unidad de presión.
     */
    @FXML
    private RadioButton btmmhg;
    
    /**
     * El ImageView utilizado para mostrar un icono basado en la actualización de la medición.
     */
    @FXML
    private ImageView imageViewIcono;
    
    /**
     * El TextField utilizado para introducir la medida de presión.
     */
    @FXML
    private TextField tfPresion;
    
    /**
     * Inicializa la clase del controlador.
     *
     * @param url La URL del archivo FXML asociado a este controlador.
     * @param rb El ResourceBundle para este controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
    
    @FXML
    private void calibrarAltura(ActionEvent event) {
        Double nuevaPresion;
        nuevaPresion = vista.pulsarBotonCalibrar(tfAltura.getText());
        labbelPresionMmhg.setText(nuevaPresion + " mmhg");
        imageViewIcono.setImage(vista.getImage());
    }
    
    @FXML
    private void actualizar(ActionEvent event) {
        Double presion;
        boolean siMmhg = false;
        LocalDateTime fecha;
        if(btmmhg.isSelected()){
            siMmhg = true;
        }
        presion = vista.getPresion(tfPresion.getText());
        
        fecha = vista.createLocalDateTime(datePickerFecha, tfHora);
        
        vista.annadirMedicion(fecha, presion, siMmhg);
        imageViewIcono.setImage(vista.getImage());
    }
    

}
