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

	public DataPlan(String itemCode, String name, Double baseCost, Double gbsPurchased) {
		super(itemCode, name, baseCost);
		this.gbsPurchased = gbsPurchased;
	}

	public DataPlan(String itemCode, String name, Double baseCost) {
		super(itemCode, name, baseCost);
	}

	public Double getGbsPurchased() {
		return this.gbsPurchased;
	}

	@Override
	public Double getCost() {
		return this.getBaseCost() * this.gbsPurchased;	}
	
	@Override
	public String getType() {
		return "Data Plan";
	}
	@Override
	public Double getTax() {
		return this.getCost() * 0.055;
	}

	@Override
	public String toString() {
		return this.getName() + " (" + this.getItemCode() + ") - Data\n"
				+ this.gbsPurchased + " GB @ $" + this.getBaseCost() + "/GB";
	}

}
