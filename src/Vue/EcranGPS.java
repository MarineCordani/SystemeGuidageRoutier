package Vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.util.Vector;

import Controlleur.MoteurTraitement;
import Modele.Intersection;
import Modele.ReseauRoutier;

import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

/**
 * Classe de la vue qui constitue la fenêtre principale
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class EcranGPS extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int DIVIDER_SIZE = 1;
	
	private EntreeUtilisateur entreeUtilisateur;
	private JournalEvenement journalEvenement;
	private Carte carte;
	private JSplitPane horizontalSplitPane;
	private JSplitPane verticalSplitPane;
	private JLabel lblOriginLabel;
	private JComboBox<String> cboOriginComboBox;
	private JLabel lblDestinationLabel;
	private JComboBox<String> cboDestinationComboBox;
	private JButton btnDemarrerButton;
	private JTextArea txtJournalEvenementTextArea;
	
	/**
	 * Le constructeur de la classe
	 * 
	 * @param titre le titre de la fenêtre
	 */
	public EcranGPS(String titre){
		super(titre);
		
		//ajout de l'icone sur la fenêtre principale
		ImageIcon icon = new ImageIcon("res/icon.png");
		this.setIconImage(icon.getImage());
		
		
		//ajout des controleurs de la fenêtre principale
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		horizontalSplitPane = new JSplitPane();
		horizontalSplitPane.setResizeWeight(0.7);
		horizontalSplitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		horizontalSplitPane.setDividerSize(DIVIDER_SIZE);
		getContentPane().add(horizontalSplitPane);
		this.carte = new Carte();
		horizontalSplitPane.setLeftComponent(carte);
		carte.setLayout(null);
		
		verticalSplitPane = new JSplitPane();
		verticalSplitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalSplitPane.setResizeWeight(0.01);
		verticalSplitPane.setDividerSize(DIVIDER_SIZE);
		verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		horizontalSplitPane.setRightComponent(verticalSplitPane);
		
		this.entreeUtilisateur = new EntreeUtilisateur();
		verticalSplitPane.setLeftComponent(entreeUtilisateur);
		entreeUtilisateur.setLayout(new MigLayout("fillx", "[][]", "[][][]"));
		
		lblOriginLabel = new JLabel("Origine");
		entreeUtilisateur.add(lblOriginLabel, "cell 0 0,alignx leading");
		
		cboOriginComboBox = new JComboBox<String>();
		entreeUtilisateur.add(cboOriginComboBox, "cell 1 0,growx");
		
		lblDestinationLabel = new JLabel("Destination");
		entreeUtilisateur.add(lblDestinationLabel, "flowx,cell 0 1,alignx leading");
		
		cboDestinationComboBox = new JComboBox<String>();
		entreeUtilisateur.add(cboDestinationComboBox, "cell 1 1,growx");
		
		btnDemarrerButton = new JButton("D\u00E9marrer");
		entreeUtilisateur.add(btnDemarrerButton, "cell 0 2 2 1,growx");
		btnDemarrerButton.addActionListener(entreeUtilisateur);
		
		this.journalEvenement = new JournalEvenement();
		verticalSplitPane.setRightComponent(journalEvenement);
		journalEvenement.setLayout(new BorderLayout(0, 0));
		
		txtJournalEvenementTextArea = new JTextArea();
		journalEvenement.add(txtJournalEvenementTextArea);
		
		
		ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();
		Vector<Intersection> intersections = reseau.getIntersections();
		for (Intersection s: intersections){
			cboOriginComboBox.addItem(s.toString());
			cboDestinationComboBox.addItem(s.toString());
		}
	}
	
	/**
	 * Méthode pour rafraîchir l'interface
	 */
	public void RafraichirInterface() {
		carte.revalidate();
		carte.repaint();
	}
	
	/**
	 * Méthode pour afficher un texte dasn le journal
	 * 
	 * @param message texte à afficher dans le journal
	 */
	public void ajouterTexteAuJournal(String message) {
		txtJournalEvenementTextArea.append(message + "\n");
	}
}
