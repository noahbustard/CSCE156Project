package com.yrl;

public class Lease extends SaleItem {
	private String startDate;
	private String endDate;

	public Lease(String saleCode, String itemCode, String startDate, String endDate) {
		super(saleCode, itemCode);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	

}
