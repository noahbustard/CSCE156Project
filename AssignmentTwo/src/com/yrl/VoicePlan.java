package com.yrl;
/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * VoicePlan is an extension of the item class and 1/2 of the Plan objects.
 * No need to calculate cost because there is a flat price,
 * rather than charging per day.
 */
public class VoicePlan extends Item {
	private String phoneNumber;
	private Integer daysPurchased;
	private Double cost;
	private String saleCode;

	public VoicePlan(String saleCode, String itemCode, String name, Double baseCost, String phoneNumber,
			Integer daysPurchased) {
		super(itemCode, name, baseCost);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
		this.cost = baseCost;
		this.saleCode = saleCode;
	}

	public VoicePlan(String itemCode, String name, Double baseCost) {
		super(name, itemCode, baseCost);
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public Integer getDaysPurchased() {
		return this.daysPurchased;
	}

	@Override
	public Double getCost() {
		return cost;
	}

	@Override
	public String getType() {
		return "VoicePlan";
	}

	@Override
	public String toString() {
		return "VoicePlan\n" + this.getItemCode() + "PhoneNumber=" + phoneNumber + "\nDaysPurchased=" + daysPurchased
				+ "\nCost=" + cost;
	}

}
