package academy.model.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.model.DAO.implementations.CourseDAOImpl;
import academy.model.pojo.Course;

@WebServlet("/courses")
public class CourseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	CourseDAOImpl courseDAO = CourseDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern

	ArrayList<Course> dbCourses = courseDAO.list();
	
	request.setAttribute("dbCourses", dbCourses);
	request.getRequestDispatcher("/views/courses.jsp").forward(request, response);	

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doGet(request, response); // Using only doGet()
	
    }

}
