package com.yrl;

public class DataPlan extends Item {
	private Double gbsPurchased;
	private Double basePrice;
	private String saleCode;

	public DataPlan(String itemCode, String saleCode, String name, Double gbsPurchased, Double basePrice) {
		super(itemCode, name);
		this.gbsPurchased = gbsPurchased;
		this.basePrice = basePrice;
		this.saleCode = saleCode;
	}

	public Double getGbsPurchased() {
		return this.gbsPurchased;
	}

	public Double getBasePrice() {
		return this.basePrice;
	}

	public String getSaleCode() {
		return this.saleCode;
	}
}
