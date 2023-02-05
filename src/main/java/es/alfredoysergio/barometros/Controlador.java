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
import java.util.Locale;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
    private MenuItem espannol;

    @FXML
    private MenuItem exptFichero;

    @FXML
    private MenuItem frances;

    @FXML
    private TextArea historial;

    @FXML
    private ImageView imageViewIcono;

    @FXML
    private MenuItem ingles;

    @FXML
    private Label lAltura;

    @FXML
    private Label lCalibrar;

    @FXML
    private Label lFecha;

    @FXML
    private Label lHistorial;

    @FXML
    private Label lHora;

    @FXML
    private Label lIcono;

    @FXML
    private Label lMedicion;

    @FXML
    private Label lPresion;

    @FXML
    private Label lReferencia;

    @FXML
    private Label labbelPresionMmhg;

    @FXML
    private Menu mAccesibilidad;

    @FXML
    private Menu mFichero;

    @FXML
    private Menu mIdiomas;

    @FXML
    private Spinner<Integer> spHora;

    @FXML
    private MenuItem textoAVoz;

    @FXML
    private TextField tfAltura;

    @FXML
    private TextField tfPresion;

    @FXML
    private ToggleGroup tgMedida;
    
        
    /**
     * Ruta de guardado del fichero Json del historial
     */
    public static final String RUTA_JSON = "src/main/resources/json/file.json";        
    /**
     * Presion al nivel del mar
     */
    public static final double PRESION_NIVEL_MAR_MMHG = 760.0;        
        
     /**
     * El objeto Vista asociado a este objeto Controles.
     */
    Modelo modelo;    

    /**
     * Validador para los campos rellenables
     */
    private ValidationSupport validationSupport = new ValidationSupport();
    
    /**
     * Resouce  Bundel para la internacionalización
     */
    private ResourceBundle i18n;
    
    /**
     * Para guardar las preferencias cuando se cierra la  
     */
    private String preferencias;
    
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

    @FXML
    void activarTextoAVoz(ActionEvent event) {

    }

    @FXML
    void cambiarAFranchute(ActionEvent event) {
        cambiarIdioma(Locale.FRANCE);
    }

    @FXML
    void cambiarEspannol(ActionEvent event) {
        cambiarIdioma(new Locale("es", "ES"));
    }

    @FXML
    void cambiarIngles(ActionEvent event) {
        cambiarIdioma(Locale.UK);
    }

    @FXML
    void exportarFichero(ActionEvent event) {

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("" + imageViewIcono.getImage().getUrl());
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12);

        spHora.setValueFactory(valueFactory);
        modelo = Modelo.cargarModelo(Controlador.RUTA_JSON);
        
        
        //validationSupport.setErrorDecorationEnabled(false);
        ///////Aplicamos Validacion en los TextFields
        //Altura
        validationSupport.registerValidator(tfAltura, 
                Validator.createRegexValidator(
                        rb.getString("validacion.Altura"), 
                        "[0-9]+", 
                        Severity.ERROR));
        
        //Presion
        validationSupport.registerValidator(tfPresion, 
                Validator.createRegexValidator(
                        rb.getString("validacion.Presion"), 
                        "[0-9]+", 
                        Severity.ERROR));
        
        ///////Aplicamos Validacion en el Date Picker
        validationSupport.registerValidator(datePickerFecha, 
                Validator.createEmptyValidator(
                        rb.getString("validacion.DatePicker")));
        
        
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
    
    public void cambiarIdioma(Locale locale){
        i18n = ResourceBundle.getBundle(App.RUTA_BUNDLE, locale);
        
        ////// Parte de añadir calibración del barómetro
        lCalibrar.setText(i18n.getString("calibracion"));
        lReferencia.setText(i18n.getString("referencia"));
        lAltura.setText(i18n.getString("altura"));
        btCalibrar.setText(i18n.getString("calibrar"));
        // Accesibilidad
        btCalibrar.accessibleTextProperty().setValue(i18n.getString
                                               ("accesibilidad.BtCalibrar"));
        // Ayuda
        btCalibrar.accessibleHelpProperty().setValue(i18n.getString
                                               ("ayuda.BtCalibrar"));
        
        ////// Parte de añadir Medicion
        lMedicion.setText(i18n.getString("medicion"));
        lPresion.setText(i18n.getString("presion"));
        lFecha.setText(i18n.getString("fecha"));
        lHora.setText(i18n.getString("hora"));
        btActualizar.setText(i18n.getString("actualizar"));
        // Accesibilidad
        tfPresion.accessibleTextProperty().setValue(
                i18n.getString("accesibilidad.TfPresion"));
        btmmhg.accessibleTextProperty().setValue(
                i18n.getString("accesibilidad.RBMercurio"));
        bthpa.accessibleTextProperty().setValue(
                i18n.getString("accesibilidad.RBPascales"));
        datePickerFecha.accessibleTextProperty().setValue(
                i18n.getString("accesibilidad.DatePicker"));
        spHora.accessibleTextProperty().setValue(
                i18n.getString("accesibilidad.SpHora"));        
        btActualizar.accessibleTextProperty().setValue(
                i18n.getString("accesibilidad.BtActualizar"));
        // Ayuda
        btActualizar.accessibleHelpProperty().setValue(i18n.getString
                                               ("ayuda.BtActualizar"));
        
        ////// Parte de icono
        lIcono.setText(i18n.getString("icono"));        
        
        ////// Parte de Historial
        lHistorial.setText(i18n.getString("historial"));
        //Ayuda
        historial.accessibleHelpProperty().setValue(i18n.getString
                                               ("ayuda.TAHistorial"));
        
        
        ////// Menu
        ////////// Fichero
        mFichero.setText(i18n.getString("fichero"));
        exptFichero.setText(i18n.getString("exportar"));
        ////////// Accesibilidad
        mAccesibilidad.setText(i18n.getString("accesibilidad"));
        textoAVoz.setText(i18n.getString("activarVoz"));        
        ////////// Idiomas
        mIdiomas.setText(i18n.getString("idioma"));
        espannol.setText(i18n.getString("espannol"));
        ingles.setText(i18n.getString("ingles"));
        frances.setText(i18n.getString("frances"));
        
    }

}
