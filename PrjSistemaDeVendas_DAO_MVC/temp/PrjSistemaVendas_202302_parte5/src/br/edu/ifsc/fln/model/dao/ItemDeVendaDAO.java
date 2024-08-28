package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.ItemDeVenda;
import br.edu.ifsc.fln.model.domain.Produto;
import br.edu.ifsc.fln.model.domain.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ItemDeVendaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(ItemDeVenda itemDeVenda) {
        String sql = "INSERT INTO item_de_venda(quantidade, valor, id_produto, id_venda) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeVenda.getQuantidade());
            stmt.setBigDecimal(2, itemDeVenda.getValor());
            stmt.setInt(3, itemDeVenda.getProduto().getId());
            stmt.setInt(4, itemDeVenda.getVenda().getId());
            
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
        String sql = "DELETE FROM item_de_venda WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeVenda.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ItemDeVenda> listar() {
        String sql = "SELECT * FROM item_de_venda";
        List<ItemDeVenda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda();
                Produto produto = new Produto();
                Venda venda = new Venda();
                itemDeVenda.setId(resultado.getInt("id"));
                itemDeVenda.setQuantidade(resultado.getInt("quantidade"));
                itemDeVenda.setValor(resultado.getBigDecimal("valor"));
                
                produto.setId(resultado.getInt("id_produto"));
                venda.setId(resultado.getInt("id_venda"));
                
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
        String sql = "SELECT * FROM item_de_venda WHERE id_venda=?";
        List<ItemDeVenda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeVenda itemDeVenda = new ItemDeVenda();
                Produto produto = new Produto();
                Venda v = new Venda();
                itemDeVenda.setId(resultado.getInt("id"));
                itemDeVenda.setQuantidade(resultado.getInt("quantidade"));
                itemDeVenda.setValor(resultado.getBigDecimal("valor"));
                
                produto.setId(resultado.getInt("id_produto"));
                v.setId(resultado.getInt("id_venda"));
                
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
        String sql = "SELECT * FROM item_de_venda WHERE id=?";
        ItemDeVenda retorno = new ItemDeVenda();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemDeVenda.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Produto produto = new Produto();
                Venda venda = new Venda();
                itemDeVenda.setId(resultado.getInt("id"));
                itemDeVenda.setQuantidade(resultado.getInt("quantidade"));
                itemDeVenda.setValor(resultado.getBigDecimal("valor"));
                
                produto.setId(resultado.getInt("id_produto"));
                venda.setId(resultado.getInt("id_venda"));
                
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
