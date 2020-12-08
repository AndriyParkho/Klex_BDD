package views;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.InsertFluxControl;
import model.aggregates.FichierFilm;
import model.aggregates.FichierPiste;

public class InsertFlux extends View{
	private int numeroFlux;
	private int nbMaxFlux;
	private JTextField codecField;
	private JTextField langueTxtField;
	private JTextField langueAudioField;
	private JRadioButton txtChoix = new JRadioButton("Flux de texte");
	private JRadioButton audioChoix = new JRadioButton("Flux audio");
	private JRadioButton videoChoix = new JRadioButton("Flux vid\u00E9o");
	private JSpinner debitField = new JSpinner();
	private JComboBox echantField = new JComboBox();
	private JSpinner largeurField = new JSpinner();
	private JSpinner hauteurField = new JSpinner();
	private JPanel container;
	private InsertFluxControl controller = new InsertFluxControl(this);
	private FichierFilm fichierFilm;
	private FichierPiste fichierPiste;

	public InsertFlux(JFrame fenetre, CardLayout switcherView, JPanel containerView, int numeroFlux, int nbMaxFlux, FichierFilm fichierFilm, FichierPiste fichierPiste) {
		super(fenetre, switcherView, containerView, new String("Ajout d'un flux"));
		this.fichierFilm = fichierFilm;
		this.fichierPiste = fichierPiste;
		this.numeroFlux = numeroFlux;
		this.nbMaxFlux = nbMaxFlux;
		container = new InsertFluxPanel();

		super.getContainerView().add(container, "Insert flux");
		super.getPanels().add("Insert flux");
		super.getSwitcherView().show(super.getContainerView() , "Insert flux");

		super.getFenetre().setSize(430, 350);
		super.getFenetre().setLocationRelativeTo(null);
	}
	
	class InsertFluxPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public InsertFluxPanel() {
			setLayout(null);
			
			JLabel fluxLabel = new JLabel("Flux n\u00B0 " + numeroFlux + " :");
			fluxLabel.setBounds(162, 13, 112, 22);
			fluxLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			add(fluxLabel);
			
			JLabel lblNewLabel = new JLabel("Type de flux :");
			lblNewLabel.setBounds(31, 61, 79, 16);
			add(lblNewLabel);
			
			ButtonGroup choixFlux = new ButtonGroup();
			
			txtChoix.setSelected(true);
			txtChoix.setBounds(41, 86, 101, 25);
			choixFlux.add(txtChoix);
			add(txtChoix);
			
			
			audioChoix.setBounds(172, 86, 87, 25);
			choixFlux.add(audioChoix);
			add(audioChoix);
			
			
			videoChoix.setBounds(303, 86, 87, 25);
			choixFlux.add(videoChoix);
			add(videoChoix);
			
			JLabel lblNewLabel_1 = new JLabel("D\u00E9bit :");
			lblNewLabel_1.setBounds(31, 131, 45, 16);
			add(lblNewLabel_1);
			
			
			debitField.setModel(new SpinnerNumberModel(new Float(0.0), new Float(0.0), null, new Float(0.1)));
			debitField.setBounds(80, 128, 62, 22);
			add(debitField);
			
			JLabel lblNewLabel_2 = new JLabel("Codec :");
			lblNewLabel_2.setBounds(218, 131, 56, 16);
			add(lblNewLabel_2);
			
			codecField = new JTextField();
			codecField.setColumns(10);
			codecField.setBounds(274, 128, 116, 22);
			add(codecField);
			
			CardLayout fluxSwitch = new CardLayout();
			JPanel panel = new JPanel(fluxSwitch);
			panel.setBounds(0, 169, 426, 86);
			add(panel);
			panel.setLayout(fluxSwitch);
			

