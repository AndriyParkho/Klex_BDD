package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.SupporteCodec;

public class DAOSupporteCodec extends DAO<SupporteCodec> {

    @Override
    public void create(SupporteCodec supporteCodec) throws SQLException {
        final String query = "INSERT INTO SupporteCodec VALUES (?, ?, ?, ?)";

        System.out.println("Statement Album\n");
        try (PreparedStatement statementAlbum = this.connection.prepareStatement(query)) {
            statementAlbum.setString(1, supporteCodec.getMarque());
            statementAlbum.setString(2, supporteCodec.getModele());
            statementAlbum.setString(3, supporteCodec.getNomCodec());
            statementAlbum.setString(4, supporteCodec.getTypeCodec());
            statementAlbum.executeUpdate();
        }
    }

    public void createOrUpdate(SupporteCodec supporteCodec) throws SQLException {
        try {
            this.create(supporteCodec);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(supporteCodec + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(SupporteCodec supporteCodec) throws SQLException {
        return this.find(supporteCodec.getMarque(), supporteCodec.getModele(), supporteCodec.getNomCodec(),
                supporteCodec.getTypeCodec());
    }

    public ResultSet find(String marque, String modele, String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format(
                "SELECT * FROM SupporteCodec WHERE marque = '%s' AND modele = '%s' AND nomCodec = '%s' AND typeCodec = '%s'",
                marque, modele, nomCodec, typeCodec);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String marque, String modele, String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format(
                "DELETE FROM SupporteCodec WHERE marque = '%s' AND modele = '%s' AND nomCodec = '%s' AND typeCodec = '%s'",
                marque, modele, nomCodec, typeCodec);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
