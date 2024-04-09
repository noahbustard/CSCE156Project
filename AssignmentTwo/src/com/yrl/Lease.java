package com.yrl;

import java.time.LocalDate;
import java.time.Period;
/**
 * Lease class is an extension of purchase. Now has start and end dates
 * to signify the lease's duration.
 */
public class Lease extends Purchase {
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Lease(String itemCode, String name,
			Double baseCost, LocalDate startDate, LocalDate endDate) {
		super(itemCode, name, baseCost);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}
	
	public String getType() {
		return "Lease";
	}

	@Override
	public String toString() {
		return this.getName() + " (" + this.getItemCode() + ")  - Lease for "
	+ Period.between(this.startDate, this.endDate).getMonths() + " Months";
	}
	@Override
	public Double getCost() {
		int months = Period.between(this.startDate, this.endDate).getMonths();
		months += Period.between(this.startDate, this.endDate).getYears() * 12;
		double monthlyCost = (this.getBaseCost() * 1.5)/months;
		return monthlyCost;
	}
	
	@Override
	public Double getTax() {
		return 0.0;
	}

}
