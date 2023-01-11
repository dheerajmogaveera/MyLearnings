package com.expense.tracker.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.app.model.Expense;
import com.expense.tracker.app.model.Report;
import com.expense.tracker.app.service.ExpenseTrackerService;

@RestController
@RequestMapping("/v1/expenses")
public class ExpensesTrackerController {
	
	@Autowired
	ExpenseTrackerService expenseTrackerService;
	
	@GetMapping("")
     public ResponseEntity<List<Expense>> getAllExpenses(){
		return new ResponseEntity<>(expenseTrackerService.getAllExpenses(),HttpStatus.OK);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<Expense> getExpenseByTitle(@PathVariable("title") String title) {
		return new ResponseEntity<Expense>(expenseTrackerService.getExpenseByTitle(title),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
		return new ResponseEntity<Expense>(expenseTrackerService.addExpense(expense),HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense) {
		return new ResponseEntity<Expense>(expenseTrackerService.updateExpense(expense),HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Expense> deleteExpense(@RequestBody Expense expense) {
		return new ResponseEntity<Expense>(expenseTrackerService.deleteExpense(expense),HttpStatus.OK);
	}
	
	@GetMapping("/report")
	public ResponseEntity<Report> generateReport(@RequestParam String range 
			,@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate){
		return new ResponseEntity<Report>(expenseTrackerService.generateReport(range,startDate,endDate),HttpStatus.OK);
	}
	

}
