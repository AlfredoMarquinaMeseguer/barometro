/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfmarmes.javafxtest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 *
 * @author Alfre
 */
public class Modelo {

    HashMap<LocalDateTime, Double> historial;
    Double presionReferencia;
    Tiempo estado;

    //Constructores
    public Modelo(HashMap<LocalDateTime, Double> historial, Double presionReferencia) {
        //Crear y llenar HashMap
        historial = new HashMap<>();
        this.historial.putAll(historial);

        actualizarTiempo();
    }

    public HashMap<LocalDateTime, Double> getHistorial() {
        return historial;
    }

    public void setHistorial(HashMap<LocalDateTime, Double> historial) {
        this.historial = historial;
    }

    public Double getPresionReferencia() {
        return presionReferencia;
    }

    public void setPresionReferencia(Double presionReferencia) {
        this.presionReferencia = presionReferencia;
    }

    public Tiempo getEstado() {
        return estado;
    }

    public Tiempo actualizarHistorial(LocalDateTime tiempoMedicion, double presion) {
        historial.put(tiempoMedicion, presion);
        return actualizarTiempo();
    }

    private Tiempo actualizarTiempo() {
        Tiempo devolver = Tiempo.INSUFICIENTE;;
        if (borrascaIntensa()) {

        } else if (borrascaLejos()) {

        } else if (anticiclonIntenso()) {

        } else if (anticiclonEntrBorrascas()) {

        } else {

        }

        return devolver;
    }

    private boolean borrascaIntensa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean borrascaLejos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean anticiclonIntenso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean anticiclonEntrBorrascas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
