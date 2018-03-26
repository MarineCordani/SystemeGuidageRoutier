package Controlleur.Algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Classe pour représenter un sommet du graphe
 * http://www.baeldung.com/java-dijkstra
 * 
 * @author Baeldung
 *
 */
public class Node {

	private String name;
	private LinkedList<Node> shortestPath = new LinkedList<Node>();
	private Integer distance = Integer.MAX_VALUE;
	private Map<Node, Integer> adjacentNodes = new HashMap<>();
	
	private boolean isVisited = false;
	private boolean isOrigin = false;
	private boolean isDestination = false;

	public void addDestination(Node destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	public Node(String name) {
		this.name = name;
	}

	// getters and setters
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public int getDistance() {
		return this.distance;
	}

	public LinkedList<Node> getShortestPath() {
		return this.shortestPath;
	}

	public void setShortestPath(LinkedList<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}
	
	/**
	 * Cette méthode permet de connaître l'attribut isVisited
	 * 
	 * @return l'attribut isVisited
	 */
	public boolean isVisited(){
		return this.isVisited;
	}
	
	/**
	 * Cette méthode permet de modifier l'attribut isVisited
	 * 
	 * @param flag le nouvel attribut isVisited
	 */
	public void setIsVisited(boolean flag){
		this.isVisited = flag;
	}
	
	/**
	 * Cette méthode permet de connaître l'attribut isOrigin
	 * 
	 * @return l'attribut isOrigin
	 */
	public boolean isOrigin(){
		return this.isOrigin;
	}
	
	/**
	 * Cette méthode permet de modifier l'attribut isOrigin
	 * 
	 * @param flag le nouvel attribut isOrigin
	 */
	public void setIsOrigin(boolean flag){
		this.isOrigin = flag;
	}
	
	/**
	 * Cette méthode permet de connaître l'attribut isDestination
	 * 
	 * @return l'attribut isDestination
	 */
	public boolean isDestination(){
		return this.isDestination;
	}
	
	/**
	 * Cette méthode permet de modifier l'attribut isDestination
	 * 
	 * @param flag le nouvel attribut isDestination
	 */
	public void setIsDestination(boolean flag){
		this.isDestination = flag;
	}
	
	
}
