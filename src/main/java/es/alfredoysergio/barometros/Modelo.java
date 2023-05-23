/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Clase Modelos
 *
 * El propio barometro
 *
 * @author Alfre
 */
public class Modelo {

    public enum Tiempo {
        BORRASCA_SUAVE, BORRASCA_INTENSA, ANTICICLON_INTENSO, ANTICICLON_SUAVE,
        INSUFICIENTE
    }

    private static final double LIM_SUP_CLIMA_SUAVE = 6.5;
    private static final double LIM_INF_CLIMA_SUAVE = 5.5;
    private static final double LIM_CLIMA_INTENSO = 1.0;
    public static final double PRESION_REF_NIVEL_MAR = 760.0;

    private HashMap<LocalDateTime, Medicion> historial;
    /**
     * Fecha de la medición con la fecha más tardía
     */
    private LocalDateTime ultimaMedicion;
    /**
     * Presion de referencia, en mmHg
     */
    private Double presionReferencia;

    // Constructores
    public Modelo(HashMap<LocalDateTime, Medicion> historial, Double presionReferencia) {

        this.presionReferencia = presionReferencia;

        //Consideramos que la primera es la última
        this.ultimaMedicion = LocalDateTime.MIN;

        //Cogemos todas los tiempos de medición
        if (historial != null) {
            this.historial = historial;

            Set<LocalDateTime> mediciones = (Set) historial.keySet();

            /*
            Por cada clave comprobamos si es posterior a nuestra última y
            si lo es la consideramos la última  cuando terminesmo de recorrer toda
            la lista, abremos encontrado la última.
             */
            for (LocalDateTime medicion : mediciones) {
                if (medicion.isAfter(this.ultimaMedicion)) {
                    this.ultimaMedicion = medicion;
                }
            }
        } else {
            this.historial = new HashMap<>();
        }

    }

    public Modelo() {
        this.ultimaMedicion = LocalDateTime.of(0, Month.JANUARY,
                1, 0, 0);
        this.presionReferencia = PRESION_REF_NIVEL_MAR;
        this.historial = new HashMap<>();
    }

    // Setter y Getters
    /**
     * Getter del historial de la clase Modelo
     *
     * @return historial
     */
    public HashMap<LocalDateTime, Medicion> getHistorial() {
        return historial;
    }

    /**
     * Getter de presion de referencia de la clase Modelo
     *
     * @return presion de referencia
     */
    public Double getPresionReferencia() {
        return presionReferencia;
    }

    /**
     * Setter de presion de referencia de la clase Modelo
     *
     */
    public void setPresionReferencia(Double presionReferencia) {
        this.presionReferencia = presionReferencia;
    }

//    /**
//     * Getter de ultima de medicion de la clase Modelo
//     *
//     * @return ultima medicion
//     */
//    public LocalDateTime getUltimaMedicion() {
//        return this.ultimaMedicion;
//    }
//
//    /**
//     * Setter de ultima de medicion de la clase Modelo
//     */
//    public void setUltimaMedicion(LocalDateTime ultimaMedicion) {
//        this.ultimaMedicion = ultimaMedicion;
//    }
    // Métodos Públicos
    /**
     * Comprueba si las propiedades son nulas y si lo son les asigna un valor
     * predeterminado.
     */
    public void comprobarNulos() {
        if (this.ultimaMedicion == null) {
            this.ultimaMedicion = LocalDateTime.MIN;
        }

        if (this.presionReferencia == null) {

            this.presionReferencia = PRESION_REF_NIVEL_MAR;
        }

        if (this.historial == null) {
            this.historial = new HashMap<>();
        }
    }

    /**
     * Añade una nueva medición o edita una existente del historial.
     *
     * @param tiempo
     * @param presion
     */
    public void annadirMedicion(LocalDateTime tiempo, double presion) {
        /* Si el tiempo de medicion es posterior a la ultima esta se convierte
        en la nueva última medicion*/
        if (tiempo.isAfter(ultimaMedicion)) {
            ultimaMedicion = tiempo;
        }
        //Añadir al historial
        historial.put(tiempo, new Medicion(tiempo, presion));
    }

    /**
     * Consulta el historial y devuelve una preción del tiempo
     *
     * @return prediccion de tiempo
     */
    public Tiempo obtenerTiempo() {
        Tiempo devolver;
        if (borrascaIntensa()) {
            devolver = Tiempo.BORRASCA_INTENSA;
        } else if (anticiclonIntenso()) {
            devolver = Tiempo.ANTICICLON_INTENSO;
        } else if (borrascaSuave()) {
            devolver = Tiempo.BORRASCA_SUAVE;
        } else if (anticiclonSuave()) {
            devolver = Tiempo.ANTICICLON_SUAVE;
        } else {
            devolver = Tiempo.INSUFICIENTE;
        }
        return devolver;
    }

    // Métodos Privados
    /**
     * Comprueba
     *
     * @return
     */
    public boolean borrascaIntensa() {
        Double anterior;
        if (historial.containsKey(ultimaMedicion.minusHours(1))) {
            anterior = historial.get(ultimaMedicion.minusHours(1))
                    .getPresion();
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion).getPresion() - anterior;
        return (diferencia <= -LIM_CLIMA_INTENSO);
    }

    public boolean borrascaSuave() {
        Double anterior;
        if (historial.containsKey(ultimaMedicion.minusDays(1))) {
            anterior = historial.get(ultimaMedicion.minusDays(1)).getPresion();
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion).getPresion() - anterior;
        return (-LIM_INF_CLIMA_SUAVE <= diferencia
                && diferencia <= -LIM_SUP_CLIMA_SUAVE);
    }

    public boolean anticiclonIntenso() {
        Double anterior;
        if (historial.containsKey(ultimaMedicion.minusHours(1))) {
            anterior = historial.get(ultimaMedicion.minusHours(1)).getPresion();
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion).getPresion() - anterior;
        return (diferencia >= LIM_CLIMA_INTENSO);
    }

    public boolean anticiclonSuave() {
        Double anterior;
        if (historial.containsKey(ultimaMedicion.minusDays(1))) {
            anterior = historial.get(ultimaMedicion.minusDays(1)).getPresion();
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion).getPresion() - anterior;
        return (LIM_INF_CLIMA_SUAVE <= diferencia && diferencia <= LIM_SUP_CLIMA_SUAVE);
    }

    // Métodos estaticos
    /**
     *
     * @param modelo
     * @param rutaJSON
     */
    public static void guardarModelo(Modelo modelo, String rutaJSON) {
        Gson gson = new GsonBuilder().registerTypeAdapter(
                LocalDateTime.class, new LocalDateTimeAdaptador()).
                setPrettyPrinting().create();
        String json = gson.toJson(modelo);
        try (FileWriter writer = new FileWriter(rutaJSON)) {
            writer.write(json);
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
    public static Modelo cargarModelo(String rutaJSON) {
        // Creamos un gson con el adaptador de tipo para la clase LocalDateTime
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class,
                        new LocalDateTimeAdaptador())
                .create();
        //Inicializamos un objeto a devolver
        Modelo devolverM;
        try (FileReader lector = new FileReader(rutaJSON)) {
            /* Si no encuentra problemas leemos el fichero y lo pasamos a
                hashMap*/

            devolverM = gson.fromJson(lector, Modelo.class);
        } catch (IOException e) {
            //Si no encuentran ningún fichero es porque todavía no se ha creado
            e.printStackTrace();
            devolverM = new Modelo();
        }

        return devolverM;
    }
}
