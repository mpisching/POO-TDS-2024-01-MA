package ifsc.poo.lavacao.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ifsc.poo.lavacao.model.domain.ItemDeVenda;
import ifsc.poo.lavacao.model.domain.Produto;
import ifsc.poo.lavacao.model.domain.Venda;

public class ItemDeVendaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(ItemDeVenda itemDeVenda) {
        String sql = "INSERT INTO itensDeVenda(quantidade, valor, cdProduto, cdVenda) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeVenda.getQuantidade());
            stmt.setDouble(2, itemDeVenda.getValor());
            stmt.setInt(3, itemDeVenda.getProduto().getCdProduto());
            stmt.setInt(4, itemDeVenda.getVenda().getCdVenda());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(ItemDeVenda itemDeVenda) {
        return true;
    }

    public boolean remover(ItemDeVenda itemDeVenda) {
        String sql = "DELETE FROM itensDeVenda WHERE cdItemDeVenda=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeVenda.getCdItemDeVenda());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ItemDeVenda> listar() {
        String sql = "SELECT * FROM itensDeVenda";
        List<ItemDeVenda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda();
                Produto produto = new Produto();
                Venda venda = new Venda();
                itemDeVenda.setCdItemDeVenda(resultado.getInt("cdItemDeVenda"));
                itemDeVenda.setQuantidade(resultado.getInt("quantidade"));
                itemDeVenda.setValor(resultado.getDouble("valor"));
                
                produto.setCdProduto(resultado.getInt("cdProduto"));
                venda.setCdVenda(resultado.getInt("cdVenda"));
                
                //Obtendo os dados completos do Produto associado ao Item de Venda
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.setConnection(connection);
                produto = produtoDAO.buscar(produto);
                
                VendaDAO vendaDAO = new VendaDAO();
                vendaDAO.setConnection(connection);
                venda = vendaDAO.buscar(venda);
                
                itemDeVenda.setProduto(produto);
                itemDeVenda.setVenda(venda);
                
                retorno.add(itemDeVenda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<ItemDeVenda> listarPorVenda(Venda venda) {
        String sql = "SELECT * FROM itensDeVenda WHERE cdVenda=?";
        List<ItemDeVenda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getCdVenda());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda();
                Produto produto = new Produto();
                Venda v = new Venda();
                itemDeVenda.setCdItemDeVenda(resultado.getInt("cdItemDeVenda"));
                itemDeVenda.setQuantidade(resultado.getInt("quantidade"));
                itemDeVenda.setValor(resultado.getDouble("valor"));
                
                produto.setCdProduto(resultado.getInt("cdProduto"));
                v.setCdVenda(resultado.getInt("cdVenda"));
                
                //Obtendo os dados completos do Produto associado ao Item de Venda
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.setConnection(connection);
                produto = produtoDAO.buscar(produto);
                
                itemDeVenda.setProduto(produto);
                itemDeVenda.setVenda(v);
                
                retorno.add(itemDeVenda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public ItemDeVenda buscar(ItemDeVenda itemDeVenda) {
        String sql = "SELECT * FROM itensDeVenda WHERE cdItemDeVenda=?";
        ItemDeVenda retorno = new ItemDeVenda();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeVenda.getCdItemDeVenda());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                Venda venda = new Venda();
                itemDeVenda.setCdItemDeVenda(resultado.getInt("cdItemDeVenda"));
                itemDeVenda.setQuantidade(resultado.getInt("quantidade"));
                itemDeVenda.setValor(resultado.getDouble("valor"));
                
                produto.setCdProduto(resultado.getInt("cdProduto"));
                venda.setCdVenda(resultado.getInt("cdVenda"));
                
                //Obtendo os dados completos do Cliente associado à Venda
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.setConnection(connection);
                produto = produtoDAO.buscar(produto);
                
                VendaDAO vendaDAO = new VendaDAO();
                vendaDAO.setConnection(connection);
                venda = vendaDAO.buscar(venda);
                
                itemDeVenda.setProduto(produto);
                itemDeVenda.setVenda(venda);
                
                retorno = itemDeVenda;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
