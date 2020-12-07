package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.APourRole;

public class DAOAPourRole extends DAO<APourRole> {

    @Override
    public void create(APourRole aPourRole) throws SQLException {
        final String query = "INSERT INTO APourRole VALUES (?, ?, ?, idArtiste_seq.currval)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, aPourRole.getRole());
            statement.setString(2, aPourRole.getTitreFilm());
            statement.setDate(3, aPourRole.getAnneeSortie());

            statement.executeUpdate();
        }
    }

    @Override
    public ResultSet find(APourRole aPourRole) throws SQLException {
        return this.find(aPourRole.getTitreFilm(), aPourRole.getAnneeSortie(), aPourRole.getIdArtiste());
    }

    public ResultSet find(String titreFilm, Date anneeSortie, long idArtiste) throws SQLException {
        final String query = String.format(
                "SELECT * FROM APourRole WHERE titreFilm = '%s' AND anneeSortie = '%s' AND idArtiste = %ld", titreFilm,
                anneeSortie, idArtiste);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String titreFilm, Date anneeSortie, long idArtiste) throws SQLException {
        final String query = String.format(
                "DELETE FROM APourRole WHERE titreFilm = '%s' AND anneeSortie = '%s' AND idArtiste = %ld", titreFilm,
                anneeSortie, idArtiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
