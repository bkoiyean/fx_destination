package com.fxdestination.model;

public class RateHistoryModel {
	private long time;
	private double rate;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public RateHistoryModel(long time, double rate) {
		super();
		this.time = time;
		this.rate = rate;
	}

	public RateHistoryModel() {
		super();
	}

	@Override
	public String toString() {
		return "[" + time + "," + rate + "]";
	}

}
