package com.fxdestination.model;

import java.util.Arrays;
import java.util.Objects;

public class CurrencyModel {

	private int id;
	private String code;
	private String name;
	private int[] notes;
	private double rate;
	private int[] qty;
	private int fxAmount;
	private int orderId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public CurrencyModel() {
		super();
	}

	public CurrencyModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public CurrencyModel(String code, int[] notes, int[] qty) {
		super();
		this.code = code;
		this.notes = notes;
		this.qty = qty;
	}

	public int[] getQty() {
		return qty;
	}

	public void setQty(int[] qty) {
		this.qty = qty;
	}

	public CurrencyModel(String code, int[] qty) {
		super();
		this.code = code;
		this.notes = qty;
	}

	public CurrencyModel(int id, String code, String name, int[] notes, double rate) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
	}

	public CurrencyModel(String code, String name, int[] notes, double rate) {
		super();
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
	}

	public CurrencyModel(String code, String name, int[] notes, double rate, int[] qty) {
		super();
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
		this.qty = qty;
	}

	public CurrencyModel(String code, String name, int[] notes, int[] qty) {
		super();
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.qty = qty;
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

	public int getFxAmount() {
		return fxAmount;
	}

	public void setFxAmount(int fxAmount) {
		this.fxAmount = fxAmount;
	}

	public CurrencyModel(int id, String code, String name, int[] notes, double rate, int[] qty, int fxAmount) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
		this.qty = qty;
		this.fxAmount = fxAmount;
	}

	public CurrencyModel(String code, String name, int[] notes, double rate, int[] qty, int fxAmount) {
		super();
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
		this.qty = qty;
		this.fxAmount = fxAmount;
	}

	public CurrencyModel(String code, String name, int[] notes, double rate, int[] qty, int fxAmount, int orderId) {
		super();
		this.code = code;
		this.name = name;
		this.notes = notes;
		this.rate = rate;
		this.qty = qty;
		this.fxAmount = fxAmount;
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		CurrencyModel other = (CurrencyModel) obj;
		if (this.code.equalsIgnoreCase(other.code)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "CurrencyModel [id=" + id + ", code=" + code + ", name=" + name + ", notes=" + Arrays.toString(notes)
				+ ", rate=" + rate + ", qty=" + Arrays.toString(qty) + ", fxAmount=" + fxAmount + ", orderId=" + orderId
				+ "]";
	}

}
