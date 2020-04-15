package com.csu.expense_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csu.expense_tracker.entity.TempUser;
import com.csu.expense_tracker.entity.User;
import com.csu.expense_tracker.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	

	@GetMapping("/showFormForUpdateProfile")
	public String shoeFormForUpdateProfile(Model theModel) {
	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User theUser = userService.findByUserName(authentication.getName());
		
		TempUser tempUser = new TempUser();
		tempUser.setEmail(theUser.getEmail());
		tempUser.setFirstName(theUser.getFirstName());
		tempUser.setLastName(theUser.getLastName());
		tempUser.setUserName(theUser.getUserName());
		tempUser.setId(theUser.getId());
		tempUser.setPassword(theUser.getPassword());
		
		
		theModel.addAttribute("theUser", tempUser);
		
		return "update-profile";
	}
	
	@PostMapping("/processUpdateProfile")
	public String updateProfile(@ModelAttribute("theUser") TempUser tempUser) {

		System.out.println("INSIDE PROCESS UPDATE PROFILE --> "+tempUser.toString());
		
		userService.update(tempUser);
		

		return "redirect:/profile";
	}
	

	
	@GetMapping("/showFormForChangePassword")
	public String showFormForChangePassword(Model theModel) {
		
		theModel.addAttribute("theUser", new TempUser());
		return "change-password";
	}
	
	
	@PostMapping("/processChangePassword")
	public String processChangePassword(@ModelAttribute("theUser") TempUser tempUser) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User theUser = userService.findByUserName(authentication.getName());
		
		tempUser.setId(theUser.getId());	
		userService.changePassword(tempUser);
		
		return "redirect:/dashboard";
	}
	
	
	
	
	
	
	
}
