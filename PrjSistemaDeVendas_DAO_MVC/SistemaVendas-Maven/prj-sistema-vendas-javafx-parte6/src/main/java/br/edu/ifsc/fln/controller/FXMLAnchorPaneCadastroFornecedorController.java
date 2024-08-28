/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.FornecedorDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Fornecedor;
import br.edu.ifsc.fln.model.domain.Internacional;
import br.edu.ifsc.fln.model.domain.Nacional;
import br.edu.ifsc.fln.utils.AlertDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLAnchorPaneCadastroFornecedorController implements Initializable {

    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btInserir;

    @FXML
    private Label lbFornecedorEmail;

    @FXML
    private Label lbFornecedorId;

    @FXML
    private Label lbFornecedorNome;

    @FXML
    private Label lbFornecedorNumFiscal;

    @FXML
    private Label lbFornecedorPais;

    @FXML
    private Label lbFornecedorFone;

    @FXML
    private Label lbFornecedorTipo;
    
    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorFone;

    @FXML
    private TableColumn<Fornecedor, String> tableColumnFornecedorNome;

    @FXML
    private TableView<Fornecedor> tableViewFornecedores;

    
    private List<Fornecedor> listaFornecedores;
    private ObservableList<Fornecedor> observableListFornecedores;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDAO.setConnection(connection);
        carregarTableViewFornecedor();
        
        tableViewFornecedores.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFornecedores(newValue));
    }     
    
    public void carregarTableViewFornecedor() {
        tableColumnFornecedorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnFornecedorFone.setCellValueFactory(new PropertyValueFactory<>("fone"));
        
        listaFornecedores = fornecedorDAO.listar();
        
        observableListFornecedores = FXCollections.observableArrayList(listaFornecedores);
        tableViewFornecedores.setItems(observableListFornecedores);
    }
    
    public void selecionarItemTableViewFornecedores(Fornecedor fornecedor) {
        if (fornecedor != null) {
            lbFornecedorId.setText(String.valueOf(fornecedor.getId())); 
            lbFornecedorNome.setText(fornecedor.getNome());
            lbFornecedorFone.setText(fornecedor.getFone());
            lbFornecedorEmail.setText(fornecedor.getEmail());
            if (fornecedor instanceof Nacional) {
                lbFornecedorTipo.setText("Nacional");
                lbFornecedorNumFiscal.setText(((Nacional)fornecedor).getCnpj());
                lbFornecedorPais.setText("Brasil");
            } else {
                lbFornecedorTipo.setText("Internacional");
                lbFornecedorNumFiscal.setText(((Internacional)fornecedor).getNif());
                lbFornecedorPais.setText(((Internacional)fornecedor).getPais());
            }
        } else {
            lbFornecedorId.setText(""); 
            lbFornecedorNome.setText("");
            lbFornecedorFone.setText("");
            lbFornecedorEmail.setText("");
            lbFornecedorTipo.setText("");
            lbFornecedorNumFiscal.setText("");
            lbFornecedorPais.setText("");
        }
        
    }
    
    @FXML
    public void handleBtInserir() throws IOException {
        Fornecedor fornecedor = getTipoFornecedor();
        if (fornecedor != null ) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroFornecedorDialog(fornecedor);
            if (btConfirmarClicked) {
                fornecedorDAO.inserir(fornecedor);
                carregarTableViewFornecedor();
            }
        }
    }
    
    private Fornecedor getTipoFornecedor() {
        List<String> opcoes = new ArrayList<>();
        opcoes.add("Nacional");
        opcoes.add("Internacional");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Nacional", opcoes);
        dialog.setTitle("Dialogo de Opções");
        dialog.setHeaderText("Escolha o tipo de fornecedor");
        dialog.setContentText("Tipo de fornecedor: ");
        Optional<String> escolha = dialog.showAndWait();
        if (escolha.isPresent()) {
            if (escolha.get().equalsIgnoreCase("Nacional")) 
                return new Nacional();
            else 
                return new Internacional();
        } else {
            return null;
        }
    }
    
    @FXML 
    public void handleBtAlterar() throws IOException {
        Fornecedor fornecedor = tableViewFornecedores.getSelectionModel().getSelectedItem();
        if (fornecedor != null) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroFornecedorDialog(fornecedor);
            if (btConfirmarClicked) {
                fornecedorDAO.alterar(fornecedor);
                carregarTableViewFornecedor();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde um Fornecedor na tabela ao lado");
            alert.show();
  }
    }
    
    @FXML
    public void handleBtExcluir() throws IOException {
        Fornecedor fornecedor = tableViewFornecedores.getSelectionModel().getSelectedItem();
        if (fornecedor != null) {
            if (AlertDialog.confirmarExclusao("Tem certeza que deseja excluir o fornecedor " + fornecedor.getNome())) {
                fornecedorDAO.remover(fornecedor);
                carregarTableViewFornecedor();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde uma Fornecedor na tabela ao lado");
            alert.show();
        }
    }

    private boolean showFXMLAnchorPaneCadastroFornecedorDialog(Fornecedor fornecedor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroFornecedorController.class.getResource("/view/FXMLAnchorPaneCadastroFornecedorDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //criação de um estágio de diálogo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Fornecedor");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //enviando o obejto fornecedor para o controller
        FXMLAnchorPaneCadastroFornecedorDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFornecedor(fornecedor);
        
        //apresenta o diálogo e aguarda a confirmação do usuário
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
    
}
