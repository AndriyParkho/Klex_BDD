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

    public static DAOSupporteCodec getSupporteCodec() {
        return new DAOSupporteCodec();
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

    public static DAOAPourRole getAPourRole() {
        return new DAOAPourRole();
    }

    public static DAOAPourInstrument getAPourInstrument() {
        return new DAOAPourInstrument();
    }

    public static DAOAlbumAPourCategorie getAlbumAPourCategorie() {
        return new DAOAlbumAPourCategorie();
    }

    public static DAOPisteAPourCategorie getPisteAPourCategorie() {
        return new DAOPisteAPourCategorie();
    }

    public static DAOEstUnFilm getEstUnFilm() {
        return new DAOEstUnFilm();
    }

    public static DAOEstUnePiste getEstUnePiste() {
        return new DAOEstUnePiste();
    }

    public static DAOFilmAPourCategorie getFilmAPourCategorie () {
        return new DAOFilmAPourCategorie();
    }

    public static DAOFlux getFlux () {
        return new DAOFlux();
    }

    public static DAOFluxAudio getFluxAudio () {
        return new DAOFluxAudio();
    }

    public static DAOFluxVideo getFluxVideo () {
        return new DAOFluxVideo();
    }

    public static DAOFluxTexte getFluxTexte () {
        return new DAOFluxTexte();
    }
}
