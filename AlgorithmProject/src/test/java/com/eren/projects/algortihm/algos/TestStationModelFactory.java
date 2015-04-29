package com.eren.projects.algortihm.algos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.eren.projects.algortihm.main.StationModelFactory;
import com.eren.projects.algortihm.model.DirectedWeightEdge;
import com.eren.projects.algortihm.model.DirectedWeightEdgeImpl;
import com.eren.projects.algortihm.model.DirectedWeightGraph;
import com.eren.projects.algortihm.model.DirectedWeightGraphImpl;
import com.eren.projects.algortihm.model.Vertex;
import com.eren.projects.algortihm.model.VertexImpl;

public class TestStationModelFactory {
	@Ignore
	@Test
	public void testFillStationModel() throws IOException {
		StationModelFactory stationModelFactory = new StationModelFactory();
		stationModelFactory.fillStationModel("src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt");

		DirectedWeightGraph graph = stationModelFactory.constructGraph();

		assertNotNull(graph);
		assertTrue(graph.getVertices().size() > 0);
	}

}