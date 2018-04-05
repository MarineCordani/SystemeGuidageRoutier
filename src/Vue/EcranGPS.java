package Vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import java.util.Vector;

import Controleur.MoteurTraitement;
import Modele.Intersection;
import Modele.ReseauRoutier;

import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

/**
 * Classe de la vue qui constitue la fen�tre principale
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class EcranGPS extends JFrame {
	/**
	 * La cl� de hachage SHA qui identifie de mani�re unique la Classe
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * La taille du diviseur de la fen�tre principale
	 */
	private final int DIVIDER_SIZE = 1;
	
	/**
	 * Le diviseur de la fen�tre principale horizontal entre le panneau des controlleurs d'entr�e et celui du journal des �v�nements
	 */
	private JSplitPane horizontalSplitPane;
	
	/**
	 * Le diviseur de la fen�tre principale vertica entre le panneau de la carte est d'autres panneaux
	 */
	private JSplitPane verticalSplitPane;
	
	/**
	 * Le panneau des controlleurs d'entr�e 
	 */
	private EntreeUtilisateur entreeUtilisateur;
	
	/**
	 * Le panneau du journal des �v�nements
	 */
	private JournalEvenement journalEvenement;
	
	/**
	 * Le panneau de la carte
	 */
	private Carte carte;
	
	/**
	 * L'�tiquette pour les intersections d'origine
	 */
	private JLabel lblOrigineLabel;
	
	/**
	 * Le contr�leur pour les intersections d'origine
	 */
	private JComboBox<String> cboOrigineComboBox;
	
	/**
	 * L'�tiquette pour les intersections de destination
	 */
	private JLabel lblDestinationLabel;
	
	/**
	 * Le contr�leur pour les intersections de destination
	 */
	private JComboBox<String> cboDestinationComboBox;
	
	/**
	 * Le bouton de d�marrer et arr�ter
	 */
	private JButton btnDemarrerArreterButton;
	
	/**
	 * Le control d'interfacer pour afficher le journal des �v�nements
	 */
	private JTextArea txtJournalEvenementTextArea;
	
	/**
	 * Le control pour les barres de d�roulement
	 */
	private JScrollPane scrollPaneJournalEvenemen;
	
	/**
	 * Le constructeur de la classe
	 * 
	 * @param titre le titre de la fen�tre
	 */
	public EcranGPS(String titre){
		super(titre);
		
		//ajout de l'icone sur la fen�tre principale
		ImageIcon icon = new ImageIcon("res/icon.png");
		this.setIconImage(icon.getImage());		
		
		//ajout des controleurs de la fen�tre principale
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
		
		lblOrigineLabel = new JLabel("Origine");
		entreeUtilisateur.add(lblOrigineLabel, "cell 0 0,alignx leading");
		
		cboOrigineComboBox = new JComboBox<String>();
		entreeUtilisateur.add(cboOrigineComboBox, "cell 1 0,growx");
		
		lblDestinationLabel = new JLabel("Destination");
		entreeUtilisateur.add(lblDestinationLabel, "flowx,cell 0 1,alignx leading");
		
		cboDestinationComboBox = new JComboBox<String>();
		entreeUtilisateur.add(cboDestinationComboBox, "cell 1 1,growx");
		
		btnDemarrerArreterButton = new JButton("D\u00E9marrer");
		entreeUtilisateur.add(btnDemarrerArreterButton, "cell 0 2 2 1,growx");
		btnDemarrerArreterButton.addActionListener(entreeUtilisateur);
		
		this.journalEvenement = new JournalEvenement();
		verticalSplitPane.setRightComponent(journalEvenement);
		journalEvenement.setLayout(new BorderLayout(0, 0));
		
		txtJournalEvenementTextArea = new JTextArea();
		scrollPaneJournalEvenemen = new JScrollPane(txtJournalEvenementTextArea);
		journalEvenement.add(scrollPaneJournalEvenemen);
		
		//ajouter les intersections dans les controleurs
		ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();
		Vector<Intersection> intersections = reseau.getIntersections();
		for (Intersection s: intersections){
			cboOrigineComboBox.addItem(s.toString());
			cboDestinationComboBox.addItem(s.toString());
		}
	}
	
	/**
	 * M�thode pour rafra�chir l'interface
	 */
	public void RafraichirInterface() {
		carte.revalidate();
		carte.repaint();
	}
	
	/**
	 * M�thode pour afficher un texte dasn le journal
	 * 
	 * @param message texte � afficher dans le journal
	 */
	public void ajouterTexteAuJournal(String message) {
		txtJournalEvenementTextArea.append(message + "\n");
	}
}
