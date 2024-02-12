package com.fxdestination.entity;

import java.util.Date;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String mobilePhone;
	private String address;
	private String state;
	private String city;
	private String password;
	private String token;
	private Date tokenValidDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenValidDate() {
		return tokenValidDate;
	}

	public void setTokenValidDate(Date tokenValidDate) {
		this.tokenValidDate = tokenValidDate;
	}

	public User(int id, String firstName, String lastName, String gender, String email, String mobilePhone,
			String address, String state, String city, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.state = state;
		this.city = city;
		this.password = password;
	}

	public User() {
		super();
	}

	public User(String firstName, String lastName, String gender, String email, String mobilePhone, String address,
			String state, String city, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.state = state;
		this.city = city;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String gender, String email, String mobilePhone, String address,
			String state, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.state = state;
		this.city = city;
	}
	
	public User(int id, String firstName, String lastName, String gender, String email, String mobilePhone,
			String address, String state, String city) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.address = address;
		this.state = state;
		this.city = city;
	}

}
