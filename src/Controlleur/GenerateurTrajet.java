package Controlleur;

import Modele.Trajet;
import Controlleur.Algorithm.*;
import Modele.Arthere;
import Modele.Intersection;
import Modele.ReseauRoutier;

public class GenerateurTrajet {
	public Trajet genererTrajet(Intersection a, Intersection b){
		Trajet trajet = new Trajet();
		
		ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();
		Intersection[] intersections = reseau.getIntersections();
		Arthere[] artheres = reseau.getArtheres();
		
		int adjacencyMatrix[][] = new int[intersections.length + 1][intersections.length + 1];
        int source = 0;
        int destination = 0;
		
        for (int i = 1; i <= intersections.length; i++)  {
    		Intersection intersection = intersections[i - 1];
			
			if(a == intersection) {
				source = i - 1;
			}
			
			if(b == intersection) {
				destination = i - 1;
			}		
			
            for (int j = 1; j <= intersections.length; j++) {
            	int poids = Integer.MAX_VALUE;       	

            	Arthere arthere = this.getArthere(artheres, intersections[i], intersections[j]);
            	
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

	private Arthere getArthere(Arthere[] artheres, Intersection a, Intersection b) {
		for(int i = 0;i < artheres.length; i++) {
			Arthere arthere = artheres[i];
			
			if((a == arthere.getA() && b == arthere.getB()) || (b == arthere.getA() && a == arthere.getB())) {
				return arthere;
			}			
		}
		
		return null;
	}
}
