package dao;

import java.sql.Connection;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.*;

public class DAOImgExtraiteFilm extends DAO<ImgExtraiteFilm> {

    @Override
    public ImgExtraiteFilm create(ImgExtraiteFilm obj) {
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
    public ImgExtraiteFilm update(ImgExtraiteFilm obj) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ImgExtraiteFilm find(String urlImg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(ImgExtraiteFilm obj) {
        // TODO Auto-generated method stub

    }
    
}
