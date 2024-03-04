package com.yrl;

public class VoicePlan extends SaleItem {
	private String phoneNumber;
	private Integer daysPurchased;

	public VoicePlan(String saleCode, String itemCode, String phoneNumber, String daysPurchased) {
		super(saleCode, itemCode);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = Integer.parseInt(daysPurchased);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Integer getDaysPurchased() {
		return daysPurchased;
	}

}
