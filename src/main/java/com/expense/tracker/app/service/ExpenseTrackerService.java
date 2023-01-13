package com.expense.tracker.app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.app.contants.ExpenseConstants;
import com.expense.tracker.app.exception.InvalidInputException;
import com.expense.tracker.app.exception.NoSuchExpenseException;
import com.expense.tracker.app.model.Expense;
import com.expense.tracker.app.model.Report;
import com.expense.tracker.app.repository.ExpenseRepository;

@Service
public class ExpenseTrackerService {

	@Autowired
	ExpenseRepository expenseRepository;

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public Expense addExpense(Expense expense) {
		expense.setExpenseDate(LocalDate.now());
		return expenseRepository.save(expense);
	}

	public Expense deleteExpense(Expense expense) {
		expenseRepository.delete(expense);
		return expense;
	}

	public Expense updateExpense(Expense expense) throws NoSuchExpenseException {
		if (expenseRepository.findById(expense.getTitle()).isEmpty())
			throw new NoSuchExpenseException("No Expense with title:" + expense.getTitle() + " found");
		return expenseRepository.save(expense);
	}

	public List<Expense> getAllExpenses() {
		List<Expense> expenseList = new ArrayList<>();
		expenseRepository.findAll().forEach(expenseList::add);
		return expenseList;
	}

	public Expense getExpenseByTitle(String title) {
		return expenseRepository.findById(title).get();
	}

	public Report generateReport(String range, String startDate, String endDate) throws InvalidInputException {
		List<Expense> expenseList = new ArrayList<>();
		expenseRepository.findAll().forEach(expenseList::add);
		if (isReportInputValid(range, startDate, endDate))
			throw new InvalidInputException();
		if (range.equals(ExpenseConstants.CUSTOM)) {
			LocalDate start = LocalDate.parse(startDate, dateTimeFormatter);
			LocalDate end = LocalDate.parse(endDate, dateTimeFormatter);
			expenseList = expenseList.stream().filter(
					o -> o.getExpenseDate().isAfter(start.minusDays(1)) && o.getExpenseDate().isBefore(end.plusDays(1))).toList();
		} else {
			int days = ExpenseConstants.WEEK.equalsIgnoreCase(range) ? 7 : 30;
			expenseList = expenseList.stream().filter(o -> o.getExpenseDate().isAfter(LocalDate.now().minusDays(days))).toList();
		}

		return generateReport(expenseList);

	}

	private Report generateReport(List<Expense> expenseList) {

		long size = expenseList.size();
		if (size == 0)
			return null;
		HashMap<String, Long> amountByDate = new HashMap<>();
		long total = 0;
		for (Expense e : expenseList) {
			String date = dateTimeFormatter.format(e.getExpenseDate());
			if (amountByDate.containsKey(date))
				amountByDate.put(date, amountByDate.get(date) + e.getAmount());
			else
				amountByDate.put(date, e.getAmount());
			total = total + e.getAmount();

		}
		return new Report(total, total / amountByDate.size(), total / expenseList.size(), amountByDate, size, null);
	}

	private boolean isReportInputValid(String range, String startDate, String endDate) {
		return ExpenseConstants.WEEK.equalsIgnoreCase(range) || ExpenseConstants.MONTH.equalsIgnoreCase(range)
				|| (ExpenseConstants.CUSTOM.equalsIgnoreCase(range) && (startDate != null && endDate != null)) ? false
						: true;
	}
}
