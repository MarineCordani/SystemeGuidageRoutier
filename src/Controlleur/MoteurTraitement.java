package Controlleur;

import Modele.ReseauRoutier;
import Vue.EcranGPS;

public class MoteurTraitement {

	static ReseauRoutier reseau = new ReseauRoutier();
	static EcranGPS ecran = new EcranGPS();
	
	public static void main(String[] args) {
		System.out.println("Fake test");//pour tester si tout compile bien
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
