package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOFluxTexte extends DAO<FluxTexte> {

    @Override
    public FluxTexte create(FluxTexte obj) {
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
    public FluxTexte update(FluxTexte obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public FluxTexte find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(FluxTexte obj) {
        // TODO Auto-generated method stub

    }
    
}
