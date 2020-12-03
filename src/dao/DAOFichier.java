package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import tables.Fichier;
import tables.Utilisateur;

public class DAOFichier extends DAO<Fichier> {

    @Override
    public Fichier create(Fichier fichier) throws SQLException {
        final String query = "INSERT INTO Fichier (taille, dateDepot, email) VALUES (?, TO_DATE(?, 'dd/mm/yyyy'), ?)";

        // création de l'utilisateur s'il n'existe pas
        Utilisateur utilisateur = new Utilisateur(fichier.getEmail());
        utilisateur = DAOFactory.getUtilisateurDAO().createOrUpdate(utilisateur);

        try (PreparedStatement statement = this.connection.prepareStatement(query, new String[] { "idFichier" })) {
            statement.setLong(1, fichier.getTaille());
            statement.setString(2, fichier.getDateDepot());
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
            fichier = this.find(createdId);
        }

        connection.commit();

        return fichier;
    }

    public Fichier createOrUpdate(Fichier fichier) throws SQLException {
        try {
            fichier = this.create(fichier);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                this.update(fichier);
            }
        }
        return fichier;
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
                fichier = new Fichier(id, rs.getLong("taille"), rs.getString("dateDepot"), rs.getString("email"));
            }
        }
        connection.commit();

        return fichier;
    }

    @Override
    public Fichier update(Fichier fichier) throws SQLException {
        final String query = "UPDATE Fichier SET taille = " + fichier.getTaille() + ", dateDepot = TO_DATE('"
                + fichier.getDateDepot() + "', 'YYYY-MM-DD HH24:MI:SS'), email = '" + fichier.getEmail()
                + "' WHERE idFichier = " + fichier.getId();

        // création de l'utilisateur s'il n'existe pas
        Utilisateur utilisateur = new Utilisateur(fichier.getEmail());
        utilisateur = DAOFactory.getUtilisateurDAO().createOrUpdate(utilisateur);

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
            connection.commit();
            fichier = this.find(fichier.getId());
        }

        return fichier;
    }

    @Override
    public void delete(final Fichier fichier) throws SQLException {
        final String query = "DELETE FROM Fichier WHERE email = '" + fichier.getEmail() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
        this.connection.commit();
    }

}
