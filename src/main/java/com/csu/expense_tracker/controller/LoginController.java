package com.csu.expense_tracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.csu.expense_tracker.entity.Email;
import com.csu.expense_tracker.entity.User;
import com.csu.expense_tracker.service.UserService;



@Controller
public class LoginController {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/showLoginForm")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/showForgotPasswordForm")
	public String showForgotPasswordForm(Model model) {
		
		User theUser = new User();
		
		model.addAttribute("theUser", theUser);
		
		return "forgotPassword";
	}
	
	@GetMapping("/showFindUsernameForm")
	public String showFindUsernameForm(Model model) {
		
		User theUser = new User();
		
		model.addAttribute("theUser", theUser);
		
		return "find-username";
	}
	
	@PostMapping("/sendEmail")
	public String sendEmail(@ModelAttribute("theUser") User theUser, Model model) {
		
		User currentUser = userService.findByUserName(theUser.getUserName());
		
		if(currentUser == null) {
			System.out.println("Null Return...!!");
			return "redirect:/showForgotPasswordForm?error";
		}

		System.out.println(currentUser.toString());
		
		int length = 5;
		
		char[] generatedPassword = userService.generatePassword(length);
		
		userService.resetPassword(currentUser.getId(),generatedPassword.toString());

		
		SimpleMailMessage seimpleMailMessage = new SimpleMailMessage();
		
		seimpleMailMessage.setFrom("fagunpatel1996@gmail.com");
		seimpleMailMessage.setTo(currentUser.getEmail());
		seimpleMailMessage.setSubject("Expense Tracker: Reset Password");
		seimpleMailMessage.setText(
				"Your New Password is: " + generatedPassword.toString()+
				"\n Please Login with new Credential on www.fagunpatel.com/expense-tracker");
		
		mailSender.send(seimpleMailMessage);
		
		model.addAttribute("success", "Your Message has been sent to your registered Email.");
		
		
		return "redirect:/showLoginForm?passwordReset";
	}
	
	
	@PostMapping("/findUsername")
	public String findUsername(@ModelAttribute("theUser") User theUser, Model model) {
		
		//User currentUser = userService.findByUserName(theUser.getUserName());

		
		User requestedUser = userService.findByEmail(theUser.getEmail());
		
		
//		System.out.println("I got UserName: "+ requestUser.getUserName());
		
		SimpleMailMessage seimpleMailMessage = new SimpleMailMessage();
		
		seimpleMailMessage.setFrom("fagunpatel1996@gmail.com");
		seimpleMailMessage.setTo(requestedUser.getEmail());
		seimpleMailMessage.setSubject("Expense Tracker: Find Username");
		seimpleMailMessage.setText(
				"Your Username is: => " + requestedUser.getUserName()+
				"\n Please Login with "+requestedUser.getUserName()+"  on www.fagunpatel.com/expense-tracker");
		
		mailSender.send(seimpleMailMessage);
		
		model.addAttribute("success", "Your Message has been sent to your registered Email.");
		
		
		return "redirect:/";
	}
}
