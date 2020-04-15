package com.csu.expense_tracker.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csu.expense_tracker.entity.TempUser;
import com.csu.expense_tracker.entity.User;
import com.csu.expense_tracker.service.UserService;


@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
    private UserService userService;

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationPage(Model theModel) {
		
		User theUser = new User();
		
		theModel.addAttribute("theUser", theUser);
		
		return "registration";
	}
	
	
	@GetMapping("/forgotPasswordForm")
	public String forgotPasswordForm(Model theModel) {
		
		User theUser = new User();
		
		theModel.addAttribute("theUser", theUser);
	
		return "forgotPassword";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") User theUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration";
	     }
		 
		 
			// check the database if user already exists
	        User existing = userService.findByUserName(userName);
	        if (existing != null){
	        	theModel.addAttribute("theUser", new User());
				theModel.addAttribute("registrationError", "User name already exists.");

				logger.warning("User name already exists.");
	        	return "registration";
	        }
	        
		 //CREATE USER ACCOUNT
		 logger.info("Successfully created user: " + userName);
		 userService.save(theUser);
        
		 System.out.println("Registered.");
		 
		 theModel.addAttribute("message");
		 
        return "redirect:/";		
	}
	
}
