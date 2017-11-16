/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 11/2017 
 * 
 *UI class for item display.<br>
 * 
 */


package p1;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class ItemPage 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Container VBox for item page. */	
	private VBox vbItemPage = new VBox(10);
	
	/** Navigation bar instance. */
	private NavBar nav = new NavBar();
	
	/** Title label. */
	private Label lblItem = new Label();
	
	/** Gridpane for page layout. */
	private GridPane gpItems = new GridPane();
	
	/** Scrollpane for displaying items */
	private ScrollPane spItems = new ScrollPane();
	
	public static Label spContent = new Label();
	
	/** Vbox to contain sorting options. */
	private VBox vbSort = new VBox(5);
	
	/** Label option. */
	private Label lblSortBy = new Label("Sort by:");
	
	/** Drop-down box for sorting by price. */
	private ComboBox<String> cbSortBy = new ComboBox<>();
	
	/** Label option. */
	private Label lblPostTime = new Label("Posted within:");
	
	/** Drop-down box for sorting by post time. */
	private ComboBox<String> cbPostTime = new ComboBox<>();

	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	//TODO
	// Make this page not-so-useless...
	
	/**
	 * Default constructor for Item page - takes in a String of the Item type:
	 * i.e. Books, Vehicles, Furniture or Rooms.
	 * 
	 * @param itemType
	 */
	protected ItemPage(String itemType)
	{
		// TODO
		// temp reset
		spContent.setText("");
		
		// Set up the title label
		lblItem.setText(itemType);
		lblItem.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		
		// Set up the sort by label
		lblSortBy.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold");
		
		// Combobox for sorting by price
		cbSortBy.setValue("Most recent");
		cbSortBy.getItems().addAll("Most recent", "Price: low to high", "Price: high to low");
		nav.setComboBoxStyle(cbSortBy);
		
		// Set up the post time label
		lblPostTime.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold");
		
		// Combobox for sorting by time posted
		cbPostTime.setValue("All time");
		cbPostTime.getItems().addAll("12 hours", "24 hours", "1 week", "1 month", "All time");
		nav.setComboBoxStyle(cbPostTime);
		 
		// Container node for the sorting nodes
		vbSort.getChildren().addAll(lblSortBy, cbSortBy, lblPostTime, cbPostTime);
		
		// Set scrollpane for item browsing
		spItems.setContent(spContent);
		spItems.setMinSize(590, 590);
		spItems.setMaxSize(590, 590);
		spItems.setStyle("-fx-background: Gray; -fx-background-color: Gray");
		spContent.setWrapText(true);
		spContent.setStyle("-fx-font-size: 2em");
		spContent.setMaxSize(650, Integer.MAX_VALUE);
		
		// Add all nodes to the gridpane
		gpItems.add(vbSort, 0, 0);
		gpItems.add(spItems, 1, 0);
		gpItems.setAlignment(Pos.CENTER);
		gpItems.setMinSize(650, 650);
		gpItems.setHgap(10);
		
		// Set VBox for use
		vbItemPage.getChildren().addAll(nav.getNavBar(), lblItem, gpItems);
		vbItemPage.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbItemPage.setAlignment(Pos.TOP_CENTER);

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
		return vbItemPage;
	}
}
