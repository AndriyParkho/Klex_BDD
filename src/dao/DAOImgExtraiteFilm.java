package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.ImgExtraiteFilm;

public class DAOImgExtraiteFilm extends DAO<ImgExtraiteFilm> {

    @Override
    public void create(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        final String query = "INSERT INTO ImgExtraiteFilm VALUES (?, ?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, imgExtraiteFilm.getUrlImg());
            statement.setString(2, imgExtraiteFilm.getTitreFilm());
            statement.setDate(3, imgExtraiteFilm.getAnneeSortie());
            statement.executeUpdate();
        }
    }

    public void createOrUpdate(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        try {
            this.create(imgExtraiteFilm);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(imgExtraiteFilm + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        return this.find(imgExtraiteFilm.getUrlImg(), imgExtraiteFilm.getTitreFilm(), imgExtraiteFilm.getAnneeSortie());
    }

    public ResultSet find(String urlImg, String titreFilm, Date anneeSortie) throws SQLException {
        final String query = String.format("SELECT * FROM ImgExtraiteFilm WHERE urlImg = '%s' AND titreFilm = '%s' AND anneeSortie = '%s'", urlImg, titreFilm, anneeSortie);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String urlImg, String titreFilm, Date anneeSortie) throws SQLException {
        final String query = String.format("DELETE FROM ImgExtraiteFilm WHERE urlImg = '%s' AND titreFilm = '%s' AND anneeSortie = '%s'", urlImg, titreFilm, anneeSortie);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
