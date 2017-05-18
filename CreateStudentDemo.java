package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			Student theStudent=new Student("Dafiny", "Ducky", "df@gmail.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			System.out.println(theStudent);
			session.save(theStudent);
			
			//commit transaction
			session.getTransaction().commit();
			//My New code
			
			
			//find out the students id: primary key
			System.out.println("Saved student. Generated id:"+theStudent.getId());
			
			//get a new transaction and start the transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrive the student based on the primary id
			System.out.println("\nGetting the Student with the id of: "+theStudent.getId());		
			Student myStudent=session.get(Student.class, theStudent.getId());
			System.out.println("Get Complete"+myStudent);
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}finally{
			//close the session
			factory.close();
		}

	}

}
