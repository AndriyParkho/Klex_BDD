package controller;

import java.awt.event.WindowEvent;

import model.Client;
import model.Codec;
import transactions.TransactionClientCodecs;
import views.CreateClient;
import views.CreateClient.InsertCodecPanel;

public class CreateClientControl {
	private CreateClient view;

	public CreateClientControl(CreateClient view) {
		this.view = view;
	}
	
	public void clicValid() {
		Client client = new Client();
		client.setModele(view.getModeleInconnu());
		client.setMarque(view.getMarqueInconnu());
		client.setHauteurMax((int) view.getHauteurField().getValue());
		client.setLargeurMax((int) view.getLargeurField().getValue());

		view.getClientCodecs().setClient(client);
		
		for(InsertCodecPanel pan : view.getCodecPanels()) {
			Codec newCodec = new Codec(pan.getNomField().getText(), (String) pan.getTypeField().getSelectedItem());
			view.getClientCodecs().getCodecs().add(newCodec);
		}
		System.out.println(view.getClientCodecs());
		TransactionClientCodecs.execute(view.getClientCodecs());
		
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)); // La fenetre se ferme
	}
}
