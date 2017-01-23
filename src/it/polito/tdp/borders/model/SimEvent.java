package it.polito.tdp.borders.model;

public class SimEvent implements Comparable<SimEvent> {

	private int time;
	private int numPeople;
	private Country destination;

	public SimEvent(int time, int numPeople, Country destination) {
		super();
		this.time = time;
		this.numPeople = numPeople;
		this.destination = destination;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	public Country getDestination() {
		return destination;
	}

	public void setDestination(Country destination) {
		this.destination = destination;
	}

	@Override
	public int compareTo(SimEvent other) {
		// TODO Auto-generated method stub
		return this.time - other.time;
	}

}
