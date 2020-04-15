package com.csu.expense_tracker.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csu.expense_tracker.dao.RoleDao;
import com.csu.expense_tracker.dao.UserDao;
import com.csu.expense_tracker.entity.Role;
import com.csu.expense_tracker.entity.TempUser;
import com.csu.expense_tracker.entity.User;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleDao roleDao;
	

	
	@Override
	@Transactional
	public void save(@Valid User theUser) {
		
		User user = new User();
		 
		user.setUserName(theUser.getUserName());
		user.setPassword(passwordEncoder.encode(theUser.getPassword()));
		user.setFirstName(theUser.getFirstName());
		user.setLastName(theUser.getLastName());
		user.setEmail(theUser.getEmail());
		
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));
		
		System.out.println("UserService: Save Method");
		 System.out.println("Try The User ROle: "+ theUser.getRoles());
		userDao.save(user);
		
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void update(TempUser theUser) {
		
		userDao.update(theUser);
		
	}

	@Override
	@Transactional
	public void changePassword(TempUser tempUser) {
		
		tempUser.setPassword(passwordEncoder.encode(tempUser.getPassword()));
		
		userDao.changePassword(tempUser);
		
	}

	@Override
	@Transactional
	public void resetPassword(Long id, String newPassword) {
		
		String encodedPassword = passwordEncoder.encode(newPassword);
		
		userDao.resetPassword(id, encodedPassword );
		
	}

	@Override
	public char[] generatePassword(int length) {
		
		return userDao.generatePassword(length);
	}

	@Override
	@Transactional
	public User findByEmail(String email) {
		
		return userDao.findByEmail(email);
	}

	

	

	

}
