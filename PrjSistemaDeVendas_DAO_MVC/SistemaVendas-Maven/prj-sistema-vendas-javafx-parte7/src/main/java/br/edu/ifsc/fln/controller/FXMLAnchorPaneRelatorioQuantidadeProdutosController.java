/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.model.dao.ProdutoDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Categoria;
import br.edu.ifsc.fln.model.domain.Estoque;
import br.edu.ifsc.fln.model.domain.Produto;
import br.edu.ifsc.fln.utils.AlertDialog;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAnchorPaneRelatorioQuantidadeProdutosController implements Initializable {

    @FXML
    private TableView<Produto> tableView;
    @FXML
    private TableColumn<Produto, Integer> tableColumnProdutoID;
    @FXML
    private TableColumn<Produto, String> tableColumnProdutoNome;
    @FXML
    private TableColumn<Produto, BigDecimal> tableColumnProdutoPreco;
    @FXML
    private TableColumn<Produto, Estoque> tableColumnProdutoQuantidade;
    @FXML
    private TableColumn<Produto, Categoria> tableColumnProdutoCategoria;
    @FXML
    private Button buttonImprimir;
    
    private List<Produto> listaProdutos;
    private ObservableList<Produto> observableListProdutos;
    
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
    }    
    
    private void carregarTableView() {
        try {
            listaProdutos = produtoDAO.listarProdutoEstoque();
        } catch (DAOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioQuantidadeProdutosController.class.getName()).log(Level.SEVERE, null, ex);
            AlertDialog.exceptionMessage(ex);
            return;
        }
        
        tableColumnProdutoID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnProdutoPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnProdutoQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        tableColumnProdutoCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        observableListProdutos = FXCollections.observableArrayList(listaProdutos);
        tableView.setItems(observableListProdutos);
    }
    
    //@FXML
    public void handleImprimir() throws JRException {
        URL url = getClass().getResource("/report/PrjSistemaVendasRelEstoque.jasper");
        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(url);
        
        //null: caso não existam filtros
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
        
        //false: não deixa fechar a aplicação principal
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);  
    }
    
}
