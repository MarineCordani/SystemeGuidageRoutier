package Controlleur;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Trajet;
import Vue.EcranGPS;

public class MoteurTraitement {

	static ReseauRoutier reseau = new ReseauRoutier();
	static EcranGPS ecran;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 
	         public void run() { 
	        	 creerEtAfficherInterface(); 
	         } 
	      }); 
		/*Intersection a = reseau.getIntersection("A1");
		Intersection b = reseau.getIntersection("C4");

		Trajet trajet = GenerateurTrajet.genererTrajet(a, b);
		trajet.imprimerTrajet();*/
	}

	/**
	 * Cette m�thode nous permet d'obtenir le r�seau routier
	 * 
	 * @return le r�seau routier
	 */
	public static ReseauRoutier getReseauRoutier() {
		return reseau;
	}

	private static void creerEtAfficherInterface() {
		ecran = new EcranGPS("Syst�me de guidage dans un r�seau routier");
		ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecran.setLocation(0, 0);
		ecran.setSize(1024, 600);
		ecran.setVisible(true);
	}

}
