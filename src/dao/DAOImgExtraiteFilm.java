package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import tables.ImgExtraiteFilm;

public class DAOImgExtraiteFilm extends DAO<ImgExtraiteFilm> {

    /**
     * @throws SQLIntegrityConstraintViolationException if the film doesn't exist
     */
    @Override
    public void create(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        final String query = "INSERT INTO ImgExtraiteFilm VALUES (?, ?, TO_DATE(?, 'dd/mm/yyyy'))";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, imgExtraiteFilm.getUrlImg());
            statement.setString(2, imgExtraiteFilm.getTitreFilm());
            statement.setString(3, imgExtraiteFilm.getAnneeSortie());
            statement.executeUpdate();
            imgExtraiteFilm = this.find(imgExtraiteFilm);
        }
        connection.commit();
    }

    /**
     * @throws SQLIntegrityConstraintViolationException if the film doesn't exist
     */
    @Override
    public void createOrUpdate(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        try {
            this.create(imgExtraiteFilm);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                this.update(imgExtraiteFilm);
            }
        }
    }

    @Override
    public ImgExtraiteFilm find(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        return this.find(imgExtraiteFilm.getUrlImg());
    }

    public ImgExtraiteFilm find(String urlImage) throws SQLException {
        ImgExtraiteFilm imgExtraiteFilm = null;
        final String query = "SELECT * FROM ImgExtraiteFilm WHERE urlImage = '" + urlImage + "'";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                imgExtraiteFilm = new ImgExtraiteFilm(urlImage, rs.getString("titreFilm"), rs.getString("anneeSortie"));
            }
        }
        connection.commit();
        return imgExtraiteFilm;
    }

    /**
     * @throws SQLIntegrityConstraintViolationException if the film doesn't exist
     */
    @Override
    public void update(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        final String query = "UPDATE ImgExtraiteFilm SET titreFilm = '" + imgExtraiteFilm.getTitreFilm() + "', anneeSortie = '"
                + imgExtraiteFilm.getAnneeSortie() + "' WHERE urlImage = '" + imgExtraiteFilm.getUrlImg() + "'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final int nbRowsAffected = statement.executeUpdate();
            if (nbRowsAffected != 1) {
                throw new SQLException("not only one row affected");
            }
            connection.commit();
        }
    }

    @Override
    public void delete(ImgExtraiteFilm imgExtraiteFilm) throws SQLException {
        final String query = "DELETE FROM ImgExtraiteFilm WHERE urlImage = '"
                + imgExtraiteFilm.getUrlImg() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        connection.commit();
    }
}
