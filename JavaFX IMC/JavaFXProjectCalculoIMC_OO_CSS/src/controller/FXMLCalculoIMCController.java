/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Imc;
import utils.Utils;

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
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 150, 25);
        spIdade.setValueFactory(valueFactory);
    }    

    @FXML
    private void handleBtCalcular(ActionEvent event) {
        String nome = tfNome.getText();
        String sexo = cbSexo.getSelectionModel().getSelectedItem();
        Integer idade = spIdade.getValue();
        float altura = Float.parseFloat(tfAltura.getText());
        float peso = Float.parseFloat(tfPeso.getText());
        Imc imc = new Imc(nome, sexo, idade, peso, altura);
        
        apresentarResultado(imc);
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

    private void apresentarResultado(Imc imc) {
        Alert dlgResultado = new Alert(Alert.AlertType.INFORMATION);
        dlgResultado.setTitle("IMC Calculado...");
        Stage dialogStage = (Stage) dlgResultado.getDialogPane().getScene().getWindow();
        dialogStage.getIcons().add(Utils.APPLICATION_ICON);
        //dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("../icons/IFSC_logo_vertical.png")));
        
        dlgResultado.setHeaderText(" Resumo do IMC do(a) " + imc.getNome());
        dlgResultado.getDialogPane().getStylesheets().add(getClass().getResource("/css/dialog_css.css").toExternalForm());
        dlgResultado.getDialogPane().getStyleClass().add("myDialog");
        dlgResultado.setContentText(imc.getDados());
        dlgResultado.showAndWait();        
    }
    
}
