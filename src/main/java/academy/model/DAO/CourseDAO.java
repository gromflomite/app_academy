package academy.model.DAO;

import java.util.ArrayList;

import academy.model.pojo.Course;

public interface CourseDAO {

    ArrayList<Course> listAllCourses(); // List all courses

    ArrayList<Course> listCoursesByProfessorId(int idProfessor); // List all courses for a given professor ID

    Course createNewCourse(Course newCourse); // Create a new course

    boolean deleteCourseCheckingUser(int idCourseToDelete, int idUser); // Delete a course checking (into the DB, not Java logic) that the course belongs to the professor

    ArrayList<Course> listCoursesWhereStudentIsEnrolled(int idStudent); // List all courses where the student is enrolled

    ArrayList<Course> listCoursesAvailableForStudent(int idStudent); // List all courses available for the student

    void enrollStudentInNewCourse(int idStudent, int idCourse) throws Exception; // Enroll the student in a new course

}
