package com.eren.projects.algortihm.benchmark;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.eren.projects.algortihm.algos.BruteForceStationProblem;
import com.eren.projects.algortihm.algos.DynamicStationAlgorithm;
import com.eren.projects.algortihm.algos.GreedyStationProblem;
import com.eren.projects.algortihm.main.StationModel;
import com.eren.projects.algortihm.main.StationModelFactory;
import com.eren.projects.algortihm.model.DirectedWeightGraph;
import com.eren.projects.algortihm.model.Vertex;

@State(Scope.Benchmark)
public class MaximumProfitStationProblemStats {
	// DirectedWeightGraph graph;
	// List<StationModel> stationModels;

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(MaximumProfitStationProblemStats.class.getSimpleName()).forks(1)
				.build();

		new Runner(opt).run();
	}

	@BenchmarkMode({/* Mode.Throughput, */Mode.AverageTime /* , Mode.SampleTime */})
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@Benchmark
	public void testGreedy() throws IOException {
		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
		StationModelFactory stationModelFactory = new StationModelFactory(fileName);
		stationModelFactory.fillStationModel();

		// DirectedWeightGraph graph = stationModelFactory.constructGraph();
		GreedyStationProblem problem = new GreedyStationProblem(stationModelFactory.getMinimumDistance(),
				stationModelFactory.getStationModels());
		List<StationModel> result = problem.solveProblem();
		int maxProfit = problem.getMaxProfit(result);
//		System.out.println("maxProfit for freedy is " + maxProfit);
	}

//	
//	@BenchmarkMode({/* Mode.Throughput, */Mode.AverageTime /* , Mode.SampleTime */})
//	@OutputTimeUnit(TimeUnit.NANOSECONDS)
//	@Benchmark
//	public void testBruteForce() throws IOException {
//		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
//		StationModelFactory stationModelFactory = new StationModelFactory();
//		stationModelFactory.fillStationModel(fileName);
//
//		// DirectedWeightGraph graph = stationModelFactory.constructGraph();
//		BruteForceStationProblem problem = new BruteForceStationProblem(stationModelFactory.getMinimumDistance(),
//				stationModelFactory.getStationModels());
//		List<StationModel> result = problem.solveProblem();
//		int maxProfit = problem.getMaxProfit(result);
//
//		assertNotNull(maxProfit);
//	}
//
//	@BenchmarkMode({/* Mode.Throughput, */Mode.AverageTime /* , Mode.SampleTime */})
//	@OutputTimeUnit(TimeUnit.NANOSECONDS)
//	@Benchmark
//	public void testDynamic() throws IOException {
//		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
//		StationModelFactory stationModelFactory = new StationModelFactory();
////		stationModelFactory.generateStationModel(fileName, 10);
//		stationModelFactory.fillStationModel(fileName);
//
//		DirectedWeightGraph graph = stationModelFactory.constructGraph();
//		DynamicStationAlgorithm problem = new DynamicStationAlgorithm(graph);
//		Map<Vertex, Double> result = problem.solveProblem(graph.getVertices().get(0));
//		Double maxProfit = -result.get(graph.getVertices().get(graph.getVertices().size() - 1));
//
//		assertNotNull(maxProfit);
//	}
}
