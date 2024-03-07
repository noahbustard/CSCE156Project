package com.yrl;

public class VoicePlan extends Item {
	private String phoneNumber;
	private Integer daysPurchased;

	public VoicePlan(String itemCode, String saleCode, String name, String phoneNumber, Integer daysPurchased,
			Double basePrice) {
		super(itemCode, name, basePrice, saleCode);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public Integer getDaysPurchased() {
		return this.daysPurchased;
	}

}
