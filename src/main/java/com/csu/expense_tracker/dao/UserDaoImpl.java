package com.csu.expense_tracker.dao;

import java.util.Random;

import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.csu.expense_tracker.entity.TempUser;
import com.csu.expense_tracker.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(@Valid User theUser) {
		
		System.out.println(theUser.toString());
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theUser);
		
	}

	@Override
	public User findByUserName(String userName) {
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

				// now retrieve/read from database using username
				Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
				theQuery.setParameter("uName", userName);
				
				System.out.println("Inside Last Method"+theQuery);
				
				User theUser = null;
				try {
					theUser = theQuery.getSingleResult();
				} catch (Exception e) {
					theUser = null;
				}

				return theUser;
	}

	@Override
	public void update(TempUser theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		TempUser tempUser = currentSession.get(TempUser.class, theUser.getId());
		
		tempUser.setFirstName(theUser.getFirstName());
		tempUser.setLastName(theUser.getLastName());
		tempUser.setEmail(theUser.getEmail());
		
		
		currentSession.saveOrUpdate(tempUser);
	}

	@Override
	public void changePassword(TempUser tempUser) {
		
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		TempUser theUser = currentSession.get(TempUser.class, tempUser.getId());
		theUser.setPassword(tempUser.getPassword());
		
		currentSession.saveOrUpdate(theUser);
		
		
	}

	@Override
	public void resetPassword(Long id, String newPassword) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		User theUser = currentSession.get(User.class, id);
		
		theUser.setPassword(newPassword);
		
		currentSession.saveOrUpdate(theUser);
		
		
	}

	@Override
	public char[] generatePassword(int length) {
		
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      char[] password = new char[length];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));

	      for(int i = 4; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      return password;
	}

	@Override
	public User findByEmail(String email) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where email=:uEmail", User.class);
		theQuery.setParameter("uEmail", email);
		
		System.out.println("Inside findByEmail Method"+theQuery);
		
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	

}
