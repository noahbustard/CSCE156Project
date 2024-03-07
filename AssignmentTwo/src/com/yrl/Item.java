package com.yrl;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 *         Date: 2024-02-22
 */
public abstract class Item {
	private String itemCode;
	private String name;
	private Double basePrice;
	private String saleCode;

	public Item(String itemCode, String name, Double basePrice, String saleCode) {
		this.itemCode = itemCode;
		this.name = name;
		this.basePrice = basePrice;
		this.saleCode = saleCode;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public String getName() {
		return this.name;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public String getSaleCode() {
		return saleCode;
	}
}
