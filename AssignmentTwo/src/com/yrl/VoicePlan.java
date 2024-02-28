package com.yrl;

public class VoicePlan extends SaleItem {
	private String phoneNumber;
	private String daysPurchased;

	public VoicePlan(String saleCode, String itemCode, String phoneNumber, String daysPurchased) {
		super(saleCode, itemCode);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getDaysPurchased() {
		return daysPurchased;
	}

}
