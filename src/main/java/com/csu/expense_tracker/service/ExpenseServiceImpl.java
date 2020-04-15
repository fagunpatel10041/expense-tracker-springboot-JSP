package com.csu.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csu.expense_tracker.dao.ExpenseDao;
import com.csu.expense_tracker.entity.Expense;
import com.csu.expense_tracker.expense.EtExpense;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseDao expenseDao;
	

	@Override
	@Transactional
	public List<Expense> loadExpensesByUser(Long userId) {
		
		return expenseDao.loadExpensesByUser(userId);
	}


	@Override
	@Transactional
	public void AddExpense(Expense theExpense) {
		
		expenseDao.AddExpense(theExpense);
		
	}


	@Override
	@Transactional
	public Expense getExpense(Long expenseId) {
		return expenseDao.getExpense(expenseId);
		
	}


	@Override
	@Transactional
	public void deleteExpense(Long expenseId) {
		expenseDao.deleteExpense(expenseId);
	}


	@Override
	@Transactional
	public List<Expense> exportReport(Long currentUserId, String start, String end) {
		
		return expenseDao.exportReport(currentUserId, start, end);
	}



}
