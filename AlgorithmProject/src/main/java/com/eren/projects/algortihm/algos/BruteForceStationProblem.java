package com.eren.projects.algortihm.algos;

import java.util.ArrayList;
import java.util.List;

import com.eren.projects.algortihm.main.StationModel;

public class BruteForceStationProblem {
	private int minimumDistance;
	List<StationModel> stationModels;

	public BruteForceStationProblem(int minimumDistance, List<StationModel> stationModels) {
		this.minimumDistance = minimumDistance;
		this.stationModels = stationModels;
	}

	public List<StationModel> solveProblem() {
		int n = stationModels.size();
		List<Integer> values = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			values.add(i);
		}
		// find all possible subsets for station array
		List<ArrayList<Integer>> subsets = findSubsets(values);
		// List<Integer> weights = new ArrayList<Integer>();
		int i = 0;
		int maxProfit = 0;
		int j = 0;
		for (List<Integer> subset : subsets) {
			if (isSubsetValid(subset)) {
				int totalProfit = calculateTotalprofit(subset);
				if (maxProfit < totalProfit) {
					maxProfit = totalProfit;
					j = i;
				}
			}
			i++;
		}

		List<StationModel> matchedStationModels = new ArrayList<StationModel>();
		for (Integer e : subsets.get(j)) {
			matchedStationModels.add(stationModels.get(e));
		}

		return matchedStationModels;
	}

	public int getMaxProfit(List<StationModel> stationModels) {
		int totalProfit = 0;
		for (StationModel model : stationModels) {
			totalProfit += model.getProfit();
		}
		return totalProfit;
	}

	private int calculateTotalprofit(List<Integer> subset) {
		int totalProfit = 0;
		for (Integer e : subset) {
			totalProfit += stationModels.get(e).getProfit();
		}
		return totalProfit;
	}

	private boolean isSubsetValid(List<Integer> subset) {
		for (int i = 1; i < subset.size(); i++) {
			for (int j = i + 1; j < subset.size(); j++) {
				int sourceDistance = stationModels.get(subset.get(i).intValue()).getDistance();
				int destinationDistance = stationModels.get(subset.get(j).intValue()).getDistance();
				if (destinationDistance - sourceDistance < minimumDistance) {
					return false;
				}
			}
		}
		return true;
	}

	public List<ArrayList<Integer>> findSubsets(List<Integer> values) {
		if (values == null)
			return null;
		// Arrays.sort(values);
		List<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < values.size(); i++) {
			List<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
			// get sets that are already in result
			for (List<Integer> a : result) {
				temp.add(new ArrayList<Integer>(a));
			}
			// add S[i] to existing sets
			for (ArrayList<Integer> a : temp) {
				a.add(values.get(i));
			}
			// add S[i] only as a set
			ArrayList<Integer> single = new ArrayList<Integer>();
			single.add(values.get(i));
			temp.add(single);

			result.addAll(temp);
		}
		// add empty set
		result.add(new ArrayList<Integer>());
		return result;
	}
}
