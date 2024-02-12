package com.fxdestination.model;

public class FXCodeAndFXAmountModel {
	private String code;
	private double totalFX;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getTotalFX() {
		return totalFX;
	}

	public void setTotalFX(double totalFX) {
		this.totalFX = totalFX;
	}

	public FXCodeAndFXAmountModel(String code, double totalFX) {
		super();
		this.code = code;
		this.totalFX = totalFX;
	}

	public FXCodeAndFXAmountModel() {
		super();
	}

}
