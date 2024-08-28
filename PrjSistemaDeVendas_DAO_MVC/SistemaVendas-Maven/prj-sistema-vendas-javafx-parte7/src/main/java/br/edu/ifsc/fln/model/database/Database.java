
package br.edu.ifsc.fln.model.database;

import java.sql.Connection;
import java.sql.SQLException;


public interface Database {
    
    public Connection conectar();
    public void desconectar(Connection conn);
    public void commit(Connection connection);
    public void rollback(Connection connection);
}
