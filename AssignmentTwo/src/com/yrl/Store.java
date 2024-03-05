package com.yrl;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 *         Date: 2024-02-22
 */

public class Store {
	private String storeCode;
	private Person manager;
	private Address address;

	public Store(String storeCode, Person manager, Address address) {
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
	}

	public Store(String storeCode, Address address) {
		this.storeCode = storeCode;
		this.address = address;
	}

	public String getCode() {
		return this.storeCode;
	}

	public String getManagerUUID() {
		return this.manager.toString();
	}

	public Address getAddress() {
		return this.address;
	}

	@Override
	public String toString() {
		if (this.manager != null) {
			return "Store storeCode=" + storeCode + ", manager=" + manager.toString() + ", address=" + address;
		}
		else {
			return "Store storeCode=" + storeCode + ", manager=null, address=" + address;
		}
	}
}
