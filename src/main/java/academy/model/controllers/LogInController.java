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

import academy.model.DAO.implementations.UserDAOImpl;
import academy.model.pojo.Feedback;
import academy.model.pojo.User;

@WebServlet("/login")
public class LogInController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger("appAcademy-log");
    private static final UserDAOImpl userDAO = UserDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	LOGGER.info("doPost() method called");

	// Get the values from the view (login.jsp)
	String userName = request.getParameter("userName");
	String userPassword = request.getParameter("userPassword");

	LOGGER.debug("Login values received from view (login.jsp): " + userName + " - " + userPassword);

	// Get session
	HttpSession session = request.getSession();

	// Call the DAO
	User userLoginDetails = userDAO.exists(userName, userPassword);

	LOGGER.debug("Called DAO - Received values: " + userLoginDetails);

	if (userLoginDetails != null) {

	    // Set attribute to session
	    session.setAttribute("userLoginDetails", userLoginDetails);

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("success", "<i class=\"fas fa-hand-spock\"></i> Welcome again, " + userLoginDetails.getName()));

	    if (userLoginDetails.getRole() == User.ROL_STUDENT) { // Student role

		// Redirect flow to StudentController (not using getRequestDispatcher in order to keep the URL's clean)
		response.sendRedirect(request.getContextPath() + "/student");

		LOGGER.info("Student logged: " + userLoginDetails.getName() + " " + userLoginDetails.getSurname());

	    } else { // Professor role

		// Redirect flow to ProfessorController (not using getRequestDispatcher in order to keep the URL's clean)
		response.sendRedirect(request.getContextPath() + "/professor");

		LOGGER.info("Professor logged: " + userLoginDetails.getName() + " " + userLoginDetails.getSurname());

	    }

	} else { // Entered incorrect login values

	    LOGGER.info("Incorrect login values entered");

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("warning", "Username or password not correct!!"));

	    // Invalidate session
	    session.invalidate();

	    // Go back to loging page
	    request.getRequestDispatcher("/views/login.jsp").forward(request, response);

	}

    }

}
