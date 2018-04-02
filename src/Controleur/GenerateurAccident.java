package Controleur;

import java.util.Vector;

import Modele.Arthere;

public class GenerateurAccident {

	private double probabilite;
	
	public GenerateurAccident(){
		probabilite = 0.05;
	}
	
	public GenerateurAccident(double p){
		probabilite = p;
	}
	
	public boolean genererAccident(){
		
		//Retourne true si un accident a bien eu lieu
		//TODO
		Vector<Arthere> arthere = MoteurTraitement.getReseauRoutier().getArtheres();
		Arthere arthereAleatoire; 
		int indexAleatoire = (int)(Math.random() * arthere.size());
		arthereAleatoire = arthere.get(indexAleatoire);

		do{
			if (arthereAleatoire.creerAccident()){
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
