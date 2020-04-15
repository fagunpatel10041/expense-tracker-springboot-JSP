package com.csu.expense_tracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.csu.expense_tracker.entity.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public List<Category> loadCategories() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Category> theQuery = currentSession.createQuery("from Category" , Category.class);
		
		List<Category> categories = theQuery.getResultList();
		
		System.out.println("List of Catwgories..."+categories);
		
		return categories;
	}

}
