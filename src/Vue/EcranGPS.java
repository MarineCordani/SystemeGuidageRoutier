package Vue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class EcranGPS extends JFrame {
	private EntreeUtilisateur entreeUtilisateur;
	private JournalEvenement journalEvenement;
	private Carte carte;
	
	
	public EcranGPS(String titre){
		super(titre);
		
		ImageIcon icon = new ImageIcon("res/icon.png");
		this.setIconImage(icon.getImage());
		
		this.entreeUtilisateur = new EntreeUtilisateur();
		this.journalEvenement = new JournalEvenement();
		this.carte = new Carte();
		
		this.add(entreeUtilisateur);
		this.add(journalEvenement);
		this.add(carte);
	}
}
