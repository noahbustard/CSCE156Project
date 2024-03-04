package com.yrl;

public class Service extends SaleItem {
	private Double hoursBilled;
	private String servicemanCode;

	public Service(String saleCode, String itemCode, String hoursBilled, String servicemanCode) {
		super(saleCode, itemCode);
		this.hoursBilled = Double.parseDouble(hoursBilled);
		this.servicemanCode = servicemanCode;
	}

	public Double getHoursBilled() {
		return hoursBilled;
	}

	public String getServicemanCode() {
		return servicemanCode;
	}

}