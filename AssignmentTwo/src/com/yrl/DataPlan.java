package com.yrl;

public class DataPlan extends Item {
	private Double gbsPurchased;

	public DataPlan(String itemCode, String saleCode, String name, Double gbsPurchased, Double basePrice) {
		super(itemCode, name, basePrice, saleCode);
		this.gbsPurchased = gbsPurchased;
	}

	public Double getGbsPurchased() {
		return this.gbsPurchased;
	}

}
