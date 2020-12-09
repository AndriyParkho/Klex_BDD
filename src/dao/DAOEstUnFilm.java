package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EstUnFilm;

public class DAOEstUnFilm extends DAO<EstUnFilm> {

    @Override
    public void create(EstUnFilm estUnFilm) throws SQLException {
        final String query = "INSERT INTO EstUnFilm VALUES (idFichier_seq.currval, ?, ?)";

        System.out.println("Statement EstUnFilm\n");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, estUnFilm.getTitreFilm());
            statement.setDate(2, estUnFilm.getAnneeSortie());
            statement.executeUpdate();
        }
    }

    @Override
    public ResultSet find(EstUnFilm estUnFilm) throws SQLException {
        return this.find(estUnFilm.getIdFichier(), estUnFilm.getTitreFilm(), estUnFilm.getAnneeSortie());
    }

    public ResultSet find(long idFichier, String titreFilm, Date anneeSortie) throws SQLException {
        final String query = String.format(
                "SELECT * FROM EstUnFilm WHERE idFichier = %l AND titreFilm = '%s' AND anneeSortie = TO_DATE('%s', 'YYYY-MM-DD')",
                idFichier, titreFilm, anneeSortie);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idFichier, String titreFilm, Date anneeSortie) throws SQLException {
        final String query = String.format(
                "DELETE FROM EstUnFilm WHERE idFichier = %l AND titreFilm = '%s' AND anneeSortie = TO_DATE('%s', 'YYYY-MM-DD')",
                idFichier, titreFilm, anneeSortie);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
