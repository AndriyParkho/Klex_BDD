package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOFilm extends DAO<Film> {

    @Override
    public Film create(Film obj) {
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
    public Film update(Film obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public Film find(String titre, int anneeSortie) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Film obj) {
        // TODO Auto-generated method stub

    }
    
}
