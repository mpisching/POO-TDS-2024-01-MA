/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ProdutoDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Produto;
import java.io.IOException;
import java.math.BigDecimal;
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
public class FXMLAnchorPaneCadastroProdutoController implements Initializable {

    @FXML
    private TableView<Produto> tableView;

    @FXML
    private TableColumn<Produto, String> tableColumnNome;

    @FXML
    private TableColumn<Produto, BigDecimal> tableColumnPreco;

    @FXML
    private Label lbProdutoId;

    @FXML
    private Label lbProdutoNome;

    @FXML
    private Label lbProdutoDescricao;

    @FXML
    private Label lbProdutoPreco;

    @FXML
    private Label lbProdutoCategoria;
    
    @FXML
    private Label lbProdutoFornecedor;

    @FXML
    private Button btInserir;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btRemover;

    private List<Produto> listaProdutos;
    private ObservableList<Produto> observableListProdutos;

    //acesso ao banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);

        carregarTableView();

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableView(newValue));

    }

    public void carregarTableView() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        listaProdutos = produtoDAO.listagem();
        
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
            lbProdutoCategoria.setText(produto.getCategoria().getDescricao());
            lbProdutoFornecedor.setText(produto.getFornecedor().getNome());
        } else {
            lbProdutoId.setText("");
            lbProdutoNome.setText("");
            lbProdutoDescricao.setText("");
            lbProdutoPreco.setText("");
            lbProdutoCategoria.setText("");
            lbProdutoFornecedor.setText("");
        }
    }
    

    @FXML
    public void handleBtInserir() throws IOException {
        Produto produto = new Produto();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosProdutosDialog(produto);
        if (buttonConfirmarClicked) {
            produtoDAO.inserir(produto);
            carregarTableView();
        }
    }
    
    @FXML
    public void handleBtAlterar() throws IOException {
        Produto produto = tableView.getSelectionModel().getSelectedItem();
        if (produto != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastrosProdutosDialog(produto);
            if (buttonConfirmarClicked) {
                produtoDAO.alterar(produto);
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela.");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtRemover() throws IOException {
        Produto produto = tableView.getSelectionModel().getSelectedItem();
        if (produto != null) {
            produtoDAO.remover(produto);
            carregarTableView();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um produto na Tabela.");
            alert.show();
        }
    }
    
    public boolean showFXMLAnchorPaneCadastrosProdutosDialog(Produto produto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroProdutoDialogController.class.getResource( 
            "/view/FXMLAnchorPaneCadastroProdutoDialog.fxml"));
        AnchorPane page = (AnchorPane)loader.load();
        
        //criando um estágio de diálogo  (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de produtos");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //Setando o produto ao controller
        FXMLAnchorPaneCadastroProdutoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduto(produto);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
    }


}
