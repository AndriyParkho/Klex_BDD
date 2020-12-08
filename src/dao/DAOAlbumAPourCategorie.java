package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.AlbumAPourCategorie;

public class DAOAlbumAPourCategorie extends DAO<AlbumAPourCategorie> {

    @Override
    public void create(AlbumAPourCategorie albumAPourCategorie) throws SQLException {
        final String query = "INSERT INTO AlbumAPourCategorie VALUES (?, ?)";

        System.out.println("Statement AlbumAPourCategorie\n");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setLong(1, albumAPourCategorie.getIdAlbum());
            statement.setString(2, albumAPourCategorie.getTypeCategorieMusique());
            statement.executeUpdate();
        }
    }

    public void createOrUpdate(AlbumAPourCategorie albumAPourCategorie) throws SQLException {
        try {
            this.create(albumAPourCategorie);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(albumAPourCategorie + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(AlbumAPourCategorie albumAPourCategorie) throws SQLException {
        return this.find(albumAPourCategorie.getIdAlbum(), albumAPourCategorie.getTypeCategorieMusique());
    }

    public ResultSet find(long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format(
                "SELECT * FROM AlbumAPourCategorie WHERE idAlbum = %ld AND typeCategorieMusique = '%s'", idAlbum,
                typeCategorieMusique);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format(
                "DELETE FROM AlbumAPourCategorie WHERE idAlbum = %ld AND typeCategorieMusique = '%s'", idAlbum,
                typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
