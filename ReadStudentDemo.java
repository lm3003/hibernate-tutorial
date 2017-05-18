package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory=new Configuration()
							   .configure("hibernate.cfg.xml")
							   .addAnnotatedClass(Student.class)
							   .buildSessionFactory();
				
		//create a session
		Session session=factory.getCurrentSession();
		
		try{
			//create the student object
			System.out.println("Creating a new student object...");
			Student theStudent=new Student("Larry", "Baige", "larrybaige@gmail.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			session.save(theStudent);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done");
		}finally{
			//close the session
			factory.close();
		}

	}

}
