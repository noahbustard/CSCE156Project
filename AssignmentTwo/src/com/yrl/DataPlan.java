package com.yrl;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 *         Date: 2024-03-07
 * 
 *         Is an extension of the item class, and 1/2 of the Plan objects. This
 *         plan's cost must be calculated by costPerGB * gbsPurchased.
 * 
 */
public class DataPlan extends Item {
	private double gbsPurchased;
	private double pricePerGb;
	private int saleItemId;

	public DataPlan(String itemCode, String name, Double pricePerGb, Double gbsPurchased) {
		super(itemCode, name, 0);
		this.gbsPurchased = gbsPurchased;
		this.pricePerGb = pricePerGb;
	}

	public DataPlan(String itemCode, String name, Double pricePerGb) {
		super(itemCode, name, 0);
		this.pricePerGb = pricePerGb;
	}

	public DataPlan(String itemCode, String name, Double pricePerGb, int itemId) {
		super(itemCode, name, itemId);
		this.pricePerGb = pricePerGb;
	}

	public DataPlan(String itemCode, String name, int itemId, double gbsPurchased,
			double pricePerGb, int saleItemId) {
		super(itemCode, name, itemId);
		this.gbsPurchased = gbsPurchased;
		this.pricePerGb = pricePerGb;
		this.saleItemId = saleItemId;
	}

	public Double getGbsPurchased() {
		return this.gbsPurchased;
	}

	public double getPricePerGb() {
		return this.pricePerGb;
	}

	@Override
	public Double getCost() {
		return this.getPricePerGb() * this.gbsPurchased;
	}

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
		return this.getName() + " (" + this.getItemCode() + ") - Data\n" + this.gbsPurchased + " GB @ $"
				+ this.getPricePerGb() + "/GB";
	}

	@Override
	public Double getBaseCost() {
		return this.getPricePerGb();
	}

	public int getSaleItemId() {
		return saleItemId;
	}

}
