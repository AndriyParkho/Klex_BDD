package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.FilmAPourCategorie;

public class DAOFilmAPourCategorie extends DAO<FilmAPourCategorie> {

    @Override
    public void create(FilmAPourCategorie filmAPourCategorie) throws SQLException {
        final String query = "INSERT INTO FilmAPourCategorie VALUES (?, ?, ?)";

        System.out.println("Statement FilmAPourCategorie");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, filmAPourCategorie.getTitreFilm());
            statement.setDate(2, filmAPourCategorie.getAnneeSortie());
            statement.setString(3, filmAPourCategorie.getTypeCategorieFilm());

            statement.executeUpdate();
        }
    }

    public void createOrUpdate(FilmAPourCategorie filmAPourCategorie) throws SQLException {
        try {
            this.create(filmAPourCategorie);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(filmAPourCategorie + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(FilmAPourCategorie filmAPourCategorie) throws SQLException {
        return this.find(filmAPourCategorie.getTitreFilm(), filmAPourCategorie.getAnneeSortie(),
                filmAPourCategorie.getTypeCategorieFilm());
    }

    public ResultSet find(String titreFilm, Date anneeSortie, String typeCategorieFilm) throws SQLException {
        final String query = String.format(
                "SELECT * FROM FilmAPourCategorie WHERE titreFilm = '%s' AND anneeSortie = '%s' AND typeCategorieFilm = '%s'",
                titreFilm, anneeSortie, typeCategorieFilm);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String titreFilm, Date anneeSortie, String typeCategorieFilm) throws SQLException {
        final String query = String.format(
                "SELECT * FROM FilmAPourCategorie WHERE titreFilm = '%s' AND anneeSortie = '%s' AND typeCategorieFilm = '%s'",
                titreFilm, anneeSortie, typeCategorieFilm);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
