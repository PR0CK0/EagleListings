/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 11/2017 
 * 
 * UI class for item display.<br>
 * 
 */


package p1;
import java.util.Objects;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	
	/** Observable array list of listing objects. */
	private ObservableList<Listing> listingContentList = FXCollections.observableArrayList();
	
	/** Listview of listing objects to display. */
	private ListView<Listing> lvListings = new ListView<Listing>(listingContentList);
	
	// TODO
	// Optional
	//private TableView<Listing> tvListings = new TableView<>(listingContentList);

	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */

	/**
	 * Default constructor for Item page - takes in a String of the Item type:
	 * i.e. Books, Vehicles, Furniture or Rooms.
	 * 
	 * @param itemType
	 */
	protected ItemPage(String itemType)
	{		
		//TODO
		// Optional
		/*
		TableColumn<Listing, String> listingTitleCol = new TableColumn<Listing, String>("Title");
		TableColumn<Listing, String> listingPriceCol = new TableColumn<Listing, String>("Price");
		TableColumn<Listing, String> listingConditionCol = new TableColumn<Listing, String>("Condition");
		listingTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		tvListings.getColumns().addAll(listingTitleCol, listingPriceCol, listingConditionCol);
		*/
		
		// Set up the title label
		lblItem.setText(itemType);
		lblItem.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		
		// Set up the sort by label
		lblSortBy.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold");
		
		// Combobox for sorting by price
		cbSortBy.setValue("Default");
		cbSortBy.getItems().addAll("Price: low to high", "Price: high to low");
		nav.setComboBoxStyle(cbSortBy);
		
		// Set up the post time label
		lblPostTime.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold");
		
		// Combobox for sorting by time posted
		cbPostTime.setValue("All time");
		cbPostTime.getItems().addAll("12 hours", "24 hours", "1 week", "1 month", "All time");
		nav.setComboBoxStyle(cbPostTime);
		 
		// Container node for the sorting nodes
		vbSort.getChildren().addAll(lblSortBy, cbSortBy, lblPostTime, cbPostTime);
		
		// Add items to the observavle array list based on item category
		if (itemType.equals("Books"))
		{
			listingContentList.setAll(MainPage.sqlm.getUser().findItems("books"));
		}
		
		if (itemType.equals("Vehicles"))
		{
			listingContentList.setAll(MainPage.sqlm.getUser().findItems("vehicles"));
		}
		
		if (itemType.equals("Furniture"))
		{
			listingContentList.setAll(MainPage.sqlm.getUser().findItems("furniture"));
		}
		
		if (itemType.equals("Rooms"))
		{
			listingContentList.setAll(MainPage.sqlm.getUser().findItems("rooms"));
		}
		
		// Listview functionality for item browsing
		lvListings.setMinSize(590, 590);
		lvListings.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");
		lvListings.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Listing> ov, Listing old_val, Listing new_val) -> 
		{
			itemClick(new_val);
		});
		
		// Sorting by price
		cbSortBy.setOnAction(e -> sortByClick());
		// TODO
		// Other combobox not working yet
		
		// Add all nodes to the gridpane
		gpItems.add(vbSort, 0, 0);
		gpItems.add(lvListings, 1, 0);
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
	 * Functionality for a listed item being clicked.
	 */
	private void itemClick(Listing clickedItem)
	{
		SpecificItemPage obj = new SpecificItemPage(clickedItem);
		MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
	}
	
	
	/**
	 * Functionality for combobox sorting by price.
	 */
	private void sortByClick()
	{
		String value = cbSortBy.getValue();
		
		if (Objects.equals(value, "Price: low to high"))
		{
			System.out.println("sort low to high");
			FXCollections.sort(listingContentList);
		}
		
		if (Objects.equals(value, "Price: high to low"))
		{
			System.out.println("sort high to low");
			FXCollections.sort(listingContentList);
			FXCollections.reverse(listingContentList);
		}
	}
	
	
	/**
	 * Getter
	 * 
	 * @return listingContentList
	 */
	public ObservableList<Listing> getListingContent() 
	{
		return listingContentList;
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
