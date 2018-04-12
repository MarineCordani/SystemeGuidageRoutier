package Controleur;

import Modele.Trajet;

import java.util.Vector;

import Controleur.Algorithm.*;
import Modele.Artere;
import Modele.Intersection;
import Modele.ReseauRoutier;

/**
 * Classe du controleur pour générer le trajet
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class GenerateurTrajet {
	public static Trajet genererTrajet(Intersection a, Intersection b){
		Trajet trajet = new Trajet();
		
		ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();
		Vector<Intersection> intersections = reseau.getIntersections();
		Vector<Artere> arteres = reseau.getArteres();
		
		//peupler la matrice d'adjacence
		int adjacencyMatrix[][] = new int[intersections.size()][intersections.size()];
        int source = 0;
        int destination = 0;
		
        for (int i = 0; i < intersections.size(); i++)  {
    		Intersection intersection = intersections.get(i);
			
			if(a == intersection) {
				source = i;
			}
			
			if(b == intersection) {
				destination = i;
			}		
			
            for (int j = 0; j < intersections.size(); j++) {
            	int poids = Integer.MAX_VALUE;       	

            	Artere artere = GenerateurTrajet.getArthere(arteres, intersections.get(i), intersections.get(j));
            	
            	if(artere != null) {
            		poids = artere.dureeTraverse();
            	}
            	else {
            		poids = 0;
            	}
            	
            	adjacencyMatrix[i][j] = poids;
            }
        }
		
        //Appliquer l'algorithme
		Dijkstra algorithme = new Dijkstra();
		algorithme.calculateShortestPath(adjacencyMatrix, source);
		        
        //System.out.println("Distance = " + algorithme.shortestDistances[destination]);
        
        GenerateurTrajet.genererTrajet(destination, algorithme.parents, trajet, intersections, arteres);
        
		return trajet; // Retourner le trajet creer
	}
	
	private static void genererTrajet(int destination, int[] parents, Trajet trajet, Vector<Intersection> intersections, Vector<Artere> arteres) {
		
		if (destination == Dijkstra.NO_PARENT) {
			return;
		}
		
		int source = parents[destination];
		
		if(source > Dijkstra.NO_PARENT) {
			GenerateurTrajet.genererTrajet(source, parents, trajet, intersections, arteres);
			Intersection a = intersections.get(source);
			Intersection b = intersections.get(destination);
			Artere r = GenerateurTrajet.getArthere(arteres, a, b);
			if(r != null) {
				trajet.ajouterProchainArthere(r);
			}
		}
	}
	
	
	private static  Artere getArthere(Vector<Artere> arteres, Intersection a, Intersection b) {
		for(int i = 0;i < arteres.size(); i++) {
			Artere artere = arteres.get(i);
			
			if((a == artere.getA() && b == artere.getB()) || (b == artere.getA() && a == artere.getB())) {
				return artere;
			}			
		}
		
		return null;
	}
}
