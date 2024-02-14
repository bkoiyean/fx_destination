package com.fxdestination.entity;

public class Tracker {
	private int id;
	private int userId;
	private String trackCode;
	private double targetRate;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTrackCode() {
		return trackCode;
	}

	public void setTrackCode(String trackCode) {
		this.trackCode = trackCode;
	}

	public double getTargetRate() {
		return targetRate;
	}

	public void setTargetRate(double targetRate) {
		this.targetRate = targetRate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Tracker(int id, int userId, String trackCode, double targetRate, String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.trackCode = trackCode;
		this.targetRate = targetRate;
		this.status = status;
	}

	public Tracker() {
		super();
	}

	public Tracker(int userId, String trackCode, double targetRate, String status) {
		super();
		this.userId = userId;
		this.trackCode = trackCode;
		this.targetRate = targetRate;
		this.status = status;
	}

	public Tracker(int userId, String trackCode, double targetRate) {
		super();
		this.userId = userId;
		this.trackCode = trackCode;
		this.targetRate = targetRate;
	}

}
