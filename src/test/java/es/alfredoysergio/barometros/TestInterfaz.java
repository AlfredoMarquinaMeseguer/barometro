/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfredoysergio.barometros;

import es.alfredoysergio.barometros.Modelo.Tiempo;
import io.cucumber.core.stepexpression.ArgumentMatcher;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.hamcrest.Description;
import org.hamcrest.core.AnyOf;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit.TestFXRule;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;

/**
 *
 * @author Alfre
 */
public class TestInterfaz extends ApplicationTest {

    App app;
    Modelo modeloMock;
    Controlador controlador;
    
    
    private static String RAIZ_IMGS =  "images/";
    private static String BORRASCA_INTENSA_IMG =  "heavy-rain_1.png";
    private static String BORRASCA_SUAVE_IMG =  "rainy.png";
    private static String ANTICICLON_INTENSO_IMG =  "sunny.png";
    private static String ANTICICLON_SUAVE_IMG = "cloudy.png";
    private static String INSUFICIENTE_IMG = "error.png";

    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        modeloMock = Mockito.mock(Modelo.class);
        app.start(stage);
        app.getControlador().setModelo(modeloMock);
        controlador = app.getControlador();
        Mockito.doNothing().when(modeloMock).annadirMedicion
        (ArgumentMatchers.any(LocalDateTime.class),
                ArgumentMatchers.anyDouble());
    }

    public void actualizarIcono() {
        // Rellenamos los campos que usa la función que cambia la el icono de ls
        
        controlador.getBtmmhg().setSelected(true);
        controlador.getTfPresion().setText("0");
        controlador.getDatePickerFecha().setValue(LocalDate.now());
        // Sp Hora no es necesario actualizar porque ya tiene valor por defecto

        // En try catch porque lanza excepción
        try {
            controlador.actualizar(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void verificarIcono(String nombreIcono) {
        actualizarIcono();
        verifyThat("#imageViewIcono", (ImageView imageView) -> {
            Image iconoMostrado = imageView.getImage();
            Image iconoTest = new Image(RAIZ_IMGS+nombreIcono);
            return Objects.equals(iconoMostrado.getUrl(), iconoTest.getUrl());
        });
    }
    
    private void cambiarReturnMock(Tiempo t){
        Mockito.doReturn(t).when(modeloMock).obtenerTiempo();
    }
    
    @Test
    public void testBorrascaIntensa(){
        cambiarReturnMock(Tiempo.BORRASCA_INTENSA);
        verificarIcono(BORRASCA_INTENSA_IMG);
    }
    
    @Test
    public void testBorrascaSuave(){
        cambiarReturnMock(Tiempo.BORRASCA_SUAVE);
        verificarIcono(BORRASCA_SUAVE_IMG);
    }
    
    @Test
    public void testAnticiclonIntenso(){
        cambiarReturnMock(Tiempo.ANTICICLON_INTENSO);
        verificarIcono(ANTICICLON_INTENSO_IMG);
    }
    
    @Test
    public void testAnticiclonSuave(){
        cambiarReturnMock(Tiempo.ANTICICLON_SUAVE);
        verificarIcono(ANTICICLON_SUAVE_IMG);
    }
    
    @Test
    public void testInsuficiente(){
        cambiarReturnMock(Tiempo.INSUFICIENTE);
        verificarIcono(INSUFICIENTE_IMG);
    }
    
    
    /*
    * Los test realizados con cucumber no activan testfx por lo que no funcionan.
    * Aunque son practicamente idénticos a los anteriores. 
    */
    
//    @Cuando("el barometro detecta una borrasca intensa")
//    public void el_barometro_detecta_una_borrasca_intensa() {
//        // Write code here that turns the phrase above into concrete actions
//        cambiarReturnMock(Tiempo.BORRASCA_INTENSA);
//    }
//
//    @Entonces("la interfaz muestra el icono de borrasca intensa")
//    public void la_interfaz_muestra_el_icono_de_borrasca_intensa() {        
//        verificarIcono(BORRASCA_INTENSA_IMG);
//    }
//
//    @Cuando("el barometro preve una borrasca suave")
//    public void el_barometro_preve_una_borrasca_suave() {
//        // Write code here that turns the phrase above into concrete actions
//        cambiarReturnMock(Tiempo.BORRASCA_SUAVE);
//    }
//
//    @Entonces("la interfaz muestra el icono de borrasca suave")
//    public void la_interfaz_muestra_el_icono_de_borrasca_suave() {
//        verificarIcono(BORRASCA_SUAVE_IMG);
//    }
//
//    @Cuando("el barometro preve un anticiclon intenso acercandose")
//    public void el_barometro_preve_un_anticiclon_intenso_acercandose() {
//        // Write code here that turns the phrase above into concrete actions
//        cambiarReturnMock(Tiempo.ANTICICLON_INTENSO);
//    }
//
//    @Entonces("la interfaz muestra el icono de anticiclon intenso")
//    public void la_interfaz_muestra_el_icono_de_anticiclon_intenso() {
//        verificarIcono(ANTICICLON_INTENSO_IMG);
//    }
//
//    @Cuando("el barometro preve un anticiclon suave")
//    public void el_barometro_preve_un_anticiclon_suave() {
//        cambiarReturnMock(Tiempo.ANTICICLON_SUAVE);
//    }
//
//    @Entonces("la interfaz muestra el icono de anticiclon suave")
//    public void la_interfaz_muestra_el_icono_de_anticiclon_suave() {
//        verificarIcono(ANTICICLON_SUAVE_IMG);
//    }
//
//    @Cuando("no tiene suficiente informacion para prever el tiempo")
//    public void no_tiene_suficiente_informacion_para_prever_el_tiempo() {
//        cambiarReturnMock(Tiempo.INSUFICIENTE);
//    }
//
//    @Entonces("la interfaz muestra el icono de error")
//    public void la_interfaz_muestra_el_icono_de_error() {
//        verificarIcono(INSUFICIENTE_IMG);
//    }
}