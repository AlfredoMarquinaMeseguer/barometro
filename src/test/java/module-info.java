module es.alfredoysergio.barometros {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.base;
    requires org.controlsfx.controls;  
    requires java.prefs;
    requires io.cucumber.java;
    requires org.testfx;

    opens es.alfredoysergio.barometros to javafx.fxml,com.google.gson,
            org.controlsfx.controls, io.cucumber.java;
    exports es.alfredoysergio.barometros;
}
