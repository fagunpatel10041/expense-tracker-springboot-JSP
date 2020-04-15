package com.csu.expense_tracker.service;

import java.util.List;

import com.csu.expense_tracker.entity.Category;

public interface CategoryService {

	List<Category> loadCategories();

}
