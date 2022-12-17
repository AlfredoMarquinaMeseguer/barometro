/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfmarmes.javafxtest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

/**
  * La clase Vista representa la vista de la interfaz de usuario JavaFX.
 * Configura los elementos de la UI y se comunica con la clase Controlador para realizar acciones.
 * @author Sergio
 */
public class Vista  {
    
     /**
     * El objeto Controlador asociado a este objeto Vista.
     */
    private Controlador controlador;
   
    /**
     * El objeto Scene para este objeto Vista.
     */
    private static Scene scene;
    
    /**
     * Crea un nuevo objeto Vista y lanza la interfaz de usuario.
     *
     * @param controlador El objeto Controlador asociado a este objeto Vista.
     */
    public Vista(Controlador controlador) {
        this.controlador = controlador;
        launch();
    }
    
    /**
     * Configura los elementos UI y muestra el escenario.
     *
     * @param stage El objeto Stage para este objeto Vista.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("barometro");
        
        TextField tfAltura = (TextField) root.lookup("#tfAltura");
        TextField tfHora = (TextField) root.lookup("#tfHora");
        TextField tfPresion = (TextField) root.lookup("#tfPResion");
        
        tfAltura.setText("0");
        
        TextFormatter<Integer> formatterOnlyNumbers = new TextFormatter<>(new IntegerStringConverter(), 0, 
        c -> {
        if (c.getControlNewText().matches("[0-9]*")) {
            return c;
        }
        return null;
    });
        
        tfAltura.setTextFormatter(formatterOnlyNumbers);
        
        
        TextFormatter<Integer> formatterHour = new TextFormatter<>(new IntegerStringConverter(), 0, 
        c -> {
        if (c.getControlNewText().matches("[0-9]|1[0-9]|2[0-3]")) {
            return c;
        }
        return null;
    });
         
        tfHora.setTextFormatter(formatterHour);
        
        
        TextFormatter<Double> formatterDouble = new TextFormatter<>(new DoubleStringConverter(), 0.0, c -> {
    if (c.getControlNewText().matches("^[1-9]\\\\d*(?:\\\\.\\\\d+)?$")) {
        return c;
    }
    return null;
});
        tfPresion.setTextFormatter(formatterDouble);
        
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
    Este método establece la raíz de la escena en un nuevo archivo FXML.
    @param fxml el nombre del archivo FXML a establecer como raíz
    @throws IOException si hay un problema cargando el archivo FXML
    */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    /**
    Este método carga un archivo FXML y lo devuelve como un objeto Parent.
    @param fxml el nombre del archivo FXML a cargar
    @devuelve el fichero FXML cargado como objeto Parent
    @throws IOException si hay un problema cargando el archivo FXML
    */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    
    
    
    /**
    Este método se utiliza para calibrar la altura del barómetro a partir de la altura en metros proporcionada.
    @param altura la altura en metros para calibrar el barómetro a
    @return la presión calibrada en milímetros de mercurio (mmHg)
*/
    public Double pulsarBotonCalibrar(String altura){
        Double h = Double.valueOf(controlador.calibrar(Integer.parseInt(altura)));
        return h;
    }
    
    /**
    Este método se utiliza para obtener la imagen actual del barómetro en función de su estado actual.
    @return la imagen que representa el estado actual del barómetro
    */
    public Image getImage(){
        Image imagenIcono = new Image(controlador.actualizarIcono());
        return imagenIcono;
    }
    
    /**
    Este método se utiliza para crear un objeto LocalDateTime basado en el selector de fecha y el campo de hora proporcionados.
    @param datePicker el objeto selector de fecha que contiene la fecha seleccionada
    @param timeField el campo de texto que contiene la hora seleccionada
    @return un objeto LocalDateTime que representa la fecha y la hora combinadas
*/
   public LocalDateTime createLocalDateTime(DatePicker datePicker, TextField timeField) {
       LocalDate date = datePicker.getValue();
       int year = date.getYear();
       Month month = date.getMonth();
       int dayOfMonth = date.getDayOfMonth();

    int hour = Integer.parseInt(timeField.getText());

        return new LocalDateTime.of(year, month, dayOfMonth, hour, 00);
   }
    /**

    Este método se utiliza para obtener el valor de la presión a partir de la representación de cadena de la presión proporcionada.
    @param presion la representación en cadena de la presión
    @return el valor de la presión como Double
    */
    public Double getPresion(String presion){
        Double p = Double.valueOf(Double.parseDouble(presion));
        return p;
    }
    /**

    Este método se utiliza para añadir una nueva medición a la lista de mediciones.
    @param tiempo la hora de la medición
    @param presion la presión de la medición
    @param b valor booleano que indica si la presión está en mmHg o hPa
    @return un nuevo objeto Medicion que representa la medida añadida
*/
    public Medicion annadirMedicion(LocalDateTime tiempo,  Double presion, boolean b){
        
    }
}

