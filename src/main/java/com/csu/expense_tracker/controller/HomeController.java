package com.csu.expense_tracker.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.csu.expense_tracker.entity.Expense;
import com.csu.expense_tracker.entity.TempUser;
import com.csu.expense_tracker.entity.User;
import com.csu.expense_tracker.service.ExpenseService;
import com.csu.expense_tracker.service.UserService;



@Controller
public class HomeController {
	
	@Autowired
	private ExpenseService expenseService;

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String showHome(Model theModel) {

		float weeklySum = 0;
		float monthlySum = 0;
		float yearlySum = 0;
		
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
				LocalDateTime now = LocalDateTime.now();
		        LocalDateTime weekBefore = now.minusDays(7);
		        LocalDateTime monthBefore = now.minusDays(30);
		        LocalDateTime yearBefore = now.minusYears(1);
		
		        String today = now.format(format);
		        String week_before = weekBefore.format(format); 
		        String month_before = monthBefore.format(format);
		        String year_before = yearBefore.format(format);
			        
		        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String currentUser = auth.getName();
				User theUser = userService.findByUserName(currentUser);
				Long currentUserId = theUser.getId();
					

				List<Expense> Week_Expenses = expenseService.exportReport(currentUserId, week_before, today);
				List<Expense> Month_Expense = expenseService.exportReport(currentUserId, month_before, today);
				List<Expense> Year_Expenses = expenseService.exportReport(currentUserId, year_before, today);
				
				System.out.println("year expenses: " + Year_Expenses);	
				
				for(Expense temp : Week_Expenses) {
					 weeklySum = weeklySum + temp.getExpenseCost();
				}
				for(Expense temp : Month_Expense) {
					monthlySum = monthlySum + temp.getExpenseCost();
				}
				for(Expense temp : Year_Expenses) {
					yearlySum = yearlySum + temp.getExpenseCost();
				}

				theModel.addAttribute("yearlyExpense", yearlySum);
				theModel.addAttribute("yearlyExpense", yearlySum);
				theModel.addAttribute("weeklyExpense", weeklySum);
				theModel.addAttribute("monthlyExpense", monthlySum);
	        

		return "dashboard";
	}
	
	@GetMapping("/dashboard")
	public String showDashboard(Model theModel) {
		
		
		
		return "dashboard";
	}
	
	@GetMapping("/profile")
	public String showProfile(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User theUser = userService.findByUserName(authentication.getName());
		theModel.addAttribute("theUser", theUser);
		
		
		return "profile";
	}
	
	
	
	
}
