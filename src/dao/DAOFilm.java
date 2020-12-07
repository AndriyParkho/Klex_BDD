package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Film;

public class DAOFilm {

    public void create(Film film) throws SQLException {
        final String insertFilmQuery = "INSERT INTO Film VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statementFilm = this.connection.prepareStatement(insertFilmQuery)) {
            statementFilm.setString(1, film.getTitreFilm());
            statementFilm.setDate(2, Date.valueOf(film.getAnneeSortie()));
            statementFilm.setString(3, film.getResume());
            statementFilm.setInt(4, film.getAgeMin());
            statementFilm.setString(5, film.getUrlAffiche());

            statementFilm.executeUpdate();
        }
    }

    public void find(String titreFilm, String anneeSortie) throws SQLException {
        final String query = String.format("SELECT * FROM Film WHERE titreFilm = '%s' AND anneeSortie = '%s'", titreFilm, Date.valueOf(anneeSortie));
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(String titreFilm, String anneeSortie) throws SQLException {
        final String query = String.format("DELETE FROM Film WHERE titreFilm = '%s' AND anneeSortie = '%s'", titreFilm, Date.valueOf(anneeSortie));
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
