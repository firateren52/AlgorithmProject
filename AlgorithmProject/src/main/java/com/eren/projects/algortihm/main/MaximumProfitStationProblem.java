package com.eren.projects.algortihm.main;

import java.util.LinkedList;

import com.eren.projects.algortihm.algos.DijkstrasAlgorithm;
import com.eren.projects.algortihm.model.DirectedWeightGraph;
import com.eren.projects.algortihm.model.DirectedWeightGraphImpl;
import com.eren.projects.algortihm.model.Vertex;

public class MaximumProfitStationProblem {
	DirectedWeightGraph graph;

	public MaximumProfitStationProblem(DirectedWeightGraph graph) {
		this.graph = graph;
	}

	public LinkedList<Vertex> greedyAlgorithm() {
		DijkstrasAlgorithm dijkstra = new DijkstrasAlgorithm(graph);
		dijkstra.execute(graph.getVertices().get(0));
		LinkedList<Vertex> path = dijkstra.getPath(graph.getVertices().get(graph.getVertices().size() - 1));
		return path;
	}

}
