package com.yrl;

public class Store {
	private String code;
	private String managerUUID;
	private Address address;
	
	public Store(String code, String managerUUID, Address address) {
		this.code = code;
		this.managerUUID = managerUUID;
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public String getManagerUUID() {
		return managerUUID;
	}

	public Address getAddress() {
		return address;
	}
	
	

}
