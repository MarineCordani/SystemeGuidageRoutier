package Vue;

import javax.swing.JFrame;

public class EcranGPS extends JFrame {
	private EntreeUtilisateur eu;
	private JournalEvenement je;
	private Carte c;
	
	
	public EcranGPS(){
		eu = new EntreeUtilisateur();
		je = new JournalEvenement();
		c = new Carte();
		
		this.add(eu);
		this.add(je);
		this.add(c);
	}
}
