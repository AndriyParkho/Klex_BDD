package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tables.Fichier;
import tables.Utilisateur;

public class DAOFichier extends DAO<Fichier> {

    @Override
    public Fichier create(Fichier fichier) throws SQLException {
        // String query = "INSERT INTO Fichier (taille, dateDepot, email) SELECT ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ? FROM dual WHERE NOT EXISTS (SELECT * FROM Fichier WHERE idFichier = ?)";
        String query = "INSERT INTO Fichier (taille, dateDepot, email) VALUES (?, TO_DATE(?, 'dd/mm/yyyy'), ?)";

        // création de l'utilisateur s'il n'existe pas
        Utilisateur utilisateur = new Utilisateur(fichier.getEmail());
        DAO<Utilisateur> utilisateurDAO = new DAOUtilisateur();
        utilisateur = utilisateurDAO.create(utilisateur);

        try (PreparedStatement statement = this.connection.prepareStatement(query, new String[] { "idFichier" })) {
            statement.setLong(1, fichier.getTaille());
            statement.setString(2, fichier.getDateDepot());
            statement.setString(3, fichier.getEmail());

            int nbRowsAffected = statement.executeUpdate();
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

    public Fichier find(long id) throws SQLException {
        Fichier fichier = null;
        String query = "SELECT * FROM Fichier WHERE idFichier = " + id;
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
        String query = "UPDATE Fichier SET taille = " + fichier.getTaille() + ", dateDepot = TO_DATE('"
                + fichier.getDateDepot() + "', 'YYYY-MM-DD HH24:MI:SS'), email = '" + fichier.getEmail()
                + "' WHERE idFichier = " + fichier.getId();

        // création de l'utilisateur s'il n'existe pas
        Utilisateur utilisateur = new Utilisateur(fichier.getEmail());
        DAO<Utilisateur> utilisateurDAO = new DAOUtilisateur();
        utilisateur = utilisateurDAO.create(utilisateur);

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
            connection.commit();
            fichier = this.find(fichier.getId());
        }

        return fichier;
    }

    @Override
    public void delete(Fichier fichier) throws SQLException {
        String queryFichier = "DELETE FROM Fichier WHERE email = '" + fichier.getEmail() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(queryFichier);
    }

}
