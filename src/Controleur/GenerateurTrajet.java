package Controleur;

import Modele.Trajet;

import java.util.Vector;

import Controleur.Algorithm.*;
import Modele.Arthere;
import Modele.Intersection;
import Modele.ReseauRoutier;

/**
 * Classe du controleur pour g�n�rer le trajet
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class GenerateurTrajet {
	public static Trajet genererTrajet(Intersection a, Intersection b){
		Trajet trajet = new Trajet();
		
		ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();
		Vector<Intersection> intersections = reseau.getIntersections();
		Vector<Arthere> artheres = reseau.getArtheres();
		
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

            	Arthere arthere = GenerateurTrajet.getArthere(artheres, intersections.get(i), intersections.get(j));
            	
            	if(arthere != null) {
            		poids = arthere.dureeTraverse();
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
        
        GenerateurTrajet.genererTrajet(destination, algorithme.parents, trajet, intersections, artheres);
        
		return trajet; // Retourner le trajet creer
	}
	
	private static void genererTrajet(int destination, int[] parents, Trajet trajet, Vector<Intersection> intersections, Vector<Arthere> artheres) {
		
		if (destination == Dijkstra.NO_PARENT) {
			return;
		}
		
		int source = parents[destination];
		
		if(source > Dijkstra.NO_PARENT) {
			GenerateurTrajet.genererTrajet(source, parents, trajet, intersections, artheres);
			Intersection a = intersections.get(source);
			Intersection b = intersections.get(destination);
			Arthere r = GenerateurTrajet.getArthere(artheres, a, b);
			if(r != null) {
				trajet.ajouterProchainArthere(r);
			}
		}
	}
	
	
	private static  Arthere getArthere(Vector<Arthere> artheres, Intersection a, Intersection b) {
		for(int i = 0;i < artheres.size(); i++) {
			Arthere arthere = artheres.get(i);
			
			if((a == arthere.getA() && b == arthere.getB()) || (b == arthere.getA() && a == arthere.getB())) {
				return arthere;
			}			
		}
		
		return null;
	}
}