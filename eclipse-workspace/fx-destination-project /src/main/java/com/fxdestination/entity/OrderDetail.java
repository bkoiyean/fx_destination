package com.fxdestination.entity;

public class OrderDetail {
	private int id;
	private int orderId;
	private String currencyCode;
	private String quantity;
	private double rate;
	private int fxAmount;

	public OrderDetail(int id, int orderId, String currencyCode, String quantity, double rate, int fxAmount) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.currencyCode = currencyCode;
		this.quantity = quantity;
		this.rate = rate;
		this.fxAmount = fxAmount;
	}

	public OrderDetail(int orderId, String currencyCode, String quantity, double rate, int fxAmount) {
		super();
		this.orderId = orderId;
		this.currencyCode = currencyCode;
		this.quantity = quantity;
		this.rate = rate;
		this.fxAmount = fxAmount;
	}

	public int getFxAmount() {
		return fxAmount;
	}

	public void setFxAmount(int fxAmount) {
		this.fxAmount = fxAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public OrderDetail(int id, int orderId, String currencyCode, String quantity, double rate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.currencyCode = currencyCode;
		this.quantity = quantity;
		this.rate = rate;
	}

	public OrderDetail(int orderId, String currencyCode, String quantity, double rate) {
		super();
		this.orderId = orderId;
		this.currencyCode = currencyCode;
		this.quantity = quantity;
		this.rate = rate;
	}

	public OrderDetail() {
		super();
	}

}
