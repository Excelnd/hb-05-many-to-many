package com.ihscode.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ihscode.hibernate.d.entity.Course;
import com.ihscode.hibernate.d.entity.Instructor;
import com.ihscode.hibernate.d.entity.InstructorDetail;
import com.ihscode.hibernate.d.entity.Review;
import com.ihscode.hibernate.d.entity.Student;

public class CreateCourseAndStudentsDm {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session  = factory.getCurrentSession();
		
		try {

			
			// start a transaction
			session.beginTransaction();		
			
			// create a course
			Course tempCourse = new Course("Doom - How To Score One Million Points");
			
			// save the course
			System.out.println("\nSaving the course ...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
			// create the students
			Student tempStudentOne = new Student("Jason", "Kal", "jason@ihscoding.com");
			Student tempStudentTwo = new Student("Jake", "gyl", "jake@ihscoding.com");			
			
			// add students to the course
			tempCourse.addStudent(tempStudentOne);
			tempCourse.addStudent(tempStudentTwo);
			
			// save the students
			System.out.println("\nSaving stduents ...");
			session.save(tempStudentOne);
			session.save(tempStudentTwo);
			System.out.println("Save students: " + tempCourse.getStudents());

			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!!");
			
		}
		finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
					

	}

}