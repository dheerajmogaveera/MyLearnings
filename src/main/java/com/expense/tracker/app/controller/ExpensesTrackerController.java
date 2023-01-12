package com.expense.tracker.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.expense.tracker.app.exception.InvalidInputException;
import com.expense.tracker.app.exception.NoSuchExpenseException;
import com.expense.tracker.app.model.Expense;
import com.expense.tracker.app.model.Report;
import com.expense.tracker.app.service.ExpenseTrackerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dheeraj
 *
 */
@RestController
@RequestMapping("/v1/expenses")
public class ExpensesTrackerController {
	
	@Autowired
	private ExpenseTrackerService expenseTrackerService;
	
	private Logger logger=LoggerFactory.getLogger(ExpensesTrackerController.class);
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	
	@GetMapping("")
     public ResponseEntity<List<Expense>> getAllExpenses(){
		logger.info("Fetching All the Expenses ...........");
		return new ResponseEntity<>(expenseTrackerService.getAllExpenses(),HttpStatus.OK);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<Expense> getExpenseByTitle(@PathVariable("title") String title) {
		logger.info("Fetching Expense for the title:{}",title);
		return new ResponseEntity<Expense>(expenseTrackerService.getExpenseByTitle(title),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) throws JsonProcessingException {
		logger.info("Adding Expense with following values :{}",objectMapper.writeValueAsString(expense));
		return new ResponseEntity<Expense>(expenseTrackerService.addExpense(expense),HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense) throws JsonProcessingException, NoSuchExpenseException {
		logger.info("Updating Expense with title:{} with the value:{}",expense.getTitle(),objectMapper.writeValueAsString(expense));
		return new ResponseEntity<Expense>(expenseTrackerService.updateExpense(expense),HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Expense> deleteExpense(@RequestBody Expense expense) {
		return new ResponseEntity<Expense>(expenseTrackerService.deleteExpense(expense),HttpStatus.OK);
	}
	
	@GetMapping("/report")
	public ResponseEntity<Report> generateReport(@RequestParam String range 
			,@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws InvalidInputException{
		logger.info("Generating report for with values- range:{}, startDate:{},endDate:{}",range,startDate,endDate);
		return new ResponseEntity<Report>(expenseTrackerService.generateReport(range,startDate,endDate),HttpStatus.OK);
	}
	

}
