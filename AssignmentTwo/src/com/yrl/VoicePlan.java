package com.yrl;

public class VoicePlan extends Item {
	private String phoneNumber;
	private Integer daysPurchased;
	private Double cost;

	public VoicePlan(String saleCode, String itemCode, String name, Double cost,
			String phoneNumber, Integer daysPurchased) {
		super(itemCode, name, saleCode);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
		this.cost = cost;
	}

	public VoicePlan(String itemCode, String name, Double cost) {
		super(itemCode, name, null);
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
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

}
