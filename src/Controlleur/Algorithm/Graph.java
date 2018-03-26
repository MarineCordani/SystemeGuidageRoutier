package Controlleur.Algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe pour repr�senter un graphe
 * http://www.baeldung.com/java-dijkstra
 * 
 * @author Baeldung
 *
 */
public class Graph {

	private Set<Node> nodes = new HashSet<>();

	public void addNode(Node node) {
		nodes.add(node);
	}

	// getters and setters
}
