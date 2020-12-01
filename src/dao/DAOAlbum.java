package src.dao;

import java.sql.Connection;
import java.sql.SQLException;

import src.JDBCUtilities;
import src.tables.Album;

public class DAOAlbum extends DAO<Album> {

    @Override
    public Album create(Album obj) {
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
    public Album update(Album obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Album find(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Album obj) {
        // TODO Auto-generated method stub

    }
    
}
