    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.controller;

import br.edu.ifsc.domain.Calculadora;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

    /**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLCalculadoraController implements Initializable {

    @FXML
    private TextField tfValor1;
    @FXML
    private TextField tfValor2;
    @FXML
    private TextField tfTotal;
    @FXML
    private Button btSomar;
    @FXML
    private Button btClear;
    @FXML
    private Button btSubtrair;

    @FXML
    private void actionBtSomar() {
        int valor1 = Integer.parseInt(tfValor1.getText());
        int valor2 = Integer.parseInt(tfValor2.getText());
        int total = Calculadora.somar(valor1, valor2);
        tfTotal.setText(Integer.toString(total));
    }

    @FXML
    private void actionBtClear() {
        tfValor1.setText("");
        tfValor2.setText("");
        tfTotal.setText("");
        tfValor1.requestFocus();
    }

    @FXML
    private void actionBtSubtrair() {
        tfTotal.setText(Integer.toString(
                Calculadora.subtrair(
                        Integer.parseInt(tfValor1.getText()), Integer.parseInt(tfValor2.getText()))));
    }    

    
   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

 

}
