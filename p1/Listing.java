/**
 * @author Tyler Procko
 * @date November 2017
 * 
 * Object class for listing items. Used for viewing listed items 
 * and searching for listed items. Has no effect on actually creating
 * a listing.
 * 
 */


package p1;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Listing 
{
	public String sellerID;
	public String sellerEmail;
	private ResultSet set;
	
	public String name;
	public String description;
	public String category;
	public String condition;
	public String price;
	
	public String specificInfo;
	
	/*
	public String bookPrefix;
	
    public String vehicleYear;
    public String vehicleMiles;
    public String vehicleBrand;
    public String vehicleType;
    
    public String furnitureCategory;
    public String furnitureRoomCategory;
    
    public String roomBathroomNumber;
    public String roomBedroomNumber;
    public String roomAddress;
    */
    
    public Listing(String sellerID, String name, String description, String category, String condition, String price, String specificInfo)
    {
    	// 				set2 = MainPage.sqlm.getStatement().executeQuery("SELECT email FROM users WHERE listings.sellerid = users.id");
    	//	            String sellerEmail = set2.getString("id");	
    	this.sellerID = sellerID;
    	/*
    	try 
    	{
			set = MainPage.sqlm.getStatement().executeQuery("SELECT email FROM users WHERE id = '"+sellerID+"'");
			sellerEmail = set.getString("email");
		} 
    	
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
		*/
    	
    	this.name = name;
    	this.description = description;
    	this.category = category;
    	this.condition = condition;
    	this.price = price;
    	this.specificInfo = specificInfo;
    }
    
    
    /**
     * Overriden toString method to display the item's info, instead
     * of the object locations in memory.
     */
    @Override
    public String toString()
    {
    	return name + " - $" + price + " - " + condition;
    }
}
