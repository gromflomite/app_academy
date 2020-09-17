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
    private final String SQL_QUERY_GET_ALL_COURSES = " SELECT c.id AS 'id_course', c.name AS 'name_course', c.identifier AS 'identifier_course', c.hours AS 'hour_course', c.id_professor AS 'id_professor_course', u.id AS 'id_professor', u.name AS 'name_professor', u.surname AS 'surname_professor', u.role AS 'user_role' FROM courses AS c, users AS u WHERE c.id_professor = u.id; ";
    private final String SQL_QUERY_GET_COURSES_BY_PROFESSOR_ID = " SELECT c.id AS 'id_course', c.hours AS 'hour_course', c.name AS 'name_course', c.id_professor AS 'id_professor_course', c.identifier AS 'identifier_course', u.id AS 'id_professor', u.name AS 'name_professor', u.surname AS 'surname_professor' FROM courses AS c, users AS u WHERE c.id_professor = u.id AND c.id_professor = ?; ";
    private final String SQL_QUERY_CREATE_COURSE = " INSERT INTO courses (name, identifier, hours, id_professor) VALUES (?, ?, ?, ?); ";
    private final String SQL_QUERY_DELETE_COURSE = " DELETE FROM courses WHERE courses.id = ? AND courses.id_professor = ?; ";
    private final String SQL_QUERY_GET_STUDENT_ENROLLED_COURSES = " SELECT c.id AS 'id_course', c.name AS 'name_course', c.identifier AS 'identifier_course', c.hours AS 'hours_course', u.name AS 'name_professor', u.surname AS 'surname_professor' FROM students_course AS sc, courses AS c, users AS u WHERE sc.id_student = ? AND sc.id_course = c.id AND c.id_professor = u.id ORDER BY c.id ASC LIMIT 25; ";
    private final String SQL_QUERY_GET_STUDENT_AVAILABLE_COURSES = " SELECT c.id AS 'id_course', c.name AS 'name_course', c.identifier AS 'identifier_course', c.hours AS 'hours_course', u.name AS 'name_professor', u.surname AS 'surname_professor' FROM courses c, users u WHERE c.id_professor = u.id AND c.id_professor = u.id AND c.id NOT IN (SELECT sc.id_course FROM students_course sc WHERE sc.id_student = ?) ORDER BY c.id LIMIT 25; ";
    private final String SQL_QUERY_ENROLL_STUDENT_COURSE = " INSERT INTO students_course (id_student, id_course) VALUES (?, ?); ";
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

    // ----------------------------------------------------------

    @Override
    public ArrayList<Course> list() {

	ArrayList<Course> courses = new ArrayList<Course>();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_GET_ALL_COURSES); ResultSet dbResultSet = preparedStatement.executeQuery()) {

	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    while (dbResultSet.next()) {

		// Map course values
		Course dbCourse = new Course();
		dbCourse.setId(dbResultSet.getInt("id_course"));
		dbCourse.setName(dbResultSet.getString("name_course"));
		dbCourse.setIdentifier(dbResultSet.getString("identifier_course"));
		dbCourse.setHours(dbResultSet.getInt("hour_course"));
		dbCourse.setId_professor_course(dbResultSet.getInt("id_professor_course"));

		// Map professor values
		User dbProfessor = new User();
		dbProfessor.setId(dbResultSet.getInt("id_professor"));
		dbProfessor.setName(dbResultSet.getString("name_professor"));
		dbProfessor.setSurname(dbResultSet.getString("surname_professor"));
		dbProfessor.setRole(dbResultSet.getInt("user_role"));

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

    // ----------------------------------------------------------

    @Override
    public ArrayList<Course> listByProfessorId(int idProfessor) {

	ArrayList<Course> coursesByProfessorId = new ArrayList<Course>();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_GET_COURSES_BY_PROFESSOR_ID)) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, idProfessor);

	    // Execute the query
	    ResultSet dbResultSet = preparedStatement.executeQuery();

	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    while (dbResultSet.next()) {

		// Map course values
		Course dbCourse = new Course();
		dbCourse.setId(dbResultSet.getInt("id_course"));
		dbCourse.setHours(dbResultSet.getInt("hour_course"));
		dbCourse.setName(dbResultSet.getString("name_course"));
		dbCourse.setIdentifier(dbResultSet.getString("identifier_course"));
		dbCourse.setId_professor_course(dbResultSet.getInt("id_professor_course"));

		// Add all values to course ArrayList
		coursesByProfessorId.add(dbCourse);

	    }

	} catch (Exception e) {
	    LOGGER.error(e);

	}

	return coursesByProfessorId;
    }

    // ----------------------------------------------------------

    @Override
    public Course create(Course newCourse) {

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_CREATE_COURSE);) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, newCourse.getName());
	    preparedStatement.setString(2, newCourse.getIdentifier());
	    preparedStatement.setInt(3, newCourse.getHours());
	    preparedStatement.setInt(4, newCourse.getId_professor_course());

	    // Executing the query
	    preparedStatement.executeUpdate();

	    LOGGER.debug("SQL query executed: " + preparedStatement);

	} catch (Exception e) {
	    LOGGER.error(e);

	}

	return newCourse;
    }

    // ----------------------------------------------------------

    @Override
    public boolean deleteCheckingUser(int idCourseToDelete, int idUser) {

	boolean courseDeletedOk = false;

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_DELETE_COURSE);) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, idCourseToDelete);
	    preparedStatement.setInt(2, idUser);

	    // Execute que SQL query and get the # of affected rows (value returned by executeUpdate())
	    int dbUpdatedRows = preparedStatement.executeUpdate();

	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    if (dbUpdatedRows == 1) { // Course properly deleted

		courseDeletedOk = true;

	    }

	} catch (Exception e) {
	    LOGGER.error(e);
	}

	return courseDeletedOk;

    }

    // ----------------------------------------------------------

    @Override
    public ArrayList<Course> studentEnrolled(int idStudent) {

	ArrayList<Course> coursesStudentEnrolled = new ArrayList<Course>();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_GET_STUDENT_ENROLLED_COURSES);) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, idStudent);

	    // Execute the query
	    ResultSet dbResultSet = preparedStatement.executeQuery();

	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    while (dbResultSet.next()) {

		// Map course values
		Course dbCourse = new Course();
		dbCourse.setId(dbResultSet.getInt("id_course"));
		dbCourse.setName(dbResultSet.getString("name_course"));
		dbCourse.setIdentifier(dbResultSet.getString("identifier_course"));
		dbCourse.setHours(dbResultSet.getInt("hours_course"));

		// Map professor values
		User dbProfessor = new User();
		dbProfessor.setName(dbResultSet.getString("name_professor"));
		dbProfessor.setSurname(dbResultSet.getString("surname_professor"));

		// Set professor values to course objet
		dbCourse.setProfessor(dbProfessor);

		// Add all values to course ArrayList
		coursesStudentEnrolled.add(dbCourse);

	    }

	} catch (Exception e) {
	    LOGGER.error(e);
	}

	return coursesStudentEnrolled;
    }

    // ----------------------------------------------------------

    @Override
    public ArrayList<Course> studentAvailable(int idStudent) {

	ArrayList<Course> coursesStudentAvailable = new ArrayList<Course>();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_GET_STUDENT_AVAILABLE_COURSES);) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, idStudent);

	    // Execute the query
	    ResultSet dbResultSet = preparedStatement.executeQuery();

	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    while (dbResultSet.next()) {

		// Map course values
		Course dbCourse = new Course();
		dbCourse.setId(dbResultSet.getInt("id_course"));
		dbCourse.setName(dbResultSet.getString("name_course"));
		dbCourse.setIdentifier(dbResultSet.getString("identifier_course"));
		dbCourse.setHours(dbResultSet.getInt("hours_course"));

		// Map professor values
		User dbProfessor = new User();
		dbProfessor.setName(dbResultSet.getString("name_professor"));
		dbProfessor.setSurname(dbResultSet.getString("surname_professor"));

		// Set professor values to course objet
		dbCourse.setProfessor(dbProfessor);

		// Add all values to course ArrayList
		coursesStudentAvailable.add(dbCourse);

	    }

	} catch (Exception e) {
	    LOGGER.error(e);

	}

	return coursesStudentAvailable;
    }

    // ----------------------------------------------------------

    @Override
    public void enrollStudent(int idStudent, int idCourse) throws Exception {

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_ENROLL_STUDENT_COURSE);) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, idStudent);
	    preparedStatement.setInt(2, idCourse);

	    // Execute que SQL query and get the # of affected rows (value returned by executeUpdate())
	    int dbUpdatedRows = preparedStatement.executeUpdate();

	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    if (dbUpdatedRows != 1) {

		throw new Exception("ERROR: The new registry has been not created");

	    }

	} catch (Exception e) {
	    LOGGER.error(e);

	}

    }

    // ----------------------------------------------------------

}
