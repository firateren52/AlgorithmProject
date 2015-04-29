package com.eren.projects.algortihm.algos;

import java.util.HashMap;
import java.util.Map;

import com.eren.projects.algortihm.model.DirectedWeightEdge;
import com.eren.projects.algortihm.model.DirectedWeightGraph;
import com.eren.projects.algortihm.model.Vertex;

/**
 * @author firat.eren Implementation of bellman-ford algorithm for shortest path
 *         We use negative value edges longest path problem which is same with
 *         positive value shortest path problem
 *
 */
public class DynamicStationAlgorithm {
	private final DirectedWeightGraph graph;

	public DynamicStationAlgorithm(DirectedWeightGraph graph) {
		this.graph = graph;
	}

	public Map<Vertex, Double> solveProblem(Vertex vertex) {
		Map<Vertex, Double> path = new HashMap<Vertex, Double>();

		for (Vertex v : graph.getVertices()) {
			path.put(v, Double.POSITIVE_INFINITY);
		}
		path.put(vertex, 0.0);

		int count;
		for (count = 0; count < graph.getVertices().size(); ++count) {
			if (!updatePath(graph, path))
				break;
		}

		if (count == graph.getVertices().size()) {
			throw new RuntimeException("Negative length cycle detected");
		}

		return path;
	}

	private boolean updatePath(DirectedWeightGraph g, Map<Vertex, Double> path) {
		boolean isUpdated = false;
		for (Vertex v : g.getVertices()) {
			for (DirectedWeightEdge e : g.getEdges(v)) {
				double oldCost = path.get(e.getDestination());
				double newCost = path.get(e.getSource()) + e.getWeight();
				if (newCost < oldCost) {
					path.put(e.getDestination(), newCost);
					isUpdated = true;
				}
			}
		}

		return isUpdated;
	}
}