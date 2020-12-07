package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class FichierFilm {
	Fichier fichier;
	Film film;
	HashSet<CategorieFilm> categorie;
	HashSet<ImgExtraiteFilm> imgExtraiteFilm;
	HashMap<Artiste, String> artistes;
	
	public FichierFilm(Fichier fichier, Film film, HashSet<CategorieFilm> categorie, HashSet<ImgExtraiteFilm> imgFilm, HashMap<Artiste, String> artistes) {
		this.fichier = fichier;
		this.film = film;
		this.categorie = categorie;
		this.imgExtraiteFilm = imgFilm;
		this.artistes = artistes;
	}
	
	public FichierFilm() {
		
	}

	public Fichier getFichier() {
		return fichier;
	}

	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public HashSet<CategorieFilm> getCategorie() {
		return categorie;
	}

	public void setCategorie(HashSet<CategorieFilm> categorie) {
		this.categorie = categorie;
	}

	public HashSet<ImgExtraiteFilm> getImgExtraiteFilm() {
		return imgExtraiteFilm;
	}

	public void setImgExtraiteFilm(HashSet<ImgExtraiteFilm> imgExtraiteFilm) {
		this.imgExtraiteFilm = imgExtraiteFilm;
	}

	public HashMap<Artiste, String> getArtistes() {
		return artistes;
	}

	public void setArtistes(HashMap<Artiste, String> artistes) {
		this.artistes = artistes;
	};
	
	
	
	
	
	
}
