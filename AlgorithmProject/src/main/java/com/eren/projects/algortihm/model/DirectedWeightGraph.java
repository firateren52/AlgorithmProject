package com.eren.projects.algortihm.model;

import java.util.List;

/**
 * @author firat.eren
 *
 *         directed weighted graph
 */
public interface DirectedWeightGraph {

	public List<Vertex> getVertices();

	public List<DirectedWeightEdge> getEdges();
	
	public List<Vertex> getAdjacents(Vertex vertex);
	
	public List<DirectedWeightEdge> getEdges(Vertex vertex);

}
