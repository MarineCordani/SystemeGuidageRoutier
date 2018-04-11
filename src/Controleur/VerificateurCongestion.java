package Controleur;

import java.util.Vector;

import Modele.Arthere;

public class VerificateurCongestion {		
	public void verifierCongestion(){
			
		Vector<Arthere> artheres = MoteurTraitement.getReseauRoutier().getArtheres();
		for(Arthere a: artheres) {
			a.verifierCongestion();
		}
	}
	
}
