package com.yrl;

public class Item {
	private String code;
	private String type;
	private String name;
	private String basePrice;
	
	public Item(String code, String type, String name, String basePrice) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.basePrice = basePrice;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getBasePrice() {
		return basePrice;
	}
}
