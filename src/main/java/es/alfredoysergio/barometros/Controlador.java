/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 *
 * @author Alfredo Marquina Meseguer
 */
public class Controlador implements Initializable {
   
    
    @FXML
    private Button btActualizar;

    @FXML
    private Button btCalibrar;

    @FXML
    private RadioButton bthpa;

    @FXML
    private RadioButton btmmhg;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private ToggleGroup grupo;

    @FXML
    private TextArea historial;

    @FXML
    private ImageView imageViewIcono;

    @FXML
    private Label labbelPresionMmhg;

    @FXML
    private Spinner<Integer> spHora;

    @FXML
    private TextField tfAltura;

    @FXML
    private TextField tfPresion;
    
     /**
     * El objeto Vista asociado a este objeto Controles.
     */
    Modelo modelo;

    /**
     * Presion al nivel del mar
     */
    private static final double PRESION_NIVEL_MAR_MMHG = 760.0;

    /**
     * Validador para los campos rellenables
     */
    ValidationSupport validationSupport = new ValidationSupport();
    
    /**
     * Resouce  Bundel para la internacionalización
     */
    ResourceBundle i18n;
    
    @FXML
    void actualizar(ActionEvent event) {
        Double presion;
        boolean siMmhg;
        LocalDateTime fecha;

        siMmhg = btmmhg.isSelected();
        presion = Double.parseDouble(tfPresion.getText());
        fecha = crearLocalDateTime(datePickerFecha,
                spHora);

        Medicion a = annadirMedicion(fecha, presion, siMmhg);
        historial.appendText(a.toString() + "\n");
        // Esto no funciona
         Image image = new Image("images/sunny.png");
        System.out.println(image.getUrl());
        imageViewIcono.setImage(image);
        //imageViewIcono.setImage(new Image(actualizarIcono()));
    }

    @FXML
    void calibrarAltura(ActionEvent event) {
        System.out.println("" + imageViewIcono.getImage().toString());
        Double nuevaPresion;
        nuevaPresion = Double.parseDouble(tfAltura.getText());
        modelo.annadirMedicion(LocalDateTime.now(), nuevaPresion);
        labbelPresionMmhg.setText(nuevaPresion + " mmhg");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("" + imageViewIcono.getImage().getUrl());
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12);

        spHora.setValueFactory(valueFactory);
        modelo = Modelo.cargarModelo();
        
        
        //validationSupport.setErrorDecorationEnabled(false);
        ///////Aplicamos Validacion en los TextFields
        //Altura
        validationSupport.registerValidator(tfAltura, 
                Validator.createRegexValidator(
                        rb.getString("validacionAltura"), 
                        "[0-9]+", 
                        Severity.ERROR));
        
        //Presion
        validationSupport.registerValidator(tfPresion, 
                Validator.createRegexValidator(
                        rb.getString("validacionPresion"), 
                        "[0-9]+", 
                        Severity.ERROR));
        
        ///////Aplicamos Validacion en el Date Picker
        validationSupport.registerValidator(datePickerFecha, 
                Validator.createEmptyValidator(
                        rb.getString("validacionDatePicker")));
        
        
    }

    
    //////Setter y getter Modelo
    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    

    /**
     * Devuelve la ruta al icono que corresponde con el estado del barometro.
     *
     * @return ruta icono
     */
    public String actualizarIcono() {

        String rutaIcono = "images/";
        switch (modelo.obtenerTiempo()) {
            case ANTICICLON:
                rutaIcono += "sunny.png";
                break;
            case ANTICICLON_ENTRE_BORRASCAS:
                rutaIcono += "cloudy.png";
                break;
            case BORRASCA_LEJOS:
                rutaIcono += "rainy.png";
                break;
            case BORRASCA_PROFUNDA:
                rutaIcono += "heavy-rain_1.png";
                break;
            default:
                rutaIcono += "error.png";
        }
        return rutaIcono;
    }

    /**
     *
     * Este método se utiliza para añadir una nueva medición a la lista de
     * mediciones.
     *
     * @param tiempo la hora de la medición
     * @param presion la presión de la medición
     * @param b valor booleano que indica si la presión está en mmHg o hPa
     * @return un nuevo objeto Medicion que representa la medida añadida
     */
    private Medicion annadirMedicion(LocalDateTime tiempo, Double presion,
            boolean siMmhg) {
        if (!siMmhg) {
            presion *= 3.0 / 4;
        }
        modelo.annadirMedicion(tiempo, presion);
        return new Medicion(tiempo, presion);
    }

    /**
     * Este método se utiliza para crear un objeto LocalDateTime basado en el
     * selector de fecha y el campo de hora proporcionados.
     *
     * @param fecha el objeto selector de fecha que contiene la fecha
     * seleccionada
     * @param hora el campo de texto que contiene la hora seleccionada
     * @return un objeto LocalDateTime que representa la fecha y la hora
     * combinadas
     */
    public LocalDateTime crearLocalDateTime(DatePicker fecha,
            Spinner<Integer> hora) {
        return LocalDateTime.of(fecha.getValue(), 
                LocalTime.of(hora.getValue(), 00));
    }

}
