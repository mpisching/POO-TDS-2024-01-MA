module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    opens br.edu.ifsc to javafx.fxml;
    opens br.edu.ifsc.controller to javafx.fxml;
    exports br.edu.ifsc;
}