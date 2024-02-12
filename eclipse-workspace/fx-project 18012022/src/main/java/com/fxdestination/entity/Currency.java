package com.fxdestination.entity;

public class Currency {
	private int id;
	private String code;
	private String name;
	private int[] notes;
	private double rate;

	public Currency() {
		super();

	}

	public Currency(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Currency(String code, int[] notes) {
		super();
		this.code = code;
		this.notes = notes;
	}

	public Currency(int id, String code, String name, int[] notes, double rate) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
	}

	public Currency(String code, String name, int[] notes, double rate) {
		super();
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getNotes() {
		return notes;
	}

	public void setNotes(int[] notes) {
		this.notes = notes;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
