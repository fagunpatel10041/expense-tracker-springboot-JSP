package com.csu.expense_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.csu.expense_tracker.entity.Category;
import com.csu.expense_tracker.entity.Expense;
import com.csu.expense_tracker.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

}
