package com.eren.projects.algortihm.algos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.javailp.Linear;
import net.sf.javailp.OptType;
import net.sf.javailp.Problem;
import net.sf.javailp.Result;
import net.sf.javailp.Solver;
import net.sf.javailp.SolverFactory;
import net.sf.javailp.SolverFactoryLpSolve;

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

public class BenchmarkMaximumProfitStationProblem {
	DirectedWeightGraph graph;
	List<StationModel> stationModels;

	@Ignore
	@BeforeClass
	public static void generateStationModel() throws IOException {
		for (int i = 1; i <= 100; i++) {
			String fileName = "src\\main\\resources\\data\\input\\input_" + i + ".txt";
			StationModelFactory stationModelFactory = new StationModelFactory(fileName);
			stationModelFactory.generateStationModel(10, i * 10);
		}

		// BenchmarkMaximumProfitStationProblem problem = new
		// BenchmarkMaximumProfitStationProblem();
		//
		// problem.testGreedy();
		// problem.testBruteForce();
	}
	@Ignore
	@Test
	public void testGreedy() throws IOException {
		long totalTime = 0;
		for (int i = 1; i <= 50; i++) {
			String fileName = "src\\main\\resources\\data\\input\\input_" + i + ".txt";
			StationModelFactory stationModelFactory = new StationModelFactory(fileName);
			stationModelFactory.fillStationModel();

			// DirectedWeightGraph graph = stationModelFactory.constructGraph();

			long time = 0;
			int maxProfit = 0;
			long startTime = System.nanoTime();
			for (int j = 1; j <= 10; j++) {
				GreedyStationProblem problem = new GreedyStationProblem(stationModelFactory.getMinimumDistance(),
						stationModelFactory.getStationModels());
				List<StationModel> result = problem.solveProblem();
				maxProfit = problem.getMaxProfit(result);
			}
			time = (System.nanoTime() - startTime) / 10;

			totalTime = totalTime + time;

			stationModelFactory.writeStatistics("greedy", time, maxProfit);
			stationModelFactory.writeSumStatistics("greedy", time);
		}

		// String fileName = "src\\main\\resources\\data\\input\\input_" + i +
		// ".txt";
		StationModelFactory stationModelFactory = new StationModelFactory("");
		stationModelFactory.writeSumStatistics("greedy", totalTime / 50);

	}

	
	@Test
	public void testBruteForce() throws IOException {
		long totalTime = 0;
		for (int i = 1; i <= 50; i++) {
			String fileName = "src\\main\\resources\\data\\input\\input_" + i + ".txt";
			StationModelFactory stationModelFactory = new StationModelFactory(fileName);
			stationModelFactory.fillStationModel();

			long time = 0;
			int maxProfit = 0;
			long startTime = System.nanoTime();
			for (int j = 1; j <= 10; j++) {
				// DirectedWeightGraph graph =
				// stationModelFactory.constructGraph();
				BruteForceStationProblem problem = new BruteForceStationProblem(
						stationModelFactory.getMinimumDistance(), stationModelFactory.getStationModels());
				List<StationModel> result = problem.solveProblem();
				maxProfit = problem.getMaxProfit(result);
			}
			time = (System.nanoTime() - startTime) / 10;

			totalTime = totalTime + time;

			stationModelFactory.writeStatistics("bruteforce", time, maxProfit);
			stationModelFactory.writeSumStatistics("bruteforce", time);
		}

		StationModelFactory stationModelFactory = new StationModelFactory("");
		stationModelFactory.writeSumStatistics("bruteforce", totalTime / 50);

	}

	@Ignore
	@Test
	public void testDynamic() throws IOException {
		long totalTime = 0;
		for (int i = 1; i <= 50; i++) {
			String fileName = "src\\main\\resources\\data\\input\\input_" + i + ".txt";
			StationModelFactory stationModelFactory = new StationModelFactory(fileName);
			stationModelFactory.fillStationModel();

			DirectedWeightGraph graph = stationModelFactory.constructGraph();

			long time = 0;
			Double maxProfit = null;
			long startTime = System.nanoTime();
			for (int j = 1; j <= 10; j++) {
				DynamicStationAlgorithm problem = new DynamicStationAlgorithm(graph);
				Map<Vertex, Double> result = problem.solveProblem(graph.getVertices().get(0));
				maxProfit = -result.get(graph.getVertices().get(graph.getVertices().size() - 1));
			}
			time = (System.nanoTime() - startTime) / 10;
			totalTime = totalTime + time;

			stationModelFactory.writeStatistics("dynamic", time, maxProfit.intValue());
			stationModelFactory.writeSumStatistics("dynamic", time);
		}

		StationModelFactory stationModelFactory = new StationModelFactory("");
		stationModelFactory.writeSumStatistics("dynamic", totalTime / 50);

	}

	@Ignore
	@Test
	public void testDivideAndConquer() throws IOException {
		long totalTime = 0;
		for (int i = 1; i <= 50; i++) {
			String fileName = "src\\main\\resources\\data\\input\\input_" + i + ".txt";
			StationModelFactory stationModelFactory = new StationModelFactory(fileName);
			stationModelFactory.fillStationModel();

			// DirectedWeightGraph graph = stationModelFactory.constructGraph();

			long time = 0;
			int maxProfit = 0;
			long startTime = System.nanoTime();
			for (int j = 1; j <= 10; j++) {
				DivideAndConquerStationProblem problem = new DivideAndConquerStationProblem(
						stationModelFactory.getMinimumDistance(), stationModelFactory.getStationModels());
				List<StationModel> result = problem.solveProblem(stationModelFactory.getStationModels(), 0,
						stationModelFactory.getStationSize() - 1);
				maxProfit = problem.getMaxProfit(result);
			}
			time = (System.nanoTime() - startTime) / 10;

			totalTime = totalTime + time;

			stationModelFactory.writeStatistics("divideandconquer", time, maxProfit);
			stationModelFactory.writeSumStatistics("divideandconquer", time);
		}

		StationModelFactory stationModelFactory = new StationModelFactory("");
		stationModelFactory.writeSumStatistics("divideandconquer", totalTime / 100);
	}

}
