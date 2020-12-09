package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EstUnePiste;

public class DAOEstUnePiste extends DAO<EstUnePiste> {

    @Override
    public void create(EstUnePiste estUnePiste) throws SQLException {
        final String query = "INSERT INTO EstUnePiste VALUES (idFichier_seq.currval, ?, ?)";

        System.out.println("Statement EstUnePiste\n");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, estUnePiste.getNumPiste());
            statement.setLong(2, estUnePiste.getIdAlbum());
            statement.executeUpdate();
        }
    }

    @Override
    public ResultSet find(EstUnePiste estUnePiste) throws SQLException {
        return this.find(estUnePiste.getIdFichier(), estUnePiste.getNumPiste(), estUnePiste.getIdAlbum());
    }

    public ResultSet find(long idFichier, long numPiste, long idAlbum) throws SQLException {
        final String query = String.format(
                "SELECT * FROM EstUnePiste WHERE idFichier = %l AND numPiste = %l AND idAlbum = %l", idFichier,
                numPiste, idAlbum);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idFichier, long numPiste, long idAlbum) throws SQLException {
        final String query = String.format(
                "DELETE FROM EstUnePiste WHERE idFichier = %l AND numPiste = %l AND idAlbum = %l", idFichier,
                numPiste, idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
