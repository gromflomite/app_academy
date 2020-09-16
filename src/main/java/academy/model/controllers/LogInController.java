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
import academy.model.DAO.implementations.UserDAOImpl;
import academy.model.pojo.Feedback;
import academy.model.pojo.User;

@WebServlet("/login")
public class LogInController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger("appAcademy-log");
    private static final UserDAOImpl userDAO = UserDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern
    private static final CourseDAOImpl courseDAO = CourseDAOImpl.getInstance();

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
	    request.setAttribute("feedback", new Feedback("success", "Welcome again!!"));

	    if (userLoginDetails.getRole() == User.ROL_STUDENT) { // Student role
		request.getRequestDispatcher("/views/private/student.jsp").forward(request, response);

		LOGGER.info("Student logged: " + userLoginDetails.getName());

	    } else { // Professor role
		
		// Get the courses of this professor
		int idProfessor = userLoginDetails.getId();
		
		session.setAttribute("coursesByProfessorId", courseDAO.listByProfessorId(idProfessor));
		
		request.getRequestDispatcher("/views/private/professor.jsp").forward(request, response);

		LOGGER.info("Professor logged: " + userLoginDetails.getName());

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
