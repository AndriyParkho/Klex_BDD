package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Fichier;

public class DAOFichier extends DAO<Fichier> {

    @Override
    public void create(Fichier fichier) throws SQLException {
        final String query = "INSERT INTO Fichier VALUES (idFichier_seq.nextval, ?, ?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setLong(1, fichier.getTaille());
            statement.setDate(2, fichier.getDateDepot());
            statement.setString(3, fichier.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public ResultSet find(Fichier fichier) throws SQLException {
        return this.find(fichier.getId());
    }

    public ResultSet find(long idFichier) throws SQLException {
        final String query = String.format("SELECT * FROM Fichier WHERE idFichier = '%ld'", idFichier);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idFichier) throws SQLException {
        final String query = String.format("DELETE FROM Fichier WHERE idFichier = '%ld'", idFichier);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
