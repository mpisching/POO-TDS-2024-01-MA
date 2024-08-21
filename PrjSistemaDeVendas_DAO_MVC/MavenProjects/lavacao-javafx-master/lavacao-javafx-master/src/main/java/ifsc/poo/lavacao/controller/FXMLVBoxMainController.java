package ifsc.poo.lavacao.controller;

import ifsc.poo.lavacao.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemCadastrosClientes;
    
    @FXML
    private MenuItem menuItemProcessosVendas;
    
    @FXML
    private MenuItem menuItemGraficosVendasPorMes;
    
    @FXML
    private MenuItem menuItemRelatoriosQuantidadeProdutos;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void handleMenuItemCadastrosClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/FXMLAnchorPaneCadastrosClientes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemProcessosVendas() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/FXMLAnchorPaneProcessosVendas.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGraficosVendasPorMes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/FXMLAnchorPaneGraficosVendasPorMes.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeProdutos() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/FXMLAnchorPaneRelatoriosQuantidadeProdutos.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemRelatoriosQuantidadeProdutosPorCategoria() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("view/FXMLAnchorPaneRelatoriosQuantidadeProdutosPorCategoria.fxml"));
        anchorPane.getChildren().setAll(a);
    }


}
