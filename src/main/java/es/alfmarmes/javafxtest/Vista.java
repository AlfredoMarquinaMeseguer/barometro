/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfmarmes.javafxtest;

//import es.alfmarmes.barometro.App;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * La clase Vista representa la vista de la interfaz de usuario JavaFX.
 * Configura los elementos de la UI y se comunica con la clase Controlador para
 * realizar acciones.
 *
 * @author Sergio
 */
public class Vista extends Application {

    /**
     * El objeto Controlador asociado a este objeto Vista.
     */
    private App controlador;

    /**
     * El objeto Scene para este objeto Vista.
     */
    private static Scene scene;

    /**
     * Crea un nuevo objeto Vista y lanza la interfaz de usuario.
     *
     * @param controlador El objeto Controlador asociado a este objeto Vista.
     */
    public Vista(App controlador) {
        this.controlador = controlador;
        System.out.println("" + controlador);
        Parent root = null;
        try {
            root = loadFXML("barometro").load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        TextField tfAltura = (TextField) root.lookup("#tfAltura");
        TextField tfHora = (TextField) root.lookup("#tfHora");
        TextField tfPresion = (TextField) root.lookup("#tfPResion");
        TextArea historial = (TextArea) root.lookup("#historial");

        tfAltura.setText("0");

        TextFormatter<Integer> formatterOnlyNumbers = new TextFormatter<>(
                new IntegerStringConverter(), 0,
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
//        tfPresion.setTextFormatter(formatterDouble);
        System.out.println("AADASD");
    }

    /**
     * Configura los elementos UI y muestra el escenario.
     *
     * @param stage El objeto Stage para este objeto Vista.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = loadFXML("barometro");
        Parent root = loader.load();
        ControlesNuevo c = loader.getController();
        c.recibirVista(this);
        scene = new Scene(root, 1000, 600);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Saliendo...");
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Este método establece la raíz de la escena en un nuevo archivo FXML.
     *
     * @param fxml el nombre del archivo FXML a establecer como raíz
     * @throws IOException si hay un problema cargando el archivo FXML
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    /**
     * Este método carga un archivo FXML y lo devuelve como un objeto Parent.
     *
     * @param fxml el nombre del archivo FXML a cargar
     * @devuelve el fichero FXML cargado como objeto Parent
     * @throws IOException si hay un problema cargando el archivo FXML
     */
    private static FXMLLoader loadFXML(String fxml) throws IOException {
        return new FXMLLoader(Vista.class.getResource(fxml + ".fxml"));
    }

    /**
     * Este método se utiliza para calibrar la altura del barómetro a partir de
     * la altura en metros proporcionada.
     *
     * @param altura la altura en metros para calibrar el barómetro a
     * @return la presión calibrada en milímetros de mercurio (mmHg)
     */
    public Double pulsarBotonCalibrar(String altura) {
        Double presion = Double.valueOf(controlador.calibrar(
                Integer.parseInt(altura)));
        return presion;
    }

    /**
     * Este método se utiliza para obtener la imagen actual del barómetro en
     * función de su estado actual.
     *
     * @return la imagen que representa el estado actual del barómetro
     */
    public Image getImage() {
        Image imagenIcono = new Image(controlador.actualizarIcono());
        return imagenIcono;
    }

    /**
     * Este método se utiliza para crear un objeto LocalDateTime basado en el
     * selector de fecha y el campo de hora proporcionados.
     *
     * @param datePicker el objeto selector de fecha que contiene la fecha
     * seleccionada
     * @param timeField el campo de texto que contiene la hora seleccionada
     * @return un objeto LocalDateTime que representa la fecha y la hora
     * combinadas
     */
    public LocalDateTime crearLocalDateTime(DatePicker datePicker, TextField timeField) {
        LocalDate fecha = datePicker.getValue();
        int hora = Integer.parseInt(timeField.getText());

        return LocalDateTime.of(fecha, LocalTime.of(hora, 00));
    }

    /**
     *
     * Este método se utiliza para obtener el valor de la presión a partir de la
     * representación de cadena de la presión proporcionada.
     *
     * @param presion la representación en cadena de la presión
     * @return el valor de la presión como Double
     */
    public Double getPresion(String presion) {
        Double p = Double.valueOf(Double.parseDouble(presion));
        return p;
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
    public Medicion annadirMedicion(LocalDateTime tiempo, Double presion, boolean siMmhg) {
        if (!siMmhg) {
            presion *= 3.0 / 4;
        }
        controlador.annadirMedida(tiempo, presion);
        return new Medicion(tiempo, presion);
    }
}
