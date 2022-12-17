/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfmarmes.javafxtest;

import java.time.LocalDateTime;

/**
 *
 * @author Alfre
 */
class Medicion {

    LocalDateTime timepo;
    Double presion;

    public Medicion() {
    }

    public Medicion(LocalDateTime timepo, Double presion) {
        this.timepo = timepo;
        this.presion = presion;
    }

    public LocalDateTime getTimepo() {
        return timepo;
    }

    public void setTimepo(LocalDateTime timepo) {
        this.timepo = timepo;
    }

    public Double getPresion() {
        return presion;
    }

    public void setPresion(Double presion) {
        this.presion = presion;
    }

}
