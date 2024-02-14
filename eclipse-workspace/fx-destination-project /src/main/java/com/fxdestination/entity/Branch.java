package com.fxdestination.entity;

public class Branch {
	private int id;
	private String name;
	private String address;
	private String state;
	private String city;
	private String tradingHour;
	private String phoneNumber;
	private String googleLat;
	private String googleLng;
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTradingHour() {
		return tradingHour;
	}

	public void setTradingHour(String tradingHour) {
		this.tradingHour = tradingHour;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGoogleLat() {
		return googleLat;
	}

	public void setGoogleLat(String googleLat) {
		this.googleLat = googleLat;
	}

	public String getGoogleLng() {
		return googleLng;
	}

	public void setGoogleLng(String googleLng) {
		this.googleLng = googleLng;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Branch(int id, String name, String address, String state, String city, String tradingHour,
			String phoneNumber, String googleLat, String googleLng, String note) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.state = state;
		this.city = city;
		this.tradingHour = tradingHour;
		this.phoneNumber = phoneNumber;
		this.googleLat = googleLat;
		this.googleLng = googleLng;
		this.note = note;
	}

	public Branch(String name, String address, String state, String city, String tradingHour, String phoneNumber,
			String googleLat, String googleLng, String note) {
		super();
		this.name = name;
		this.address = address;
		this.state = state;
		this.city = city;
		this.tradingHour = tradingHour;
		this.phoneNumber = phoneNumber;
		this.googleLat = googleLat;
		this.googleLng = googleLng;
		this.note = note;
	}

	public Branch() {
		super();
	}

}
