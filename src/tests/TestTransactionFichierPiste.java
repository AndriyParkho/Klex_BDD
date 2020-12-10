package tests;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import dao.DAOFactory;
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
        fichier.setEmail("andriy.parkho@gmail.com");
        

        Album album = new Album();
        try {
			ResultSet rs = DAOFactory.getAlbumDAO().find("Meteora");
			if(rs.next()) {
				album.setId(rs.getLong("idAlbum"));
				album.setGroupe(rs.getString("nomGroupe"));
				album.setTitre("Meteora");
				album.setDateSortie(rs.getDate("dateSortieAlbum"));
				album.setUrlImagePochette(rs.getString("urlImagePochette"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//        album.setGroupe("Linkin Park");
//        album.setTitre("Meteora");
//        album.setUrlImagePochette("https://img.png");
//        album.setDateSortie(Date.valueOf("2003-09-08"));

        Piste piste = new Piste();
        piste.setDuree("00:02:20");
        piste.setNum(15);
        piste.setTitre("Test2");

        HashSet<Flux> flux = new HashSet<Flux>();
        FluxAudio fluxAudio = new FluxAudio();
        fluxAudio.setDebit(250);
        fluxAudio.setEchantillonnage(16);
        fluxAudio.setLangue("Anglais");
        fluxAudio.setNomCodec(".mp3");
        fluxAudio.setTypeCodec("audio");
        flux.add(fluxAudio);

        FluxTexte fluxTexte = new FluxTexte();
        fluxTexte.setDebit(300);
        fluxTexte.setLangue("Français"); // pcq on est bilingue
        fluxTexte.setNomCodec(".txt");
        fluxTexte.setTypeCodec("texte");
        flux.add(fluxTexte);

        HashSet<CategorieMusique> categories = new HashSet<CategorieMusique>();
        categories.add(new CategorieMusique("rap"));
        categories.add(new CategorieMusique("machin"));

        HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();
        Artiste artiste = new Artiste();
        artiste.setNom("Autre");
        artiste.setUrlPhoto("https://commons.wikimedia.org/wiki/File:LinkinParkBerlin2010.jpg?uselang=fr");
        artiste.setSpecialite("Chanteur");
        artistes.put(artiste, "Beat");

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
