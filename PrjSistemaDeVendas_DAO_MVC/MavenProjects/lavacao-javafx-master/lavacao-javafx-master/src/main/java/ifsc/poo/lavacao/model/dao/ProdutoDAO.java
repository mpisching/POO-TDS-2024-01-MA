package ifsc.poo.lavacao.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ifsc.poo.lavacao.model.domain.Categoria;
import ifsc.poo.lavacao.model.domain.Produto;

public class ProdutoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produtos(nome, preco, quantidade, cdCategoria) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getCategoria().getCdCategoria());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Produto produto) {
        String sql = "UPDATE produtos SET nome=?, preco=?, quantidade=?, cdCategoria=? WHERE cdProduto=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getCategoria().getCdCategoria());
            stmt.setInt(5, produto.getCdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Produto produto) {
        String sql = "DELETE FROM produtos WHERE cdCliente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getCdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Produto> listar() {
        String sql = "SELECT * FROM produtos";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                Categoria categoria = new Categoria();
                produto.setCdProduto(resultado.getInt("cdProduto"));
                produto.setNome(resultado.getString("nome"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                categoria.setCdCategoria(resultado.getInt("cdCategoria"));
                
                //Obtendo os dados completos da Categoria associada ao Produto
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                categoriaDAO.setConnection(connection);
                categoria = categoriaDAO.buscar(categoria);
                
                produto.setCategoria(categoria);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Produto> listarPorCategoria(Categoria categoria) {
        String sql = "SELECT * FROM produtos WHERE cdCategoria=?";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getCdCategoria());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setCdProduto(resultado.getInt("cdProduto"));
                produto.setNome(resultado.getString("nome"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getInt("quantidade"));
                categoria.setCdCategoria(resultado.getInt("cdCategoria"));
                produto.setCategoria(categoria);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Produto buscar(Produto produto) {
        String sql = "SELECT * FROM produtos WHERE cdProduto=?";
        Produto retorno = new Produto();
        Categoria categoria = new Categoria();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getCdProduto());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setCdProduto(resultado.getInt("cdProduto"));
                retorno.setNome(resultado.getString("nome"));
                retorno.setPreco(resultado.getDouble("preco"));
                retorno.setQuantidade(resultado.getInt("quantidade"));
                categoria.setCdCategoria(resultado.getInt("cdCategoria"));
                retorno.setCategoria(categoria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
