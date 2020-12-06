package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;

import connections.JDBCUtilities;
import model.SupporteCodec;

public class DAOSupporteCodec {
    
    public void create(SupporteCodec supporteCodec) throws SQLException {
        final String query = "INSERT INTO SupporteCodec VALUES (?, ?, ?, ?)";

        System.out.println("Statement Album\n");
        try (PreparedStatement statementAlbum = this.connection.prepareStatement(query)) {
            statementAlbum.setString(1, supporteCodec.getMarque());
            statementAlbum.setString(2, supporteCodec.getModele());
            statementAlbum.setString(3, supporteCodec.getNomCodec());
            statementAlbum.setString(4, supporteCodec.gettypeCodec());

            statementAlbum.executeUpdate();
        }
    }

    public void find(String marque, String modele, String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format("SELECT * FROM SupporteCodec WHERE marque = '%s' AND modele = '%s' AND nomCodec = '%s' AND typeCodec = '%s'", marque, modele, nomCodec, typeCodec);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(String marque, String modele, String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format("DELETE FROM SupporteCodec WHERE marque = '%s' AND modele = '%s' AND nomCodec = '%s' AND typeCodec = '%s'", marque, modele, nomCodec, typeCodec);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
