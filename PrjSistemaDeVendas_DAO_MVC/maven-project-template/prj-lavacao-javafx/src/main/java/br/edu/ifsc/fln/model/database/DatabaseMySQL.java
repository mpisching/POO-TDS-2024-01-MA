package br.edu.ifsc.fln.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseMySQL implements Database {

    private Connection connection;

    @Override
    public Connection conectar() {
        try {
            final String DRIVER = "com.mysql.cj.jdbc.Driver";//MySQL 8
            //String driver = "com.mysql.jdbc.Driver"; //MySQL 5
            final String URL = "jdbc:mysql://localhost:3306/db_vendas?useTimezone=true&serverTimezone=UTC";//MySQL 8
            //String url = "jdbc:mysql://localhost:3306/db_vendas?useTimezone=true&serverTimezone=America/Sao_Paulo";
            //String url = "jdbc:mysql://localhost:3306/db_vendas";//MySQL 5
            final String USER = "root";
            final String PASS = "";
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexão realizada com sucesso!");
            return this.connection;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha na conexão com o banco de dados.");
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
