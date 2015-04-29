package com.eren.projects.algortihm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author firat.eren
 *
 * 
 */
public class DirectedWeightGraphImpl implements DirectedWeightGraph {
	private final List<Vertex> vertices;
	private final List<DirectedWeightEdge> edges;

	public DirectedWeightGraphImpl(List<Vertex> vertices, List<DirectedWeightEdge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<DirectedWeightEdge> getEdges() {
		return edges;
	}

	public List<Vertex> getAdjacents(Vertex vertex) {
		List<Vertex> adjacents = new ArrayList<Vertex>();
		for (DirectedEdge edge : edges) {
			if (edge.getSource().equals(vertex)) {
				adjacents.add(edge.getDestination());
			}
		}
		return adjacents;
	}

	public List<DirectedWeightEdge> getEdges(Vertex vertex) {
		List<DirectedWeightEdge> adjacents = new ArrayList<DirectedWeightEdge>();
		for (DirectedWeightEdge edge : edges) {
			if (edge.getSource().equals(vertex)) {
				adjacents.add(edge);
			}
		}
		return adjacents;
	}

}
