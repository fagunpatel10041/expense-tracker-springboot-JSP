package com.csu.expense_tracker.entity;

import org.springframework.lang.NonNull;

public class Email {
	
	@NonNull
	private String senderName;
	
	@NonNull
	private String senderEmail;
	
	@NonNull
	private String message;
	
	public Email() {
		
	}
	
	public Email(String senderName, String senderEmail, String message) {
		this.senderName = senderName;
		this.senderEmail = senderEmail;
		this.message = message;
	}
	
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "Email [senderName=" + senderName + ", senderEmail=" + senderEmail + ", message=" + message + "]";
	}
	
	
	
}
