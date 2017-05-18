package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory=new Configuration()
							   .configure("hibernate.cfg.xml")
							   .addAnnotatedClass(Student.class)
							   .buildSessionFactory();
				
		//get a session
		Session session=factory.getCurrentSession();
		
		try{
			
			
			//start a transaction
			session.beginTransaction();
			
			//query the students
			List<Student> theStudents=session.createQuery("from Student").getResultList();
			
			//display the students
			System.out.println("All students:");
			displayStudents(theStudents);
		
			//query the students:last name='Ducky'
			theStudents=session.createQuery("from Student s where s.lastName='Ducky'").getResultList();
		
			//disply the students
			System.out.println("Student with last name Doe:");
			displayStudents(theStudents);
			
			//query students with last name 'Ducky' or first name 'Larry'
			theStudents=
					session.createQuery("from Student s where"
					+ " s.lastName='Ducky' OR s.firstName='Larry'").getResultList();
			//display the students
			System.out.println("students with last name 'Ducky' or first name 'Larry':");
			displayStudents(theStudents);
			
			//query the email like '%gmail.com'
			theStudents=
					session.createQuery("from Student s where"
							+" s.email LIKE '%gmail.com'").getResultList();
			//display the students
			System.out.println("query the email like '%gmail.com'");
			displayStudents(theStudents);

			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}finally{
			//close the session
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}
	}

}
