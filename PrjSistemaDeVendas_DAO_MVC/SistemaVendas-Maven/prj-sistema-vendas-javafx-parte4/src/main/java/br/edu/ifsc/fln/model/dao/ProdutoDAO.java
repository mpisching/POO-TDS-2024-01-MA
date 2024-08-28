package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Categoria;
import br.edu.ifsc.fln.model.domain.ESituacao;
import br.edu.ifsc.fln.model.domain.Fornecedor;
import br.edu.ifsc.fln.model.domain.Internacional;
import br.edu.ifsc.fln.model.domain.Nacional;
import br.edu.ifsc.fln.model.domain.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutoDAO{

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Produto produto) {
        final String sql = "INSERT INTO produto(nome, descricao, preco, id_categoria, id_fornecedor) VALUES(?,?,?,?,?);";
        final String sqlEstoque = "INSERT INTO estoque(id_produto) (SELECT max(id) FROM produto);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //registra o produto
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getFornecedor().getId());
            stmt.execute();
            //registra o estoque do produto imediatamente
            stmt = connection.prepareStatement(sqlEstoque);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET nome=?, descricao=?, preco=?, id_categoria=?, id_fornecedor=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getFornecedor().getId());
            stmt.setInt(6, produto.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Produto produto) {
        String sql = "DELETE FROM produto WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Produto> listar() {
        String sql =  "SELECT * FROM produto;";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = populateSingleVO(resultado);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /**
     * Este método retorna uma lista de Produto de acordo com a situação do estoque 
     * @param situacao - Enum ESituacao
     * @return uma List de Produtos
     */
    public List<Produto> listar(ESituacao situacao) {
        String sql =  "SELECT * FROM produto p INNER JOIN estoque e ON p.id = e.id_produto WHERE e.situacao = ?;";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, situacao.name());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = populateSingleVO(resultado);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
    
    public List<Produto> listarPorCategoria(Categoria categoria) {
        String sql =  "SELECT p.id as produto_id, p.nome as produto_nome, p.descricao as produto_descricao, p.preco as produto_preco, "
                + "c.id as categoria_id, c.descricao as categoria_descricao "
                + "FROM produto p INNER JOIN categoria c ON c.id = p.id_categoria WHERE c.id = ?;";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = populateFullVO(resultado);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Produto buscar(Produto produto) {
        String sql =  "SELECT * FROM produto p INNER JOIN estoque e ON p.id = e.id_produto WHERE p.id = ?;";
        Produto retorno = new Produto();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateFullVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    private Produto populateFullVO(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        Categoria categoria = new Categoria();
        //dados do produto
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getBigDecimal("preco"));
        
        //dados da categoria do produto
        categoria.setId(rs.getInt("id_categoria"));
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.setConnection(connection);
        categoria = categoriaDAO.buscar(categoria);
        produto.setCategoria(categoria);
        
        //dados do fornecedor
        int idFornecedor = rs.getInt("id_fornecedor");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.setConnection(connection);
        Fornecedor fornecedor = fornecedorDAO.buscar(idFornecedor);
        produto.setFornecedor(fornecedor);
        
        //dados do estoque
        produto.getEstoque().setProduto(produto);
        produto.getEstoque().setSituacao(Enum.valueOf(ESituacao.class, rs.getString("situacao")));
        produto.getEstoque().setQuantidade(rs.getInt("quantidade"));
        produto.getEstoque().setQtdMinima(rs.getInt("qtd_minima"));
        produto.getEstoque().setQtdMaxima(rs.getInt("qtd_maxima"));
        
        return produto;
    }  
    
    private Produto populateSingleVO(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        //dados do produto
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getBigDecimal("preco"));
        
        
        return produto;        
    }
    
}
