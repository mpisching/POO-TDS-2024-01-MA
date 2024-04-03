/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.Utils;

/**
 *
 * @author mpisc
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/FXMLCalculoIMC.fxml"));
            
            Scene scene = new Scene(root, 379, 250);
            
            //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../icons/IFSC_logo_vertical.png")));
            primaryStage.getIcons().add(Utils.APPLICATION_ICON);
            primaryStage.setTitle("Calculadora de IMC - IFSC");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
