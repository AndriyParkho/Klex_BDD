package views;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controller.SignUpControl;


public class SignUp extends View {
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField mailField;
	private JTextField langueField;
	private JTextField codeField;
	private JButton validButton = new JButton("Valider");
	private JButton backButton = new JButton("<");
	private JPanel container = new SignUpPanel();
	private SignUpControl controller = new SignUpControl(this);
	
	public SignUp(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Inscription"));

		super.getContainerView().add(container, "Inscription");
		super.getSwitcherView().show(super.getContainerView() , "Inscription");

		super.getFenetre().setSize(460, 350);
		super.getFenetre().setLocationRelativeTo(null);
	}
	
	class SignUpPanel extends JPanel {
		

		/**
		 * Create the panel.
		 */
		public SignUpPanel() {
			setLayout(null);
			
			nomField = new JTextField();
			nomField.setBounds(81, 91, 116, 22);
			add(nomField);
			nomField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("INSCRIPTION");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(158, 13, 152, 34);
			add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Nom :");
			lblNewLabel_1.setBounds(12, 94, 47, 16);
			add(lblNewLabel_1);
			
			prenomField = new JTextField();
			prenomField.setColumns(10);
			prenomField.setBounds(81, 138, 116, 22);
			add(prenomField);
			
			JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
			lblPrnom.setBounds(12, 141, 57, 16);
			add(lblPrnom);
			
			mailField = new JTextField();
			mailField.setColumns(10);
			mailField.setBounds(81, 188, 116, 22);
			add(mailField);
			
			JLabel label_1 = new JLabel("Email :");
			label_1.setBounds(12, 191, 47, 16);
			add(label_1);
			
			JLabel lblAge = new JLabel("Age :");
			lblAge.setBounds(253, 94, 47, 16);
			add(lblAge);
			
			JSpinner ageField = new JSpinner();
			ageField.setBounds(322, 91, 47, 22);
			add(ageField);
			
			JLabel lblLangue = new JLabel("Langue :");
			lblLangue.setBounds(253, 141, 57, 16);
			add(lblLangue);
			
			langueField = new JTextField();
			langueField.setColumns(10);
			langueField.setBounds(322, 138, 116, 22);
			add(langueField);
			
			JLabel lblCode = new JLabel("Code :");
			lblCode.setBounds(253, 191, 47, 16);
			add(lblCode);
			
			codeField = new JTextField();
			codeField.setColumns(10);
			codeField.setBounds(322, 188, 116, 22);
			add(codeField);
			validButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicValid();
				}
			});
			
			validButton.setBounds(176, 249, 97, 25);
			add(validButton);
			
			JButton backButton = new JButton("<");
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicBack();
				}
			});
			backButton.setIcon(null);
			backButton.setBounds(12, 13, 47, 34);
			add(backButton);

		}
	}

	public JTextField getNomField() {
		return nomField;
	}

	public void setNomField(JTextField nomField) {
		this.nomField = nomField;
	}

	public JTextField getPrenomField() {
		return prenomField;
	}

	public void setPrenomField(JTextField prenomField) {
		this.prenomField = prenomField;
	}

	public JTextField getMailField() {
		return mailField;
	}

	public void setMailField(JTextField mailField) {
		this.mailField = mailField;
	}

	public JTextField getLangueField() {
		return langueField;
	}

	public void setLangueField(JTextField langueField) {
		this.langueField = langueField;
	}

	public JTextField getCodeField() {
		return codeField;
	}

	public void setCodeField(JTextField codeField) {
		this.codeField = codeField;
	}

	public JButton getValidButton() {
		return validButton;
	}

	public void setValidButton(JButton validButton) {
		this.validButton = validButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}
	
}
