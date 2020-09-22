package academy.model.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.model.DAO.implementations.CourseDAOImpl;
import academy.model.pojo.User;

@WebServlet("/professor")
public class ProfessorController extends HttpServlet {

    private static final long serialVersionUID = 1L;    
    private static final CourseDAOImpl courseDAO = CourseDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Get session
	HttpSession session = request.getSession();

	// Get userId to call DAO method
	User userLoginDetails = (User) session.getAttribute("userLoginDetails");
	int professorId = userLoginDetails.getId();

	// Call DAO - Get the courses of this professor
	session.setAttribute("coursesByProfessorId", courseDAO.listCoursesByProfessorId(professorId));

	// Redirect flow to the view
	request.getRequestDispatcher("/views/private/professor.jsp").forward(request, response);

    }

}
