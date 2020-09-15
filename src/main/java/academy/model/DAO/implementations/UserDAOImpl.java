package academy.model.DAO.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import academy.model.ConnectionManager;
import academy.model.DAO.UserDAO;
import academy.model.pojo.User;

public class UserDAOImpl implements UserDAO {

    private final static Logger LOGGER = LogManager.getLogger("appAcademy-log");

    // SQL queries **************************************************
    private final String SQL_QUERY_CHECKUSER = " SELECT u.id AS 'id_user', u.name AS 'name_user', u.surname AS 'surname_user', u.role AS 'role_user', u.password AS 'password_user' FROM users AS u WHERE name = ? AND password = MD5(?); ";
    // End SQL queries **********************************************

    // Singleton pattern ********************************************
    private UserDAOImpl() {
	super();
    }

    public static UserDAOImpl INSTANCE = null;

    public static synchronized UserDAOImpl getInstance() {

	if (INSTANCE == null) {

	    INSTANCE = new UserDAOImpl();
	}

	LOGGER.info("Instantiated new DAO");

	return INSTANCE;
    }
    // End Singleton pattern ****************************************

    @Override
    public User exists(String userName, String userPassword) {	

	User userLoginDetails = new User();

	userLoginDetails = null; // In LoginController, we use a null to check if the entered login values are correct
	
	try (
		Connection dbConnection = ConnectionManager.getConnection(); 
		PreparedStatement preparedStatement = dbConnection.prepareStatement(SQL_QUERY_CHECKUSER)) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, userName);
	    preparedStatement.setString(2, userPassword);

	    // Execute the query	    
	    ResultSet dbResultSet = preparedStatement.executeQuery();
	    
	    LOGGER.debug("SQL query executed: " + preparedStatement);

	    if (dbResultSet.next()) {

		// Map user values
		userLoginDetails = new User();

		userLoginDetails.setId(dbResultSet.getInt("id_user"));
		userLoginDetails.setName(dbResultSet.getString("name_user"));
		userLoginDetails.setSurname(dbResultSet.getString("surname_user"));
		userLoginDetails.setRole(dbResultSet.getInt("role_user"));
		userLoginDetails.setPassword(dbResultSet.getString("password_user"));
	    }

	} catch (Exception e) {
	    LOGGER.error(e);
	    
	}

	return userLoginDetails;
    }

}
