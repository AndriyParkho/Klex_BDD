package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import tables.Artiste;

public class DAOArtiste extends DAO<Artiste> {

    @Override
    public void create(Artiste artiste) throws SQLException {
        final String query = "INSERT INTO Artiste (nomArtiste, dateNaissance, urlPhoto, specialite, biographie) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query, new String[] { "idArtiste" })) {
            statement.setString(1, artiste.getNom());
            statement.setDate(2, artiste.getDateNaissance());
            statement.setString(3, artiste.getUrlPhoto());
            statement.setString(4, artiste.getSpecialite());
            statement.setString(5, artiste.getBiographie());

            final int nbRowsAffected = statement.executeUpdate();
            Long createdId = null;
            if (nbRowsAffected == 1) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    createdId = rs.getLong(1);
                    artiste.setId(createdId);
                }
            }
        }
    }

    @Override
    public void createOrUpdate(Artiste artiste) throws SQLException {
        try {
            this.create(artiste);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(artiste + " est déjà dans la BDD. On l'update.");
                this.update(artiste);
            }
        }
    }

    @Override
    public Artiste find(final Artiste artiste) throws SQLException {
        return this.find(artiste.getId());
    }

    public Artiste find(final long id) throws SQLException {
        Artiste artiste = null;
        final String query = "SELECT * FROM Artiste WHERE idArtiste = " + id;
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                artiste = new Artiste(id, rs.getString("nomArtiste"), rs.getDate("dateNaissance"),
                        rs.getString("urlPhoto"), rs.getString("specialite"), rs.getString("biographie"));
            }
        }
        connection.commit();

        return artiste;
    }

    @Override
    public void update(Artiste artiste) throws SQLException {
        // attributs optionnels
        String dateNaissance = "TO_DATE('" + artiste.getDateNaissance() + "', 'YYYY-MM-DD')";
        if (artiste.getDateNaissance() == null)
            dateNaissance = "NULL";
        if (artiste.getBiographie() == null)
            artiste.setBiographie("");

        final String query = "UPDATE Artiste SET nomArtiste = '" + artiste.getNom() + "', dateNaissance = "
                + dateNaissance + ", urlPhoto = '" + artiste.getUrlPhoto()
                + "', specialite = '" + artiste.getSpecialite() + "', biographie = '" + artiste.getBiographie()
                + "' WHERE idArtiste = " + artiste.getId();
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(final Artiste artiste) throws SQLException {
        final String query = "DELETE FROM Artiste WHERE idArtiste = " + artiste.getId();
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
