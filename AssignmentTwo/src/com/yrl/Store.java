package com.yrl;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 *         Date: 2024-02-22
 */

public class Store {
	private String code;
	private Person manager;
	private Address address;

	public Store(String code, String manager, Address address) {
		this.code = code;
		this.manager = new Person(manager);
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public String getManagerUUID() {
		return manager.toString();
	}

	public Address getAddress() {
		return address;
	}
}
