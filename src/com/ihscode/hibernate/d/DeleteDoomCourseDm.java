package com.ihscode.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ihscode.hibernate.d.entity.Course;
import com.ihscode.hibernate.d.entity.Instructor;
import com.ihscode.hibernate.d.entity.InstructorDetail;
import com.ihscode.hibernate.d.entity.Review;
import com.ihscode.hibernate.d.entity.Student;

public class DeleteDoomCourseDm {

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
			
			// get Doom course from database
			int courseId = 10;
			Course tempCourse = session.get(Course.class, courseId);
			
			// delete the course
			System.out.println("Deleting course: " + tempCourse);
			
			session.delete(tempCourse);
			
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