package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.PisteAPourCategorie;

public class DAOPisteAPourCategorie extends DAO<PisteAPourCategorie> {

    @Override
    public void create(PisteAPourCategorie pisteAPourCategorie) throws SQLException {
        final String query = "INSERT INTO PisteAPourCategorie VALUES (idPiste_seq.currval, idAlbum_seq.currval, ?)";

        System.out.println("Statement PisteAPourCategorie");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, pisteAPourCategorie.getTypeCategorieMusique());

            statement.executeUpdate();
        }
    }

    public void find(long idPiste, long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format("SELECT * FROM PisteAPourCategorie WHERE idPiste = %ld AND idAlbum = %ld AND typeCategorieMusique = '%s'", idPiste, idAlbum, typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idPiste, long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format("DELETE FROM PisteAPourCategorie WHERE idPiste = %ld AND idAlbum = %ld AND typeCategorieMusique = '%s'", idPiste, idAlbum, typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
