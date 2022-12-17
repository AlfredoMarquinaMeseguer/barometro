/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alfmarmes.javafxtest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import javafx.util.converter.LocalDateTimeStringConverter;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Alfre
 */
public class Controlador {

    private static final String ruta = "C:\\Users\\Alfre\\Documents\\"
            + "NetBeansProjects\\barometro\\src\\main\\resources\\"
            + "es\\alfmarmes\\file.json";
    String rutaIcono;
    public HashMap<LocalDateTime, Double> hashMap;

    public Controlador() {
        hashMap = cargarHistorial();
    }

    public static void main(String args[]) {
        Controlador ctrl = new Controlador();
        System.out.println("" + ctrl.hashMap);
    }

    public void guardarHistorial() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(hashMap);
        Path path = Paths.get(ruta);
        try ( FileWriter writer = new FileWriter(path.toString())) {
            writer.write(json);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<LocalDateTime, Double> cargarHistorial() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        Path path = Paths.get(ruta);
        HashMap<LocalDateTime, Double> hashMap;
        try ( FileReader reader = new FileReader(path.toString())) {
            hashMap = gson.fromJson(reader,
                    new TypeToken<HashMap<LocalDateTime, Double>>() {
                    }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            hashMap = new HashMap<>();
        }
        return hashMap;
    }

    public double calibrar(int altura) {

        return 0.0;
    }

    public void annadirMedida(LocalDateTime tiempo, double medida) {

    }

    public String actualizarIcono() {

        return "ruta";
    }

    /*
    Devuelve todo el contenido del HashMap de mediciones con un objeto que es la asociación de la tabla de dispersión
     */
    public ArrayList<Medicion> historialCompleto() {
        return null;
    }

}
