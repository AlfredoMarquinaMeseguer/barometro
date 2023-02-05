module es.alfredoysergio.barometros {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.base;
    requires org.controlsfx.controls;

   // opens javafx.graphics to org.controlsfx.controls;
    opens es.alfredoysergio.barometros to javafx.fxml,com.google.gson,org.controlsfx.controls;
    exports es.alfredoysergio.barometros;
}
