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
 * Servlet implementation class DeleteCourseController
 */
@WebServlet("/deleteCourse")
public class CourseDeleteController extends HttpServlet {

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
	int userId = userLoginDetails.getId();

	try {

	    // Get parameters from view
	    int idCourseToDelete = Integer.parseInt(request.getParameter("idCourseToDelete"));

	    // Call DAO
	    if (courseDAO.deleteCourseCheckingUser(idCourseToDelete, userId)) { // DAO method returns true if course is deleted ok

		feedback = new Feedback("success", "The course was properly deleted");

	    } else {

		feedback = new Feedback("danger", "The course is not deleted (check there are not students enrolled)");

	    }

	} catch (Exception e) {
	    LOGGER.error(e);

	} finally {

	    // Put the created feedback into response to view
	    request.setAttribute("feedback", feedback);

	    // Redirect flow to ProfessorController (not using getRequestDispatcher in order to keep the URL's clean)
	    response.sendRedirect(request.getContextPath() + "/professor");

	}

    }

}
