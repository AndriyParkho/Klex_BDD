package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
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

    public void find(String urlImg, String titreFilm, String anneeSortie) throws SQLException {
        final String query = String.format("SELECT * FROM ImgExtraiteFilm WHERE urlImg = '%s' AND titreFilm = '%s' AND anneeSortie = '%s'", urlImg, titreFilm, Date.valueOf(anneeSortie));
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(String urlImg, String titreFilm, String anneeSortie) throws SQLException {
        final String query = String.format("DELETE FROM ImgExtraiteFilm WHERE urlImg = '%s' AND titreFilm = '%s' AND anneeSortie = '%s'", urlImg, titreFilm, Date.valueOf(anneeSortie));
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
