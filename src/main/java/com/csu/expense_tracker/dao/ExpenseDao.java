package com.csu.expense_tracker.dao;

import java.util.List;

import com.csu.expense_tracker.entity.Expense;
import com.csu.expense_tracker.expense.EtExpense;

public interface ExpenseDao {

	void AddExpense(Expense theExpense);

	List<Expense> loadExpensesByUser(Long userId);

	Expense getExpense(Long expenseId);

	void deleteExpense(Long expenseId);

	List<Expense> exportReport(Long currentUserId, String start, String end);

}
