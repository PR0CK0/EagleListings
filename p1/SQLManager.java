/**
 * 
 * @author Tyler Procko
 * @date 10/2017 - 11/2017
 * 
 * Prepares a connection to the server and database specified within the default constructor.
 * 
 */


package p1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class SQLManager 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Prepares a connection to the database. */
	private Connection connection = null;
	
	/** Creates a statement in order to speak with the database. */
	private Statement statement = null;
	
	/** Instance of User class, which adds functionality to the database connection. */
	private User user;
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Default constructor for SQLManager.
	 * Sets up a connection to the specified database and creates a statement for use.<br>
	 * Additionally, creates a User instance that has further functionality; searching, logging in,
	 * creating new accounts, posting items, etc.
	 */
	public SQLManager() 
	{
		// The url for Carson Schulz's PHPMyAdmin-hosted server and DB
		String url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9203861?autoReconnect=true&useSSL=false";
		// Username and password provided by Carson Schulz to access the database
		String username = "sql9203861";
		String password = "K3YHk5fQKg";
		
		// Such try
		try
		{
			// Attempt connection to the database and create an SQL statement
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			
			// Sucess!
			System.out.println("Connection to database successful!");
			
			// Create new User instance
			user = new User();
		}
		
		// Very catch
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Database connection failed!");
		}
	}

	
	/**
	 * Getter
	 * 
	 * @return connection
	 */
	public Connection getConnection() 
	{
		return connection;
	}
	

	/**
	 * Getter
	 * 
	 * @return statement
	 */
	public Statement getStatement() 
	{
		return statement;
	}
	

	/**
	 * Getter
	 * 
	 * @return user
	 */
	public User getUser() 
	{
		return user;
	}
}
