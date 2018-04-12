package Controleur;

import java.util.Vector;

import Modele.Arthere;

public class VerificateurCongestion {		
	public void verifierCongestion(){
			
		Vector<Arthere> artheres = MoteurTraitement.getReseauRoutier().getArtheres();
		for(Arthere a: artheres) {
			if(a.verifierCongestion()) {
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'arthère " + a.getIdentifiant() + " est congestionnée");
			}
		}
	}
	
}
