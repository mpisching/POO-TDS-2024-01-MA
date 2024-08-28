/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.EstoqueDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Produto;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAnchorPaneProcessoEstoqueController implements Initializable {

    @FXML
    private Button btRepor;

    @FXML
    private Button btRetirar;

    @FXML
    private Button btAtualizar;

    @FXML
    private Label lbProdutoDescricao;

    @FXML
    private Label lbProdutoId;

    @FXML
    private Label lbProdutoNome;

    @FXML
    private Label lbProdutoPreco;

    @FXML
    private Label lbProdutoQtdMaxima;

    @FXML
    private Label lbProdutoQtdMinima;

    @FXML
    private Label lbProdutoQuantidade;

    @FXML
    private Label lbProdutoSituacao;

    @FXML
    private TableColumn<Produto, String> tableColumnNome;

    @FXML
    private TableColumn<Produto, Produto> tableColumnQuantidade;

    @FXML
    private TableView<Produto> tableView;


    private List<Produto> listaProdutos;
    private ObservableList<Produto> observableListProdutos;

    //acesso ao banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estoqueDAO.setConnection(connection);

        carregarTableView();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableView(newValue));

    }

    public void carregarTableView() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        
        listaProdutos = estoqueDAO.listar();
        
        observableListProdutos = FXCollections.observableArrayList(listaProdutos);
        tableView.setItems(observableListProdutos);
    }
    
    public void selecionarItemTableView(Produto produto) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (produto != null) {
            lbProdutoId.setText(Integer.toString(produto.getId()));
            lbProdutoNome.setText(produto.getNome());
            lbProdutoDescricao.setText(produto.getDescricao());
            lbProdutoPreco.setText(df.format(produto.getPreco().doubleValue()));
            lbProdutoQuantidade.setText(Integer.toString(produto.getEstoque().getQuantidade()));
            lbProdutoQtdMinima.setText(Integer.toString(produto.getEstoque().getQtdMinima()));
            lbProdutoQtdMaxima.setText(Integer.toString(produto.getEstoque().getQtdMaxima()));
            lbProdutoSituacao.setText(produto.getEstoque().getSituacao().getDescricao());
        } else {
            lbProdutoId.setText("");
            lbProdutoNome.setText("");
            lbProdutoDescricao.setText("");
            lbProdutoPreco.setText("");
            lbProdutoQuantidade.setText("");
            lbProdutoQtdMinima.setText("");
            lbProdutoQtdMaxima.setText("");
            lbProdutoSituacao.setText("");
        }
    }
    

    @FXML
    public void handleBtRepor() throws IOException {
        Produto produto = tableView.getSelectionModel().getSelectedItem();
        if (produto != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessoEstoqueMovimentacaoDialog(produto, "Repor");
            if (buttonConfirmarClicked) {
                estoqueDAO.atualizar(produto.getEstoque());
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na tabela ao lado.");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtRetirar() throws IOException {
        Produto produto = tableView.getSelectionModel().getSelectedItem();
        if (produto != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessoEstoqueMovimentacaoDialog(produto, "Retirar");
            if (buttonConfirmarClicked) {
                estoqueDAO.atualizar(produto.getEstoque());
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na tabela ao lado.");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtAtualizar() throws IOException {
        Produto produto = tableView.getSelectionModel().getSelectedItem();
        if (produto != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneProcessoEstoqueAtualizacaoDialog(produto);
            if (buttonConfirmarClicked) {
                estoqueDAO.atualizar(produto.getEstoque());
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela.");
            alert.show();
        }    
    }
    
    public boolean showFXMLAnchorPaneProcessoEstoqueAtualizacaoDialog(Produto produto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroProdutoDialogController.class.getResource( 
            "/view/FXMLAnchorPaneProcessoEstoqueAtualizacaoDialog.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        
        //criando um estágio de diálogo  (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Atualização do Estoque");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o produto ao controller
        FXMLAnchorPaneProcessoEstoqueAtualizacaoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEstoque(produto.getEstoque());
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }

    public boolean showFXMLAnchorPaneProcessoEstoqueMovimentacaoDialog(Produto produto, String tipoMovimento) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneProcessoEstoqueMovimentoDialogController.class.getResource( 
            "/view/FXMLAnchorPaneProcessoEstoqueMovimentoDialog.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        
        //criando um estágio de diálogo  (Stage Dialog)
        Stage dialogStage = new Stage();
        if (tipoMovimento.equalsIgnoreCase("Repor")) {
            dialogStage.setTitle("Movimentação de Reposição: " + produto.getNome());
        } else {
            dialogStage.setTitle("Movimentação de Retirada: " + produto.getNome());
        }
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o produto ao controller
        FXMLAnchorPaneProcessoEstoqueMovimentoDialogController controller = loader.getController();
        controller.setTipoMovimento(tipoMovimento);
        controller.setDialogStage(dialogStage);
        controller.setEstoque(produto.getEstoque());
        
        
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
        
    }

}
