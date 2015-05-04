package com.eren.projects.algortihm.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.eren.projects.algortihm.model.DirectedWeightEdge;
import com.eren.projects.algortihm.model.DirectedWeightEdgeImpl;
import com.eren.projects.algortihm.model.DirectedWeightGraph;
import com.eren.projects.algortihm.model.DirectedWeightGraphImpl;
import com.eren.projects.algortihm.model.Vertex;
import com.eren.projects.algortihm.model.VertexImpl;

/**
 * @author firat.eren Read and Convert station input to graph
 */
public class StationModelFactory {
	private String fileName = "";
	private int minimumDistance; // length between adjacent settled stations
	private int count;
	// must be
	// greater or equeal to capacity
	private int totalProfit = 0; // sum of all weights

	List<StationModel> stationModels;

	public StationModelFactory(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Read filename and save in StationModel list
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void fillStationModel() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String read = "";
			String line[];
			minimumDistance = Integer.parseInt(br.readLine().trim());
			stationModels = new ArrayList<StationModel>();
			count = 0;
			while ((read = br.readLine()) != null) {
				StationModel stationModel = new StationModel();
				line = read.split(" ");
				stationModel.setDistance(Integer.parseInt(line[0]));
				stationModel.setProfit(Integer.parseInt(line[1]));
				stationModels.add(stationModel);

				if (count > 0 && stationModel.getDistance() <= stationModels.get(count - 1).getDistance()) {
					throw new IllegalArgumentException("Distance array must be in ascending order!");
				}

				count++;
				totalProfit += stationModel.getProfit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void generateStationModel(int minimumDistance, int count) {

		BufferedWriter bw = null;
		int maxProfit = 10;
		try {
			FileOutputStream erasor = new FileOutputStream(fileName);
			erasor.write(new String().getBytes());
			erasor.close();

			bw = new BufferedWriter(new FileWriter(new File(fileName), true));

			bw.write(minimumDistance + "");
			bw.newLine();

			Random random = new Random();
			TreeMap<Integer, Integer> inputMap = new TreeMap<Integer, Integer>();
			for (int i = 0; i < count; i++) {
				int length = random.nextInt(count * maxProfit);
				int profit = random.nextInt(maxProfit);
				inputMap.put(length, profit);
			}

			Set set = inputMap.entrySet();
			// Get an iterator
			Iterator i = set.iterator();
			// Display elements
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				bw.write(me.getKey() + " " + me.getValue());
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * construct the weighted directed graph
	 * 
	 * @return
	 */
	public DirectedWeightGraph constructGraph() {
		List<Vertex> vertices = constructVertices();
		List<DirectedWeightEdge> edges = constructEdges(vertices);

		DirectedWeightGraph graph = new DirectedWeightGraphImpl(vertices, edges);
		return graph;
	}

	/**
	 * 
	 * construct weighted directed edges we add extra edges from start vertex to
	 * other vertices(except end vertex) and from other vertices(except start
	 * vertex) to end vertex. Also convert weights to negative
	 *
	 * 
	 * @param vertices
	 * @return
	 */
	private List<DirectedWeightEdge> constructEdges(List<Vertex> vertices) {
		int index = 0;
		List<DirectedWeightEdge> edges = new ArrayList<DirectedWeightEdge>();

		int sourceIndex = 0;
		for (Vertex source : vertices) {
			if (sourceIndex == vertices.size() - 1) {
				continue;
			}

			int destinationIndex = 0;
			for (StationModel stationModel : stationModels) {

				if (sourceIndex == 0) {
					// is first vertex?
					String edgeId = "edge_" + index;
					DirectedWeightEdge edge = new DirectedWeightEdgeImpl(edgeId, source,
							vertices.get(destinationIndex + 1), -stationModel.getProfit());
					edges.add(edge);
					index++;
				} else if (sourceIndex < vertices.size() - 1) {
					// is source vertex neither first nor last
					int distance = stationModels.get(destinationIndex).getDistance()
							- stationModels.get(sourceIndex - 1).getDistance();
					if (distance >= minimumDistance) {
						// is source and destination vertices satisfy minimum
						// distance value?
						String edgeId = "edge_" + index;
						DirectedWeightEdge edge = new DirectedWeightEdgeImpl(edgeId, source,
								vertices.get(destinationIndex + 1), -stationModel.getProfit());
						edges.add(edge);
						index++;
					}
				}

				destinationIndex++;
			}

			if (sourceIndex != 0) {
				String edgeId = "edge_" + index;
				DirectedWeightEdge edge = new DirectedWeightEdgeImpl(edgeId, source, vertices.get(vertices.size() - 1),
						0);
				edges.add(edge);
				index++;
			}

			sourceIndex++;
		}

		return edges;
	}

	/**
	 * construct vertices we add a start and finish vertex to convert the
	 * problem into shortest path problem
	 * 
	 * @return
	 */
	private List<Vertex> constructVertices() {
		List<Vertex> vertices = new ArrayList<Vertex>();
		Vertex first = new VertexImpl("vertex_0", "vertex_0");
		vertices.add(first);

		int index = 1;
		for (StationModel stationModel : stationModels) {
			Vertex vertex = new VertexImpl("vertex_" + index, "vertex_" + index);
			vertices.add(vertex);
			index++;
		}

		Vertex last = new VertexImpl("vertex_" + index, "vertex_" + index);
		vertices.add(last);
		return vertices;
	}

	public int getMinimumDistance() {
		return minimumDistance;
	}

	public List<StationModel> getStationModels() {
		return stationModels;
	}

	public void writeStatistics(String algorithm, long time, int maxProfit) throws IOException {
		BufferedWriter bw = null;
		String fileNameStats = fileName.replace("input\\input_", "output\\"+algorithm + "_");
		try {
			FileOutputStream erasor = new FileOutputStream(fileNameStats);
			erasor.write(new String().getBytes());
			erasor.close();

			bw = new BufferedWriter(new FileWriter(new File(fileNameStats), true));

			bw.write("minimumDistance:" + minimumDistance + "");
			bw.newLine();
			bw.newLine();
			bw.write("count: " + count);
			bw.newLine();
			bw.newLine();
			bw.write("maxProfit: " + maxProfit);
			bw.newLine();
			bw.newLine();
			bw.write("time: " + time);
			bw.newLine();

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeSumStatistics(String algorithm, long time) throws IOException {
		BufferedWriter bw = null;
		String fileNameStats = "src\\main\\resources\\data\\stats\\" + algorithm;
		try {
//			FileOutputStream erasor = new FileOutputStream(fileNameStats);
//			erasor.write(new String().getBytes());
//			erasor.close();

			bw = new BufferedWriter(new FileWriter(new File(fileNameStats), true));


			bw.write("time: " + time);
			bw.newLine();

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getStationSize() {
		return stationModels.size();
	}

}
