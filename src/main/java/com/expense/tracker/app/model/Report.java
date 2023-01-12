package com.expense.tracker.app.model;

import java.util.HashMap;

public class Report {

	Long totalAmount;
	Long averageAmountPerDay;
    Long averageAmountPerExpense;
	HashMap<String, Long> amountByDate;
    Long totalExpenses;
    HashMap<String,Long> numberOfExpensesByDays;
	


	public Report(Long totalAmount, Long averageAmountPerDay, Long averageAmountPerExpense,
			HashMap<String, Long> amountByDate, Long totalExpenses, HashMap<String, Long> numberOfExpensesByDays) {
		super();
		this.totalAmount = totalAmount;
		this.averageAmountPerDay = averageAmountPerDay;
		this.averageAmountPerExpense = averageAmountPerExpense;
		this.amountByDate = amountByDate;
		this.totalExpenses = totalExpenses;
		this.numberOfExpensesByDays = numberOfExpensesByDays;
	}

	public HashMap<String, Long> getAmountByDate() {
		return amountByDate;
	}

	public void setAmountByDate(HashMap<String, Long> amountByDate) {
		this.amountByDate = amountByDate;
	}

	public Long getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Long totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public HashMap<String, Long> getNumberOfExpensesByDays() {
		return numberOfExpensesByDays;
	}

	public void setNumberOfExpensesByDays(HashMap<String, Long> numberOfExpensesByDays) {
		this.numberOfExpensesByDays = numberOfExpensesByDays;
	}

	public Long getAverageAmountPerDay() {
		return averageAmountPerDay;
	}

	public void setAverageAmountPerDay(Long averageAmountPerDay) {
		this.averageAmountPerDay = averageAmountPerDay;
	}

	public Report(Long totalAmount, Long averageAmountPerDay, Long averageAmountPerExpense,
			HashMap<String, Long> amountByDate) {
		super();
		this.totalAmount = totalAmount;
		this.averageAmountPerDay = averageAmountPerDay;
		this.averageAmountPerExpense = averageAmountPerExpense;
		this.amountByDate = amountByDate;
	}

	public Long getAverageAmountPerExpense() {
		return averageAmountPerExpense;
	}

	public void setAverageAmountPerExpense(Long averageAmountPerExpense) {
		this.averageAmountPerExpense = averageAmountPerExpense;
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
