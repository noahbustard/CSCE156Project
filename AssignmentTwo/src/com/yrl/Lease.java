package com.yrl;

import java.time.LocalDate;

public class Lease extends Product {
	private LocalDate startDate;
	private LocalDate endDate;

	public Lease(String itemCode, String saleCode, String name, Double basePrice, LocalDate startDate,
			LocalDate endDate) {
		super(itemCode, name, basePrice, saleCode);
		this.startDate = startDate;
		this.endDate = endDate;
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
