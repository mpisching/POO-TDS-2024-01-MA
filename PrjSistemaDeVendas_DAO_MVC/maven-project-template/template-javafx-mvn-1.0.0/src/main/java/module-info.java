module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires java.sql;
    requires jasperreports;
    opens br.edu.ifsc.fln to javafx.fxml;
    opens br.edu.ifsc.fln.controller to javafx.fxml;
    opens br.edu.ifsc.fln.model.domain to javafx.base;
    exports br.edu.ifsc.fln;
}