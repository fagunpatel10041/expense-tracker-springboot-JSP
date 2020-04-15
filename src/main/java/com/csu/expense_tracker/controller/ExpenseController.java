package com.csu.expense_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csu.expense_tracker.entity.Category;
import com.csu.expense_tracker.entity.Expense;
import com.csu.expense_tracker.entity.User;
import com.csu.expense_tracker.service.CategoryService;
import com.csu.expense_tracker.service.ExpenseService;
import com.csu.expense_tracker.service.UserService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/showFormForAdd")
	public String AddExpense(Model theModel) {

		// TO GET CATEGORIES LIST FROM DB
		List<Category> categories = categoryService.loadCategories();
		theModel.addAttribute("theCategory" , categories);
		
		// SEND EXPENSE OBJECT FOR ADD EXPENSE FORM
		
		Expense theExpense = new Expense();
		theModel.addAttribute("theExpense", theExpense);
		
		return "add-expense";
	}
	
	@GetMapping("/showFormForUpdate")
	public String UpdateExpense(@RequestParam("expenseId") Long expenseId , Model theModel) {
		
		Expense theExpense = expenseService.getExpense(expenseId);
		theModel.addAttribute("theExpense" , theExpense);
		//System.out.println("Here we go Fagun: " + theExpense.toString() + "Now See This Id: "+ theExpense.getCategory().getId());
		
		// TO GET CATEGORIES LIST FROM DB
		List<Category> categories = categoryService.loadCategories();
		theModel.addAttribute("theListCategory" , categories);
		
		theModel.addAttribute("theCategory" , theExpense.getCategory());
		
		return "update-expense";
	}
	
	
	@GetMapping("/showManageExpenses")
	public String manageExpense(Model theModel) {
		
		//TO FIND USER DETAILS BY USERNAME
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByUserName(authentication.getName());
		Long userId = user.getId();
		
		// TO GET EXPENSE LIST FROM DB BY USER
		List<Expense> expenses = expenseService.loadExpensesByUser(userId);
		
		for(Expense temp : expenses) {
			System.out.println(temp.toString());
		}
		
		theModel.addAttribute("theExpenses" , expenses);
		
		
			return "manage-expenses";
	}
	
	@PostMapping("/processAddExpense")
	public String processAddExpense(Model theModel, @ModelAttribute("theExpense") Expense theExpense) {
		
		//TO FIND USER DETAILS BY USERNAME
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		Long userId = user.getId();
		
		theExpense.setUserId(userId);
		System.out.println(theExpense.toString());
		expenseService.AddExpense(theExpense);

		return "redirect:/expenses/showManageExpenses";
	}
	
	@PostMapping("/processUpdateExpense")
	public String processUpdateExpense(Model theModel, @ModelAttribute("theExpense") Expense theExpense) {
		
		expenseService.AddExpense(theExpense);
		return "redirect:/expenses/showManageExpenses";
	}
	
	@GetMapping("/deleteExpense")
	public String processDeleteExpense(Model theModel, @RequestParam("expenseId") Long expenseId) {
		
		expenseService.deleteExpense(expenseId);
		return "redirect:/expenses/showManageExpenses";
	}
	
	
	@GetMapping("/showExportReportForm")
	public String showExportReportForm() {
		
		return "export-report";
	}
	
	
	
	@PostMapping("processExportReportForm")
	public String processExportReportForm(@RequestParam("start") String start, @RequestParam("end") String end, Model theModel) {
		
		System.out.println(start + "  " + end);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentUser = auth.getName();
		User theUser = userService.findByUserName(currentUser);
		Long currentUserId = theUser.getId();
		
		List<Expense> theExpenses = expenseService.exportReport(currentUserId, start, end);
		
		for(Expense temp : theExpenses) {
			System.out.println(temp.toString());
		}
		
		theModel.addAttribute("theExpenses" , theExpenses);
		
		return "export-report-list";
	}
	
	
}
