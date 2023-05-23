/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.time.LocalTime;
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

    private static final Double CONVERTIR = 1.0 / 11.0;
    private static final Double HPA_A_MMHG = 3.0 / 4.0;

    /**
     * Ruta de guardado del fichero Json del historial
     */
    public static final String RUTA_JSON = "src/main/resources/json/file.json";
    /**
     * Presion al nivel del mar
     */
    public static final double PRESION_NIVEL_MAR_MMHG = 760.0;

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
     * El objeto Vista asociado a este objeto Controles.
     */
    private Modelo modelo;

    /**
     * Validador para los campos rellenables
     */
    private ValidationSupport validationSupport = new ValidationSupport();

    /**
     * Resouce Bundel para la internacionalización
     */
    private ResourceBundle i18n;

    /**
     * Para guardar las preferencias cuando se cierra la
     */
    private String preferencias;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preferencias = rb.getLocale().getLanguage().toUpperCase(rb.getLocale());

        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
                        LocalTime.MAX.getHour());

        spHora.setValueFactory(valueFactory);
        modelo = Modelo.cargarModelo(Controlador.RUTA_JSON);
        // En el nombre pone label mal, culpo a Sergio
        escribirPresionRef(modelo.getPresionReferencia());

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

    /**
     * Acción realizada al pulsar el botón Actualizar del barómetro. Crea un
     * objeto Medición a partir de la presión obtenida de la etiqueta presión,
     * la selección de los botones de rádio, las fecha escogida y la hora del
     * spinner.
     *
     * @param event
     */
    @FXML
    public void actualizar(ActionEvent event) throws Exception {
        Double presion;
        boolean siMmhg;
        LocalDateTime fecha;

        siMmhg = btmmhg.isSelected();
        presion = Double.valueOf(tfPresion.getText());
        fecha = crearLocalDateTime(datePickerFecha,
                spHora);

        Medicion a = annadirMedicion(fecha, presion, siMmhg);
        historial.appendText(a.toString() + "\n");
        // Esto no funciona
        imageViewIcono.setImage(new Image(actualizarIcono()));
    }

    /**
     *
     * @param event
     */
    @FXML
    void calibrarAltura(ActionEvent event) {
        Double nuevaReferencia;
        nuevaReferencia = PRESION_NIVEL_MAR_MMHG
                - (Double.valueOf(tfAltura.getText()) * CONVERTIR);
        modelo.setPresionReferencia(nuevaReferencia);
        escribirPresionRef(nuevaReferencia);
    }

    @FXML
    void activarTextoAVoz(ActionEvent event) {
        System.out.println("Nothing");
    }

    /**
     * Cambia el idioma a francés
     *
     * @param event
     */
    @FXML
    void cambiarAFranchute(ActionEvent event) {
        preferencias = "FR";
        cambiarIdioma(Locale.FRANCE);
    }

    /**
     * Cambia el idioma a español
     *
     * @param event
     */
    @FXML
    void cambiarEspannol(ActionEvent event) {
        preferencias = "ES";
        cambiarIdioma(new Locale("es", "ES"));
    }

    /**
     * Cambia el idioma a inglés
     *
     * @param event
     */
    @FXML
    void cambiarIngles(ActionEvent event) {
        preferencias = "EN";
        cambiarIdioma(Locale.UK);
    }

    /**
     * Exporta el JSON del historial
     *
     * @param event
     */
    @FXML
    void exportarFichero(ActionEvent event) {
        //Llama a un file chooser para coger la ruta del fichero a guardar
        // Exporta el fichero
        //Modelo.guardarModelo(this.modelo, rutacogia);
    }

    /**
     * Getter del objeto Modelo
     *
     * @return objeto Modelo del Controlador
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * Setter del objeto Modelo *
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    /**
     * Getter preferencia de idioma del usuario
     *
     * @return preferencia de idioma del usuario
     */
    public String getPreferencias() {
        return preferencias;
    }

    /**
     * Devuelve la ruta al icono que corresponde con el estado del barometro.
     *
     * @return ruta icono
     */
    public String actualizarIcono() {

        String rutaIcono = "images/";
        switch (modelo.obtenerTiempo()) {
            case ANTICICLON_INTENSO:
                rutaIcono += "sunny.png";
                break;
            case ANTICICLON_SUAVE:
                rutaIcono += "cloudy.png";
                break;
            case BORRASCA_SUAVE:
                rutaIcono += "rainy.png";
                break;
            case BORRASCA_INTENSA:
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
     * @param siMmhg valor booleano que indica si la presión está en mmHg o hPa
     * @return un nuevo objeto Medicion que representa la medida añadida
     */
    public Medicion annadirMedicion(LocalDateTime tiempo, Double presion,
            boolean siMmhg) {
        if (!siMmhg) {
            presion *= HPA_A_MMHG;
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
                LocalTime.of(hora.getValue(), 0));
    }

    /**
     * Cambia el idioma de los controles al idioma especificado en el locale
     *
     * @param locale Locale del idioma a cambiar
     */
    public void cambiarIdioma(Locale locale) {
        i18n = ResourceBundle.getBundle(App.RUTA_BUNDLE, locale);

        ////// Parte de añadir calibración del barómetro
        lCalibrar.setText(i18n.getString("calibracion"));
        lReferencia.setText(i18n.getString("referencia"));
        lAltura.setText(i18n.getString("altura"));
        btCalibrar.setText(i18n.getString("calibrar"));
        // Accesibilidad
        btCalibrar.accessibleTextProperty().setValue(i18n.getString("accesibilidad.BtCalibrar"));
        // Ayuda
        btCalibrar.accessibleHelpProperty().setValue(i18n.getString("ayuda.BtCalibrar"));

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
        btActualizar.accessibleHelpProperty().setValue(i18n.getString("ayuda.BtActualizar"));

        ////// Parte de icono
        lIcono.setText(i18n.getString("icono"));

        ////// Parte de Historial
        lHistorial.setText(i18n.getString("historial"));
        //Ayuda
        historial.accessibleHelpProperty().setValue(i18n.getString("ayuda.TAHistorial"));

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

    /**
     * Escribe la presion de referencia en la etiqueta 
     * @param presionReferencia 
     */
    
    private void escribirPresionRef(Double presionReferencia) {
        labbelPresionMmhg.setText(String.format(i18n.getLocale(),
                "%.2f", presionReferencia) + " mmhg");
    }

    //**************************************************************************
    //Getters
    //**************************************************************************
    public Button getBtActualizar() {
        return btActualizar;
    }

    public Button getBtCalibrar() {
        return btCalibrar;
    }

    public RadioButton getBthpa() {
        return bthpa;
    }

    public RadioButton getBtmmhg() {
        return btmmhg;
    }

    public DatePicker getDatePickerFecha() {
        return datePickerFecha;
    }

    public MenuItem getEspannol() {
        return espannol;
    }

    public MenuItem getExptFichero() {
        return exptFichero;
    }

    public MenuItem getFrances() {
        return frances;
    }

    public TextArea getHistorial() {
        return historial;
    }

    public ImageView getImageViewIcono() {
        return imageViewIcono;
    }

    public MenuItem getIngles() {
        return ingles;
    }

    public Label getlAltura() {
        return lAltura;
    }

    public Label getlCalibrar() {
        return lCalibrar;
    }

    public Label getlFecha() {
        return lFecha;
    }

    public Label getlHistorial() {
        return lHistorial;
    }

    public Label getlHora() {
        return lHora;
    }

    public Label getlIcono() {
        return lIcono;
    }

    public Label getlMedicion() {
        return lMedicion;
    }

    public Label getlPresion() {
        return lPresion;
    }

    public Label getlReferencia() {
        return lReferencia;
    }

    public Label getLabbelPresionMmhg() {
        return labbelPresionMmhg;
    }

    public Menu getmAccesibilidad() {
        return mAccesibilidad;
    }

    public Menu getmFichero() {
        return mFichero;
    }

    public Menu getmIdiomas() {
        return mIdiomas;
    }

    public Spinner<Integer> getSpHora() {
        return spHora;
    }

    public MenuItem getTextoAVoz() {
        return textoAVoz;
    }

    public TextField getTfAltura() {
        return tfAltura;
    }

    public TextField getTfPresion() {
        return tfPresion;
    }

    public ToggleGroup getTgMedida() {
        return tgMedida;
    }

    public ValidationSupport getValidationSupport() {
        return validationSupport;
    }

    public ResourceBundle getI18n() {
        return i18n;
    }

}
