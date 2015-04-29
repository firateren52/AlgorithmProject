package com.eren.projects.algortihm.algos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.util.FileUtils;

import com.eren.projects.algortihm.algos.DijkstrasAlgorithm;
import com.eren.projects.algortihm.main.MaximumProfitStationProblem;
import com.eren.projects.algortihm.main.StationModel;
import com.eren.projects.algortihm.main.StationModelFactory;
import com.eren.projects.algortihm.model.DirectedWeightGraph;
import com.eren.projects.algortihm.model.DirectedWeightGraphImpl;
import com.eren.projects.algortihm.model.Vertex;

public class TestMaximumProfitStationProblem {
	DirectedWeightGraph graph;
	List<StationModel> stationModels;

	// @Ignore
	// @Before
	// public void testFillStationModel() throws IOException {
	// StationModelFactory stationModelFactory = new StationModelFactory();
	// stationModelFactory.fillStationModel("src\\main\\java\\com\\eren\\projects\\algortihm\\data\\greedy_1.txt");
	//
	// graph = stationModelFactory.constructGraph();
	//
	// assertNotNull(graph);
	// assertTrue(graph.getVertices().size() > 0);
	// }
	//
	// @Ignore
	// @Test
	// public void greedyAlgorithm() {
	// MaximumProfitStationProblem problem = new
	// MaximumProfitStationProblem(graph);
	// LinkedList<Vertex> path = problem.greedyAlgorithm();
	//
	// for (Vertex vertex : path) {
	// System.out.println(vertex);
	// }
	// }
	
	@BeforeClass
	public static void generateStationModel() {
		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
		StationModelFactory stationModelFactory = new StationModelFactory();
		stationModelFactory.generateStationModel(fileName, 10);
	}

	@Test
	public void testGreedy() throws IOException {
		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
		StationModelFactory stationModelFactory = new StationModelFactory();
		stationModelFactory.fillStationModel(fileName);

		// DirectedWeightGraph graph = stationModelFactory.constructGraph();
		GreedyStationProblem problem = new GreedyStationProblem(stationModelFactory.getMinimumDistance(),
				stationModelFactory.getStationModels());
		List<StationModel> result = problem.solveProblem();
		int maxProfit = problem.getMaxProfit(result);

		assertNotNull(maxProfit);
	}

	
	@Ignore
	@Test
	public void testBruteForce() throws IOException {
		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
		StationModelFactory stationModelFactory = new StationModelFactory();
		stationModelFactory.fillStationModel(fileName);

		// DirectedWeightGraph graph = stationModelFactory.constructGraph();
		BruteForceStationProblem problem = new BruteForceStationProblem(stationModelFactory.getMinimumDistance(),
				stationModelFactory.getStationModels());
		List<StationModel> result = problem.solveProblem();
		int maxProfit = problem.getMaxProfit(result);

		assertNotNull(maxProfit);
	}

	@Test
	public void testDynamic() throws IOException {
		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
		StationModelFactory stationModelFactory = new StationModelFactory();
//		stationModelFactory.generateStationModel(fileName, 10);
		stationModelFactory.fillStationModel(fileName);

		DirectedWeightGraph graph = stationModelFactory.constructGraph();
		DynamicStationAlgorithm problem = new DynamicStationAlgorithm(graph);
		Map<Vertex, Double> result = problem.solveProblem(graph.getVertices().get(0));
		Double maxProfit = -result.get(graph.getVertices().get(graph.getVertices().size() - 1));

		assertNotNull(maxProfit);
	}
}
