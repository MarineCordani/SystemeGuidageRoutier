package Controleur;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Vehicule;
import Vue.EcranGPS;
import Vue.EcranGPS;

public class MoteurTraitement {

	static private ReseauRoutier reseau;
	static private EcranGPS ecran;

	public static void main(String[] args) {
		reseau = new ReseauRoutier();
		reseau.creerVehiculeinitiaux();
		//SwingUtilities.invokeLater(new Runnable() { 
	         //public void run() { 
	        	 creerEtAfficherInterface(); 
	       //  } 
	      //}); 
		/*Intersection a = reseau.getIntersection("A1");
		Intersection b = reseau.getIntersection("C4");

		Trajet trajet = GenerateurTrajet.genererTrajet(a, b);
		trajet.imprimerTrajet();*/
		
	      
		//Cr�er boucle d'execution
		GenerateurVehicule gv = new GenerateurVehicule();
		Vehicule vehiculeTemporaire = null;
		Intersection intersectionTemporaire = null;
		
		GenerateurAccident ga = new GenerateurAccident();
		
		do{
			//Generer voiture
			vehiculeTemporaire = gv.genererVehicule();
			if (vehiculeTemporaire != null){
				reseau.ajouterVehicule(vehiculeTemporaire);
			}
			
			//Generer accident
			ga.genererAccident();
			
			//Faire avancer vehicule
			//TODO: Faire deplacer les vehicules
			reseau.avancerVehicule();
			
			//Attente de quelques milisecondes pour que l'animation soit visible
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}
			//Rafraichir carte
			ecran.RafraichirInterface();
		} while (true);
		
	}

	/**
	 * M�thode nous permet d'obtenir le r�seau routier
	 * 
	 * @return le r�seau routier
	 */
	public static ReseauRoutier getReseauRoutier() {
		return reseau;
	}

	public static EcranGPS getEcran() {
		return ecran;
	}

	
	/**
	 * M�thode pour cr�er et afficher la fen�tre principale
	 */
	private static void creerEtAfficherInterface() {
		ecran = new EcranGPS("Syst�me de guidage dans un r�seau routier");
		ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecran.setLocation(0, 0);
		ecran.setSize(1024, 600);
		ecran.setVisible(true);
	}

}
