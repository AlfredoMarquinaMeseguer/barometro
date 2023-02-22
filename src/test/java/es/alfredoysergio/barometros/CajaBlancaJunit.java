/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Alfre
 */
public class CajaBlancaJunit {

    Modelo espia = Mockito.spy(new Modelo());

    @Test
    public void testBorrascaIntensa() {
        Mockito.doReturn(true).when(espia).borrascaIntensa();
        assertEquals(Modelo.Tiempo.BORRASCA_INTENSA, espia.obtenerTiempo());
    }

    @Test
    public void testBorrascaSuave() {
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(true).when(espia).borrascaLejos();
        assertEquals(Modelo.Tiempo.BORRASCA_SUAVE, espia.obtenerTiempo());
    }

    @Test
    public void testAnticiclonIntenso() {
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(false).when(espia).borrascaLejos();
        Mockito.doReturn(true).when(espia).anticiclonIntenso();
        assertEquals(Modelo.Tiempo.ANTICICLON_INTENSO, espia.obtenerTiempo());
    }

    @Test
    public void testAnticiclonSuave() {
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(false).when(espia).borrascaLejos();
        Mockito.doReturn(false).when(espia).anticiclonIntenso();
        Mockito.doReturn(true).when(espia).anticiclonEntreBorrascas();
        assertEquals(Modelo.Tiempo.ANTICICLON_SUAVE, espia.obtenerTiempo());
    }

    @Test
    public void testInsuficiente() {
        Mockito.doReturn(false).when(espia).borrascaIntensa();
        Mockito.doReturn(false).when(espia).borrascaLejos();
        Mockito.doReturn(false).when(espia).anticiclonIntenso();
        Mockito.doReturn(false).when(espia).anticiclonEntreBorrascas();
        assertEquals(Modelo.Tiempo.INSUFICIENTE, espia.obtenerTiempo());
    }

}
