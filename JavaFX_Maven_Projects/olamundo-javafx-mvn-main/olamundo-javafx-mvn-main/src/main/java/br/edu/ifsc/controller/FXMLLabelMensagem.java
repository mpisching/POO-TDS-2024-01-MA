    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

    /**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLLabelMensagem implements Initializable {

    @FXML
    public Label mensagem;

    private final String fraseA = "Java é legal!";
    private final String fraseB = "E Python também!";

    private String fraseAtiva;

        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fraseAtiva = fraseA;
        this.mensagem.setText(fraseAtiva);
    }

    @FXML
    public void mudarFrase() {

        // Toggle 'fraseAtiva'
        if(fraseAtiva.equals(fraseA)) {
            fraseAtiva = fraseB;
        } else {
            fraseAtiva = fraseA;
        }

        this.mensagem.setText(fraseAtiva);

        // Com operador ternário
        // fraseAtiva = fraseAtiva.equals(fraseA) ? fraseB : fraseA;

    }

}
