package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.ESituacao;
import br.edu.ifsc.fln.model.domain.Estoque;
import br.edu.ifsc.fln.model.domain.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EstoqueDAO{

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean atualizar(Estoque estoque) {
        String sql = "UPDATE estoque SET quantidade=?, qtd_minima=?, qtd_maxima=?, situacao=? WHERE id_produto=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getQtdMinima());
            stmt.setInt(3, estoque.getQtdMaxima());
            stmt.setString(4, estoque.getSituacao().name());
            stmt.setInt(5, estoque.getProduto().getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Produto> listar() {
        String sql = "SELECT * FROM estoque e INNER JOIN produto p ON p.id = e.id_produto";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = populateVO(resultado);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Produto> listarPorEstoque(Estoque estoque) {
        String sql = "SELECT * FROM estoque e INNER JOIN produto p ON p.id = e.id_produto WHERE e.id_produto = ?";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, estoque.getProduto().getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = populateVO(resultado);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    private Produto populateVO(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        //Categoria categoria = new Categoria();
        //produto.setCategoria(categoria);
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getBigDecimal("preco"));
        produto.getEstoque().setQuantidade(rs.getInt("quantidade"));
        produto.getEstoque().setQtdMaxima(rs.getInt("qtd_maxima"));
        produto.getEstoque().setQtdMinima(rs.getInt("qtd_minima"));
        produto.getEstoque().setSituacao(Enum.valueOf(ESituacao.class, rs.getString("situacao")));
        return produto;
    }   
    

}
