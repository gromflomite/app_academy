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

import academy.model.pojo.Feedback;
import academy.model.pojo.User;

@WebServlet("/logout")
public class LogOutController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger("appAcademy-log");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Get user session
	HttpSession session = request.getSession();

	// Get user name and surname
	User userLoginDetails = (User) session.getAttribute("userLoginDetails");
	String userFullName = userLoginDetails.getName() + userLoginDetails.getSurname();

	try {

	    LOGGER.info("User logged out: " + userFullName);

	    // Invalidate user session
	    session.invalidate();

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("success", "<i class=\"fas fa-sign-out-alt\"></i> You are out, see you soon " + userLoginDetails.getName()));

	} catch (Exception e) {

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("warning", "We had a problem getting you out, sorry"));

	    LOGGER.error(e);

	} finally {

	    // Send the user back to all courses list using a redirect to clean the URL
	    response.sendRedirect(request.getContextPath() + "/courses");

	}

    }

}
