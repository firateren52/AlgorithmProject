package com.eren.projects.algortihm.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eren.projects.algortihm.main.StationModel;

public class GreedyStationProblem {
	private int minimumDistance;
	List<StationModel> stationModels;
	List<StationModel> matchedStations = new ArrayList<StationModel>();

	public GreedyStationProblem(int minimumDistance, List<StationModel> stationModels) {
		super();
		this.minimumDistance = minimumDistance;
		this.stationModels = stationModels;
	}

	public List<StationModel> solveProblem() {

		List<StationModel> orderedStations = new ArrayList<StationModel>(stationModels);
		Collections.sort(orderedStations);

		for (StationModel model : orderedStations) {
			int index = stationModels.indexOf(model);

			if (walkLeft(index) && walkRight(index)) {
				matchedStations.add(model);
			}
		}

		return matchedStations;
	}
	
	public int getMaxProfit(List<StationModel> stationModels) {
		int totalProfit = 0;
		for (StationModel model : stationModels) {
			totalProfit += model.getProfit();
		}
		return totalProfit;
	}


	public boolean walkLeft(int index) {
		int i = index - 1;
		while (i >= 0) {
			int matchedIndex = matchedStations.indexOf(stationModels.get(i));
			// is a matched station closer to this station less than
			// minimumDistance?
			if (matchedIndex >= 0
					&& stationModels.get(index).getDistance() - stationModels.get(i).getDistance() < minimumDistance) {
				return false;
			}
			i--;
		}

		return true;
	}

	public boolean walkRight(int index) {
		int i = index + 1;
		while (i < stationModels.size()) {
			int matchedIndex = matchedStations.indexOf(stationModels.get(i));
			// is a matched station closer to this station less than
			// minimumDistance?
			if (matchedIndex >= 0
					&& stationModels.get(i).getDistance() - stationModels.get(index).getDistance() < minimumDistance) {
				return false;
			}
			i++;
		}

		return true;
	}
}
