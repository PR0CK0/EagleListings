/**
 * 
 * @author TP
 *
 */
package p1;

/*
public static void tfTextValidator(TextField tf)
{
	tf.textProperty().addListener((var, oldText, newText) ->
	{
		if(newText.length() > 0)
		{
			// Whats the last character of the new text (basically what we added)
			// newText.length() - 1 is grabbing the index of the last character (count starts from 0,
			// but length starts at 1, so subtract 1)
			switch(newText.charAt(newText.length() - 1))
			{
			// Don't do anything for the accepted alphabet
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				break;
			default:
				// Otherwise reset the text field to the old text
				tf.setText(oldText);
			}
		}
	});
}
*/



/*
private void gameLoop()
{
	timeline = new Timeline();
	// Set the game's timeline to be indefinite
	timeline.setCycleCount(Timeline.INDEFINITE);
	// Repeat the timeline cycle every 16ms, which equates to 1000ms / 16ms =~ 60fps
	// Every time it's enabled, update the navbar
	timeline.getKeyFrames().add(new KeyFrame(Duration.millis(16), e -> nav.setDisables(true, sqlm.getUser().isLoggedIn(), true)));
	timeline.play();
}
*/



/*
	String url = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9202636?autoReconnect=true&useSSL=false";
	String username = "sql9202636";
	String password = "2duFdHsyG5";
*/



/*
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
*/



/*
	//MainPage.instance.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.instance.sqlm.getUser().getIsLoggedInProperty().get());
	//MainPage.instance.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.instance.sqlm.getUser().getIsLoggedInProperty().get());
*/


/*
 /**
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
			
			tfRegexChecker(tf, "[0-9]");
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
			
			tfRegexChecker(tf, "[a-z A-Z 0-9 . @ !]");
		}
	}
*/

/*
 public String search(String userSearch)
	{
		String searchResults = "";
		try 
		{
			// Execute a query, grabbing all listings where the title is similar to the user's entered search term
			set = MainPage.sqlm.getStatement().executeQuery("SELECT name, category FROM listings WHERE name LIKE '%"+userSearch+"%'");

			while (set.next())
			{
				searchResults += set.getString("name") + ", " + set.getString("category") + "\n";
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return searchResults;
	}
*/

/*
	private void setUpGridPanes()
	{	
		// Set up the gridpanes
		gpBooks.add(new ImageView(new Image("p1/img/books.png", 100, 100, true, true)), 0, 0);
		gpBooks.add(new ImageView(new Image("p1/img/books.png", 100, 100, true, true)), 1, 0);
		gpBooks.add(new ImageView(new Image("p1/img/books.png", 100, 100, true, true)), 2, 0);
		gpBooks.add(new ImageView(new Image("p1/img/books.png", 100, 100, true, true)), 3, 0);
		gpBooks.add(new ImageView(new Image("p1/img/books.png", 100, 100, true, true)), 4, 0);
		gpBooks.setHgap(10);
		gpBooks.setPadding(new Insets(10, 0, 10, 0));
		gpBooks.setAlignment(Pos.CENTER);
		
		gpVehicles.add(new ImageView(new Image("p1/img/vehicles.png", 100, 100, true, true)), 0, 0);
		gpVehicles.add(new ImageView(new Image("p1/img/vehicles.png", 100, 100, true, true)), 1, 0);
		gpVehicles.add(new ImageView(new Image("p1/img/vehicles.png", 100, 100, true, true)), 2, 0);
		gpVehicles.add(new ImageView(new Image("p1/img/vehicles.png", 100, 100, true, true)), 3, 0);
		gpVehicles.add(new ImageView(new Image("p1/img/vehicles.png", 100, 100, true, true)), 4, 0);
		gpVehicles.setHgap(10);
		gpVehicles.setPadding(new Insets(10, 0, 10, 0));
		gpVehicles.setAlignment(Pos.CENTER);
		
		gpFurniture.add(new ImageView(new Image("p1/img/furniture.png", 100, 100, true, true)), 0, 0);
		gpFurniture.add(new ImageView(new Image("p1/img/furniture.png", 100, 100, true, true)), 1, 0);
		gpFurniture.add(new ImageView(new Image("p1/img/furniture.png", 100, 100, true, true)), 2, 0);
		gpFurniture.add(new ImageView(new Image("p1/img/furniture.png", 100, 100, true, true)), 3, 0);
		gpFurniture.add(new ImageView(new Image("p1/img/furniture.png", 100, 100, true, true)), 4, 0);
		gpFurniture.setHgap(10);
		gpFurniture.setPadding(new Insets(10, 0, 10, 0));
		gpFurniture.setAlignment(Pos.CENTER);
		
		gpRooms.add(new ImageView(new Image("p1/img/rooms.png", 100, 100, true, true)), 0, 0);
		gpRooms.add(new ImageView(new Image("p1/img/rooms.png", 100, 100, true, true)), 1, 0);
		gpRooms.add(new ImageView(new Image("p1/img/rooms.png", 100, 100, true, true)), 2, 0);
		gpRooms.add(new ImageView(new Image("p1/img/rooms.png", 100, 100, true, true)), 3, 0);
		gpRooms.add(new ImageView(new Image("p1/img/rooms.png", 100, 100, true, true)), 4, 0);
		gpRooms.setHgap(10);
		gpRooms.setPadding(new Insets(10, 0, 10, 0));
		gpRooms.setAlignment(Pos.CENTER);
	}
 */

/*
MainPage.sqlm.getStatement().executeUpdate("INSERT INTO listings (id, sellerid, name, category, description, listingcondition, price, vehicleyear, vehiclemiles, vehicletype, vehiclebrand, bookprefix, furncategory, furnroomcategory, roombednum, roombathnum, roomaddress) "
		+ "VALUES ("+maxid+", "+loggedInID+", '"+name+"', '"+itemCategory+"', '"+description+"', '"+productCondition+"', '"+price+"', '"+vehicleYear+"', '"+vehicleMiles+"', '"+vehicleType+"', '"+vehicleBrand+"', '"+coursePrefix+"', '"+furnitureCategory+"', "
		+ "'"+furnitureRoomCategory+"', "+roomBedNum+", "+roomBathNum+", '"+roomAddress+"')");
 */
