//package com.eren.projects.algortihm.algos;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.javailp.Linear;
//import net.sf.javailp.OptType;
//import net.sf.javailp.Problem;
//import net.sf.javailp.Result;
//import net.sf.javailp.Solver;
//import net.sf.javailp.SolverFactory;
//import net.sf.javailp.SolverFactoryLpSolve;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.openjdk.jmh.annotations.Setup;
//import org.openjdk.jmh.util.FileUtils;
//
//import com.eren.projects.algortihm.algos.DijkstrasAlgorithm;
//import com.eren.projects.algortihm.main.MaximumProfitStationProblem;
//import com.eren.projects.algortihm.main.StationModel;
//import com.eren.projects.algortihm.main.StationModelFactory;
//import com.eren.projects.algortihm.model.DirectedWeightGraph;
//import com.eren.projects.algortihm.model.DirectedWeightGraphImpl;
//import com.eren.projects.algortihm.model.Vertex;
//
//public class TestMaximumProfitStationProblem {
//	DirectedWeightGraph graph;
//	List<StationModel> stationModels;
//
//	// @Ignore
//	// @Before
//	// public void testFillStationModel() throws IOException {
//	// StationModelFactory stationModelFactory = new StationModelFactory();
//	// stationModelFactory.fillStationModel("src\\main\\java\\com\\eren\\projects\\algortihm\\data\\greedy_1.txt");
//	//
//	// graph = stationModelFactory.constructGraph();
//	//
//	// assertNotNull(graph);
//	// assertTrue(graph.getVertices().size() > 0);
//	// }
//	//
//	// @Ignore
//	// @Test
//	// public void greedyAlgorithm() {
//	// MaximumProfitStationProblem problem = new
//	// MaximumProfitStationProblem(graph);
//	// LinkedList<Vertex> path = problem.greedyAlgorithm();
//	//
//	// for (Vertex vertex : path) {
//	// System.out.println(vertex);
//	// }
//	// }
//	
//	@BeforeClass
//	public static void generateStationModel() {
//		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
//		StationModelFactory stationModelFactory = new StationModelFactory();
//		stationModelFactory.generateStationModel(fileName, 10);
//	}
//
//	@Test @Ignore
//	public void testGreedy() throws IOException {
//		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
//		StationModelFactory stationModelFactory = new StationModelFactory();
//		stationModelFactory.fillStationModel(fileName);
//
//		// DirectedWeightGraph graph = stationModelFactory.constructGraph();
//		GreedyStationProblem problem = new GreedyStationProblem(stationModelFactory.getMinimumDistance(),
//				stationModelFactory.getStationModels());
//		List<StationModel> result = problem.solveProblem();
//		int maxProfit = problem.getMaxProfit(result);
//
//		assertNotNull(maxProfit);
//	}
//
//	
//	@Ignore
//	@Test
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
//	@Test @Ignore
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
//	
//	@Test
//	public void testLineer() throws IOException {
//		String fileName = "src\\main\\java\\com\\eren\\projects\\algortihm\\data\\stationdata_1.txt";
//		StationModelFactory stationModelFactory = new StationModelFactory();
////		stationModelFactory.generateStationModel(fileName, 10);
//		stationModelFactory.fillStationModel(fileName);
//
//		SolverFactory factory = new SolverFactoryLpSolve(); // use lp_solve
//		factory.setParameter(Solver.VERBOSE, 0); 
//		factory.setParameter(Solver.TIMEOUT, 100); // set timeout to 100 seconds
//
//		/**
//		* Constructing a Problem: 
//		* Maximize: 143x+60y 
//		* Subject to: 
//		* 120x+210y <= 15000 
//		* 110x+30y <= 4000 
//		* x+y <= 75
//		* 
//		* With x,y being integers
//		* 
//		*/
//		Problem problem = new Problem();
//
//		Linear linear = new Linear();
//		linear.add(143, "x");
//		linear.add(60, "y");
//
//		problem.setObjective(linear, OptType.MAX);
//
//		linear = new Linear();
//		linear.add(120, "x");
//		linear.add(210, "y");
//
//		problem.add(linear, "<=", 15000);
//
//		linear = new Linear();
//		linear.add(110, "x");
//		linear.add(30, "y");
//
//		problem.add(linear, "<=", 4000);
//
//		linear = new Linear();
//		linear.add(1, "x");
//		linear.add(1, "y");
//
//		problem.add(linear, "<=", 75);
//
//		problem.setVarType("x", Integer.class);
//		problem.setVarType("y", Integer.class);
//
//		Solver solver = factory.get(); // you should use this solver only once for one problem
//		Result result = solver.solve(problem);
//
//		System.out.println(result);
//
//		/**
//		* Extend the problem with x <= 16 and solve it again
//		*/
//		problem.setVarUpperBound("x", 16);
//
//		solver = factory.get();
//		result = solver.solve(problem);
//
//		System.out.println(result);
////		assertNotNull(maxProfit);
//	}
//	
//	
//	
//	
//
//}
