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

	public VoicePlan(String itemCode, String name, Double baseCost, String phoneNumber,
			Integer daysPurchased) {
		super(itemCode, name, baseCost);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
	}

	public VoicePlan(String itemCode, String name, Double baseCost) {
		super(itemCode, name, baseCost);
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public Integer getDaysPurchased() {
		return this.daysPurchased;
	}

	@Override
	public Double getCost() {
		return this.getBaseCost() * this.daysPurchased / 30;
	}
	
	@Override
	public Double getTax() {
		return this.getCost() * 0.065;
	}
	
	@Override
	public String getType() {
		return "Voice Plan";
	}

	@Override
	public String toString() {
		return "VoicePlan\n" + "ItemCode=" + super.getItemCode() + "PhoneNumber=" + this.phoneNumber + "\nDaysPurchased=" + this.daysPurchased
				+ "\nbaseCost=" + this.getBaseCost();
	}

}
