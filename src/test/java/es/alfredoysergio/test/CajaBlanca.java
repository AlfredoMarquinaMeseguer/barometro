/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.test;

import es.alfredoysergio.barometros.Modelo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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
public class CajaBlanca {

    //doReturn(X).when(spy).method(any())
    Modelo testModelo = new Modelo();
    Modelo spy;
    @Test
    public void testMockito() {
        //Modelo testModelo = new Modelo();        
        Modelo testModelo = mock(Modelo.class);
        Modelo testModelo2 = new Modelo();
        Modelo spy = Mockito.spy(testModelo2);
//        Mockito.doReturn(new Modelo().obtenerTiempo()).when(testModelo).obtenerTiempo();
        Mockito.doReturn(true).when(spy).borrascaIntensa();
//        Mockito.doReturn(false).when(testModelo).borrascaLejos();
//        Mockito.doReturn(false).when(testModelo).anticiclonIntenso();
//        Mockito.doReturn(false).when(testModelo).anticiclonEntreBorrascas();
        assertEquals(Modelo.Tiempo.BORRASCA_INTENSA, spy.obtenerTiempo());
    }

    @Cuando("borrascaIntensa devuelve True")
    public void borrasca_intensa_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
         Mockito.doReturn(true).when(spy).borrascaIntensa();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.BORRASCA_INTENSA")
    public void obtener_medicion_devuelve_tiempo_borrasca_intensa() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Cuando("borrascaLejos devuelve True")
    public void borrasca_lejos_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(spy).borrascaIntensa();
        Mockito.doReturn(true).when(spy).borrascaLejos();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.BORRASCA_SUAVE")
    public void obtener_medicion_devuelve_tiempo_borrasca_suave() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Cuando("anticiclonIntenso devuelve True")
    public void anticiclon_intenso_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(spy).borrascaIntensa();
        Mockito.doReturn(false).when(spy).borrascaLejos();
        Mockito.doReturn(true).when(spy).anticiclonIntenso();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.ANTICICLON_INTENSO")
    public void obtener_medicion_devuelve_tiempo_anticiclon_intenso() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Cuando("anticiclonEntreBorrascas devuelve True")
    public void anticiclon_entre_borrascas_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(spy).borrascaIntensa();
        Mockito.doReturn(false).when(spy).borrascaLejos();
        Mockito.doReturn(false).when(spy).anticiclonIntenso();
        Mockito.doReturn(true).when(spy).anticiclonIntenso();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.ANTICICLON_SUAVE")
    public void obtener_medicion_devuelve_tiempo_anticiclon_suave() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Cuando("Todas las comprobaviones son falsas")
    public void todas_las_comprobaviones_son_falsas() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.INSUFICIENTE")
    public void obtener_medicion_devuelve_tiempo_insuficiente() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
