package model.aggregates;

import java.util.HashMap;
import java.util.HashSet;

import model.Album;
import model.Artiste;
import model.CategorieMusique;
import model.Fichier;
import model.Flux;
import model.Piste;

public class FichierPiste {
	private Fichier fichier;
	private Piste piste;
	private Album album;
	private HashSet<CategorieMusique> categories = new HashSet<CategorieMusique>();
	private HashSet<Flux> flux = new HashSet<Flux>();
	private HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();
	
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
