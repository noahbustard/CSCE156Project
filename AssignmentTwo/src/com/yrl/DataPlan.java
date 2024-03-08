package com.yrl;
/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * Is an extension of the item class, and 1/2 of the Plan objects.
 * This plan's cost must be calculated bu costPerGB * gbsPurchased.
 * 
 */
public class DataPlan extends Item {
	private Double gbsPurchased;
	private Double costPerGb;
	private String saleCode;

	public DataPlan(String saleCode, String itemCode, String name, Double baseCost, Double gbsPurchased) {
		super(itemCode, name, baseCost);
		this.gbsPurchased = gbsPurchased;
		this.costPerGb = baseCost;
		this.saleCode = saleCode;
	}

	public DataPlan(String itemCode, String name, Double baseCost) {
		super(name, itemCode, baseCost);
	}

	public Double getGbsPurchased() {
		return this.gbsPurchased;
	}

	public Double getCostPerGb() {
		return this.costPerGb;
	}

	public String getSaleCode() {
		return saleCode;
	}

	@Override
	public Double getCost() {
		return this.costPerGb * this.gbsPurchased;
	}

	@Override
	public String getType() {
		return "Data Plan";
	}

	@Override
	public String toString() {
		return "DataPlan\nItemCode= " + this.getItemCode() + "\ngbsPurchased=" + gbsPurchased + "\ncostPerGb="
				+ costPerGb;
	}

}
