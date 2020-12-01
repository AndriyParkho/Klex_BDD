package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOFluxAudio extends DAO<FluxAudio> {

    @Override
    public FluxAudio create(FluxAudio obj) {
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
    public FluxAudio update(FluxAudio obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public FluxAudio find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(FluxAudio obj) {
        // TODO Auto-generated method stub

    }
    
}
