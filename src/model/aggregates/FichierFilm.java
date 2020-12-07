package model.aggregates;

import java.util.HashMap;
import java.util.HashSet;

import model.Artiste;
import model.CategorieFilm;
import model.Fichier;
import model.Film;
import model.Flux;
import model.ImgExtraiteFilm;

public class FichierFilm {
	private Fichier fichier;
	private Film film;
	private HashSet<Flux> flux;
	private HashSet<CategorieFilm> categories;
	private HashSet<ImgExtraiteFilm> imgExtraiteFilms;
	private HashMap<Artiste, String> artistes;
	
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

	public HashSet<Flux> getFlux() {
		return flux;
	}

	public void setFlux(HashSet<Flux> flux) {
		this.flux = flux;
	}

	public HashSet<CategorieFilm> getCategories() {
		return categories;
	}

	public void setCategories(HashSet<CategorieFilm> categories) {
		this.categories = categories;
	}

	public HashSet<ImgExtraiteFilm> getImgExtraiteFilms() {
		return imgExtraiteFilms;
	}

	public void setImgExtraiteFilms(HashSet<ImgExtraiteFilm> imgExtraiteFilms) {
		this.imgExtraiteFilms = imgExtraiteFilms;
	}

	public HashMap<Artiste, String> getArtistes() {
		return artistes;
	}

	public void setArtistes(HashMap<Artiste, String> artistes) {
		this.artistes = artistes;
	}
	
}
