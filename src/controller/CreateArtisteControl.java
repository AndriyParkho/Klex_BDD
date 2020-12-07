package controller;

import java.awt.event.WindowEvent;

import views.CreateArtiste;

public class CreateArtisteControl {
	private CreateArtiste view;

	public CreateArtisteControl(CreateArtiste view) {
		this.view = view;
	}
	
	public void clicValid() {
		// TODO
		
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
	}
}
