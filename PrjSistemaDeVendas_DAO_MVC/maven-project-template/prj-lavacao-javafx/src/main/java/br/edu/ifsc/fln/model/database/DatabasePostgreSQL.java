package br.edu.ifsc.fln.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabasePostgreSQL implements Database {
    private Connection connection;

    @Override
    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/db_vendas", "postgres","postgres");
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DatabasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
