package transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFactory;
import model.APourRole;
import model.Artiste;
import model.CategorieFilm;
import model.EstUnFilm;
import model.FilmAPourCategorie;
import model.Flux;
import model.FluxAudio;
import model.FluxTexte;
import model.FluxVideo;
import model.ImgExtraiteFilm;
import model.aggregates.FichierFilm;

public final class TransactionFichierFilm {
    public static void execute(FichierFilm fichierFilm) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            // ImgExtraiteFilm references Film (titreFilm, anneeSortie)
            // FilmAPourCategorie references CategorieFilm (typeCategorieFilm) and Film
            // (titreFilm, anneeSortie)
            // APourRole references Artiste (idArtiste) and Film (titreFilm, anneeSortie)

            DAOFactory.getFichierDAO().create(fichierFilm.getFichier());
            DAOFactory.getFilmDAO().createOrUpdate(fichierFilm.getFilm());
            DAOFactory.getEstUnFilm().create(new EstUnFilm(fichierFilm.getFichier().getId(),
                    fichierFilm.getFilm().getTitreFilm(), fichierFilm.getFilm().getAnneeSortie()));
            
            for (Flux flux : fichierFilm.getFlux()) {
                DAOFactory.getFlux().create(flux);
                if (flux instanceof FluxAudio) {
                    DAOFactory.getFluxAudio().create((FluxAudio)flux);
                } else if (flux instanceof FluxVideo) {
                    DAOFactory.getFluxVideo().create((FluxVideo)flux);
                } else if (flux instanceof FluxTexte) {
                    DAOFactory.getFluxTexte().create((FluxTexte)flux);
                }
            }

            // on doit créer les catégories et les liens
            for (CategorieFilm categorieFilm : fichierFilm.getCategories()) {
                DAOFactory.getCategorieFilmDAO().createOrUpdate(categorieFilm);
                DAOFactory.getFilmAPourCategorie().createOrUpdate(new FilmAPourCategorie(fichierFilm.getFilm().getTitreFilm(),
                        fichierFilm.getFilm().getAnneeSortie(), categorieFilm.getCategorie()));
            }

            // on doit créer les artistes et les liens
            for (Map.Entry<Artiste, String> entry : fichierFilm.getArtistes().entrySet()) {
                DAOFactory.getArtisteDAO().createOrUpdate(entry.getKey());
                DAOFactory.getAPourRole().createOrUpdate(new APourRole(fichierFilm.getFilm().getTitreFilm(),
                        fichierFilm.getFilm().getAnneeSortie(), entry.getKey().getId(), entry.getValue()));
            }

            // on doit créer les ImgExtraitesFilm
            for (ImgExtraiteFilm imgExtraiteFilm : fichierFilm.getImgExtraiteFilms()) {
                DAOFactory.getImgExtraiteFilmDAO().createOrUpdate(imgExtraiteFilm);
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
