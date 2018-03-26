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
	 * Cette méthode nous permet d'obtenir le réseau routier
	 * 
	 * @return le réseau routier
	 */
	public static ReseauRoutier getReseauRoutier(){
		return reseau;
	}
	
}
