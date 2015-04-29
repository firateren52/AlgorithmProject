package com.eren.projects.algortihm.model;

/**
 * @author firat.eren
 * 
 */
public class DirectedWeightEdgeImpl implements DirectedWeightEdge {
	private final Vertex source;
	private final Vertex destination;
	private final String id;
	private final int weight;

	public DirectedWeightEdgeImpl(String id, Vertex source, Vertex destination,
			int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return source + " to " + destination;
	}

}