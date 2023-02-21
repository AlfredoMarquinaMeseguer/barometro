/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import java.time.LocalDateTime;

/**
 * Clase medicion
 *
 * @author Alfredo Marquina Meseguer
 */
public class Medicion implements Comparable {
    
    

    LocalDateTime tiempo;
    Double presion;

    public Medicion() {
    }

    public Medicion(LocalDateTime tiempo, Double presion) {
        this.tiempo = tiempo;
        this.presion = presion;
    }

    public LocalDateTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalDateTime timepo) {
        this.tiempo = timepo;
    }

    public Double getPresion() {
        return presion;
    }

    public void setPresion(Double presion) {
        this.presion = presion;
    }

    @Override
    public int compareTo(Object o) {
        int devolver = 0;
        if (this.tiempo.isBefore(((Medicion) o).getTiempo())) {
            devolver = -1;
        } else if (this.tiempo.isAfter(((Medicion) o).getTiempo())) {
            devolver = 1;
        }
        return devolver;
    }

    @Override
    public String toString() {
        return tiempo.getDayOfMonth() + "/" + tiempo.getMonth() + "/"
                + tiempo.getYear() + "-" + tiempo.getHour() + ":"
                + tiempo.getMinute() + "-" + presion;
    }

}