			txtChoix.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            fluxSwitch.show(panel, "textPanel");

		        }
		    });
			audioChoix.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            fluxSwitch.show(panel, "audioPanel");

		        }
		    });
			videoChoix.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            fluxSwitch.show(panel, "videoPanel");

		        }
		    });
			
			JPanel textPanel = new JPanel();
			panel.add(textPanel, "textPanel");
			textPanel.setLayout(null);
			
			JLabel lblNewLabel_2_1 = new JLabel("Langue :");
			lblNewLabel_2_1.setBounds(31, 16, 56, 16);
			textPanel.add(lblNewLabel_2_1);
			
			langueTxtField = new JTextField();
			langueTxtField.setColumns(10);
			langueTxtField.setBounds(87, 13, 116, 22);
			textPanel.add(langueTxtField);
			
			JPanel audioPanel = new JPanel();
			panel.add(audioPanel, "audioPanel");
			audioPanel.setLayout(null);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("Langue :");
			lblNewLabel_2_1_1.setBounds(32, 16, 56, 16);
			audioPanel.add(lblNewLabel_2_1_1);
			
			langueAudioField = new JTextField();
			langueAudioField.setColumns(10);
			langueAudioField.setBounds(88, 13, 116, 22);
			audioPanel.add(langueAudioField);
			
			JLabel lblNewLabel_3 = new JLabel("Echantillonnage :");
			lblNewLabel_3.setBounds(32, 48, 98, 16);
			audioPanel.add(lblNewLabel_3);
			
			
			echantField.setModel(new DefaultComboBoxModel(new Integer[] {16, 24, 32}));
			echantField.setBounds(142, 48, 48, 22);
			audioPanel.add(echantField);
			
			JLabel lblNewLabel_4 = new JLabel("bits");
			lblNewLabel_4.setBounds(197, 51, 56, 16);
			audioPanel.add(lblNewLabel_4);
			
			JPanel videoPanel = new JPanel();
			panel.add(videoPanel, "videoPanel");
			videoPanel.setLayout(null);
			
			JLabel lblNewLabel_5 = new JLabel("Largeur :");
			lblNewLabel_5.setBounds(34, 13, 56, 16);
			videoPanel.add(lblNewLabel_5);
			
			
			largeurField.setBounds(102, 10, 56, 22);
			videoPanel.add(largeurField);
			
			
			hauteurField.setBounds(102, 51, 56, 22);
			videoPanel.add(hauteurField);
			
			JLabel lblNewLabel_5_1 = new JLabel("Hauteur :");
			lblNewLabel_5_1.setBounds(34, 54, 56, 16);
			videoPanel.add(lblNewLabel_5_1);
			
			JButton suivButton = new JButton("Suivant");
			suivButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSuiv();
				}
			});
			suivButton.setBounds(162, 262, 97, 25);
			add(suivButton);

		}
	}

	public int getNumeroFlux() {
		return numeroFlux;
	}

	public void setNumeroFlux(int numeroFlux) {
		this.numeroFlux = numeroFlux;
	}

	public int getNbMaxFlux() {
		return nbMaxFlux;
	}

	public void setNbMaxFlux(int nbMaxFlux) {
		this.nbMaxFlux = nbMaxFlux;
	}

	public JTextField getCodecField() {
		return codecField;
	}

	public void setCodecField(JTextField codecField) {
		this.codecField = codecField;
	}

	public JTextField getLangueTxtField() {
		return langueTxtField;
	}

	public void setLangueTxtField(JTextField langueTxtField) {
		this.langueTxtField = langueTxtField;
	}

	public JTextField getLangueAudioField() {
		return langueAudioField;
	}

	public void setLangueAudioField(JTextField langueAudioField) {
		this.langueAudioField = langueAudioField;
	}

	public JRadioButton getTxtChoix() {
		return txtChoix;
	}

	public void setTxtChoix(JRadioButton txtChoix) {
		this.txtChoix = txtChoix;
	}

	public JRadioButton getAudioChoix() {
		return audioChoix;
	}

	public void setAudioChoix(JRadioButton audioChoix) {
		this.audioChoix = audioChoix;
	}

	public JRadioButton getVideoChoix() {
		return videoChoix;
	}

	public void setVideoChoix(JRadioButton videoChoix) {
		this.videoChoix = videoChoix;
	}

	public JSpinner getDebitField() {
		return debitField;
	}

	public void setDebitField(JSpinner debitField) {
		this.debitField = debitField;
	}

	public JComboBox getEchantField() {
		return echantField;
	}

	public void setEchantField(JComboBox echantField) {
		this.echantField = echantField;
	}

	public JSpinner getLargeurField() {
		return largeurField;
	}

	public void setLargeurField(JSpinner largeurField) {
		this.largeurField = largeurField;
	}

	public JSpinner getHauteurField() {
		return hauteurField;
	}

	public void setHauteurField(JSpinner hauteurField) {
		this.hauteurField = hauteurField;
	}

	public FichierFilm getFichierFilm() {
		return fichierFilm;
	}

	public void setFichierFilm(FichierFilm fichierFilm) {
		this.fichierFilm = fichierFilm;
	}

	public FichierPiste getFichierPiste() {
		return fichierPiste;
	}

	public void setFichierPiste(FichierPiste fichierPiste) {
		this.fichierPiste = fichierPiste;
	}
	

}
