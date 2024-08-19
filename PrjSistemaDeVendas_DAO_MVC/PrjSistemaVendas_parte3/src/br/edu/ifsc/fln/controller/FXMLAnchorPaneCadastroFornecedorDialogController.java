/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Fornecedor;
import br.edu.ifsc.fln.model.domain.Internacional;
import br.edu.ifsc.fln.model.domain.Nacional;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLAnchorPaneCadastroFornecedorDialogController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private RadioButton rbInternacional;

    @FXML
    private RadioButton rbNacional;

    @FXML
    private Group gbTipo;

    @FXML
    private ToggleGroup tgTipo;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFone;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfNumFiscal;

    @FXML
    private TextField tfPais;

    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Fornecedor fornecedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;

            this.tfNome.setText(this.fornecedor.getNome());
            this.tfEmail.setText(this.fornecedor.getEmail());
            this.tfFone.setText(this.fornecedor.getFone());
            this.gbTipo.setDisable(true);
            if (fornecedor instanceof Nacional) {
                rbNacional.setSelected(true);
                tfNumFiscal.setText(((Nacional) this.fornecedor).getCnpj());
                tfPais.setText("BRASIL");
                tfPais.setDisable(true);
            } else {
                rbInternacional.setSelected(true);
                tfNumFiscal.setText(((Internacional) this.fornecedor).getNif());
                tfPais.setText(((Internacional) this.fornecedor).getPais());
                tfPais.setDisable(false);
            }
        this.tfNome.requestFocus();
    }

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            fornecedor.setNome(tfNome.getText());
            fornecedor.setEmail(tfEmail.getText());
            fornecedor.setFone(tfFone.getText());
            if (rbNacional.isSelected()) {
                ((Nacional) fornecedor).setCnpj(tfNumFiscal.getText());
            } else {
                ((Internacional) fornecedor).setNif(tfNumFiscal.getText());
                ((Internacional) fornecedor).setPais(tfPais.getText());
            }
            btConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleBtCancelar() {
        dialogStage.close();
    }

    @FXML
    public void handleRbNacional() {
        this.tfPais.setText("BRASIL");
        this.tfPais.setDisable(true);
    }

    @FXML
    public void handleRbInternacional() {
        this.tfPais.setText("");
        this.tfPais.setDisable(false);
    }

    //método para validar a entrada de dados
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (this.tfNome.getText() == null || this.tfNome.getText().length() == 0) {
            errorMessage += "Nome inválido.\n";
        }

        if (this.tfFone.getText() == null || this.tfFone.getText().length() == 0) {
            errorMessage += "Telefone inválido.\n";
        }

        if (this.tfEmail.getText() == null || this.tfEmail.getText().length() == 0 || !this.tfEmail.getText().contains("@")) {
            errorMessage += "Email inválido.\n";
        }

        if (rbNacional.isSelected()) {
            if (this.tfNumFiscal.getText() == null || this.tfNumFiscal.getText().length() == 0) {
                errorMessage += "CNPJ inválido.\n";
            }
        } else {
            if (this.tfNumFiscal.getText() == null || this.tfNumFiscal.getText().length() == 0) {
                errorMessage += "NIF inválido.\n";
            }
            if (this.tfPais.getText() == null || this.tfPais.getText().length() == 0) {
                errorMessage += "Informe o nome do País.\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            //exibindo uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Corrija os campos inválidos!");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
