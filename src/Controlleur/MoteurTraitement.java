package Controlleur;

import Modele.ReseauRoutier;
import Vue.EcranGPS;

public class MoteurTraitement {

	static ReseauRoutier reseau = new ReseauRoutier();
	static EcranGPS ecran = new EcranGPS();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static ReseauRoutier obtenirReseauRoutier(){
		return reseau;
	}
	
}
