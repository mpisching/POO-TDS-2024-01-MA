
package br.edu.ifsc.fln.model.database;

import java.sql.Connection;


public interface Database {
    
    public Connection conectar();
    public void desconectar(Connection conn);
    public void commit(Connection connection);
    public void rollback(Connection connection);
}
