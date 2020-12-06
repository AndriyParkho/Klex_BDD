package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.APourRole;

public class DAOAPourRole {

    public void create(APourRole aPourRole) throws SQLException {
        final String query = "INSERT INTO APourRole VALUES (?, ?, ?, idArtiste_seq.currval)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, aPourRole.getRoleFilm());
            statement.setString(2, aPourRole.getTitreFilm());
            statement.setDate(3, Date.valueOf(aPourRole.getAnneeSortie()));
            
            statement.executeUpdate();
        }
    }

    public void find(String titreFilm, String anneeSortie, long idArtiste) throws SQLException {
        final String query = String.format("SELECT * FROM APourRole WHERE titreFilm = '%s' AND anneeSortie = '%s' AND idArtiste = %ld", titreFilm, Date.valueOf(anneeSortie), idArtiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(String urlImg, String titreFilm, String anneeSortie) throws SQLException {
        final String query = String.format("DELETE FROM APourRole WHERE titreFilm = '%s' AND anneeSortie = '%s' AND idArtiste = %ld", titreFilm, Date.valueOf(anneeSortie), idArtiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
