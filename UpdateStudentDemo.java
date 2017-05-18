package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory=new Configuration()
							   .configure("hibernate.cfg.xml")
							   .addAnnotatedClass(Student.class)
							   .buildSessionFactory();
				
		//create a session
		Session session=factory.getCurrentSession();
		
		try{
			
			
			//start a transaction
			session.beginTransaction();
			
			int studentId=1;
			
			//retrive the student based on the primary id
			System.out.println("\nGetting the Student with the id of: "+studentId);		
			Student myStudent=session.get(Student.class, studentId);
			System.out.println("Updated Student...");
			
			myStudent.setFirstName("Melody");
			//commit the transaction
			session.getTransaction().commit();
			
			//NEW CODE
			//make a build update
			
			session=factory.getCurrentSession();
			session.beginTransaction();
			//update email for all students
			System.out.println("Update email for all students...");
			session.createQuery("update Student SET email='foo@luv2code.com'")
			.executeUpdate();
			
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}finally{
			//close the session
			factory.close();
		}

	}

}
