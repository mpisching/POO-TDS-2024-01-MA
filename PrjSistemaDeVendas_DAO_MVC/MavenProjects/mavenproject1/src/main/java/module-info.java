module br.edu.fln.ifsc.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.edu.fln.ifsc.mavenproject1 to javafx.fxml;
    exports br.edu.fln.ifsc.mavenproject1;
}
