package com.yrl;

public abstract class Product extends Item {

	private Double basePrice;

	public Product(String itemCode, String name, Double basePrice) {
		super(itemCode, name);
		this.basePrice = basePrice;
	}

	public Double getBasePrice() {
		return this.basePrice;
	}

}
