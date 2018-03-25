package Vue;

import javax.swing.JFrame;

public class EcranGPS extends JFrame {

	
	EntreeUtilisateur eu;
	JournalEvenement je;
	Carte c;
	
	
	public EcranGPS(){
		eu = new EntreeUtilisateur();
		je = new JournalEvenement();
		c = new Carte();
		
		this.add(eu);
		this.add(je);
		this.add(c);
	}
	
	
}
