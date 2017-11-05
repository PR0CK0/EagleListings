/**
 * 
 * @author Tyler Procko
 * @date 10/2017 - 11/2017
 *  
 *  
 * User class. Defines functionality for logging in and out, creating a new account,
 * posting items and searching items.
 * 
 */


package p1;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */

	/** Name stored when a user is logged in */
	private String loggedInName;
	
	/** Email stored when a user is logged in */
	private String loggedInEmail;
	
	/** Password stored when a user is logged in */
	private String loggedInPassword;
	
	/** Picture URI stored when a user is logged in */
	private String pictureURI;
	
	/** Number of good ratings stored when a user is logged in */
	private int ratingGood;
	
	/** Number of bad ratings stored when a user is logged in */
	private int ratingBad;
	
	/** Number of items sold stored when a user is logged in */
	private int itemsSold;
	
	/** Timestamp stored when a user is logged in */
	private String regDate;
	
	/** Variable storing whether a user is logged in or not */
	private boolean isLoggedIn;
	
	/** Variable storing the user's rank */
	private boolean isAdmin;
	
	/** ResultSet for querying info from the database */
	private ResultSet set;
	
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */

	/**
	 * Default constructor for User
	 */
	public User()
	{
		// The user created is empty (created when the application is started)
		// So initially, no one is logged in
		isLoggedIn = false;
	}
	
	
	/**
	 * Test method - called in main
	 */
	public void test()
	{
		// Such try
		try 
		{
			set = MainPage.sqlm.getStatement().executeQuery("SELECT name FROM users");
			
			while(set.next())
			{
				String name = set.getString("name");
				System.out.println(name);
			}
		}
		
		// Very catch
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find passwords from database!");
		}
	}
	
	
	/**
	 * Takes in an input email and password and attempts to match them to a row in the SQL database,
	 * through the class SQLManager
	 * 
	 * @param email
	 * @param password
	 */
	public void login(String email, String password)
	{
		// Set everything to null values before attempting login
		logout();
		
		// Such try
		try 
		{
			set = MainPage.sqlm.getStatement().executeQuery("SELECT name, email, password, admin FROM users");
		
			while(set.next())
			{
				// If the input email AND password match ANY email/password combo from the DB exactly, user is logged in
				if ((email.equals(set.getString("email"))) && (password.equals(set.getString("password"))))
				{
					// Store the user's name, email and password
					loggedInName = set.getString("name");
					loggedInEmail = set.getString("email");
					loggedInPassword = set.getString("password");
				
					// Set the logged in value so the application's NavBar can update appropriately
					isLoggedIn = true;
					
					// Confirmatory login message
					System.out.println("Login successful, " + loggedInName + "!");
					
					// Admin or not
					if (set.getString("admin").equals("true"))
					{
						isAdmin = true;
					}
						
					else
					{
						isAdmin = false;
					}
				}
			}
		}
		
		// Very catch
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find passwords from database!");
		}
	}
	
	
	/**
	 * Method to log the user out.
	 * Simply sets all Java-stored user-info to null or default values.<br>
	 * Does not touch the database in any way.
	 */
	public void logout()
	{
		loggedInName = null;
	    loggedInEmail = null;
	    loggedInPassword = null;
	    pictureURI = null;
		ratingGood = 0;
		ratingBad = 0;
		itemsSold = 0;
		regDate = null; 
		
		isLoggedIn = false;
	}
	
	
	/**
	 * Provides functionality for creating a new user and adding it to the connected database.<br>
	 * There is no need to include a timestamp on the Java end, as the PHPMyAdmin DB service inputs
	 * it automatically for every new row added.
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @param pictureURI
	 */
	public void createNewUser(String name, String email, String password)
	{
		// Set everything to null values before attempting login
		logout();
		
		// Set new id to use initially to 0
		int maxid = 0;
		
		// Such try
		try 
		{
			// Set a new id for the user by iterating through all of them
			// Basically finding the biggest id number in the table and adding one to it
			// in order to form the new user's id
			// This is important as it allows items to be added even if the table ids are not sorted
			set = MainPage.sqlm.getStatement().executeQuery("SELECT id FROM users");
			while(set.next())
			{
				// Set current id to the one being iterated over
				int curid = set.getInt("id");
				
				// If current is greater than max, set new max
				if (curid > maxid)
				{
					maxid = curid;
				}
			}
			// New listing id by adding one to max
			maxid++;
			
			// TODO update GUI
			// Iterate over every email in the database and check if the user-input email is already in use
			set = MainPage.sqlm.getStatement().executeQuery("SELECT email FROM users");
			while(set.next())
			{
				// If the input email is already in use, handle it
				if (email.equals(set.getString("email")))
				{
					System.out.println("Email is already in use!");
					return;
				}
			}
			
			// TODO 
			// image selection (right now it's default)
			pictureURI = "../img/avatars/default.png";
			
			// If all is well, create a new user
			// Format is a little strange for entering non-concrete values (i.e. variables) into the database, but it's not terrible
			// For String variables: '"+string+"'
			// For int variables: "+int+"
			// For bools: 'true' or 'false'
			// For null: null
			MainPage.sqlm.getStatement().executeUpdate("INSERT INTO users (id, name, email, password, picture, ratinggood, ratingbad, itemssold, verified, admin) "
	        + "VALUES ("+maxid+", '"+name+"', '"+email+"', '"+password+"', '"+pictureURI+"', 0, 0, 0, 'false', 'false')");
		} 
		
		// Very catch
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not create new user!");
		}
	}
	
	
	//TODO
	/**
	 * Allows a user to create a new item listing.<br>
	 */
	//test
	public void createListing()
	//public void createListing(String title, String description, int price, String category, String imageURI, String productcondition, 
    //String courseprefix, int year, String model, int miles, int bathroom, int bedroom)
	{	
		// Set a new id to 0
		int maxid = 0;
		
		// Such try
		try 
		{
			// Set a new id for the user by iterating through all of them
			// Basically finding the biggest id number in the table and adding one to it
			// in order to form the new listing id
			// This is important as it allows items to be added even if the table ids are not sorted
			set = MainPage.sqlm.getStatement().executeQuery("SELECT id FROM listings");
			while(set.next())
			{
				// Set current id to the one being iterated over
				int curid = set.getInt("id");
				
				// If current is greater than max, set new max
				if (curid > maxid)
				{
					maxid = curid;
				}
			}
			// New listing id by adding one to max
			maxid++;
						
			// test
			MainPage.sqlm.getStatement().executeUpdate("INSERT INTO listings (id, sellerid, name, category, description, listingcondition, price, vehicleyear, vehiclemiles, vehicletype, vehiclebrand, bookprefix, furncategory, furnroomcategory, roombednum, roombathnum, roomaddress) "
			+ "VALUES ("+maxid+", 1, 'Something legal', 'Furniture', 'Totally not drugs (its drugs)', 'New', 69, null, null, null, null, null, 'comfort', 'bedroom', null, null, null)");
		} 
		
		// Very catch
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Could not create a new listing in the database!");
		}
	}
	
	
	/**
	 * Functionality for user searching through the search bar in the NavBar class.
	 * Uses SQL commands to return any "like" results, based on user input.
	 * 
	 * @param userSearch
	 */
	public void search(String userSearch)
	{
		try 
		{
			// Execute a query, grabbing all listings where the title is similar to the user's entered search term
			set = MainPage.sqlm.getStatement().executeQuery("SELECT name FROM listings WHERE name LIKE '%"+userSearch+"%'");
			
			System.out.println("-----------------");
			
			while (set.next())
			{
				System.out.println(set.getString("name"));
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Getter
	 * 
	 * @return isLoggedIn
	 */
	public boolean isLoggedIn() 
	{
		return isLoggedIn;
	}
	
	
	/**
	 * Setter
	 * 
	 * @param isLoggedIn
	 */
	public void setLoggedIn(boolean isLoggedIn)
	{
		this.isLoggedIn = isLoggedIn;
	}
	
	
	/**
	 * Getter
	 * 
	 * @return isAdmin
	 */
	public boolean isAdmin()
	{
		return isAdmin;
	}
	
	
	/** 
	 * Getter
	 * 
	 * @return loggedInEmail
	 */
	public String getLoggedInEmail() 
	{
		return loggedInEmail;
	}


	/**
	 * Getter
	 * 
	 * @return loggedInName
	 */
	public String getName() 
	{
		return loggedInName;
	}


	/**
	 * Getter
	 * 
	 * @return loggedInPassword
	 */
	public String getLoggedInPassword() 
	{
		return loggedInPassword;
	}
	
	
	/**
	 * Getter for the user's profile picture URI <br>
	 * In the URL/URI world, forward and backslash count as the same thing, apparently
	 * 
	 * @return pictureURI
	 */
	public String getPictureURI() 
	{
		return pictureURI;
	}


	/**
	 * Getter
	 * 
	 * @return ratingGood
	 */
	public int getRatingGood() 
	{
		return ratingGood;
	}


	/**
	 * Getter
	 * 
	 * @return ratingBad
	 */
	public int getRatingBad() 
	{
		return ratingBad;
	}


	/**
	 * Getter
	 * 
	 * @return itemsSold
	 */
	public int getItemsSold()
	{
		return itemsSold;
	}


	/**
	 * Getter
	 * 
	 * @return regDate
	 */
	public String getRegDate() 
	{
		return regDate;
	}
}
