package com.eren.projects.algortihm.main;

public class StationModel implements Comparable<StationModel> {

	private int distance;
	private int profit;

	public int getDistance() {
		return distance;
	}

	public int getProfit() {
		return profit;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public int compareTo(StationModel other) {
		return other.getProfit() - profit;
	}

}
