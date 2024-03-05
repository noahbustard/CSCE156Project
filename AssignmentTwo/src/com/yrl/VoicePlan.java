package com.yrl;

public class VoicePlan extends Item {
	private String phoneNumber;
	private Integer daysPurchased;
	private Double basePrice;
	private String saleCode;

	public VoicePlan(String itemCode, String saleCode, String name, String phoneNumber, Integer daysPurchased,
			Double basePrice) {
		super(itemCode, name);
		this.phoneNumber = phoneNumber;
		this.daysPurchased = daysPurchased;
		this.basePrice = basePrice;
		this.saleCode = saleCode;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public Integer getDaysPurchased() {
		return this.daysPurchased;
	}

	public Double getBasePrice() {
		return this.basePrice;
	}

	public String getSaleCode() {
		return saleCode;
	}

}
