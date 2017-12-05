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


public class Listing implements Comparable<Object>
{
	public String title;
	public String description;
	public String category;
	public String condition;
	public String price;
	
	public String specificInfo;

    
    public Listing(String title, String description, String category, String condition, String price, String specificInfo)
    {
    	this.title = title;
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
    	return title + " - $" + price + " - " + condition;
    }
    
    
    /**
     * Allows for Listing items to be sorted by price.
     */
    @Override
    public int compareTo(Object o) 
    {
    	if (o == null)
    	{
    		return 0;
    	}
    	
    	else if (o instanceof Listing)
    	{
    		Listing other = (Listing)o;
    		int myPrice = Integer.parseInt(price);
    		int otherPrice = Integer.parseInt(other.price);
    		if (myPrice == otherPrice)
    		{
    			return 0;
    		}
    		
    		else if (myPrice > otherPrice)
    		{
    			return 1;
    		}
    		
    		else 
    		{
    			return -1;
    		}
    	}
    	
    	else
    	{
    		return 0;
    	}
    }
}