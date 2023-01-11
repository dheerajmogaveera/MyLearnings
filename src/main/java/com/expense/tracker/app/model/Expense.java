package com.expense.tracker.app.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class Expense {

	@Id
	String title;

	Long amount;

	List<String> categories;
	
	public 	LocalDate getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(	LocalDate expenseDate) {
		this.expenseDate = expenseDate;
	}

	LocalDate expenseDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Expense() {
		
	}

	public Expense(String title, Long amount, List<String> categories) {
		super();
		this.title = title;
		this.amount = amount;
		this.categories = categories;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}
