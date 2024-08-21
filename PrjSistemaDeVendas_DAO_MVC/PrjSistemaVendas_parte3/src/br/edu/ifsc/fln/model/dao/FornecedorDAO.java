/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Fornecedor;
import br.edu.ifsc.fln.model.domain.Internacional;
import br.edu.ifsc.fln.model.domain.Nacional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpisc
 */
public class FornecedorDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor(nome, email, fone) VALUES(?, ?, ?)";
        String sqlFN = "INSERT INTO nacional(id_fornecedor, cnpj) VALUES((SELECT max(id) FROM fornecedor), ?)";
        String sqlFI = "INSERT INTO internacional(id_fornecedor, nif, pais) VALUES((SELECT max(id) FROM fornecedor), ?, ?)";
        try {
            //armazena os dados da superclasse
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEmail());
            stmt.setString(3, fornecedor.getFone());
            stmt.execute();
            //armazena os dados da subclasse
            if (fornecedor instanceof Nacional) {
                stmt = connection.prepareStatement(sqlFN);
                stmt.setString(1, ((Nacional)fornecedor).getCnpj());
                stmt.execute();
            } else {
                stmt = connection.prepareStatement(sqlFI);
                stmt.setString(1, ((Internacional)fornecedor).getNif());
                stmt.setString(2, ((Internacional)fornecedor).getPais());
                stmt.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome=?, email=?, fone=? WHERE id=?";
        String sqlFN = "UPDATE nacional SET cnpj=? WHERE id_fornecedor = ?";
        String sqlFI = "UPDATE internacional SET nif=?, pais=? WHERE id_fornecedor = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEmail());
            stmt.setString(3, fornecedor.getFone());
            stmt.setInt(4, fornecedor.getId());
            stmt.execute();
            if (fornecedor instanceof Nacional) {
                stmt = connection.prepareStatement(sqlFN);
                stmt.setString(1, ((Nacional)fornecedor).getCnpj());
                stmt.setInt(2, fornecedor.getId());
                stmt.execute();
            } else {
                stmt = connection.prepareStatement(sqlFI);
                stmt.setString(1, ((Internacional)fornecedor).getNif());
                stmt.setString(2, ((Internacional)fornecedor).getPais());
                stmt.setInt(3, fornecedor.getId());
                stmt.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedor WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Fornecedor> listar() {
        String sql = "SELECT * FROM fornecedor f "
                        + "LEFT JOIN nacional n on n.id_fornecedor = f.id "
                        + "LEFT JOIN internacional i on i.id_fornecedor = f.id;";
        List<Fornecedor> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fornecedor fornecedor = populateVO(resultado);
                retorno.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Fornecedor buscar(Fornecedor fornecedor) {
        String sql = "SELECT * FROM fornecedor f "
                        + "LEFT JOIN nacional n on n.id_fornecedor = f.id "
                        + "LEFT JOIN internacional i on i.id_fornecedor = f.id WHERE id=?";
        Fornecedor retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Fornecedor buscar(int id) {
        String sql = "SELECT * FROM fornecedor f "
                        + "LEFT JOIN nacional n on n.id_fornecedor = f.id "
                        + "LEFT JOIN internacional i on i.id_fornecedor = f.id WHERE id=?";
        Fornecedor retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
    
    private Fornecedor populateVO(ResultSet rs) throws SQLException {
        Fornecedor fornecedor;
        if (rs.getString("nif") == null || rs.getString("nif").length() <= 0) {
            //é um fornecedor nacional
            fornecedor = new Nacional();
            ((Nacional)fornecedor).setCnpj(rs.getString("cnpj"));
        } else {
            //é um fornecedor internacional
            fornecedor = new Internacional();
            ((Internacional)fornecedor).setNif(rs.getString("nif"));
            ((Internacional)fornecedor).setPais(rs.getString("pais"));
        }
        fornecedor.setId(rs.getInt("id"));
        fornecedor.setNome(rs.getString("nome"));
        fornecedor.setEmail(rs.getString("email"));
        fornecedor.setFone(rs.getString("fone"));
        return fornecedor;
    }
}
