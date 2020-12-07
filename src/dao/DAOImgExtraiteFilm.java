package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
