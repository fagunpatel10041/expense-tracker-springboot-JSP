package com.csu.expense_tracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csu.expense_tracker.entity.Expense;

@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	public List<Expense> loadExpensesByUser(Long userId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
	
		Query<Expense> theQuery = currentSession.createQuery("from Expense e where e.userId = :theId", Expense.class);
		theQuery.setParameter("theId", userId);
		List<Expense> expenses = theQuery.list();
		
		
		
	
		return expenses;
	}


	@Override
	public void AddExpense(Expense theExpense) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theExpense);
		
	}


	@Override
	public Expense getExpense(Long expenseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println("Probably Null : " + expenseId);
		
		 Expense theExpense = currentSession.get(Expense.class, expenseId);
		
		
		return theExpense;
		
	}


	@Override
	public void deleteExpense(Long expenseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Expense where id=:expenseId");
		theQuery.setParameter("expenseId", expenseId);
				
		theQuery.executeUpdate();		
		
	}


	@Override
	public List<Expense> exportReport(Long currentUserId, String start, String end) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Expense> theQuery = currentSession.createQuery("from Expense where expenseDate between :start and :end", Expense.class);
		theQuery.setParameter("start", start);
		theQuery.setParameter("end", end);
		List<Expense> expenses = theQuery.list();
		
		
		return expenses;
	}



}
