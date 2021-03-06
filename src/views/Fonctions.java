package views;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.FonctionsControl;

public class Fonctions extends View {
	private JButton insertButton = new JButton("Ins\u00E9rer un nouveau fichier");
	private JButton selectButton = new JButton("S\u00E9lection d'un contenu");
	private JButton deleteButton = new JButton("Supprimer un contenu");
	private JButton discntButton = new JButton("Se d\u00E9connecter");
	private JPanel container = new FonctionPanel();
	private FonctionsControl controller = new FonctionsControl(this);

	public Fonctions(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Fonctions"));
		
		super.getContainerView().add(container, "Fonctions");
		super.getPanels().add("Fonctions");
		super.getSwitcherView().show(super.getContainerView() , "Fonctions");

		super.getFenetre().setSize(453, 350);
		super.getFenetre().setLocationRelativeTo(null);
	}

	class FonctionPanel extends JPanel {
		
		/**
		 * Create the panel.
		 */
		public FonctionPanel() {
			setLayout(null);
			insertButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicInsert();
				}
			});
			
			
			insertButton.setBounds(122, 71, 215, 36);
			add(insertButton);
			selectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSelect();
				}
			});
			
			
			selectButton.setBounds(122, 130, 215, 36);
			add(selectButton);
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicDelete();
				}
			});
			
			
			deleteButton.setBounds(122, 191, 215, 36);
			add(deleteButton);
			discntButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicDisconnect();
				}
			});
			
			
			discntButton.setBounds(12, 13, 133, 25);
			add(discntButton);

		}
	}

	public JButton getInsertButton() {
		return insertButton;
	}

	public void setInsertButton(JButton insertButton) {
		this.insertButton = insertButton;
	}

	public JButton getSelectButton() {
		return selectButton;
	}

	public void setSelectButton(JButton selectButton) {
		this.selectButton = selectButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getDiscntButton() {
		return discntButton;
	}

	public void setDiscntButton(JButton discntButton) {
		this.discntButton = discntButton;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}
	
	
}
