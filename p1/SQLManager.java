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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class SQLManager 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Prepares a connection to the database. */
	private Connection connection = null;
	
	/** Creates a statement in order to speak with the database. */
	private Statement statement = null;
	
	// TODO
    // Use this guy instead?
	// private PreparedStatement preparedstmt;
	
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
	 * Limits user input in a textfield to a specified length.
	 * Very important, as the DB columns have specified sizes.
	 * 
	 * @param tf
	 * @param length
	 */
	public static void tfLengthLimiter(TextField tf, int length)
	{
		tf.textProperty().addListener((var, oldText, newText) -> 
		{
			if (newText.length()-1 >= length)
			{
				tf.setText(oldText);
			}
     	});
	}
	
	
	/**
	 * Prevents SQL injection by limiting user input in JavaFX textfields.
	 * Allows for a regular limitation or numbers only.
	 * 
	 * @param tf
	 */
	public static void tfTextValidator(TextField tf, boolean numbersOnly)
	{
		// Numbers only
		if (numbersOnly)
		{
			// Listener bound to the textfield
			tf.textProperty().addListener((var, oldText, newText) ->
			{
				if(newText.length() > 0)
				{
					// Essentially checking the last character (what the user is inputting REAL-TIME),
					// to see if it matches the regex 0-9
					// If the character entered is bad, reset the textfield to the previous
					if (!(newText.substring(newText.length() - 1)).matches("[0-9]"))
					{
						tf.setText(oldText);
					}
				}
			});
		}
		
		// Regular limitation
		else
		{
			// Listener bound to the textfield
			tf.textProperty().addListener((var, oldText, newText) ->
			{
				if(newText.length() > 0)
				{
					// Essentially checking the last character (what the user is inputting REAL-TIME),
					// to see if it matches the regex A-Z, a-z, 0-9, periods, at symbols (for emails) and exclamation points
					if (!(newText.substring(newText.length() - 1)).matches("[a-z A-Z 0-9 . @ !]"))
					{
						tf.setText(oldText);
					}
				}
			});
		}
	}
	
	
	/**
	 * Prevents SQL injection by limiting user input in JavaFX textareas.
	 * 
	 * @param ta
	 */
	public static void taTextValidator(TextArea ta)
	{
		// Listener bound to the textfield
		ta.textProperty().addListener((var, oldText, newText) ->
		{
			if(newText.length() > 0)
			{
				// Essentially checking the last character (what the user is inputting REAL-TIME),
				// to see if it matches the regex A-Z, a-z, 0-9 and periods
				// If the character entered is bad, reset the textfield to the previous
				if (!(newText.substring(newText.length() - 1)).matches("[a-z A-Z 0-9 .]"))
				{
					ta.setText(oldText);
				}
			}
		});
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
