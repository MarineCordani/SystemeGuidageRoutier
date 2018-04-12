package Controleur;

import java.util.Vector;

import Modele.Artere;

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
		
		Vector<Artere> artere = MoteurTraitement.getReseauRoutier().getArteres();
		Artere artereAleatoire; 
		int indexAleatoire = (int)(Math.random() * artere.size());
		artereAleatoire = artere.get(indexAleatoire);

		do{
			if (artereAleatoire.creerAccident()){
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'arthère " + artereAleatoire.getIdentifiant() + " est accidentée");
				return true;
			}
			else
			{
				indexAleatoire++; //Essayer de creer accident sur prochain artere
			}
		} while (indexAleatoire != artere.size());

		return false;
		
	}
	
}
