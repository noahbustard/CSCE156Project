package com.yrl;

import java.time.LocalDate;

public class Lease extends Product {
	private LocalDate startDate;
	private LocalDate endDate;
	private String saleCode;

	public Lease(String itemCode, String saleCode, String name, Double basePrice, LocalDate startDate, LocalDate endDate) {
		super(itemCode, name, basePrice);
		this.startDate = startDate;
		this.endDate = endDate;
		this.saleCode = saleCode;
	}

	public Lease(String itemCode, String saleCode, String name, Double basePrice, String type) {
		super(itemCode, name, basePrice);
		this.saleCode = saleCode;
		this.startDate = null;
		this.endDate = null;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

}
