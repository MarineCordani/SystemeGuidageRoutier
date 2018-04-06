package Modele;

import java.util.Vector;

public class Trajet {

	private Vector<Arthere> artheres = new Vector<Arthere>();
	
	
	public Arthere retirerProchainArthere(){ //Retire le prochain arthere du trajet et l'envoi a la m�thode appelante
		if (artheres.isEmpty()){
			return null;
		}
		Arthere temporaire = artheres.get(0);
		artheres.remove(0);
		return temporaire;
	}
	
	public void ajouterProchainArthere(Arthere a){
		artheres.add(a);
	}
	
	public boolean estTermine(){ //Indique (True/false) s'il reste des �l�ments
		return artheres.isEmpty();
	}

	//Pour d�boggage seulement
	public void imprimerTrajet(){
		for (Arthere a: artheres){
			System.out.print(a.toString() + ",");
		}
		System.out.println();
	}
	
	
	
}
