package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.EstUnFilm;

public class DAOEstUnFilm {
    
    public void create(EstUnFilm estUnFilm) throws SQLException {
        final String query = "INSERT INTO EstUnFilm VALUES (idFichier_seq.currval, ?, ?)";

        System.out.println("Statement EstUnFilm\n");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, estUnFilm.getTitreFilm());
            statement.setDate(2, Date.valueOf(estUnFilm.getAnneeSortie()));

            statement.executeUpdate();
        }
    }

    public void find(long idFichier, String titreFilm, String anneeSortie) throws SQLException {
        final String query = String.format("SELECT * FROM FilmAPourCategorie WHERE idFichier = %ld AND titreFilm = '%s' AND anneeSortie = '%s'", idFichier, titreFilm, Date.valueOf(anneeSortie));
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idFichier, String titreFilm, String anneeSortie) throws SQLException {
        final String query = String.format("DELETE FROM FilmAPourCategorie WHERE idFichier = %ld AND titreFilm = '%s' AND anneeSortie = '%s'", idFichier, titreFilm, Date.valueOf(anneeSortie));
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
