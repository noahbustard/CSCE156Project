package com.yrl;

public class DataPlan extends Item {
	private Double gbsPurchased;
	private Double costPerGb;
	

	public DataPlan(String saleCode, String itemCode, String name, Double costPerGb, Double gbsPurchased) {
		super(itemCode, name, saleCode);
		this.gbsPurchased = gbsPurchased;
		this.costPerGb = costPerGb;
	}

	public DataPlan(String itemCode, String name, Double costPerGb) {
		super(itemCode, name, null);
	}

	public Double getGbsPurchased() {
		return this.gbsPurchased;
	}

	public Double getCostPerGb() {
		return this.costPerGb;
	}

	@Override
	public Double getCost() {
		return this.costPerGb;
	}

	@Override
	public String getType() {
		return "Data Plan";
	}

}
