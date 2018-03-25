package Controlleur;

import Modele.Trajet;
import Controlleur.Algorithm.*;
import Modele.Intersection;
import Modele.ReseauRoutier;

public class GenerateurTrajet {
	public Trajet genererTrajet(Intersection a, Intersection b){
		Trajet trajet = new Trajet();
		
		ReseauRoutier reseau = MoteurTraitement.obtenirReseauRoutier();
		
		
		//Utiliser MoteurTraitement.obtenirReseauRoutier(); pour acceder carte 
		
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D"); 
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		 
		nodeA.addDestination(nodeB, 10);
		nodeA.addDestination(nodeC, 15);
		 
		nodeB.addDestination(nodeD, 12);
		nodeB.addDestination(nodeF, 15);
		 
		nodeC.addDestination(nodeE, 10);
		 
		nodeD.addDestination(nodeE, 2);
		nodeD.addDestination(nodeF, 1);
		 
		nodeF.addDestination(nodeE, 5);
		 
		Graph graph = new Graph();
		 
		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);
		 
		graph = Dijkstra.calculateShortestPathFromSource(graph, nodeA);
		
		return trajet; //Retourner le trajet creer
	}
}
