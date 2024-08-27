/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.EstoqueDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.ESituacao;
import br.edu.ifsc.fln.model.domain.Estoque;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAnchorPaneProcessoEstoqueAtualizacaoController implements Initializable {


    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfQtdMaxima;

    @FXML
    private TextField tfQtdMinima;

    
    @FXML
    private ChoiceBox<ESituacao> cbSituacao;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;
    
    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Estoque estoque;  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarChoiceBoxSituacao();
        setFocusLostHandle();
    } 
    
    private void setFocusLostHandle() {
        tfNome.focusedProperty().addListener((ov, oldV, newV) -> {
        if (!newV) { // focus lost
                if (tfNome.getText() == null || tfNome.getText().isEmpty()) {
                    //System.out.println("teste focus lost");
                    tfNome.requestFocus();
                }
            }
        });
    }
    
    public void carregarChoiceBoxSituacao() {
        cbSituacao.setItems( FXCollections.observableArrayList( ESituacao.values()));
        //cbSituacao.getSelectionModel().select(-1);
    }    
    
    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the buttonConfirmarClicked
     */
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    /**
     * @param buttonConfirmarClicked the buttonConfirmarClicked to set
     */
    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    /**
     * @return the estoque
     */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
        tfNome.setText(estoque.getProduto().getNome());
        tfQtdMinima.setText(Integer.toString(estoque.getQtdMinima()));
        tfQtdMaxima.setText(Integer.toString(estoque.getQtdMaxima()));
        cbSituacao.getSelectionModel().select(estoque.getSituacao());
    }    
    
    @FXML
    private void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            estoque.setQtdMinima(Integer.parseInt(tfQtdMinima.getText()));
            estoque.setQtdMaxima(Integer.parseInt(tfQtdMaxima.getText()));
            estoque.setSituacao(cbSituacao.getSelectionModel().getSelectedItem());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void handleBtCancelar() {
        dialogStage.close();
    }
    
        //validar entrada de dados do cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        int qtdMaxima = 0, qtdMinima = 0;
        
        try {
            qtdMaxima = Integer.parseInt(tfQtdMaxima.getText());
            qtdMinima = Integer.parseInt(tfQtdMinima.getText());
            if (qtdMinima >= qtdMaxima) {
                errorMessage += "A quantidade máxima informada deve ser maior que a mínima.\nCorrija!";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Certifique-se de que a quantidade mínima ou máxima foram digitadas.";
        }
        
        if (cbSituacao.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Selecione uma situação para o estoque!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro na atualização do estoque");
            alert.setHeaderText("Campo(s) inválido(s), por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
   
}
