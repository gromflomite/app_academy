package academy.model.DAO.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import academy.model.ConnectionManager;
import academy.model.DAO.CourseDAO;
import academy.model.pojo.Course;
import academy.model.pojo.User;

public class CourseDAOImpl implements CourseDAO {
    
    private final static Logger LOGGER = LogManager.getLogger("appAcademy-log");

    // SQL queries **************************************************
    private final String SQL_QUERY_GETALL = " SELECT c.id AS 'id_course', c.name AS 'name_course', c.identifier AS 'identifier_course', c.hours AS 'hour_course', c.id_professor AS 'id_professor_course', u.id AS 'id_professor', u.name AS 'name_professor', u.surname AS 'surname_professor', u.role AS 'user_role' FROM courses AS c, users AS u WHERE c.id_professor = u.id; ";
    // End SQL queries **********************************************

    // Singleton pattern ********************************************
    private CourseDAOImpl() {
	super();
    }

    public static CourseDAOImpl INSTANCE = null;

    public static synchronized CourseDAOImpl getInstance() {

	if (INSTANCE == null) {
	    
	    INSTANCE = new CourseDAOImpl();
	}

	LOGGER.info("Instantiated new DAO");
	
	return INSTANCE;	
    }
    // End Singleton pattern ****************************************

    @Override
    public ArrayList<Course> list() {

	ArrayList<Course> courses = new ArrayList<Course>();

	try (
		Connection dbConnection = ConnectionManager.getConnection(); 
		PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_GETALL);		
		ResultSet resultSet = preparedStatement.executeQuery()) {
	    
	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    while (resultSet.next()) {

		// Map course values
		Course dbCourse = new Course();
		dbCourse.setId(resultSet.getInt("id_course"));
		dbCourse.setName(resultSet.getString("name_course"));
		dbCourse.setIdentifier(resultSet.getString("identifier_course"));
		dbCourse.setHours(resultSet.getInt("hour_course"));
		dbCourse.setId_professor_course(resultSet.getInt("id_professor_course"));

		// Map professor values
		User dbProfessor = new User();
		dbProfessor.setId(resultSet.getInt("id_professor"));
		dbProfessor.setName(resultSet.getString("name_professor"));
		dbProfessor.setSurname(resultSet.getString("surname_professor"));
		dbProfessor.setRole(resultSet.getInt("user_role"));

		// Set professor values to course objet
		dbCourse.setProfessor(dbProfessor);

		// Add all values to course ArrayList
		courses.add(dbCourse);
		
	    }

	} catch (Exception e) {
	    LOGGER.error(e);
	    
	} 
	
	return courses;
    }

}
