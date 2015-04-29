package com.eren.projects.algortihm.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eren.projects.algortihm.model.DirectedEdge;
import com.eren.projects.algortihm.model.DirectedWeightEdge;
import com.eren.projects.algortihm.model.DirectedWeightGraph;
import com.eren.projects.algortihm.model.Vertex;

/**
 * @author firat.eren Find shortest path by Dijkstra Algorithm
 */
public class DijkstrasAlgorithm {

	private final DirectedWeightGraph graph;
	private Set<Vertex> matchedvertices;
	private Set<Vertex> unmatchedvertices;
	private Map<Vertex, Vertex> ancestors;
	private Map<Vertex, Integer> distance;

	public DijkstrasAlgorithm(DirectedWeightGraph graph) {
		this.graph = graph;
	}

	public void execute(Vertex source) {
		matchedvertices = new HashSet<Vertex>();
		unmatchedvertices = new HashSet<Vertex>();
		distance = new HashMap<Vertex, Integer>();
		ancestors = new HashMap<Vertex, Vertex>();
		distance.put(source, 0);
		unmatchedvertices.add(source);
		while (unmatchedvertices.size() > 0) {
			Vertex minVertex = getMinimumVertex(unmatchedvertices);
			matchedvertices.add(minVertex);
			unmatchedvertices.remove(minVertex);
			findMinimalDistances(minVertex);
		}
	}

	/*
	 * find the path from the source to the selected target
	 */
	public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		// if a path exists return
		if (ancestors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (ancestors.get(step) != null) {
			step = ancestors.get(step);
			path.add(step);
		}

		Collections.reverse(path);
		return path;
	}

	private void findMinimalDistances(Vertex vertex) {
		List<Vertex> adjacentVertices = getAdjacents(vertex);
		for (Vertex target : adjacentVertices) {
			int calculatedDistance =  getShortestDistance(vertex) + getDistance(vertex, target);
			if (getShortestDistance(target) > calculatedDistance) {
				distance.put(target, calculatedDistance);
				ancestors.put(target, vertex);
				unmatchedvertices.add(target);
			}
		}

	}

	private int getDistance(Vertex vertex, Vertex target) {
		for (DirectedWeightEdge edge : graph.getEdges()) {
			if (edge.getSource().equals(vertex) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Undefined error in getDistance(?,?) method");
	}

	private List<Vertex> getAdjacents(Vertex vertex) {
		List<Vertex> adjacents = new ArrayList<Vertex>();
		for (DirectedEdge edge : graph.getEdges()) {
			if (edge.getSource().equals(vertex) && !isSettled(edge.getDestination())) {
				adjacents.add(edge.getDestination());
			}
		}
		return adjacents;
	}

	private Vertex getMinimumVertex(Set<Vertex> vertices) {
		Vertex minimum = null;
		for (Vertex vertex : vertices) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertex vertex) {
		return matchedvertices.contains(vertex);
	}

	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return 0;
		} else {
			return d;
		}
	}

}
