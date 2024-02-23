package com.yrl;
/**
 * @author Noah Bustard
 * @author Caden France
 * 
 * Date: 2024-02-22
 */

public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	@Override
	public String toString() {
		return street + ", " + city + ", " + state + ", " + zip;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}
}
