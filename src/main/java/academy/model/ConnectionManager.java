package academy.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {

	Connection connection = null;

	Class.forName("com.mysql.cj.jdbc.Driver");

	InitialContext initialContext = new InitialContext();
	
	Context envCtx = (Context) initialContext.lookup("java:comp/env");
	
	DataSource dataSource = (DataSource) envCtx.lookup("jdbc/academy");

	connection = dataSource.getConnection();

	return connection;
    };

}
