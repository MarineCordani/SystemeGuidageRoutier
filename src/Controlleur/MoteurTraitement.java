package Controlleur;

import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Trajet;
import Vue.EcranGPS;

public class MoteurTraitement {

	static ReseauRoutier reseau = new ReseauRoutier();
	static EcranGPS ecran = new EcranGPS();
	
	public static void main(String[] args) {
		
		Intersection a = reseau.getIntersection("A1");
		Intersection b = reseau.getIntersection("C1");
		
		Trajet trajet = GenerateurTrajet.genererTrajet(a, b);
		
	}

	/**
	 * Cette m�thode nous permet d'obtenir le r�seau routier
	 * 
	 * @return le r�seau routier
	 */
	public static ReseauRoutier getReseauRoutier(){
		return reseau;
	}
	
}
