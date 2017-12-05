/** 
 * 
 * @author Asia Wright 
 * @date 11/2017 
 * 
 * UI class for viewing a specific, selected item.<br>
 * 
 */


package p1;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


public class SpecificItemPage 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */

	/** Overall Vbox for the specific item page. */
	private VBox vbSpecificItemPage= new VBox(15);
	
	/** Navbar instance. */
	private NavBar nav = new NavBar();
	
	/** Label for title. */
	private Label lblTitle; 

	/** Image for item. */
	private Image img;
	
	/** Image wrapper. */
	private ImageView imgItem = new ImageView();
	
	/** Scrollpane for description. */
	private ScrollPane spDescription;
	
	/** Label for description. */
	private Label lblDescription;
	
	/** Label for price. */
	private Label lblPrice;
	
	/** Label for condition. */
	private Label lblItemCondition;

	/** Vbox for specific item type info. */
	private VBox vbItemInfo = new VBox(10);
	

	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Constructor for specific item page. Takes in a string representing
	 * the item type (books, vehicles, furniture or rooms) to decide how the
	 * UI layout should look.
	 * 
	 * @param item
	 */
	protected SpecificItemPage(Listing item)
	{
		lblTitle = new Label(item.title);
		lblDescription = new Label(item.description);
		lblDescription.setWrapText(true);
		lblDescription.setMaxSize(650, Integer.MAX_VALUE);
		spDescription = new ScrollPane(lblDescription);
		spDescription.setMinSize(650, 150);
		spDescription.setMaxSize(650, 150);	
		spDescription.setStyle("-fx-background: Gray; -fx-background-color: Gray");
		spDescription.setHbarPolicy(ScrollBarPolicy.NEVER);
		lblPrice = new Label("Price: $" + item.price);
		lblItemCondition = new Label("Condition: " + item.condition);
		lblTitle.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 2em; -fx-font-weight: bold");
		lblDescription.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em");
		lblPrice.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");
		lblItemCondition.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");
		Label lblSpecificInfo = new Label("");
		lblSpecificInfo.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");
		
		if (item.category.equalsIgnoreCase("Books"))
		{ 
			 img = new Image("p1/img/books.png", 150, 150, true, true);
		}
		
		else if (item.category.equalsIgnoreCase("Vehicles"))
		{
		    img = new Image("p1/img/vehicles.png", 150, 150, true, true);
		}
		
		else if (item.category.equalsIgnoreCase("Rooms"))
		{
			img = new Image("p1/img/rooms.png", 150, 150, true, true);
		}
		
		else if (item.category.equalsIgnoreCase("Furniture"))
		{
			img = new Image("p1/img/furniture.png", 150, 150, true, true);
		}
		
		lblSpecificInfo.setText(item.specificInfo);
		lblSpecificInfo.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");
		lblSpecificInfo.setTextAlignment(TextAlignment.CENTER);
		
		imgItem.setImage(img);
		vbItemInfo.setAlignment(Pos.CENTER);

		vbSpecificItemPage.setAlignment(Pos.TOP_CENTER);
		vbSpecificItemPage.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbSpecificItemPage.getChildren().addAll(nav.getNavBar(), lblTitle, imgItem, lblPrice, spDescription, lblItemCondition, lblSpecificInfo);
		
		MainPage.sqlm.getUser().getIsLoggedInProperty().addListener((observable) ->
		{
			nav.setDisables(MainPage.sqlm.getUser().isLoggedIn(), true);
		});
		MainPage.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.sqlm.getUser().isLoggedIn());
		MainPage.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.sqlm.getUser().isLoggedIn());
	}
	
	
	/**
	 * Getter
	 * 
	 * @return vbItemPage
	 */
	protected VBox getRootPane()
	{
		return vbSpecificItemPage;
	}
}
