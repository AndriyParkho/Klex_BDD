package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PisteAPourCategorie;

public class DAOPisteAPourCategorie extends DAO<PisteAPourCategorie> {

    @Override
    public void create(PisteAPourCategorie pisteAPourCategorie) throws SQLException {
        final String query = "INSERT INTO PisteAPourCategorie VALUES (?, idAlbum_seq.currval, ?)";

        System.out.println("Statement PisteAPourCategorie");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, pisteAPourCategorie.getNumPiste());
            statement.setString(2, pisteAPourCategorie.getTypeCategorieMusique());

            statement.executeUpdate();
        }
    }

    @Override
    public ResultSet find(PisteAPourCategorie pisteAPourCategorie) throws SQLException {
        return this.find(pisteAPourCategorie.getNumPiste(), pisteAPourCategorie.getIdAlbum(),
                pisteAPourCategorie.getTypeCategorieMusique());
    }

    public ResultSet find(int numPiste, long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format(
                "SELECT * FROM PisteAPourCategorie WHERE numPiste = %d AND idAlbum = %ld AND typeCategorieMusique = '%s'",
                numPiste, idAlbum, typeCategorieMusique);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(int numPiste, long idAlbum, String typeCategorieMusique) throws SQLException {
        final String query = String.format(
                "DELETE FROM PisteAPourCategorie WHERE numPiste = %d AND idAlbum = %ld AND typeCategorieMusique = '%s'",
                numPiste, idAlbum, typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
