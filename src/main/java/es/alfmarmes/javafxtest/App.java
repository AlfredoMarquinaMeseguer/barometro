package es.alfmarmes.javafxtest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static final String RUTA_JSON = "C:\\Users\\Alfre\\Documents\\"
            + "NetBeansProjects\\barometro\\src\\main\\resources\\"
            + "es\\alfmarmes\\file.";

    /**
     * Presion al nivel del mar
     */
    private static final double PRESION_NIVEL_MAR_MMHG = 760.0;
    Modelo modelo;
    Vista vista;
    // Probablemnete Eliminar
    String rutaIcono;
    //Probablemente guardar solo en Modelo
    public HashMap<LocalDateTime, Double> hashMap;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader a = loadFXML("barometro");
        Parent root = a.load();
        ControlesNuevo controles = a.getController();
        controles.recibirVista(this.vista);
        scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    private static FXMLLoader loadFXML(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void main(String[] args) {
        App controlador = new App();
        launch();
    }

    public App() {
        hashMap = cargarHistorial();
        vista = new Vista(this);
        //vista.launch();
        modelo = new Modelo(hashMap, PRESION_NIVEL_MAR_MMHG);
    }

    /**
     * Guardar el fichero JSON especificado en la variable
     *
     *
     *
     */
    public void guardarHistorial() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(hashMap);
        Path path = Paths.get(RUTA_JSON);
        try ( FileWriter writer = new FileWriter(path.toString())) {
            writer.write(json);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el fichero JSON especificado en la variable
     *
     *
     *
     */
    public HashMap<LocalDateTime, Double> cargarHistorial() {
        // Creamos un gson con el adaptador de tipo para la clase LocalDateTime
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        new LocalDateTimeAdaptador())
                .create();
        //Conseguimos la ruta en la que se encuenra el
        Path path = Paths.get(RUTA_JSON);

        //Inicializamos un mapa a devolver
        HashMap<LocalDateTime, Double> devolver;
        //Intentamos crear un FileReader para leer el JSON
        try ( FileReader lector = new FileReader(path.toString())) {
            /* Si no encuentra problemas leemos el fichero y lo pasamos a
                hashMap*/
            devolver = gson.fromJson(lector,
                    new TypeToken<HashMap<LocalDateTime, Double>>() {
                    }.getType());
        } catch (IOException e) {
            //Si no encuentran ningún fichero es porque todavía no se ha creado
            e.printStackTrace();
            devolver = new HashMap<>();
        }
        return devolver;
    }

    /**
     * Recibe la altura a la que se encuentra el barometro y devuelve la presion
     * de referencia.
     *
     * @param altura
     * @return Nueva presion de referencia
     */
    public double calibrar(int altura) {
        double presion = PRESION_NIVEL_MAR_MMHG - (altura / 11);
        modelo.setPresionReferencia(presion);
        return presion;
    }

    /**
     * Recibe el tiempo y la medida y los añade al hashMap del modelo
     *
     * @param tiempo
     * @param medida
     */
    public void annadirMedida(LocalDateTime tiempo, double medida) {
        modelo.annadirMedicion(tiempo, medida);
    }

    /**
     * Devuelve la ruta al icono que corresponde con el estado del barometro.
     *
     * @return ruta icono
     */
    public String actualizarIcono() {

        String rutaIcono = "/main/resources/es/alfmarmes/javafxtest/";
        switch (modelo.obtenerTiempo()) {
            case ANTICICLON:
                rutaIcono = "sunny.png";
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
     * Devuelve todo el contenido del HashMap de mediciones con un objeto que es
     * la asociación de la tabla de dispersión.
     *
     * return historial
     */
    /**
     * Devuelve todo el contenido del HashMap de mediciones con un objeto que es
     * la asociación de la tabla de dispersión.
     *
     * return historial
     */
    public ArrayList<Medicion> historialCompleto() {
        // Conseguimos el mapa a modo de un Set
        Set<Map.Entry<LocalDateTime, Double>> mapa = modelo.getHistorial().
                entrySet();

        // Inicializamos el arraylist a devolver
        ArrayList<Medicion> devolver = new ArrayList<>();

        // Introducimos todos los valores del set en la lista como objetos Medicion
        for (Map.Entry<LocalDateTime, Double> medicion : mapa) {
            devolver.add(new Medicion(medicion.getKey(),
                    medicion.getValue()));
        }

        // Ordenamos la lista segun fecha de medición
        Collections.sort(devolver);

        return devolver;
    }

}
