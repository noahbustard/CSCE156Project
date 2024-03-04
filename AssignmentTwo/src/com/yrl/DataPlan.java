package com.yrl;

public class DataPlan extends SaleItem {
	private Double gbsPurchased;

	public DataPlan(String saleCode, String itemCode, String gbsPurchased) {
		super(saleCode, itemCode);
		this.gbsPurchased = Double.parseDouble(gbsPurchased);
	}

	public Double getGbsPurchased() {
		return gbsPurchased;
	}

}
