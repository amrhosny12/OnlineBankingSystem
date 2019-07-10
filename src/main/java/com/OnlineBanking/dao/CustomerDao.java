package com.OnlineBanking.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.OnlineBanking.beans.Customers;
import com.OnlineBanking.util.HibernateUtil;

public class CustomerDao {
	

	public void insertCustomer(Customers cust) {
		
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(cust);
			tx.commit();
			
		}catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			
		}finally{
			session.close();
			
		}
		
	}
	
	public Customers getCustByCIS(Integer CIS) {
		
		Customers cust = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			cust = (Customers)session.get(Customers.class, CIS);
			tx.commit();
			
		}catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
		e.printStackTrace();
		
		}finally {
			session.close();
		}
		
		return cust;
	}
	
	public Customers getCustByUserId(String userId) {
		
		Customers cust = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			cust = (Customers)session.get(Customers.class, userId);
			tx.commit();
		
		}catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return cust;
		
	}
		

	public Customers getCustBySSN(Integer ssn) {
		
		Customers cust = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			cust = (Customers)session.get(Customers.class, ssn);
			tx.commit();
			
		}catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return cust;
	}
	
	
	public Integer loginCust(String userName, String pw) {
		
		List <Customers> AllCust  = null;
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			AllCust = session.createQuery("FROM CUSTOMERS").list();
			
			for(Customers cust : AllCust) {
				if(cust.getUserName().equals(userName) && cust.getPassword().equals(pw))
					return cust.getCIS();
			}
			
		}catch(HibernateException e) {
			if (tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		
		}finally {
			session.close();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
