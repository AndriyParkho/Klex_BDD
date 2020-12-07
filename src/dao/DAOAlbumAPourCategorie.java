package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.AlbumAPourCategorie;

public class DAOAlbumAPourCategorie extends DAO<AlbumAPourCategorie> {

    @Override
    public void create(AlbumAPourCategorie albumAPourCategorie) throws SQLException {
        final String query = "INSERT INTO AlbumAPourCategorie VALUES (idAlbum_seq.currval, ?)";

        System.out.println("Statement AlbumAPourCategorie\n");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, albumAPourCategorie.getTypeCategorieMusique());

            statement.executeUpdate();
        }
    }

    public void find(long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format("SELECT * FROM AlbumAPourCategorie WHERE idAlbum = %ld AND typeCategorieMusique = '%s'", idAlbum, typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format("DELETE FROM AlbumAPourCategorie WHERE idAlbum = %ld AND typeCategorieMusique = '%s'", idAlbum, typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
