package com.yrl;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 *Date: 2024-03-08
 *
 *Address class represents address to people's homes
 *or stores.
 * 
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
		return street + "\n        "
				+ this.city + " " + this.state + " " + this.zip;
	}

	public String getStreet() {
		return this.street;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getZip() {
		return this.zip;
	}
}
