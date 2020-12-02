package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.JDBCUtilities;
import tables.Fichier;
import tables.Utilisateur;

public class DAOFichier extends DAO<Fichier> {

    @Override
    public Fichier create(Fichier fichier) throws SQLException {
        String insertFichierQuery = "INSERT INTO Fichier (taille, dateDepot, email) VALUES (?, TO_DATE(?, 'dd/mm/yyyy'), ?)";

        // création de l'utilisateur s'il n'existe pas
        Utilisateur utilisateur = new Utilisateur(fichier.getEmail());
        DAO<Utilisateur> utilisateurDAO = new DAOUtilisateur();
        try {
            utilisateur = utilisateurDAO.create(utilisateur);
        } catch (SQLException e) {
            // si l'utilisateur existe déjà alors ne rien faire
            if (!e.getSQLState().equalsIgnoreCase("23000")) {
                JDBCUtilities.printSQLException(e);
            }
        }

        try (PreparedStatement statementAlbum = this.connection.prepareStatement(insertFichierQuery,
                new String[] { "idFichier" })) {
            statementAlbum.setLong(1, fichier.getTaille());
            statementAlbum.setString(2, fichier.getDateDepot());
            statementAlbum.setString(3, fichier.getEmail());

            int nbRowsAffected = statementAlbum.executeUpdate();
            Long createdId = null;
            if (nbRowsAffected == 1) {
                try (ResultSet rs = statementAlbum.getGeneratedKeys()) {
                    rs.next();
                    createdId = rs.getLong(1);
                    fichier.setId(createdId);
                }
            } else {
                throw new SQLException("no rows affected");
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Fichier fichier) throws SQLException {
        // TODO Auto-generated method stub

    }

}
