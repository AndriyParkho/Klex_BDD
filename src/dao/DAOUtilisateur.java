package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOUtilisateur extends DAO<Utilisateur> {

    @Override
    public Utilisateur create(Utilisateur obj) {
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
    public Utilisateur update(Utilisateur obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Utilisateur find(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Utilisateur obj) {
        // TODO Auto-generated method stub

    }
    
}
