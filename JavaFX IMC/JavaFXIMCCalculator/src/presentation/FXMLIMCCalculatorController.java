/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.IMCCalulator;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLIMCCalculatorController implements Initializable {

    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfPeso;
    @FXML
    private TextField tfAltura;
    @FXML
    private Button btCalcular;
    @FXML
    private Button btLimpar;
    @FXML
    private TextArea taResultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBtCalcular(ActionEvent event) {
        String nome = tfNome.getText();
        float peso = Float.parseFloat(tfPeso.getText());
        float altura = Float.parseFloat(tfAltura.getText());
        IMCCalulator imc = new IMCCalulator(nome, peso, altura);
        taResultado.setText(imc.getDados());
    }

    @FXML
    private void handleBtLimpar(ActionEvent event) {
        tfNome.setText("");
        tfPeso.setText("");
        tfAltura.setText("");
        taResultado.setText("");
        tfNome.requestFocus();
    }
    
}
