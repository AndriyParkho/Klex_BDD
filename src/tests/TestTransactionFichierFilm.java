package tests;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;

import model.Artiste;
import model.CategorieFilm;
import model.Fichier;
import model.Film;
import model.Flux;
import model.FluxAudio;
import model.FluxVideo;
import model.ImgExtraiteFilm;
import model.aggregates.FichierFilm;
import transactions.TransactionFichierFilm;

public class TestTransactionFichierFilm {
    public static void main(String[] args) {

        Fichier fichier = new Fichier();
        fichier.setDateDepot(Date.valueOf("2019-04-13"));
        fichier.setTaille(76000);
        fichier.setEmail("@gmail.com");

        Film film = new Film();
        film.setAgeMin(18);
        film.setAnneeSortie(Date.valueOf("2015-11-23"));
        film.setResume("BLABLABLA");
        film.setTitreFilm("Le Dernier Voeux");
        film.setUrlAffiche("http://urlaffiche");

        HashSet<Flux> flux = new HashSet<Flux>();
        FluxAudio fluxAudio = new FluxAudio();
        fluxAudio.setDebit(850);
        fluxAudio.setEchantillonnage(16);
        fluxAudio.setLangue("Français");
        fluxAudio.setNomCodec("MPEG2");
        fluxAudio.setTypeCodec("video");
        flux.add(fluxAudio);

        FluxVideo fluxVideo = new FluxVideo();
        fluxVideo.setDebit(850);
        fluxVideo.setHauteur(2160);
        fluxVideo.setLargeur(3840);
        fluxVideo.setNomCodec("MPEG4");
        fluxVideo.setTypeCodec("video");
        flux.add(fluxVideo);

        HashSet<CategorieFilm> categories = new HashSet<CategorieFilm>();
        categories.add(new CategorieFilm("comédie"));
        categories.add(new CategorieFilm("action"));
        categories.add(new CategorieFilm("horreur"));

        HashSet<ImgExtraiteFilm> imgExtraiteFilms = new HashSet<ImgExtraiteFilm>();
        imgExtraiteFilms.add(new ImgExtraiteFilm("https://1.jpg", "Le Dernier Voeux", Date.valueOf("2015-11-23")));
        imgExtraiteFilms.add(new ImgExtraiteFilm("https://2.jpg", "Le Dernier Voeux", Date.valueOf("2015-11-23")));
        imgExtraiteFilms.add(new ImgExtraiteFilm("https://3.jpg", "Le Dernier Voeux", Date.valueOf("2015-11-23")));

        HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();
        Artiste artiste = new Artiste();
        artiste.setNom("Calvin Harris");
        artiste.setDateNaissance(Date.valueOf("2014-11-04"));
        artiste.setUrlPhoto("https://www.journaldugeek.com/content/uploads/2019/06/supermariorunta.jpg");
        artiste.setSpecialite("Chanteur");
        artiste.setBiographie("Plus besoin de présenter ce chanteur !!");
        artistes.put(artiste, "Chanteur");

        Artiste newArtiste = new Artiste();
        newArtiste.setDateNaissance(null);
        newArtiste.setBiographie(null);
        newArtiste.setNom("Théo Manfredi");
        newArtiste.setUrlPhoto("https://theo.jpg");
        newArtiste.setSpecialite("Manager");
        artistes.put(newArtiste, "rédacteur");

        FichierFilm fichierFilm = new FichierFilm();
        fichierFilm.setFichier(fichier);
        fichierFilm.setFilm(film);
        fichierFilm.setCategories(categories);
        fichierFilm.setFlux(flux);
        fichierFilm.setImgExtraiteFilms(imgExtraiteFilms);
        fichierFilm.setArtistes(artistes);

        TransactionFichierFilm.execute(fichierFilm);
    }
}
