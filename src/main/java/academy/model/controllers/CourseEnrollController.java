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
import academy.model.pojo.Feedback;
import academy.model.pojo.User;

@WebServlet("/enroll")
public class CourseEnrollController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger("appAcademy-log");
    private static final CourseDAOImpl courseDAO = CourseDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Create objects
	Feedback feedback = new Feedback();

	// Get user session
	HttpSession session = request.getSession();

	// Get idStudent (from session)
	User userLoginDetails = (User) session.getAttribute("userLoginDetails");
	int idStudent = userLoginDetails.getId();

	// Get idCourse (from view)
	int idCourse = Integer.parseInt(request.getParameter("idCourseToEnroll"));

	try {

	    // Call DAO
	    courseDAO.enrollStudentInNewCourse(idStudent, idCourse);

	    // Create feedback
	    feedback = new Feedback("success", "You are now enrolled on the course");

	} catch (Exception e) {

	    // Create feedback
	    feedback = new Feedback("danger", "We had a problem trying to enroll you on the course, sorry");

	    LOGGER.error(e);

	} finally {

	    // Add feedbaack to session
	    session.setAttribute("feedback", feedback);

	    // Redirect flow to StudentController (not using getRequestDispatcher in order to keep the URL's clean)
	    response.sendRedirect(request.getContextPath() + "/student");

	}

    }

}
