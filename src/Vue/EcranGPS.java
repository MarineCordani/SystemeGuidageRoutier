package Vue;

import javax.swing.JFrame;

public class EcranGPS extends JFrame {
	private EntreeUtilisateur entreeUtilisateur;
	private JournalEvenement journalEvenement;
	private Carte carte;
	
	
	public EcranGPS(){
		this.entreeUtilisateur = new EntreeUtilisateur();
		this.journalEvenement = new JournalEvenement();
		this.carte = new Carte();
		
		this.add(entreeUtilisateur);
		this.add(journalEvenement);
		this.add(carte);
	}
}
