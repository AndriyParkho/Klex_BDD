package controller;

import model.Flux;
import model.FluxAudio;
import model.FluxTexte;
import model.FluxVideo;
import views.InsertFichier;
import views.InsertFlux;

public class InsertFluxControl {
	private InsertFlux view;
	
	public InsertFluxControl(InsertFlux view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		if(view.getAudioChoix().isSelected()) {
			FluxAudio newFlux = new FluxAudio();
			newFlux.setEchantillonnage((int) view.getEchantField().getSelectedItem());
			newFlux.setLangue(view.getLangueAudioField().getText());
			newFlux.setTypeCodec("audio");
			newFlux.setDebit((float)view.getDebitField().getValue());
			newFlux.setNomCodec(view.getCodecField().getText());
			if(view.getFichierFilm() != null) {
				view.getFichierFilm().getFlux().add(newFlux);
			} else if(view.getFichierPiste() != null) {
				view.getFichierPiste().getFlux().add(newFlux);
			}
		} else if(view.getTxtChoix().isSelected()) {
			FluxTexte newFlux = new FluxTexte();
			newFlux.setLangue(view.getLangueTxtField().getText());
			newFlux.setTypeCodec("texte");
			newFlux.setDebit((float)view.getDebitField().getValue());
			newFlux.setNomCodec(view.getCodecField().getText());
			if(view.getFichierFilm() != null) {
				view.getFichierFilm().getFlux().add(newFlux);
			} else if(view.getFichierPiste() != null) {
				view.getFichierPiste().getFlux().add(newFlux);
			}
		} else {
			FluxVideo newFlux = new FluxVideo();
			newFlux.setHauteur((int) view.getHauteurField().getValue());
			newFlux.setLargeur((int) view.getLargeurField().getValue());
			newFlux.setTypeCodec("video");
			newFlux.setDebit((float)view.getDebitField().getValue());
			newFlux.setNomCodec(view.getCodecField().getText());
			if(view.getFichierFilm() != null) {
				view.getFichierFilm().getFlux().add(newFlux);
			} else if(view.getFichierPiste() != null) {
				view.getFichierPiste().getFlux().add(newFlux);
			}
		} 
		if(view.getNumeroFlux() < view.getNbMaxFlux()) {
			// On réouvre une fenetre de flux car nous n'avons pas encore atteint le nombre de flux que l'util veut ajouter
			new InsertFlux(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getNumeroFlux() + 1, view.getNbMaxFlux(), view.getFichierFilm(), view.getFichierPiste());
		} else {
			// On passe à la fenetre d'après
			new InsertFichier(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm(), view.getFichierPiste());
		}
	}
}
