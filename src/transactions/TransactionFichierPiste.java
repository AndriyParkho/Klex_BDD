package transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFactory;
import model.APourInstrument;
import model.AlbumAPourCategorie;
import model.Artiste;
import model.CategorieMusique;
import model.EstUnePiste;
import model.Flux;
import model.FluxAudio;
import model.FluxTexte;
import model.FluxVideo;
import model.PisteAPourCategorie;
import model.aggregates.FichierPiste;

public final class TransactionFichierPiste {
    public static void execute(FichierPiste fichierPiste) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            // fichier, piste, album et lien
            DAOFactory.getFichierDAO().create(fichierPiste.getFichier());
            DAOFactory.getPisteDAO().create(fichierPiste.getPiste());
            DAOFactory.getAlbumDAO().create(fichierPiste.getAlbum());
            DAOFactory.getEstUnePiste().create(new EstUnePiste(fichierPiste.getFichier().getId(),
                    fichierPiste.getPiste().getNum(), fichierPiste.getPiste().getIdAlbum()));

            for (Flux flux : fichierPiste.getFlux()) {
                DAOFactory.getFlux().create(flux);
                if (flux instanceof FluxAudio) {
                    DAOFactory.getFluxAudio().create((FluxAudio) flux);
                } else if (flux instanceof FluxVideo) {
                    DAOFactory.getFluxVideo().create((FluxVideo) flux);
                } else if (flux instanceof FluxTexte) {
                    DAOFactory.getFluxTexte().create((FluxTexte) flux);
                }
            }

            // on doit créer les catégories et les liens
            for (CategorieMusique categorieMusique : fichierPiste.getCategories()) {
                DAOFactory.getCategorieMusiqueDAO().createOrUpdate(categorieMusique);
                DAOFactory.getAlbumAPourCategorie().create(
                        new AlbumAPourCategorie(fichierPiste.getAlbum().getId(), categorieMusique.getCategorie()));
                DAOFactory.getPisteAPourCategorie().create(new PisteAPourCategorie(fichierPiste.getPiste().getNum(),
                        fichierPiste.getAlbum().getId(), categorieMusique.getCategorie()));
            }

            // on doit créer les artistes et les liens
            for (Map.Entry<Artiste, String> entry : fichierPiste.getArtistes().entrySet()) {
                DAOFactory.getArtisteDAO().createOrUpdate(entry.getKey());
                DAOFactory.getAPourInstrument().create(new APourInstrument(entry.getKey().getId(),
                        fichierPiste.getPiste().getNum(), fichierPiste.getAlbum().getId(), entry.getValue()));
            }

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
