package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOCategorieFilm extends DAO<CategorieFilm> {

    @Override
    public CategorieFilm create(CategorieFilm obj) {
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
    public CategorieFilm update(CategorieFilm obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public CategorieFilm find(String nomCategorie) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(CategorieFilm obj) {
        // TODO Auto-generated method stub

    }
    
}
