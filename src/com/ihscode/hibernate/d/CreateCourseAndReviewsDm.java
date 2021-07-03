package com.ihscode.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ihscode.hibernate.d.entity.Course;
import com.ihscode.hibernate.d.entity.Instructor;
import com.ihscode.hibernate.d.entity.InstructorDetail;
import com.ihscode.hibernate.d.entity.Review;

public class CreateCourseAndReviewsDm {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session
		Session session  = factory.getCurrentSession();
		
		try {

			
			// start a transaction
			session.beginTransaction();		
			
			// create a course
			Course tempCourse = new Course("Doom - How To Score One Million Points");
			
			// add some reviews
			tempCourse.addReview(new Review("Greate Course ... loved it!"));
			tempCourse.addReview(new Review("What a Dumb course, you are an idiot!"));
			tempCourse.addReview(new Review("Cool nice course, well done"));
			
			// save the course... and leverage the cascade all
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse); 
			
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