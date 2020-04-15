package com.csu.expense_tracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
//	@Column(name="category_id")
//	private float categoryId;
	
	@Column(name="expense_date")
	private String expenseDate;
	
	@Column(name="expense_item")
	private String expenseItem;
	
	@Column(name="expense_cost")
	private float expenseCost;
	
	@ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
	
	public Expense() {
		
	}

	public Expense(Long id, Long userId, String expenseDate, String expenseItem, float expenseCost,
			Category category) {
		this.id = id;
		this.userId = userId;
		this.expenseDate = expenseDate;
		this.expenseItem = expenseItem;
		this.expenseCost = expenseCost;
		this.category = category;
	}

	public Expense(Long userId, String expenseDate, String expenseItem, float expenseCost, Category category) {
		super();
		this.userId = userId;
		this.expenseDate = expenseDate;
		this.expenseItem = expenseItem;
		this.expenseCost = expenseCost;
		this.category = category;
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

	public float getExpenseCost() {
		return expenseCost;
	}

	public void setExpenseCost(float expenseCost) {
		this.expenseCost = expenseCost;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", userId=" + userId + ", expenseDate=" + expenseDate + ", expenseItem="
				+ expenseItem + ", expenseCost=" + expenseCost + ", category=" + category + "]";
	}
	
	
	
	
}
	
	
	