package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connections.JDBCUtilities;
import tables.Fichier;
import tables.Utilisateur;

public class DAOUtilisateur extends DAO<Utilisateur> {

    @Override
    public Utilisateur create(Utilisateur utilisateur) throws SQLException {
        String insertUtilisateurQuery = "INSERT INTO Utilisateur VALUES (?, ?, ?, ?, ?, ?)";

        if (utilisateur.getNom() == "")
            utilisateur.setNom("default_name");
        if (utilisateur.getPrenom() == "")
            utilisateur.setPrenom("default_surname");
        if (utilisateur.getAge() == 0)
            utilisateur.setAge(50);
        if (utilisateur.getLangueDiffusion() == "")
            utilisateur.setLangueDiffusion("Français");
        if (utilisateur.getCode() == 0)
            utilisateur.setCode(9999);

        try (PreparedStatement statementAlbum = this.connection.prepareStatement(insertUtilisateurQuery)) {
            statementAlbum.setString(1, utilisateur.getEmail());
            statementAlbum.setString(2, utilisateur.getNom());
            statementAlbum.setString(3, utilisateur.getPrenom());
            statementAlbum.setInt(4, utilisateur.getAge());
            statementAlbum.setString(5, utilisateur.getLangueDiffusion());
            statementAlbum.setInt(6, utilisateur.getCode());

            int nbRowsAffected = statementAlbum.executeUpdate();
            if (nbRowsAffected != 1) {
                throw new SQLException("no rows affected");
            }

            // on doit créer les fichiers
            for (Fichier fichier : utilisateur.getFichiers()) {
                DAO<Fichier> fichierDAO = new DAOFichier();
                try {
                    fichier = fichierDAO.create(fichier);
                } catch (SQLException e) {
                    // si le fichier existe déjà alors ne rien faire
                    if (!e.getSQLState().equalsIgnoreCase("23000")) {
                        JDBCUtilities.printSQLException(e);
                    }
                }
            }
            utilisateur = this.find(utilisateur.getEmail());
        }

        connection.commit();

        return utilisateur;
    }

    public Utilisateur find(String email) throws SQLException {
        Utilisateur utilisateur = null;
        String query = "SELECT * FROM Utilisateur LEFT JOIN Fichier ON Utilisateur.email = Fichier.email AND Utilisateur.email = '"
                + email + "'";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                ArrayList<Fichier> fichiers = new ArrayList<Fichier>();
                DAOFichier fichierDAO = new DAOFichier();

                rs.beforeFirst();
                while (rs.next() && rs.getString("idFichier") != null) {
                    fichiers.add(fichierDAO.find(rs.getLong("idFichier")));
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
    public Utilisateur update(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE Utilisateur SET nom = '" + utilisateur.getNom() + "', prenom = '"
                + utilisateur.getPrenom() + "', age = " + utilisateur.getAge() + ", langueDiffusion = '"
                + utilisateur.getLangueDiffusion() + "', code = " + utilisateur.getCode() + " WHERE email = '"
                + utilisateur.getEmail() + "'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
            connection.commit();

            for (Fichier fichier : utilisateur.getFichiers()) {
                DAOFichier fichierDAO = new DAOFichier();
                // Si le fichier n'existe pas, on le crè
                try {
                    fichier = fichierDAO.create(fichier);
                } catch (SQLException e) {
                    // si le fichier existe déjà alors ne rien faire
                    if (!e.getSQLState().equalsIgnoreCase("23000")) {
                        JDBCUtilities.printSQLException(e);
                    }
                }
            }
            utilisateur = this.find(utilisateur.getEmail());
        }
        connection.commit();

        return utilisateur;
    }

    @Override
    public void delete(Utilisateur utilisateur) throws SQLException {
        String queryUtilisateur = "DELETE FROM Utilisateur WHERE email = '" + utilisateur.getEmail() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(queryUtilisateur);

        connection.commit();
    }

}
