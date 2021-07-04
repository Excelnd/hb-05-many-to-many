package com.ihscode.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ihscode.hibernate.d.entity.Course;
import com.ihscode.hibernate.d.entity.Instructor;
import com.ihscode.hibernate.d.entity.InstructorDetail;
import com.ihscode.hibernate.d.entity.Review;
import com.ihscode.hibernate.d.entity.Student;

public class GetCoursesForJakeDm {

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
			
			// get the students Jake from database
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded stduent: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());
			
			// create more courses
			Course tempCourse1 = new Course("Rubik's Cube - How to Speed UP Cube");
			Course tempCourse2 = new Course("Atari 2550 - Game Development");
			
			// add student to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);			
			
			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
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