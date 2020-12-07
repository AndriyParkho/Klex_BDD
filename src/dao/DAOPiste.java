package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Piste;

public class DAOPiste extends DAO<Piste>{

    @Override
    public void create(Piste piste) throws SQLException {
        final String query = "INSERT INTO Piste VALUES (?, ?, INTERVAL ? HOUR TO SECOND, ?, idFichier_seq.currval)";
        // format '09:08:56' pour 9h8m56s

        System.out.println("Statement Piste");
        try (PreparedStatement statementAlbum = this.connection.prepareStatement(query)) {
            statementAlbum.setInt(1, piste.getNum());
            statementAlbum.setString(2, piste.getTitre());
            statementAlbum.setString(3, piste.getDuree());
            statementAlbum.setLong(4, piste.getIdAlbum());
            statementAlbum.executeUpdate();
        }
    }

    @Override
    public ResultSet find(Piste piste) throws SQLException {
        return this.find(piste.getNum(), piste.getIdAlbum());
    }

    public ResultSet find(int numPiste, long idAlbum) throws SQLException {
        final String query = String.format("SELECT * FROM Piste WHERE numPiste = %d AND idAlbum = %ld", numPiste, idAlbum);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }
    
    public ResultSet find(String titrePiste, String titreAlbum) throws SQLException {
        final String query = String.format("SELECT * FROM Piste WHERE titrePiste = %s AND titreAlbum = %s", titrePiste, titreAlbum);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(int numPiste, long idAlbum) throws SQLException {
        final String query = String.format("DELETE FROM Piste WHERE numPiste = %d AND idAlbum = %ld", numPiste, idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
