package tests;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;

import model.Album;
import model.Artiste;
import model.CategorieMusique;
import model.Fichier;
import model.Flux;
import model.FluxAudio;
import model.FluxTexte;
import model.Piste;
import model.aggregates.FichierPiste;
import transactions.TransactionFichierPiste;

public class TestTransactionFichierPiste {
    public static void main(String[] args) {

        Fichier fichier = new Fichier();
        fichier.setDateDepot(Date.valueOf("2019-04-13"));
        fichier.setTaille(2600);
        fichier.setEmail("@gmail.com");

        Album album = new Album();
        album.setGroupe("Linkin Park");
        album.setTitre("Meteora");
        album.setUrlImagePochette("https://img.png");
        album.setDateSortie(Date.valueOf("2003-09-08"));

        Piste piste = new Piste();
        piste.setDuree("00:04:21");
        piste.setNum(13);
        piste.setTitre("Numb");

        HashSet<Flux> flux = new HashSet<Flux>();
        FluxAudio fluxAudio = new FluxAudio();
        fluxAudio.setDebit(850);
        fluxAudio.setEchantillonnage(16);
        fluxAudio.setLangue("Français");
        fluxAudio.setNomCodec("MPEG2");
        fluxAudio.setTypeCodec("audio");
        flux.add(fluxAudio);

        FluxTexte fluxTexte = new FluxTexte();
        fluxTexte.setDebit(850);
        fluxTexte.setLangue("Anglais"); // pcq on est bilingue
        fluxTexte.setNomCodec("Kate");
        fluxTexte.setTypeCodec("texte");
        flux.add(fluxTexte);

        HashSet<CategorieMusique> categories = new HashSet<CategorieMusique>();
        categories.add(new CategorieMusique("métal"));
        categories.add(new CategorieMusique("rock"));

        HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();
        Artiste artiste = new Artiste();
        artiste.setNom("Linkin Park");
        artiste.setUrlPhoto("https://commons.wikimedia.org/wiki/File:LinkinParkBerlin2010.jpg?uselang=fr");
        artiste.setSpecialite("Chanteur");
        artistes.put(artiste, "Basse");

        FichierPiste fichierPiste = new FichierPiste();
        fichierPiste.setFichier(fichier);
        fichierPiste.setAlbum(album);
        fichierPiste.setPiste(piste);
        fichierPiste.setCategories(categories);
        fichierPiste.setFlux(flux);
        fichierPiste.setArtistes(artistes);

        TransactionFichierPiste.execute(fichierPiste);
    }
}
