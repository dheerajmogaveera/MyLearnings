package com.expense.tracker.app.model;

import java.util.HashMap;

public class Report {

	Long totalAmount;
	Long averageAmountPerDay;
    Long averageAmountPerExpense;
	HashMap<String, Long> costsByDate;
	


	public Long getAverageAmountPerDay() {
		return averageAmountPerDay;
	}

	public void setAverageAmountPerDay(Long averageAmountPerDay) {
		this.averageAmountPerDay = averageAmountPerDay;
	}

	public Report(Long totalAmount, Long averageAmountPerDay, Long averageAmountPerExpense,
			HashMap<String, Long> costsByDate) {
		super();
		this.totalAmount = totalAmount;
		this.averageAmountPerDay = averageAmountPerDay;
		this.averageAmountPerExpense = averageAmountPerExpense;
		this.costsByDate = costsByDate;
	}

	public Long getAverageAmountPerExpense() {
		return averageAmountPerExpense;
	}

	public void setAverageAmountPerExpense(Long averageAmountPerExpense) {
		this.averageAmountPerExpense = averageAmountPerExpense;
	}

	public HashMap<String, Long> getCostsByDate() {
		return costsByDate;
	}

	public void setCostsByDate(HashMap<String, Long> costsByDate) {
		this.costsByDate = costsByDate;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	

	public Report() {
		
	}

	public Report(Long totalAmount) {
		super();
		this.totalAmount = totalAmount;
	}

	
	
	
	
	
}
