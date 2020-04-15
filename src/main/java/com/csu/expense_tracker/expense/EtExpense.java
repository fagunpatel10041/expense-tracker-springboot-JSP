package com.csu.expense_tracker.expense;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expenses")
public class EtExpense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="expense_date")
	private String expenseDate;
	
	@Column(name="expense_item")
	private String expenseItem;
	
	@Column(name="expense_cost")
	private String expenseCost;

	
	public EtExpense() {
		
	}


	public EtExpense(Long id, Long userId, int categoryId, String expenseDate, String expenseItem, String expenseCost) {
		this.id = id;
		this.userId = userId;
		this.categoryId = categoryId;
		this.expenseDate = expenseDate;
		this.expenseItem = expenseItem;
		this.expenseCost = expenseCost;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getExpenseDate() {
		return expenseDate;
	}


	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}


	public String getExpenseItem() {
		return expenseItem;
	}


	public void setExpenseItem(String expenseItem) {
		this.expenseItem = expenseItem;
	}


	public String getExpenseCost() {
		return expenseCost;
	}


	public void setExpenseCost(String expenseCost) {
		this.expenseCost = expenseCost;
	}


	@Override
	public String toString() {
		return "EtExpense [id=" + id + ", userId=" + userId + ", categoryId=" + categoryId + ", expenseDate="
				+ expenseDate + ", expenseItem=" + expenseItem + ", expenseCost=" + expenseCost + "]";
	}
	
	
	
	
	

}
