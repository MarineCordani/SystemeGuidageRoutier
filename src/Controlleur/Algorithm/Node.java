package Controlleur.Algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Node {

	private String name;

	private LinkedList<Node> shortestPath = new LinkedList<Node>();

	private Integer distance = Integer.MAX_VALUE;

	Map<Node, Integer> adjacentNodes = new HashMap<>();

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
}
