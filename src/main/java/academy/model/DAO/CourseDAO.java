package academy.model.DAO;

import java.util.ArrayList;

import academy.model.pojo.Course;

public interface CourseDAO {

    ArrayList<Course> list(); // List all courses

    ArrayList<Course> listByProfessorId(int idProfessor); // List all courses for a given professor

    Course create(Course newCourse); // Create a new course

    boolean deleteCheckingUser(int idCourseToDelete, int idUser); // Delete a course checking (into the DB, not Java logic) that the course
								 // belongs to the professor

}
