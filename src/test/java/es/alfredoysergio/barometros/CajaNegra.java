/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import es.alfredoysergio.barometros.Medicion;
import es.alfredoysergio.barometros.Modelo;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Alfre
 */
public class CajaNegra {

    LocalDateTime tiempoReferencia = LocalDateTime.now();
    double presionReferencia = 760;

    Modelo modeloPrueba;

    public void crearModelo(double cambio, ChronoUnit unidad) {
        HashMap<LocalDateTime, Medicion> historial = new HashMap<>();
        historial.put(tiempoReferencia, new Medicion(tiempoReferencia,
                presionReferencia+ cambio/10));// Temporal

        historial.put(tiempoReferencia.minus(1, unidad),
                new Medicion(tiempoReferencia.minus(1,
                        unidad),
                        presionReferencia));
//        System.out.println("" + (presionReferencia + cambio));
        modeloPrueba = new Modelo(historial, presionReferencia);
    }

    @Cuando("la medicion de hace una hora es {double} mmHg menor que la mas reciente")
    public void la_medicion_de_hace_una_hora_es_mm_hg_menor_que_la_mas_reciente(Double double1) {
        //Write code here that turns the phrase above into concrete actions
        crearModelo(-double1, ChronoUnit.HOURS);
    }

    @Cuando("la medicion de hace una hora es {double} mmHg mayor que la mas reciente")
    public void la_medicion_de_hace_una_hora_es_mm_hg_mayor_que_la_mas_reciente(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        crearModelo(double1, ChronoUnit.HOURS);
    }

    @Cuando("la medicion de hace un dia es {double} mmHg menor que la mas reciente")
    public void la_medicion_de_hace_un_dia_es_mm_hg_menor_que_la_mas_reciente(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        crearModelo(-double1, ChronoUnit.DAYS);
    }

    @Cuando("la medicion de hace un dia es {double} mmHg mayor que la mas reciente")
    public void la_medicion_de_hace_un_dia_es_mm_hg_mayor_que_la_mas_reciente(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        crearModelo(double1, ChronoUnit.DAYS);
    }

    @Entonces("se acerca una borrasca intensa")
    public void se_acerca_una_borrasca_intensa() {
//         Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.BORRASCA_INTENSA, modeloPrueba.obtenerTiempo());
    }

    @Entonces("NO se acerca una borrasca intensa ni un anticiclon intenso")
    public void no_se_acerca_una_borrasca_intensa_ni_un_anticiclon_intenso() {
        // Write code here that turns the phrase above into concrete actions
        assertNotEquals(Modelo.Tiempo.BORRASCA_INTENSA,
                modeloPrueba.obtenerTiempo());
        assertNotEquals(Modelo.Tiempo.ANTICICLON_INTENSO,
                modeloPrueba.obtenerTiempo());
    }

    @Entonces("se acerca un anticiclon intenso")
    public void se_acerca_un_anticiclon_intenso() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.ANTICICLON_INTENSO,
                modeloPrueba.obtenerTiempo());
    }

    @Entonces("NO se acerca una borrasca suave")
    public void no_se_acerca_una_borrasca_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertNotEquals(Modelo.Tiempo.BORRASCA_SUAVE,
                modeloPrueba.obtenerTiempo());
    }

    @Entonces("se acerca una borrasca suave")
    public void se_acerca_una_borrasca_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.BORRASCA_SUAVE,
                modeloPrueba.obtenerTiempo());
    }

    @Entonces("NO se acerca una borrasca suave ni un anticiclon suave")
    public void no_se_acerca_una_borrasca_suave_ni_un_anticiclon_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertNotEquals(Modelo.Tiempo.BORRASCA_SUAVE,
                modeloPrueba.obtenerTiempo());
        assertNotEquals(Modelo.Tiempo.ANTICICLON_SUAVE,
                modeloPrueba.obtenerTiempo());
    }

    @Entonces("NO se acerca un anticiclon suave")
    public void no_se_acerca_un_anticiclon_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertNotEquals(Modelo.Tiempo.ANTICICLON_SUAVE,
                modeloPrueba.obtenerTiempo());
    }

    @Entonces("se acerca un anticiclon suave")
    public void se_acerca_un_anticiclon_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.ANTICICLON_SUAVE,
                modeloPrueba.obtenerTiempo());
    }
}
