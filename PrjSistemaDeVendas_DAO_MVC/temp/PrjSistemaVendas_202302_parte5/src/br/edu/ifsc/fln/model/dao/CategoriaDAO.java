package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.model.domain.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO {

    private Connection connection;
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Categoria categoria) throws DAOException {
        String sql = "INSERT INTO categoria(descricao) VALUES(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.execute();
            //return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
            throw new DAOException("Não foi possível salvar o registro no banco de dados!", ex);
        }
    }

    public void alterar(Categoria categoria) throws DAOException {
        String sql = "UPDATE categoria SET descricao=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.setInt(2, categoria.getId());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Não foi possível atualizar o registro no banco de dados.", ex);
        }
    }

    public void remover(Categoria categoria) throws DAOException {
        String sql = "DELETE FROM categoria WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Não foi possível excluir  o registro do banco de dados.", ex);
        }
    }

    public List<Categoria> listar() throws DAOException {
        String sql = "SELECT * FROM categori";
        List<Categoria> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultado.getInt("id"));
                categoria.setDescricao(resultado.getString("descricao"));
                retorno.add(categoria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
        }
        return retorno;
    }

    public Categoria buscar(Categoria categoria) throws DAOException {
        String sql = "SELECT * FROM categoria WHERE id=?";
        Categoria retorno = new Categoria();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                categoria.setDescricao(resultado.getString("descricao"));
                retorno = categoria;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Não foi possível realizar a pesquisa no banco de dados", ex);
        }
        return retorno;
    }
}
