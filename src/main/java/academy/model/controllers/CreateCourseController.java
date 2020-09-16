package academy.model.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import academy.model.DAO.implementations.CourseDAOImpl;
import academy.model.pojo.Course;
import academy.model.pojo.Feedback;
import academy.model.pojo.User;

@WebServlet("/createCourse")
public class CreateCourseController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger("appAcademy-log");
    private static final CourseDAOImpl courseDAO = CourseDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Create objects
	Course newCourse = new Course();
	Feedback feedback = new Feedback();

	// Get Java Session
	HttpSession session = request.getSession();

	// Get userId to call DAO methods
	User userLoginDetails = (User) session.getAttribute("userLoginDetails");
	int courseIdProfessor = userLoginDetails.getId();

	// Get values from the view (professor.jsp)
	try {

	    String courseName = request.getParameter("courseName");
	    String courseIdentifier = request.getParameter("courseIdentifier");
	    int courseHours = Integer.parseInt(request.getParameter("courseHours"));

	    // Set the values to course object
	    newCourse.setName(courseName);
	    newCourse.setIdentifier(courseIdentifier);
	    newCourse.setHours(courseHours);
	    newCourse.setId_professor_course(courseIdProfessor);

	    // Call DAO
	    courseDAO.create(newCourse);

	    // Create feedback
	    feedback = new Feedback("success", "Course properly created");

	} catch (Exception e) {
	    LOGGER.error(e);

	} finally {

	    // Call DAO to refresh in session the existing courses for this professor
	    session.setAttribute("coursesByProfessorId", courseDAO.listByProfessorId(courseIdProfessor));

	    session.setAttribute("feedback", feedback);

	    // Calling the form view passing the feeback and the object
	    request.getRequestDispatcher("/views/private/professor.jsp").forward(request, response);

	}

    }

}
