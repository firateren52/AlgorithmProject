package com.eren.projects.algortihm.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eren.projects.algortihm.main.StationModel;

/**
 * @author firat.eren
 *
 */
public class DivideAndConquerStationProblem {
	private int minimumDistance;

	// List<StationModel> stationModels;

	public DivideAndConquerStationProblem(int minimumDistance, List<StationModel> stationModels) {
		super();
		this.minimumDistance = minimumDistance;
		// this.stationModels = stationModels;
	}

	public List<StationModel> solveProblem(List<StationModel> stationModels, int leftIndex, int rightIndex) {
		List<StationModel> mergedStations = new ArrayList<StationModel>();

		if (rightIndex < 0 || leftIndex < 0) {
			return mergedStations;
		}

		if (leftIndex == rightIndex) {
			if (leftIndex > 60) {
				@SuppressWarnings("unused")
				int x = 1;
			}
			mergedStations.add(stationModels.get(leftIndex));
			return mergedStations;
		}

		int midIndex = (leftIndex + rightIndex) / 2;

		List<StationModel> leftStations = solveProblem(stationModels, leftIndex, midIndex);
		List<StationModel> rightStations = solveProblem(stationModels, midIndex + 1, rightIndex);

		StationModel leftStation = null;
		StationModel rightStation = null;
		if (leftStations.isEmpty() == false) {
			leftStation = leftStations.get(leftStations.size() - 1);
		}
		if (rightStations.isEmpty() == false) {
			rightStation = rightStations.get(0);
		}

		if (leftStation != null && rightStation != null && rightStation.getDistance() - leftStation.getDistance() < minimumDistance) {
			List<StationModel> leftStationsTemp = new ArrayList<StationModel>(stationModels.subList(leftIndex,
					stationModels.indexOf(leftStation)));
			List<StationModel> rightStationsTemp = new ArrayList<StationModel>(stationModels.subList(
					stationModels.indexOf(rightStation) + 1, rightIndex + 1));
			List<StationModel> allStationsTemp = new ArrayList<StationModel>(stationModels.subList(leftIndex, rightIndex + 1));

			leftStationsTemp = getStationModelsBelowLimit(leftStationsTemp, rightStation.getDistance() - minimumDistance);
			rightStationsTemp = getStationModelsAboveLimit(rightStationsTemp, leftStation.getDistance() + minimumDistance);
			allStationsTemp.remove(leftStation);
			allStationsTemp.remove(rightStation);

			List<StationModel> leftStations2 = solveProblem(leftStationsTemp, 0, leftStationsTemp.size() - 1);
			List<StationModel> rightStations2 = solveProblem(rightStationsTemp, 0, rightStationsTemp.size() - 1);
			List<StationModel> allStations2 = solveProblem(allStationsTemp, 0, allStationsTemp.size() - 1);

			int maxProfitLeft = getMaxProfit(leftStations);
			int maxProfitRight = getMaxProfit(rightStations);
			int maxProfitLeft2 = getMaxProfit(leftStations2);
			int maxProfitRight2 = getMaxProfit(rightStations2);
			int maxProfitAll2 = getMaxProfit(allStations2);

			int maxProfit = Math.max(maxProfitAll2, Math.max(maxProfitLeft + maxProfitRight2, maxProfitLeft2 + maxProfitRight));

			if (maxProfit == maxProfitAll2) {
				mergedStations.addAll(allStations2);
			} else if (maxProfit == (maxProfitLeft + maxProfitRight2)) {
				mergedStations.addAll(leftStations);
				mergedStations.addAll(rightStations2);
			} else {
				mergedStations.addAll(leftStations2);
				mergedStations.addAll(rightStations);
			}
			return mergedStations;
		}
		mergedStations.addAll(leftStations);
		mergedStations.addAll(rightStations);
		return mergedStations;

	}

	public int getMaxProfit(List<StationModel> stationModels) {
		int totalProfit = 0;
		for (StationModel model : stationModels) {
			totalProfit += model.getProfit();
		}
		return totalProfit;
	}

	private List<StationModel> getStationModelsBelowLimit(List<StationModel> stationModels, int limit) {
		for (int i = 0; i < stationModels.size(); i++) {
			if (stationModels.get(i).getDistance() > limit) {
				stationModels.remove(i);
				i--;
			}
		}
		return stationModels;
	}

	private List<StationModel> getStationModelsAboveLimit(List<StationModel> stationModels, int limit) {
		for (int i = 0; i < stationModels.size(); i++) {
			if (stationModels.get(i).getDistance() < limit) {
				stationModels.remove(i);
				i--;
			}
		}
		return stationModels;
	}

}
