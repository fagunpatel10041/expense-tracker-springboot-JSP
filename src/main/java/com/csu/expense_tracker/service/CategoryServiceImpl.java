package com.csu.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csu.expense_tracker.dao.CategoryDao;
import com.csu.expense_tracker.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	@Transactional
	public List<Category> loadCategories() {
		return categoryDao.loadCategories();
	}

}
