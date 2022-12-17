module es.alfmarmes.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens es.alfmarmes.javafxtest to javafx.fxml;
    exports es.alfmarmes.javafxtest;
}
