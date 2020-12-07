package model;

import java.util.HashMap;
import java.util.HashSet;

public class FichierPiste {
	private Fichier fichier;
	private Piste piste;
	private Album album;
	private HashSet<CategorieMusique> categories;
	private HashSet<Flux> flux;
	private HashMap<Artiste, String> artistes;
	
	public Piste getPiste() {
		return piste;
	}
	
	public void setPiste(Piste piste) {
		this.piste = piste;
	}
	
	public Fichier getFichier() {
		return fichier;
	}
	
	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}
	
	public Album getAlbum() {
		return album;
	}
	
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public HashSet<CategorieMusique> getCategories() {
		return categories;
	}
	
	public void setCategories(HashSet<CategorieMusique> categories) {
		this.categories = categories;
	}
	
	public HashSet<Flux> getFlux() {
		return flux;
	}
	
	public void setFlux(HashSet<Flux> flux) {
		this.flux = flux;
	}
	
	public HashMap<Artiste, String> getArtistes() {
		return artistes;
	}
	
	public void setArtistes(HashMap<Artiste, String> artistes) {
		this.artistes = artistes;
	}
}
