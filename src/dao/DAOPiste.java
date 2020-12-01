package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOPiste extends DAO<Piste> {

    @Override
    public Piste create(Piste obj) {
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
    public Piste update(Piste obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Piste find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Piste obj) {
        // TODO Auto-generated method stub

    }
    
}
