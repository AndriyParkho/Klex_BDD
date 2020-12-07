package dao;

public final class DAOFactory {

    public static DAOAlbum getAlbumDAO() {
        return new DAOAlbum();
    }

    public static DAOArtiste getArtisteDAO() {
        return new DAOArtiste();
    }

    public static DAOCategorieFilm getCategorieFilmDAO() {
        return new DAOCategorieFilm();
    }

    public static DAOCategorieMusique getCategorieMusiqueDAO() {
        return new DAOCategorieMusique();
    }

    public static DAOCodec getCodecDAO() {
        return new DAOCodec();
    }

    public static DAOClient getClientDAO() {
        return new DAOClient();
    }

    public static DAOFichier getFichierDAO() {
        return new DAOFichier();
    }

    public static DAOFilm getFilmDAO() {
        return new DAOFilm();
    }

    public static DAOImgExtraiteFilm getImgExtraiteFilmDAO() {
        return new DAOImgExtraiteFilm();
    }
    
    public static DAOPiste getPisteDAO() {
        return new DAOPiste();
    }

    public static DAOUtilisateur getUtilisateurDAO() {
        return new DAOUtilisateur();
    }
}
