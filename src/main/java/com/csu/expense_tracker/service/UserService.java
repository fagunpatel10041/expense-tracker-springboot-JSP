package com.csu.expense_tracker.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.csu.expense_tracker.entity.TempUser;
import com.csu.expense_tracker.entity.User;

public interface UserService extends UserDetailsService {

	void save(User theUser);

	User findByUserName(String userName);

	void update(TempUser theUser);

	void changePassword(TempUser tempUser);

	void resetPassword(Long id, String string);

	char[] generatePassword(int length);

	User findByEmail(String email);

	

	
	
}
