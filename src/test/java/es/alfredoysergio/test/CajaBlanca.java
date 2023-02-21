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
    Modelo spy = Mockito.spy(new Modelo());
//    @Test
//    public void testMockito() {
//        //Modelo testModelo = new Modelo();        
//        Modelo testModelo = mock(Modelo.class);
//        Modelo testModelo2 = new Modelo();
//        Modelo spy = Mockito.spy(testModelo2);
////        Mockito.doReturn(new Modelo().obtenerTiempo()).when(testModelo).obtenerTiempo();
//        Mockito.doReturn(true).when(spy).borrascaIntensa();
////        Mockito.doReturn(false).when(testModelo).borrascaLejos();
////        Mockito.doReturn(false).when(testModelo).anticiclonIntenso();
////        Mockito.doReturn(false).when(testModelo).anticiclonEntreBorrascas();
//        assertEquals(Modelo.Tiempo.BORRASCA_INTENSA, spy.obtenerTiempo());
//    }

    /*
     * Camino 1: 1 – 2 – 3 - 11
     */
    @Cuando("borrascaIntensa devuelve True")
    public void borrasca_intensa_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
         Mockito.doReturn(true).when(spy).borrascaIntensa();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.BORRASCA_INTENSA")
    public void obtener_medicion_devuelve_tiempo_borrasca_intensa() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.BORRASCA_INTENSA, spy.obtenerTiempo());
    }

    
    /*
     * Camino 2: 1 - 2 - 4 - 5 - 11
     */
    @Cuando("borrascaLejos devuelve True")
    public void borrasca_lejos_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(spy).borrascaIntensa();
        Mockito.doReturn(true).when(spy).borrascaLejos();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.BORRASCA_SUAVE")
    public void obtener_medicion_devuelve_tiempo_borrasca_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.BORRASCA_SUAVE, spy.obtenerTiempo());
    }

    /*
     * Camino 3: 1 - 2 - 4 - 6 - 7 - 11
     */
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
        assertEquals(Modelo.Tiempo.ANTICICLON_INTENSO, spy.obtenerTiempo());
    }

    /*
     * Camino 4: 1 - 2 - 4 - 6 - 8 - 9 - 11
     */
    @Cuando("anticiclonEntreBorrascas devuelve True")
    public void anticiclon_entre_borrascas_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(spy).borrascaIntensa();
        Mockito.doReturn(false).when(spy).borrascaLejos();
        Mockito.doReturn(false).when(spy).anticiclonIntenso();
        Mockito.doReturn(true).when(spy).anticiclonEntreBorrascas();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.ANTICICLON_SUAVE")
    public void obtener_medicion_devuelve_tiempo_anticiclon_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.ANTICICLON_SUAVE, spy.obtenerTiempo());
    }

    /*
     * Camino 5: 1 - 2 - 4 - 6 - 8 - 10 - 11
     */
    @Cuando("Todas las comprobaciones son falsas")
    public void todas_las_comprobaviones_son_falsas() {        
        Mockito.doReturn(false).when(spy).borrascaIntensa();
        Mockito.doReturn(false).when(spy).borrascaLejos();
        Mockito.doReturn(false).when(spy).anticiclonIntenso();
        Mockito.doReturn(false).when(spy).anticiclonEntreBorrascas();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.INSUFICIENTE")
    public void obtener_medicion_devuelve_tiempo_insuficiente() {
        assertEquals(Modelo.Tiempo.INSUFICIENTE, spy.obtenerTiempo());
    }

}
