package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;

import connections.JDBCUtilities;
import model.Fichier;
import model.Utilisateur;

public class DAOUtilisateur extends DAO<Utilisateur> {

    @Override
    public void create(Utilisateur utilisateur) throws SQLException {
        final String query = "INSERT INTO Utilisateur VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getEmail());
            statement.setString(2, utilisateur.getNom());
            statement.setString(3, utilisateur.getPrenom());
            statement.setInt(4, utilisateur.getAge());
            statement.setString(5, utilisateur.getLangueDiffusion());
            statement.setInt(6, utilisateur.getCode());

            statement.executeUpdate();
        }
        // on doit créer les fichiers
        for (Fichier fichier : utilisateur.getFichiers()) {
            DAOFactory.getFichierDAO().createOrUpdate(fichier);
        }

    }

    @Override
    public void createOrUpdate(Utilisateur utilisateur) throws SQLException {
        try {
            this.create(utilisateur);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(utilisateur + " est déjà dans la BDD. On l'update.");
                this.update(utilisateur);
            }
        }
    }

    @Override
    public Utilisateur find(final Utilisateur utilisateur) throws SQLException {
        return this.find(utilisateur.getEmail());
    }

    public Utilisateur find(final String email) throws SQLException {
        Utilisateur utilisateur = null;
        final String query = "SELECT * FROM Utilisateur LEFT JOIN Fichier ON Utilisateur.email = Fichier.email AND Utilisateur.email = '"
                + email + "'";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                final HashSet<Fichier> fichiers = new HashSet<Fichier>();

                rs.beforeFirst();
                while (rs.next() && rs.getString("idFichier") != null) {
                    fichiers.add(DAOFactory.getFichierDAO().find(rs.getLong("idFichier")));
                }
                // on se replace
                rs.first();

                utilisateur = new Utilisateur(email, rs.getString("nom"), rs.getString("prenom"), rs.getInt("age"),
                        rs.getString("langueDiffusion"), rs.getInt("code"), fichiers);
            }
        }
        connection.commit();

        return utilisateur;
    }

    @Override
    public void update(Utilisateur utilisateur) throws SQLException {
        final String query = "UPDATE Utilisateur SET nom = '" + utilisateur.getNom() + "', prenom = '"
                + utilisateur.getPrenom() + "', age = " + utilisateur.getAge() + ", langueDiffusion = '"
                + utilisateur.getLangueDiffusion() + "', code = " + utilisateur.getCode() + " WHERE email = '"
                + utilisateur.getEmail() + "'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
        for (Fichier fichier : utilisateur.getFichiers()) {
            // Si le fichier n'existe pas, on le crè
            DAOFactory.getFichierDAO().createOrUpdate(fichier);
        }
    }

    @Override
    public void delete(final Utilisateur utilisateur) throws SQLException {
        final String query = "DELETE FROM Utilisateur WHERE email = '" + utilisateur.getEmail() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
