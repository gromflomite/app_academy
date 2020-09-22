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

/**
 * Servlet implementation class CourseRemoveStudent
 */
@WebServlet("/leaveStudent")
public class CourseLeaveStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger("appAcademy-log");
    private static final CourseDAOImpl courseDAO = CourseDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Get user session
	HttpSession session = request.getSession();

	// New feedback object
	Feedback feedback = new Feedback();

	// Get userId to call DAO methods
	User userLoginDetails = (User) session.getAttribute("userLoginDetails");
	int idUser = userLoginDetails.getId();

	try {

	    // Get parameters from view
	    int idCourseToLeave = Integer.parseInt(request.getParameter("idCourse"));

	    if (courseDAO.removeStudentFromCourse(idCourseToLeave, idUser)) { // DAO method returns true if student is removed properly from the course

		feedback = new Feedback("success", "You have been removed from the course");

	    } else {

		feedback = new Feedback("danger", "Sorry, you can not be removed from the course");

	    }

	} catch (Exception e) {

	    LOGGER.error(e);

	} finally {

	    // Add feedbaack to session
	    session.setAttribute("feedback", feedback);

	    // Redirect flow to StudentController (not using getRequestDispatcher in order to keep the URL's clean)
	    response.sendRedirect(request.getContextPath() + "/student");

	}

    }

}
