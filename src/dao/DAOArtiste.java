package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Artiste;

public class DAOArtiste extends DAO<Artiste> {

    @Override
    public void create(Artiste artiste) throws SQLException {
        // on check si l'artiste existe déjà
        boolean artisteAlreadyExist = false;
        int nextID_from_seq = 0;
        try (ResultSet rs = this.find(artiste.getNom())) {
            if (rs.next()) {
                artisteAlreadyExist = true;
                nextID_from_seq = rs.getInt(1);
                artiste.setId(nextID_from_seq);
            }
        }

        // si l'album n'existe pas on le crè
        if (!artisteAlreadyExist) {
            String queryId = "SELECT idArtiste_seq.nextval from DUAL";
            try (ResultSet rs = this.connection.prepareStatement(queryId).executeQuery()) {
                if (rs.next()) {
                    nextID_from_seq = rs.getInt(1);
                    artiste.setId(nextID_from_seq);
                }
            }

            final String query = "INSERT INTO Artiste VALUES (idArtiste_seq.currval, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = this.connection.prepareStatement(query)) {
                statement.setString(1, artiste.getNom());
                statement.setDate(2, artiste.getDateNaissance());
                statement.setString(3, artiste.getUrlPhoto());
                statement.setString(4, artiste.getSpecialite());
                statement.setString(5, artiste.getBiographie());
                statement.executeUpdate();
            }
        }
    }

    public void createOrUpdate(Artiste artiste) throws SQLException {
        try {
            this.create(artiste);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(artiste + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(Artiste artiste) throws SQLException {
        return this.find(artiste.getId());
    }

    public ResultSet find(long idArtiste) throws SQLException {
        final String query = String.format("SELECT * FROM Artiste WHERE idArtiste = %d", idArtiste);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }
    
    public ResultSet find(String nomArtiste) throws SQLException {
        final String query = String.format("SELECT * FROM Artiste WHERE nomArtiste = '%s'", nomArtiste);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idArtiste) throws SQLException {
        final String query = String.format("DELETE FROM Artiste WHERE idArtiste = %d", idArtiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
