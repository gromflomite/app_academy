package academy.model.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import academy.model.DAO.implementations.CourseDAOImpl;
import academy.model.pojo.Course;

@WebServlet("/courses")
public class CourseController extends HttpServlet {

    private final static long serialVersionUID = 1L;
    private final static Logger LOGGER = LogManager.getLogger("appAcademy-log");
    private final static CourseDAOImpl courseDAO = CourseDAOImpl.getInstance(); // Instantiate DAO via Singleton pattern

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	LOGGER.info("doGet() method called");
	
	ArrayList<Course> dbCourses = courseDAO.list();

	request.setAttribute("dbCourses", dbCourses);
	request.getRequestDispatcher("/views/courses.jsp").forward(request, response);
	
	LOGGER.debug("getRequestDispatcher(\"/views/courses.jsp\") called - Values forwarded to view: " + dbCourses);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doGet(request, response); // Using only doGet()

    }

}
