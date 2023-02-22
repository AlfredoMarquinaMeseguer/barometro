/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import org.mockito.Mockito;
import es.alfredoysergio.barometros.Modelo;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Alfre
 */
public class CajaBlanca {

    //doReturn(X).when(espia).method(any())
    Modelo espia = Mockito.spy(new Modelo());

    // Ejemplo si solo se usa JUnitTest con Mockito
//    @Test
//    public void testMoquito() {
//        Modelo espia = Mockito.spy(new Modelo());
//        Mockito.doReturn(true).when(espia).borrascaIntensa();
////        Mockito.doReturn(false).when(espia).borrascaLejos();
////        Mockito.doReturn(false).when(espia).anticiclonIntenso();
////        Mockito.doReturn(false).when(espia).anticiclonEntreBorrascas();
//        assertEquals(Modelo.Tiempo.BORRASCA_INTENSA, espia.obtenerTiempo());
//    }

    /*
     * Camino 1: 1 – 2 – 3 - 11
     */
    @Cuando("borrascaIntensa devuelve True")
    public void borrasca_intensa_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(true).when(espia).borrascaIntensa();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.BORRASCA_INTENSA")
    public void obtener_medicion_devuelve_tiempo_borrasca_intensa() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.BORRASCA_INTENSA, espia.obtenerTiempo());
    }

    /*
     * Camino 2: 1 - 2 - 4 - 5 - 11
     */
    @Cuando("borrascaLejos devuelve True")
    public void borrasca_lejos_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(true).when(espia).borrascaLejos();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.BORRASCA_SUAVE")
    public void obtener_medicion_devuelve_tiempo_borrasca_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.BORRASCA_SUAVE, espia.obtenerTiempo());
    }

    /*
     * Camino 3: 1 - 2 - 4 - 6 - 7 - 11
     */
    @Cuando("anticiclonIntenso devuelve True")
    public void anticiclon_intenso_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(false).when(espia).borrascaLejos();
        Mockito.doReturn(true).when(espia).anticiclonIntenso();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.ANTICICLON_INTENSO")
    public void obtener_medicion_devuelve_tiempo_anticiclon_intenso() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.ANTICICLON_INTENSO, espia.obtenerTiempo());
    }

    /*
     * Camino 4: 1 - 2 - 4 - 6 - 8 - 9 - 11
     */
    @Cuando("anticiclonEntreBorrascas devuelve True")
    public void anticiclon_entre_borrascas_devuelve_true() {
        // Write code here that turns the phrase above into concrete actions
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(false).when(espia).borrascaLejos();
        Mockito.doReturn(false).when(espia).anticiclonIntenso();
        Mockito.doReturn(true).when(espia).anticiclonEntreBorrascas();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.ANTICICLON_SUAVE")
    public void obtener_medicion_devuelve_tiempo_anticiclon_suave() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(Modelo.Tiempo.ANTICICLON_SUAVE, espia.obtenerTiempo());
    }

    /*
     * Camino 5: 1 - 2 - 4 - 6 - 8 - 10 - 11
     */
    @Cuando("Todas las comprobaciones son falsas")
    public void todas_las_comprobaviones_son_falsas() {
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(false).when(espia).borrascaLejos();
        Mockito.doReturn(false).when(espia).anticiclonIntenso();
        Mockito.doReturn(false).when(espia).anticiclonEntreBorrascas();
    }

    @Entonces("obtenerMedicion devuelve Tiempo.INSUFICIENTE")
    public void obtener_medicion_devuelve_tiempo_insuficiente() {
        assertEquals(Modelo.Tiempo.INSUFICIENTE, espia.obtenerTiempo());
    }

}
