package com.yrl;

public class Service extends SaleItem {
	private String hoursBilled;
	private String servicemanCode;

	public Service(String saleCode, String itemCode, String hoursBilled, String servicemanCode) {
		super(saleCode, itemCode);
		this.hoursBilled = hoursBilled;
		this.servicemanCode = servicemanCode;
	}

	public String getHoursBilled() {
		return hoursBilled;
	}

	public String getServicemanCode() {
		return servicemanCode;
	}

}