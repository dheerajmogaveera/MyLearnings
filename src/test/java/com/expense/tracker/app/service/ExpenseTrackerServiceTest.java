package com.expense.tracker.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expense.tracker.app.contants.ExpenseConstants;
import com.expense.tracker.app.exception.InvalidInputException;
import com.expense.tracker.app.exception.NoSuchExpenseException;
import com.expense.tracker.app.model.Expense;
import com.expense.tracker.app.model.Report;
import com.expense.tracker.app.repository.ExpenseRepository;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseTrackerServiceTest {
	
	@InjectMocks
	ExpenseTrackerService expenseTrackerService;
	@Mock
	ExpenseRepository expenseRepository;
	
	
	@Test
	public void addExpenseTest() {
		Expense expense =new Expense();
		expense.setTitle("abcd");
		when(expenseRepository.save(expense)).thenReturn(expense);
		assertEquals(expenseTrackerService.addExpense(expense),expense);
		assertEquals(expenseTrackerService.addExpense(expense).getTitle(), "abcd");
	}
	
	@Test
	public void deleteExpenseTest() {
		Expense expense =new Expense();
		expense.setTitle("abcd");
		doNothing().when(expenseRepository).delete(expense);
		expenseTrackerService.deleteExpense(expense);
		verify(expenseRepository).delete(expense);
		assertEquals(expenseTrackerService.deleteExpense(expense), expense);
	}
	
	@Test
	public void updateExpenseTest() throws NoSuchExpenseException {
		Expense expense =new Expense();
		expense.setTitle("abcd");
		when(expenseRepository.findById("abcd")).thenReturn(Optional.ofNullable(expense));
		when(expenseRepository.save(expense)).thenReturn(expense);
		assertEquals(expenseTrackerService.updateExpense(expense), expense);
		Optional<Expense> e=Optional.empty();
		when(expenseRepository.findById("abcd")).thenReturn(e);
		assertThrows(NoSuchExpenseException.class,()->{ expenseTrackerService.updateExpense(expense);});
		
		
	}
	
	@Test
	public void getExpenseByTitle() {
		Expense expense =new Expense();
		expense.setTitle("abcd");
		when(expenseRepository.findById("abcd")).thenReturn(Optional.ofNullable(expense));
		assertEquals(expenseTrackerService.getExpenseByTitle("abcd"), expense);
	}
	
	@Test
	public void getAllExpensesTest() {
		Expense e1=new Expense();
		Expense e2=new Expense();
		e1.setTitle("abcd");
		e2.setTitle("defg");
		e1.setAmount(100l);
		e2.setAmount(120l);
		List<Expense> expenseList=new ArrayList<>();
		expenseList.add(e1);
		expenseList.add(e2);
		when(expenseRepository.findAll()).thenReturn(expenseList);
		assertEquals(expenseTrackerService.getAllExpenses().get(0).getTitle(), "abcd");
	}

	@Test
	public void generateReportTest() throws InvalidInputException {
		Expense e1=new Expense();
		Expense e2=new Expense();
		e1.setTitle("abcd");
		e2.setTitle("defg");
		e1.setAmount(100l);
		e2.setAmount(120l);
		e1.setExpenseDate(LocalDate.now().minusDays(6));
		e2.setExpenseDate(LocalDate.now().minusDays(5));
		List<Expense> expenseList=new ArrayList<>();
		expenseList.add(e1);
		expenseList.add(e2);
		when(expenseRepository.findAll()).thenReturn(expenseList);
		Report report=expenseTrackerService.generateReport(ExpenseConstants.WEEK,null,null);
		assertEquals(report.getTotalAmount(), Long.valueOf(220l));
		assertThrows(InvalidInputException.class, ()->{expenseTrackerService.generateReport("abcd", null, null);});
		String start="0"+LocalDate.now().minusDays(5).getDayOfMonth()+"-0"+LocalDate.now().minusDays(5).getMonthValue()+"-"+LocalDate.now().minusDays(5).getYear();
		String end="0"+LocalDate.now().minusDays(4).getDayOfMonth()+"-0"+LocalDate.now().minusDays(4).getMonthValue()+"-"+LocalDate.now().minusDays(4).getYear();
		Report report1=expenseTrackerService.generateReport(ExpenseConstants.CUSTOM, start, end);
		assertEquals(report1.getTotalAmount(), Long.valueOf(120l));
	}
}
