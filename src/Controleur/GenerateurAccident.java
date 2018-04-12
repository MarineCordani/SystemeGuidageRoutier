package Controleur;

import java.util.Vector;

import Modele.Arthere;

public class GenerateurAccident {

	private double probabilite;
	
	public GenerateurAccident(){
		//probabilite = 0.05;
		probabilite = 0.50;
	}
	
	public GenerateurAccident(double p){
		probabilite = p;
	}
	
	public boolean genererAccident(){
		//Retourne true si un accident a bien eu lieu
		//TODO
		
		if (Math.random() > probabilite) {
			return false;
		}
		
		Vector<Arthere> arthere = MoteurTraitement.getReseauRoutier().getArtheres();
		Arthere arthereAleatoire; 
		int indexAleatoire = (int)(Math.random() * arthere.size());
		arthereAleatoire = arthere.get(indexAleatoire);

		do{
			if (arthereAleatoire.creerAccident()){
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'arthère " + arthereAleatoire.getIdentifiant() + " est accidentée");
				return true;
			}
			else
			{
				indexAleatoire++; //Essayer de creer accident sur prochain arthere
			}
		} while (indexAleatoire != arthere.size());

		return false;
		
	}
	
}
