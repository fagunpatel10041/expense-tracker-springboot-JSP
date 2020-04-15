package com.csu.expense_tracker.dao;

import com.csu.expense_tracker.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
