package model;

import java.util.HashMap;
import java.util.HashSet;

public class FichierPiste {
	private Piste piste;
	private Fichier fichier;
	private Album album;
	private HashSet<CategorieMusique> categories = new HashSet<CategorieMusique>();
	private HashSet<Flux> flux = new HashSet<Flux>();
	private HashSet<Artiste> artistes = new HashSet<Artiste>();
	private HashSet<APourInstrument> aPourInstrument = new HashSet<APourInstrument>();
	private HashSet<PisteAPourCategorie> pisteAPourCateg = new HashSet<PisteAPourCategorie>();
	private HashSet<AlbumAPourCategorie> albumAPourCateg = new HashSet<AlbumAPourCategorie>();
	
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
	
	public HashSet<Artiste> getArtistes() {
		return artistes;
	}
	
	public void setArtistes(HashSet<Artiste> artistes) {
		this.artistes = artistes;
	}
	
	public HashSet<APourInstrument> getaPourInstrument() {
		return aPourInstrument;
	}
	
	public void setaPourInstrument(HashSet<APourInstrument> aPourInstrument) {
		this.aPourInstrument = aPourInstrument;
	}
	
	public HashSet<PisteAPourCategorie> getPisteAPourCateg() {
		return pisteAPourCateg;
	}
	
	public void setPisteAPourCateg(HashSet<PisteAPourCategorie> pisteAPourCateg) {
		this.pisteAPourCateg = pisteAPourCateg;
	}
	
	public HashSet<AlbumAPourCategorie> getAlbumAPourCateg() {
		return albumAPourCateg;
	}
	
	public void setAlbumAPourCateg(HashSet<AlbumAPourCategorie> albumAPourCateg) {
		this.albumAPourCateg = albumAPourCateg;
	}
	
}
