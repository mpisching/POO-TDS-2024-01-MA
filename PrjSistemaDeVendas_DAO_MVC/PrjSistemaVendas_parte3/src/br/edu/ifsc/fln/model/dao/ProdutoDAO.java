package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Categoria;
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
        String sql =  "SELECT p.id as produto_id, p.nome as produto_nome, p.descricao as produto_descricao, p.preco as produto_preco, "
                + "c.id as categoria_id, c.descricao as categoria_descricao, f.id as fornecedor_id "
                + "FROM produto p INNER JOIN categoria c ON c.id = p.id_categoria "
                + "INNER JOIN fornecedor f on f.id = p.id_fornecedor;";
        List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = populateVO(resultado);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Produto> listagem() {
        String sql = "select * from produto p "
                +       "inner join categoria c on c.id = p.id_categoria " 
                +       "inner join fornecedor f on f.id = p.id_fornecedor " 
                +       "left join nacional n on n.id_fornecedor = f.id "
                +       "left join internacional i on i.id_fornecedor = f.id;";
                List<Produto> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = populateVOFull(resultado);
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
                Produto produto = populateVO(resultado);
                retorno.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Produto buscar(Produto produto) {
        String sql =  "SELECT p.id as produto_id, p.nome as produto_nome, p.descricao as produto_descricao, p.preco as produto_preco, "
                + "c.id as categoria_id, c.descricao as categoria_descricao "
                + "FROM produto p INNER JOIN categoria c ON c.id = p.id_categoria WHERE p.id = ?;";
        Produto retorno = new Produto();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    private Produto populateVO(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        Categoria categoria = new Categoria();
        produto.setCategoria(categoria);
        
        produto.setId(rs.getInt("produto_id"));
        produto.setNome(rs.getString("produto_nome"));
        produto.setDescricao(rs.getString("produto_descricao"));
        produto.setPreco(rs.getBigDecimal("produto_preco"));
        categoria.setId(rs.getInt("categoria_id"));
        categoria.setDescricao(rs.getString("categoria_descricao"));
        int idFornecedor = rs.getInt("fornecedor_id");
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        fornecedorDAO.setConnection(connection);
        Fornecedor fornecedor = fornecedorDAO.buscar(idFornecedor);
        produto.setFornecedor(fornecedor);
        return produto;
    }   
    
    
    private Produto populateVOFull(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        Categoria categoria = new Categoria();
        produto.setCategoria(categoria);
        
        produto.setId(rs.getInt(1));
        produto.setNome(rs.getString(2));
        produto.setDescricao(rs.getString(3));
        produto.setPreco(rs.getBigDecimal(4));
        categoria.setId(rs.getInt(7));
        categoria.setDescricao(rs.getString(8));
        Fornecedor fornecedor;
        if (rs.getString("nif") == null) {
            fornecedor = new Nacional();
            ((Nacional)fornecedor).setCnpj(rs.getString(14));
        } else {
            fornecedor = new Internacional();
            ((Internacional)fornecedor).setNif(rs.getString(16));
            ((Internacional)fornecedor).setPais(rs.getString(17));
        }
        fornecedor.setId(rs.getInt(6));
        fornecedor.setNome(rs.getString(10));
        fornecedor.setEmail(rs.getString(11));
        fornecedor.setFone(rs.getString(12));
        produto.setFornecedor(fornecedor);
//        int idFornecedor = rs.getInt("fornecedor_id");
//        FornecedorDAO fornecedorDAO = new FornecedorDAO();
//        Fornecedor fornecedor = fornecedorDAO.buscar(idFornecedor);
//        produto.setFornecedor(fornecedor);
        return produto;
    } 

}
