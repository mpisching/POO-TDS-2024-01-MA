package ifsc.poo.lavacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import atlantafx.base.theme.*;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        // Temas: https://mkpaz.github.io/atlantafx/#themes 
        Application.setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());
        
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLVBoxMain.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Sistema de Vendas (JavaFX MVC)");
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

