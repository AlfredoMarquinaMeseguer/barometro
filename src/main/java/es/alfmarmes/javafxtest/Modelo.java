/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfmarmes.javafxtest;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private HashMap<LocalDateTime, Double> historial;
    /**
     * Fecha de la medición con la fecha más tardía
     */
    private LocalDateTime ultimaMedicion;
    /**
     * Presion de referencia, en mmHg
     */
    private Double presionReferencia;

    // Constructores
    public Modelo(HashMap<LocalDateTime, Double> historial, Double presionReferencia) {
        this.historial = historial;
        this.presionReferencia = presionReferencia;

        //Cogemos todas los tiempos de medición
        Set<LocalDateTime> mediciones = (Set) historial.keySet();

        //Consideramos que la primera es la última
        this.ultimaMedicion = LocalDateTime.MIN;

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
    }

    // Setter y Getters
    /**
     * Getter del historial de la clase Modelo
     *
     * @return historial
     */
    public HashMap<LocalDateTime, Double> getHistorial() {
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

    // Métodos Públicos
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
        historial.put(tiempo, presion);
    }

    /**
     * Consulta el historial y devuelve una preción del tiempo
     *
     * @return prediccion de tiempo
     */
    public Tiempo obtenerTiempo() {
        Tiempo devolver;
        if (borrascaIntensa()) {
            devolver = Tiempo.BORRASCA_PROFUNDA;
        } else if (borrascaLejos()) {
            devolver = Tiempo.BORRASCA_LEJOS;
        } else if (anticiclonIntenso()) {
            devolver = Tiempo.ANTICICLON;
        } else if (anticiclonEntreBorrascas()) {
            devolver = Tiempo.ANTICICLON_ENTRE_BORRASCAS;
        } else {
            devolver = Tiempo.ANTICICLON;
        }
        return devolver;
    }

    // Métodos Privados
    /**
     * Comproueba
     *
     * @return
     */
    private boolean borrascaIntensa() {
        Double a;
        if (historial.containsKey(ultimaMedicion.minusHours(1))) {
            a = historial.get(ultimaMedicion.minusHours(1));
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion) - a;
        return (diferencia <= -1);
    }

    private boolean borrascaLejos() {
        Double a;
        if (historial.containsKey(ultimaMedicion.minusDays(1))) {
            a = historial.get(ultimaMedicion.minusDays(1));
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion) - a;
        return (-6.5 <= diferencia && diferencia <= -5.5);
    }

    private boolean anticiclonIntenso() {
        Double a;
        if (historial.containsKey(ultimaMedicion.minusHours(1))) {
            a = historial.get(ultimaMedicion.minusHours(1));
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion) - a;
        return (diferencia >= 1);
    }

    private boolean anticiclonEntreBorrascas() {
        Double a;
        if (historial.containsKey(ultimaMedicion.minusDays(1))) {
            a = historial.get(ultimaMedicion.minusDays(1));
        } else {
            return false;
        }
        Double diferencia = historial.get(ultimaMedicion) - a;
        return (5.5 <= diferencia && diferencia <= 6.5);
    }
}
