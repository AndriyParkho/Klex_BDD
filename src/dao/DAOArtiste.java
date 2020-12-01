package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOArtiste extends DAO<Artiste> {

    @Override
    public Artiste create(Artiste obj) {
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);


            connection.commit();
        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
        

        return null;
    }

    @Override
    public Artiste update(Artiste obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Artiste find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Artiste obj) {
        // TODO Auto-generated method stub

    }
    
}
