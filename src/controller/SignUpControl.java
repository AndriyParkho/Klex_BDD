package controller;

import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.DAOUtilisateur;
import tables.Utilisateur;
import views.SignUp;

public class SignUpControl {
	private SignUp view;
	private DAOUtilisateur bddUtil = new DAOUtilisateur();
	private Utilisateur util = new Utilisateur();
	
	public SignUpControl(SignUp view) {
		this.view = view;
	}
	
	public void clicBack() {
		// TODO Auto-generated method stub
		
	}

	public void clicValid() {
		// TODO Auto-generated method stub
		//email, nom, prenom,  age, langueDiffusion, code
				String email = view.getMailField().getText();
				try{
					util = bddUtil.find(email);
						
				}catch(SQLException e) {
					System.out.println("l'email existe deja");
					JDialog erreur = new JDialog(view.getFenetre(),"erreur");
					JLabel label = new JLabel("l'email existe deja", SwingConstants.CENTER);
					erreur.add(label);
					erreur.setSize(300, 100);
					erreur.setLocationRelativeTo(null);
					erreur.setVisible(true);
				}
				String nom = view.getNomField().getText();
				String prenom = view.getPrenomField().getText();
				//int age = Integer.parseInt(view.getAgeField().getText());
				String langue = view.getLangueField().getText();
				int code = Integer.parseInt(view.getCodeField().getText());
				util = new Utilisateur(email, nom, prenom, 18 , langue, code, null);
				try{
					bddUtil.create(util);
				}catch(SQLException e) {
					System.out.println(e);
				}
				System.out.println(util);
			
		
	}

}
