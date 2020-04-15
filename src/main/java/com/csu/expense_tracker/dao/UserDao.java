package com.csu.expense_tracker.dao;

import javax.validation.Valid;

import com.csu.expense_tracker.entity.TempUser;
import com.csu.expense_tracker.entity.User;

public interface UserDao {

	void save(@Valid User theUser);

	User findByUserName(String userName);

	void update(TempUser theUser);


	void changePassword(TempUser tempUser);

	void resetPassword(Long id, String newPassword);

	char[] generatePassword(int length);

	User findByEmail(String email);

}
