package transactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.JDBCUtilities;

public final class Selections {
        public static ResultSet findFilm(Connection connection, String categorie, String marque, String modele,
                        String email) throws SQLException {
                final String filmQuery = String.format("SELECT DISTINCT fil.titreFilm, fil.anneeSortie "
                                + "FROM CategorieFilm cf, FilmAPourCategorie fapc, Film fil, ImgExtraiteFilm ief, "
                                + "Fichier fic, EstUnFilm euf, Flux flu, Codec co, SupporteCodec sc, Client cli, Utilisateur util "
                                + "WHERE cf.typeCategorieFilm = fapc.typeCategorieFilm AND fapc.titreFilm = fil.titreFilm "
                                + "AND fapc.anneeSortie = fil.anneeSortie AND fil.titreFilm = ief.titreFilm AND fil.anneeSortie = ief.anneeSortie "
                                + "AND fil.titreFilm = euf.titreFilm AND fil.anneeSortie = euf.anneeSortie AND fic.idFichier = euf.idFichier "
                                + "AND fic.idFichier = flu.idFichier AND flu.nomCodec = sc.nomCodec AND flu.typeCodec = sc.typeCodec "
                                + "AND util.email = fic.email AND sc.marque = cli.marque AND sc.modele = cli.modele "
                                + "AND cf.typeCategorieFilm = '%s' AND cli.marque = '%s' AND cli.modele = '%s' "
                                + "AND fil.ageMin <= util.age", categorie, marque, modele, email);
                String titreFilm = null;
                Date anneeSortie = null;
                try (ResultSet rs = connection
                                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                                .executeQuery(filmQuery)) {

                        System.out.println(JDBCUtilities.dumpResultSet(rs));
                        rs.beforeFirst();
                        if (rs.next()) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                                try {
                                        System.out.println("Entrer le titre du film: ");
                                        titreFilm = br.readLine();// rs.getString("titreFilm");
                                        System.out.println("Entrer l'année de sortie (format YYYY-MM-DD): ");
                                        anneeSortie = Date.valueOf(br.readLine());// rs.getDate("anneeSortie");
                                } catch (IOException e) {
                                        System.out.println("Erreur d'entrée");
                                }

                                System.out.println("TitreFilm: " + titreFilm + ", AnneeSortie: " + anneeSortie);
                                final String fichiersQuery = String.format(
                                                "SELECT Film.titreFilm, Film.anneeSortie, Film.resume, Film.ageMin, Film.urlAffiche, Fichier.taille, Fichier.dateDepot "
                                                                + "FROM Film LEFT JOIN EstUnFilm ON Film.titreFilm = EstUnFilm.titreFilm AND Film.anneeSortie = EstUnFilm.anneeSortie "
                                                                + "INNER JOIN Fichier ON Fichier.idFichier = EstUnFilm.idFichier AND Film.titreFilm = '%s' AND "
                                                                + "Film.anneeSortie = TO_DATE('%s', 'YYYY-MM-DD')",
                                                titreFilm, anneeSortie);
                                return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_UPDATABLE).executeQuery(fichiersQuery);
                        }
                }
                return null;
        }

        public static ResultSet findPiste(Connection connection, String categorie, String marque, String modele,
                        String email) throws SQLException {
                final String pisteQuery = String.format("SELECT DISTINCT p.numPiste, a.idAlbum "
                                + "FROM CategorieMusique cm, Piste p, Album a, AlbumAPourCategorie aapc, PisteAPourCategorie papc, "
                                + "EstUnePiste eup, Fichier fic, Flux flu, Codec co, SupporteCodec sc, Client cli, Utilisateur util "
                                + "WHERE cm.typeCategorieMusique = aapc.typeCategorieMusique AND cm.typeCategorieMusique = papc.typeCategorieMusique "
                                + "AND a.idAlbum = aapc.idAlbum AND p.idAlbum = a.idAlbum AND p.numPiste = papc.numPiste "
                                + "AND p.numPiste = eup.numPiste AND fic.idFichier = eup.idFichier AND fic.idFichier = flu.idFichier "
                                + "AND flu.nomCodec = sc.nomCodec AND flu.typeCodec = sc.typeCodec AND util.email = fic.email "
                                + "AND sc.marque = cli.marque AND sc.modele = cli.modele "
                                + "AND cm.typeCategorieMusique = '%s' AND cli.marque = '%s' AND cli.modele = '%s'",
                                categorie, marque, modele, email);
                Integer numPiste = null;
                Long idAlbum = null;
                try (ResultSet rs = connection
                                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                                .executeQuery(pisteQuery)) {
                        System.out.println(JDBCUtilities.dumpResultSet(rs));
                        rs.beforeFirst();
                        if (rs.next()) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                                try {
                                        System.out.println("Entrer le numéro de la Piste: ");
                                        numPiste = Integer.parseInt(br.readLine()); // rs.getInt("numPiste");
                                        System.out.println("Entrer l'identifiant de l'Album: ");
                                        idAlbum = Long.parseLong(br.readLine()); // rs.getLong("idAlbum");
                                } catch (IOException e) {
                                        System.out.println("Erreur d'entrée");
                                }

                                System.out.println("NumPiste: " + numPiste + ", idAlbum: " + idAlbum);
                                final String fichiersQuery = String.format(
                                                "SELECT Piste.numPiste, Piste.titrePiste, Album.titreAlbum, Piste.dureePiste, Album.nomGroupe, Album.dateSortieAlbum, "
                                                                + "Album.urlImagePochette, Fichier.taille, Fichier.dateDepot "
                                                                + "FROM Piste LEFT JOIN EstUnePiste ON Piste.numPiste = EstUnePiste.numPiste AND Piste.idAlbum = EstUnePiste.idAlbum "
                                                                + "INNER JOIN Fichier ON Fichier.idFichier = EstUnePiste.idFichier AND Piste.numPiste = %d AND Piste.idAlbum = %d "
                                                                + "JOIN Album ON Piste.idAlbum = Album.idAlbum",
                                                numPiste, idAlbum);
                                return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                                                .executeQuery(fichiersQuery);
                        }
                }
                return null;
        }
}
