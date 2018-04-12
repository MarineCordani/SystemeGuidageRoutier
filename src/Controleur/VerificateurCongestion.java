package Controleur;

import java.util.Vector;

import Modele.Arthere;

public class VerificateurCongestion {	
	private boolean siChangementEtat = false;
	public void verifierCongestion(){
			
		Vector<Arthere> artheres = MoteurTraitement.getReseauRoutier().getArtheres();
		for(Arthere a: artheres) {
			boolean presenceCongestion = a.getPresenceCongestion();
			boolean congestionDetecte = a.verifierCongestion();
			if(presenceCongestion == false && congestionDetecte) {
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'arthère " + a.getIdentifiant() + " est congestionnée");
			}
		}
	}
	
}
