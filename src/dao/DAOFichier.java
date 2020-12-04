package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Fichier;
import model.Utilisateur;

public class DAOFichier extends DAO<Fichier> {

    @Override
    public void create(Fichier fichier) throws SQLException {
        final String query = "INSERT INTO Fichier (taille, dateDepot, email) VALUES (?, ?, ?)";

        // création de l'utilisateur s'il n'existe pas
        Utilisateur utilisateur = new Utilisateur(fichier.getEmail());
        DAOFactory.getUtilisateurDAO().createOrUpdate(utilisateur);
        connection.commit(); // NECESSARY because Fichier references Utilisateur.email

        try (PreparedStatement statement = this.connection.prepareStatement(query, new String[] { "idFichier" })) {
            statement.setLong(1, fichier.getTaille());
            statement.setDate(2, fichier.getDateDepot());
            statement.setString(3, fichier.getEmail());

            final int nbRowsAffected = statement.executeUpdate();
            Long createdId = null;
            if (nbRowsAffected == 1) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    createdId = rs.getLong(1);
                    fichier.setId(createdId);
                }
            }
        }
    }

    public void createOrUpdate(Fichier fichier) throws SQLException {
        try {
            this.create(fichier);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(fichier + " est déjà dans la BDD. On l'update.");
                this.update(fichier);
            }
        }
    }

    @Override
    public Fichier find(final Fichier fichier) throws SQLException {
        return this.find(fichier.getId());
    }

    public Fichier find(final long id) throws SQLException {
        Fichier fichier = null;
        final String query = "SELECT * FROM Fichier WHERE idFichier = " + id;
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                fichier = new Fichier(id, rs.getLong("taille"), rs.getDate("dateDepot"), rs.getString("email"), null);
            }
        }
        connection.commit();

        return fichier;
    }

    @Override
    public void update(Fichier fichier) throws SQLException {
        final String query = "UPDATE Fichier SET taille = " + fichier.getTaille() + ", dateDepot = TO_DATE('"
                + fichier.getDateDepot() + "', 'YYYY-MM-DD'), email = '" + fichier.getEmail()
                + "' WHERE idFichier = " + fichier.getId();

        // création de l'utilisateur s'il n'existe pas
        Utilisateur utilisateur = new Utilisateur(fichier.getEmail());
        DAOFactory.getUtilisateurDAO().createOrUpdate(utilisateur);

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(final Fichier fichier) throws SQLException {
        final String query = "DELETE FROM Fichier WHERE email = '" + fichier.getEmail() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
