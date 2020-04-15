package com.csu.expense_tracker.dao;

import java.util.List;

import com.csu.expense_tracker.entity.Category;

public interface CategoryDao {

	List<Category> loadCategories();

}
