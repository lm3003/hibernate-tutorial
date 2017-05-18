package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			
			//delete the student
//			System.out.println("Deleting student"+myStudent);
//			session.delete(myStudent);
			
			//deleting the student with id=2
			System.out.println("deleting the student with id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done");
			
		}finally{
			//close the session
			factory.close();
		}

	}

}
