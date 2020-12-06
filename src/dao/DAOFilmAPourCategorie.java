package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.FilmAPourCategorie;

public class DAOFilmAPourCategorie {
    
    public void create(FilmAPourCategorie filmAPourCategorie) throws SQLException {
        final String query = "INSERT INTO FilmAPourCategorie VALUES (?, ?, ?)";

        System.out.println("Statement FilmAPourCategorie");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, filmAPourCategorie.getTitreFilm());
            statement.setDate(2, Date.valueOf(filmAPourCategorie.getAnneeSortie()));
            statement.setString(3, filmAPourCategorie.getTypeCategorieFilm());

            statement.executeUpdate();
        }
    }

    public void find(String titreFilm, String anneeSortie, String typeCategorieFilm) throws SQLException {
        final String query = String.format("SELECT * FROM FilmAPourCategorie WHERE titreFilm = '%s' AND anneeSortie = '%s' AND typeCategorieFilm = '%s'", titreFilm, Date.valueOf(anneeSortie),
        typeCategorieFilm);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(String titreFilm, String anneeSortie, String typeCategorieFilm) throws SQLException {
        final String query = String.format("SELECT * FROM FilmAPourCategorie WHERE titreFilm = '%s' AND anneeSortie = '%s' AND typeCategorieFilm = '%s'", titreFilm, Date.valueOf(anneeSortie),
        typeCategorieFilm);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
