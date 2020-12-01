package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOFichier extends DAO<Fichier> {

    @Override
    public Fichier create(Fichier obj) {
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
    public Fichier update(Fichier obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Fichier find(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Fichier obj) {
        // TODO Auto-generated method stub

    }
    
}
