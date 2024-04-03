/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLCalculoIMCController implements Initializable {

    @FXML
    private TextField tfNome;
    @FXML
    private Spinner<Integer> spIdade;
    @FXML
    private TextField tfAltura;
    @FXML
    private TextField tfPeso;
    @FXML
    private ChoiceBox<String> cbSexo;
    @FXML
    private Button btCalcular;
    @FXML
    private Button btNovo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Definição das opções do objeto ChoiceBox
        cbSexo.getItems().add("Masculino");
        cbSexo.getItems().add("Feminino");
        //Definição dos valores do Spinner por meio de um SpinnerValueFactory
        //O primeiro e o segundo parâmetro é a faixa de valores, o terceiro, o valor padrão
        SpinnerValueFactory<Integer> valueFactory = 
                new SpinnerValueFactory.
                        IntegerSpinnerValueFactory(0, 150, 25);
        spIdade.setValueFactory(valueFactory);
    }    

    @FXML
    private void handleBtCalcular(ActionEvent event) {
        String sexo = 
                cbSexo.getSelectionModel().getSelectedItem();
        Integer idade = spIdade.getValue();
        float altura = Float.parseFloat(tfAltura.getText());
        float peso = Float.parseFloat(tfPeso.getText());
        
        float imc = calcularIMC(peso, altura);
        String classificacao = classificarIMC(imc);
        
        apresentarResultado(sexo, idade, altura, peso, imc, classificacao);
    }

    @FXML
    private void handleBtNovo(ActionEvent event) {
        tfNome.setText(null);
        tfAltura.setText(null);
        tfPeso.setText(null);
        spIdade.getValueFactory().setValue(25);
        cbSexo.getSelectionModel().clearAndSelect(-1);
        tfNome.requestFocus();        
    }
    
    private float calcularIMC(float peso, float altura) {
        return (float) (peso / (Math.pow(altura, 2)));
    }
    
    private String classificarIMC(float imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else if (imc < 34.9) {
            return "Obesidade";
        } else 
            return "Obesidade Grave";
    }    

    private void apresentarResultado(String sexo, Integer idade, float altura, float peso, float imc, String classificacao) {
        Alert dlgResultado = new Alert(Alert.AlertType.INFORMATION);
        dlgResultado.setTitle("IMC Calculado...");
        dlgResultado.setHeaderText("Resumo do IMC do(a) " + tfNome.getText());
        dlgResultado.setContentText(
                "Sexo: " + sexo + "\n" +
                "idade: " + idade + "\n" +
                "Altura: " + altura + "\n" +
                "Peso:" + peso + "\n" +
                "IMC calculado: " + imc + "\n" +
                "Classificação: " + classificacao + "\n ") ;
        dlgResultado.showAndWait();        
    }
    
}
