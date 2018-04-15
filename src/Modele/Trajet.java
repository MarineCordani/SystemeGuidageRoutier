package Modele;

import java.util.Vector;

/**
 * Classe du mod�le qui constitue l'ensemble d'art�res suivies par un v�hicule
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class Trajet {

	private Vector<Artere> arteres = new Vector<Artere>();
	
	
	public Artere retirerProchainArthere(){ //Retire le prochain artere du trajet et l'envoi a la m�thode appelante
		if (arteres.isEmpty()){
			return null;
		}
		Artere temporaire = arteres.get(0);
		arteres.remove(0);
		return temporaire;
	}
	
	public void ajouterProchainArthere(Artere a){
		arteres.add(a);
	}
	
	public boolean estTermine(){ //Indique (True/false) s'il reste des �l�ments
		return arteres.isEmpty();
	}

	//Pour d�boggage seulement
	public void imprimerTrajet(){
		for (Artere a: arteres){
			System.out.print(a.toString() + ",");
		}
		System.out.println();
	}
	
	public Vector<Artere> getArteres(){
		return arteres;
	}
	
	
}
