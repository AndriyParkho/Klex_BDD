package transactions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFactory;

public final class TransactionDeletes {

    public static void deleteFilm(String titreFilm, Date anneeSortie) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            // suppression des fichiers
            final String query = String.format("DELETE FROM (SELECT Fichier.idFichier FROM Fichier "
                    + "LEFT JOIN EstUnFilm ON Fichier.idFichier = EstUnFilm.idFichier "
                    + "INNER JOIN Film ON Film.titreFilm = EstUnFilm.titreFilm AND Film.anneeSortie = EstUnFilm.anneeSortie "
                    + "AND Film.titreFilm = '%s' AND Film.anneeSortie = TO_DATE('%s', 'YYYY-MM-DD'))", titreFilm,
                    anneeSortie);
            connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                    .executeUpdate(query);

            DAOFactory.getFilmDAO().delete(titreFilm, anneeSortie);

            // suppression des artistes
            final String queryArtistes = "DELETE FROM Artiste WHERE (idArtiste) NOT IN ("
                    + "SELECT idArtiste FROM APourRole UNION SELECT idArtiste FROM APourInstrument)";
            connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                    .executeUpdate(queryArtistes);

            connection.commit();
        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    JDBCUtilities.printSQLException(excep);
                }
            }
        } finally {
            ConnectionOracle.closeInstance();
        }
    }

    public static void deletePiste(String titrePiste, String titreAlbum) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            // suppression du fichier et de la piste associée (CASCADE)
            final String queryFichiers = String.format("DELETE FROM (SELECT Fichier.idFichier FROM Fichier "
                    + "LEFT JOIN EstUnePiste ON Fichier.idFichier = EstUnePiste.idFichier "
                    + "INNER JOIN Piste ON Piste.numPiste = EstUnePiste.numPiste AND Piste.idAlbum = EstUnePiste.idAlbum "
                    + "AND Piste.titrePiste = '%s' JOIN Album ON Piste.idAlbum = Album.idAlbum AND Album.titreAlbum = '%s')",
                    titrePiste, titreAlbum);
            connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                    .executeUpdate(queryFichiers);

            // suppression des albums
            final String queryAlbum = "DELETE FROM Album WHERE (idAlbum) NOT IN (SELECT idAlbum FROM Piste)";
            connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                    .executeUpdate(queryAlbum);

            // suppression des artistes
            final String queryArtistes = "DELETE FROM Artiste WHERE (idArtiste) NOT IN ("
                    + "SELECT idArtiste FROM APourRole UNION SELECT idArtiste FROM APourInstrument)";
            connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                    .executeUpdate(queryArtistes);

            connection.commit();
        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    JDBCUtilities.printSQLException(excep);
                }
            }
        } finally {
            ConnectionOracle.closeInstance();
        }
    }
}
