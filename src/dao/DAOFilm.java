package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Film;
import transactions.TransactionFichierFilm;

public class DAOFilm extends DAO<Film> {

    @Override
    public void create(Film film) throws SQLException {
        final String insertFilmQuery = "INSERT INTO Film VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statementFilm = this.connection.prepareStatement(insertFilmQuery)) {
            statementFilm.setString(1, film.getTitreFilm());
            statementFilm.setDate(2, film.getAnneeSortie());
            statementFilm.setString(3, film.getResume());
            statementFilm.setInt(4, film.getAgeMin());
            statementFilm.setString(5, film.getUrlAffiche());
            statementFilm.executeUpdate();
        }
    }

    public void createOrUpdate(Film film) throws SQLException {
        try {
            this.create(film);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(film + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(Film film) throws SQLException {
        return this.find(film.getTitreFilm(), film.getAnneeSortie());
    }

    public ResultSet find(String titreFilm, Date anneeSortie) throws SQLException {
        final String query = String.format("SELECT * FROM Film WHERE titreFilm = '%s' AND anneeSortie = '%s'",
                titreFilm, anneeSortie);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String titreFilm, Date anneeSortie) throws SQLException {
        final String query = String.format("DELETE FROM Film WHERE titreFilm = '%s' AND anneeSortie = '%s'", titreFilm,
                anneeSortie);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
