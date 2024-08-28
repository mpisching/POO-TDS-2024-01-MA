/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ClienteDAO;
import br.edu.ifsc.fln.model.dao.ProdutoDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.ESituacao;
import br.edu.ifsc.fln.model.domain.EStatusVenda;
import br.edu.ifsc.fln.model.domain.ItemDeVenda;
import br.edu.ifsc.fln.model.domain.Produto;
import br.edu.ifsc.fln.model.domain.Venda;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAnchorPaneProcessoVendaDialogController implements Initializable {

    @FXML
    private ComboBox<Cliente> comboBoxClientes;
    @FXML
    private DatePicker datePickerData;
    @FXML
    private CheckBox checkBoxPago;
    @FXML
    private TableView<ItemDeVenda> tableViewItensDeVenda;
    @FXML
    private TableColumn<ItemDeVenda, Produto> tableColumnProduto;
    @FXML
    private TableColumn<ItemDeVenda, Integer> tableColumnQuantidade;
    @FXML
    private TableColumn<ItemDeVenda, Double> tableColumnValor;
    @FXML
    private TextField textFieldValor;
    @FXML
    private ComboBox<Produto> comboBoxProduto;
    @FXML
    private TextField textFieldQuantidadeProduto;
    @FXML
    private Button buttonAdicionar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private ContextMenu contextMenuTableView;
    @FXML
    private MenuItem contextMenuItemAtualizarQtd;
    @FXML
    private MenuItem contextMenuItemRemoverItem;
    @FXML
    private ChoiceBox choiceBoxSituacao;
    @FXML
    private TextField textFieldDesconto;
    

    private List<Cliente> listaClientes;
    private List<Produto> listaProdutos;
    private ObservableList<Cliente> observableListClientes;
    private ObservableList<Produto> observableListProdutos;
    private ObservableList<ItemDeVenda> observableListItensDeVenda;

    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Venda venda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        produtoDAO.setConnection(connection);
        carregarComboBoxClientes();
        carregarComboBoxProdutos();
        carregarChoiceBoxSituacao();
        setFocusLostHandle();
        tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    private void carregarComboBoxClientes() {
        listaClientes = clienteDAO.listar();
        observableListClientes = FXCollections.observableArrayList(listaClientes);
        comboBoxClientes.setItems(observableListClientes);
    }

    private void carregarComboBoxProdutos() {
        /* carrega apenas os produtos  com estoque cuja SITUACAO está em ATIVO para operações */
        listaProdutos = produtoDAO.listar(ESituacao.ATIVO);
        observableListProdutos = FXCollections.observableArrayList(listaProdutos);
        comboBoxProduto.setItems(observableListProdutos);
    }
    
    
    public void carregarChoiceBoxSituacao() {
        choiceBoxSituacao.setItems( FXCollections.observableArrayList(EStatusVenda.values()));
        choiceBoxSituacao.getSelectionModel().select(0);
    }

    private void setFocusLostHandle() {
        textFieldDesconto.focusedProperty().addListener((ov, oldV, newV) -> {
        if (!newV) { // focus lost
                if (textFieldDesconto.getText() != null && !textFieldDesconto.getText().isEmpty()) {
                    //System.out.println("teste focus lost");
                    venda.setTaxaDesconto(Double.parseDouble(textFieldDesconto.getText()));
                    textFieldValor.setText(venda.getTotal().toString());
                    
                }
            }
        });
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
     * @return the venda
     */
    public Venda getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(Venda venda) {
        this.venda = venda;
        if (venda.getId() != 0) { 
            comboBoxClientes.getSelectionModel().select(this.venda.getCliente());
            datePickerData.setValue(this.venda.getData());
            checkBoxPago.setSelected(this.venda.isPago());
            observableListItensDeVenda = FXCollections.observableArrayList(
                    this.venda.getItensDeVenda());
            tableViewItensDeVenda.setItems(observableListItensDeVenda);
            textFieldValor.setText(String.format("%.2f", this.venda.getTotal()));
            textFieldDesconto.setText(String.format("%.2f", this.venda.getTaxaDesconto()));
            choiceBoxSituacao.getSelectionModel().select(this.venda.getStatusVenda());

        }
    }

    @FXML
    public void handleButtonAdicionar() {
        Produto produto;
        ItemDeVenda itemDeVenda = new ItemDeVenda();
        if (comboBoxProduto.getSelectionModel().getSelectedItem() != null) {
            //o comboBox possui dados sintetizados de Produto para evitar carga desnecessária de informação
            produto = comboBoxProduto.getSelectionModel().getSelectedItem();
            //a instrução a seguir busca detalhes do produto selecionado
            produto = produtoDAO.buscar(produto);
            if (produto.getEstoque().getQuantidade() >= Integer.parseInt(textFieldQuantidadeProduto.getText())) {
                itemDeVenda.setProduto(produto);
                itemDeVenda.setQuantidade(Integer.parseInt(textFieldQuantidadeProduto.getText()));
                itemDeVenda.setValor(produto.getPreco().multiply(BigDecimal.valueOf(itemDeVenda.getQuantidade())));
                itemDeVenda.setVenda(venda);
                venda.getItensDeVenda().add(itemDeVenda);
                observableListItensDeVenda = FXCollections.observableArrayList(venda.getItensDeVenda());
                tableViewItensDeVenda.setItems(observableListItensDeVenda);
                textFieldValor.setText(String.format("%.2f", venda.getTotal()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Problemas na escolha do produto");
                alert.setContentText("Não existe quantidade suficiente de produtos para venda.");
                alert.show();
            }
        }
    }

    @FXML
    private void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            venda.setCliente(comboBoxClientes.getSelectionModel().getSelectedItem());
            venda.setPago(checkBoxPago.isSelected());
            venda.setData(datePickerData.getValue());
            venda.setStatusVenda((EStatusVenda)choiceBoxSituacao.getSelectionModel().getSelectedItem());
            venda.setTaxaDesconto(Double.parseDouble(textFieldDesconto.getText()));
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleButtonCancelar() {
        dialogStage.close();
    }
    
    @FXML
    void handleTableViewMouseClicked(MouseEvent event) {
        ItemDeVenda itemDeVenda
                = tableViewItensDeVenda.getSelectionModel().getSelectedItem();
        if (itemDeVenda == null) {
            contextMenuItemAtualizarQtd.setDisable(true);
            contextMenuItemRemoverItem.setDisable(true);
        } else {
            contextMenuItemAtualizarQtd.setDisable(false);
            contextMenuItemRemoverItem.setDisable(false);
        }

    }    

    @FXML
    private void handleContextMenuItemAtualizarQtd() {
        ItemDeVenda itemDeVenda
                = tableViewItensDeVenda.getSelectionModel().getSelectedItem();
        int index = tableViewItensDeVenda.getSelectionModel().getSelectedIndex();
        
        int qtdAtualizada = Integer.parseInt(inputDialog(itemDeVenda.getQuantidade()));
        if (itemDeVenda.getProduto().getEstoque().getQuantidade() >= qtdAtualizada) {
            itemDeVenda.setQuantidade(qtdAtualizada);
            //venda.getItensDeVenda().set(venda.getItensDeVenda().indexOf(itemDeVenda),itemDeVenda);
            venda.getItensDeVenda().set(index, itemDeVenda);
            itemDeVenda.setValor(itemDeVenda.getProduto().getPreco().multiply(BigDecimal.valueOf(itemDeVenda.getQuantidade())));
            tableViewItensDeVenda.refresh();
            textFieldValor.setText(String.format("%.2f", venda.getTotal()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erro no estoque");
            alert.setContentText("Não há quantidade suficiente de produtos para venda.");
            alert.show();
        }
    }
    
    private String inputDialog(int value) {
        TextInputDialog dialog = new TextInputDialog(Integer.toString(value));
        dialog.setTitle("Entrada de dados.");
        dialog.setHeaderText("Atualização da quantidade de produtos.");
        dialog.setContentText("Quantidade: ");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        return result.get();
    }

    @FXML
    private void handleContextMenuItemRemoverItem() {
        ItemDeVenda itemDeVenda
                = tableViewItensDeVenda.getSelectionModel().getSelectedItem();
        int index = tableViewItensDeVenda.getSelectionModel().getSelectedIndex();
        venda.getItensDeVenda().remove(index);
        observableListItensDeVenda = FXCollections.observableArrayList(venda.getItensDeVenda());
        tableViewItensDeVenda.setItems(observableListItensDeVenda);

        textFieldValor.setText(String.format("%.2f", venda.getTotal()));
    }

    //validar entrada de dados do cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (comboBoxClientes.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Cliente inválido!\n";
        }

        if (datePickerData.getValue() == null) {
            errorMessage += "Data inválida!\n";
        }

        if (observableListItensDeVenda == null) {
            errorMessage += "Itens de venda inválidos!\n";
        }
        
        DecimalFormat df = new DecimalFormat("0.00");
        try {
            textFieldDesconto.setText(df.parse(textFieldDesconto.getText()).toString());
        } catch (ParseException ex) {
            errorMessage += "A taxa de desconto está incorreta! Use \",\" como ponto decimal.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}
