package com.yrl;

public class Sale {
	private String saleCode;
	private String storeCode;
	private String customerCode;
	private String salespersonCode;
	private String date;

	public Sale(String storeCode, String saleCode, String customerCode, String salespersonCode, String date) {
		this.storeCode = storeCode;
		this.saleCode = saleCode;
		this.customerCode = customerCode;
		this.salespersonCode = salespersonCode;
		this.date = date;
	}

	public String getSaleCode() {
		return this.saleCode;
	}

	public String getStoreCode() {
		return this.storeCode;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public String getSalespersonCode() {
		return this.salespersonCode;
	}

	public String getDate() {
		return this.date;
	}

}
