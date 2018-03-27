package Controlleur;

import Modele.Trajet;

import java.util.Vector;

import Controlleur.Algorithm.*;
import Modele.Arthere;
import Modele.Intersection;
import Modele.ReseauRoutier;

public class GenerateurTrajet {
	public Trajet genererTrajet(Intersection a, Intersection b){
		Trajet trajet = new Trajet();
		
		ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();
		Vector<Intersection> intersections = reseau.getIntersections();
		Vector<Arthere> artheres = reseau.getArtheres();
		
		int adjacencyMatrix[][] = new int[intersections.size() + 1][intersections.size() + 1];
        int source = 0;
        int destination = 0;
		
        for (int i = 1; i <= intersections.size(); i++)  {
    		Intersection intersection = intersections.get(i - 1);
			
			if(a == intersection) {
				source = i - 1;
			}
			
			if(b == intersection) {
				destination = i - 1;
			}		
			
            for (int j = 1; j <= intersections.size(); j++) {
            	int poids = Integer.MAX_VALUE;       	

            	Arthere arthere = this.getArthere(artheres, intersections.get(i), intersections.get(j));
            	
            	if(arthere != null) {
            		poids = arthere.dureeTraverse();
            	}
            	else {
            		poids = Integer.MAX_VALUE;
            	}
            	
            	adjacencyMatrix[i][j] = poids;
            	
                if (i == j)
                {
                    adjacencyMatrix[i][j] = 0;
                    continue;
                }
                
                if (adjacencyMatrix[i][j] == 0)
                {
                    adjacencyMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
				
		
 
		Dijkstra algorithme = new Dijkstra();
		algorithme.calculateShortestPath(adjacencyMatrix, source);
/*
		System.out.println("The Shorted Path from " + source + " to " + destination + " is: ");
		for (int i = 1; i <= algorithme.distances.length - 1; i++) {
			if (i == destination)
				System.out.println(source + " to " + i + " is " + algorithme.distances[i]);
		}
*/
		return trajet; // Retourner le trajet creer
	}

	private Arthere getArthere(Vector<Arthere> artheres, Intersection a, Intersection b) {
		for(int i = 0;i < artheres.size(); i++) {
			Arthere arthere = artheres.get(i);
			
			if((a == arthere.getA() && b == arthere.getB()) || (b == arthere.getA() && a == arthere.getB())) {
				return arthere;
			}			
		}
		
		return null;
	}
}
