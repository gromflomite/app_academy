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

@WebServlet("/student")
public class StudentController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final CourseDAOImpl courseDAO = CourseDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Get session
	HttpSession session = request.getSession();

	// Get userId to call DAO's method
	User userLoginDetails = (User) session.getAttribute("userLoginDetails");
	int idStudent = userLoginDetails.getId();

	// Call DAO - Get the courses where this student is enrolled
	session.setAttribute("coursesStudentEnrolled", courseDAO.listCoursesWhereStudentIsEnrolled(idStudent));

	// Call DAO - Get the courses available for this student
	session.setAttribute("coursesStudentAvailable", courseDAO.listCoursesAvailableForStudent(idStudent));

	// Redirect flow to the view
	request.getRequestDispatcher("/views/private/student.jsp").forward(request, response);

    }

}
