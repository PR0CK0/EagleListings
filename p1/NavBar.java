/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 11/2017
 * 
 * Custom navbar class that creates an attractive navigation panel to be used
 * by other classes. Intended to be placed at the top of a page.<br>
 * The top row contains buttons for returning home, selecting a buying category,
 * selling, logging in, logging out and help.<br>
 * The bottom row contains a search bar and button to search for items.<br>
 * When the user is not logged in: Logout button disabled, Profile button disabled, 
 * Selling button disabled.<br>
 * When the user is logged in: Login button disabled.
 * 
 */


package p1;
import java.util.Objects;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class NavBar 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Overall container node for the entire NavBar. */
	private VBox vbNav = new VBox(15);
	
	/** HBox to contain navigation buttons. */
	private HBox hbNav = new HBox(25);
	
	/** Title label. */
	private Label lblTitle = new Label("EagleListings");
	
	/** Return home button. */
	private Button btHome = new Button("Home");
	
	/** Essentially a drop-down box to select buying categories. */
	private ComboBox<String> cbBuying = new ComboBox<>();
	
    /** Selling button. */
	private Button btSelling = new Button("Selling");
	
	/** Login button. */
	private Button btLogin = new Button("Login");
	
	/** Logout button. */
	private Button btLogout = new Button("Logout");
	
	/** Profile button. */
	private Button btProfile = new Button("Profile");
	
	/** Help button. */
	private HelpScene help = new HelpScene();
	
	/** HBox to contain search function. */
	private HBox hbSearch = new HBox();
	
	/** Textfield to take in user searches. */
	public TextField tfSearchBar = new TextField();
	
	/** Search button. */
	private Button btSearch = new Button("Search");
	
	/** Dropshadow effect for use in the entire application. */
	protected DropShadow dropShadowButton = new DropShadow(5.5, 3.0, 3.0, Color.DARKSLATEGREY);
	
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Default constructor for NavBar.
	 */
	public NavBar()
	{	
		// Set looks of the title in the NavBar
		lblTitle.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold; -fx-font-style: italic; -fx-text-fill: WHITE");
		
		// Apply CSS and other effects to NavBar elements
		setNavStyle();
	
		// Add items to combobox
		cbBuying.setPromptText("Buying");
		cbBuying.getItems().addAll("Books", "Vehicles", "Furniture", "Rooms");
		
		// Search bar setup
		tfSearchBar.setMinWidth(250);
		tfSearchBar.setPromptText("What are you looking for...");
		tfSearchBar.setEffect(dropShadowButton);
		
		// Hbox containing all buttons
		hbNav.setAlignment(Pos.CENTER);
		hbNav.getChildren().addAll(btHome, cbBuying, btSelling, btLogin, btProfile, help.getHelpButton(), btLogout);
		
		// Hbox containing search nodes
		hbSearch.setAlignment(Pos.CENTER);
		hbSearch.getChildren().addAll(tfSearchBar, btSearch);
				
		// Set up the VBox to be displayed
		vbNav.getChildren().addAll(lblTitle, hbNav, hbSearch);
		vbNav.setAlignment(Pos.CENTER);
		VBox.setMargin(vbNav, new Insets(0, 0, 15, 0));
		vbNav.setPadding(new Insets(5, 0, 15, 0));
		vbNav.setBackground(new Background(new BackgroundFill(Color.DARKSLATEBLUE, null, null)));
		
		// Node functionality
		btHome.setOnAction(e -> homeButtonClick());
		cbBuying.setOnAction(e -> buyingBoxClick());
		btLogin.setOnAction(e -> loginButtonClick());
		btProfile.setOnAction(e-> profileButtonClick());
		btLogout.setOnAction(e -> logoutButtonClick());
		btSelling.setOnAction(e -> sellingButtonClick());
		btSearch.setOnAction(e -> searchButtonClick());
		
		// Validate text of search bar
		TextValidation.tfTextValidator(tfSearchBar, false);
	}
	
	
	/**
	 * Applies a listener to the user's logged in property. 
	 * Calls setDisables in the NavBar class, which updates any
	 * NavBar instance's buttons depending on if the user is
	 * logged in or not. 
	 * 
	 * @param isSearchable
	 */
	protected void navListener(boolean isSearchable)
	{
		// Still tentative... listeners only begin if their property is changed,
		// so the last two lines of this method are to force that
		MainPage.sqlm.getUser().getIsLoggedInProperty().addListener((observable) ->
		{
			setDisables(MainPage.sqlm.getUser().isLoggedIn(), isSearchable);
		});	
		MainPage.sqlm.getUser().setLoggedIn(true);
		MainPage.sqlm.getUser().setLoggedIn(false);
	}
	
	
	/**
	 * Method used to dynamically set the disabled values of each button in the NavBar.
	 * 
	 * @param isLoggedIn
	 * @param isSearchable
	 */
	protected void setDisables(boolean isLoggedIn, boolean isSearchable)
	{
		// Are we logged in?
		if (!isLoggedIn)
		{
			btSelling.setDisable(true);
			btProfile.setDisable(true);
			btLogin.setDisable(false);
			btLogout.setDisable(true);
		}
		
		else
		{
			btSelling.setDisable(false);
			btProfile.setDisable(false);
			btLogin.setDisable(true);
			btLogout.setDisable(false);
		}
		
		// If we are in the profile/login/specific item pages, we don't need a search bar
		if (!isSearchable)
		{
			tfSearchBar.setVisible(false);
			btSearch.setVisible(false);
		}
		
		else
		{
			tfSearchBar.setVisible(true);
			btSearch.setVisible(true);
		}
	}
	
	
	/**
	 * Functionality for home button click.
	 */
	private void homeButtonClick()
	{
		MainPage.instance.getStage().getScene().setRoot(MainPage.instance.getMainVBox());
	}
	
	
	/**
	 * Functionality for buying drop-down box click.
	 */
	private void buyingBoxClick()
	{
		// Store the value of the drop-down box's selected value
		String value = cbBuying.getValue();
		
		// Navigate to specific item page based on four if selections
		if(Objects.equals(value, "Books"))
		{
			ItemPage obj = new ItemPage("Books");
			MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
		}
		
		if(Objects.equals(value, "Vehicles"))
		{
			ItemPage obj = new ItemPage("Vehicles");
			MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
		}
		
		if(Objects.equals(value, "Furniture"))
		{
			ItemPage obj = new ItemPage("Furniture");
			MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
		}
		
		if(Objects.equals(value, "Rooms"))
		{
			ItemPage obj = new ItemPage("Rooms");
			MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
		}
	}
	
	/**
	 * Functionality for login button click.
	 */
	private void loginButtonClick()
	{
		LoginPage obj = new LoginPage();
		MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
	}
	
	
	/**
	 * Functionality for profile button click.
	 */
	private void profileButtonClick()
	{
		ProfilePage obj = new ProfilePage();
		MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
	}
	
	
	/**
	 * Functionality for logout button click.
	 * Displays an alert and logs the user out by setting the isLoggedIn variable in the User class to false.
	 */
	private void logoutButtonClick() 
	{
		// Create a confirmation alert
		Alert alertLogout = new Alert(AlertType.CONFIRMATION, "Are you sure?");
		alertLogout.setTitle("Logging out...");
		alertLogout.setHeaderText(null);		
	
		// Getting the proper functionality out of the alert
		// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
		Optional<ButtonType> result = alertLogout.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.OK)
		{
			MainPage.sqlm.getUser().logout();
			// Return to main page
			MainPage.instance.getStage().getScene().setRoot(MainPage.instance.getMainVBox());
			System.out.println("Logout successful!");
		}
		
		else
		{
			alertLogout.close();
		}
	}
	
	
	/**
	 * Functionality for selling button click.
	 */
	private void sellingButtonClick()
	{
		SellingPage obj = new SellingPage();
		MainPage.instance.getStage().getScene().setRoot(obj.getRootPane());
	}
	
	
	/**
	 * Functionality for search button click.
	 */
	private void searchButtonClick()
	{
		// Spit out some searches
		ItemPage searchPage = new ItemPage("Search results");
		MainPage.instance.getStage().getScene().setRoot(searchPage.getRootPane());
		searchPage.getListingContent().setAll(MainPage.sqlm.getUser().search(tfSearchBar.getText()));

	}
	
	
	/**
	 * Helper function - applies CSS styling, JavaFX effects and other things to NavBar elements.
	 */
	private void setNavStyle()
	{
		// Buttons with no backgrounds look nice
		btHome.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-text-fill: WHITE");
		btHome.setBackground(null);
		cbBuying.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-background-radius: 0,0,0,0");
		btSelling.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-text-fill: WHITE");
		btSelling.setBackground(null);
		btLogin.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-text-fill: WHITE");
		btLogin.setBackground(null);
		btProfile.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-text-fill: WHITE");
		btProfile.setBackground(null);
		btLogout.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-text-fill: WHITE");
		btLogout.setBackground(null);
		tfSearchBar.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-background-radius: 0,0,0,0");
		btSearch.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-background-radius: 0,0,0,0; -fx-background-insets:0,0,0,0");
		btSearch.setBackground(null);

		// Dropshadow effect
		btHome.setEffect(dropShadowButton);
		cbBuying.setEffect(dropShadowButton);
		btSelling.setEffect(dropShadowButton);
		btLogin.setEffect(dropShadowButton);
		btProfile.setEffect(dropShadowButton);
		btLogout.setEffect(dropShadowButton);
		btSearch.setEffect(dropShadowButton);
		
		// Tooltips!
		btHome.setTooltip(new Tooltip("Return to home page."));
		cbBuying.setTooltip(new Tooltip("Select a category to browse."));
		btSelling.setTooltip(new Tooltip("List your item here!"));
		btLogin.setTooltip(new Tooltip("Login to your EagleListings account."));
		btProfile.setTooltip(new Tooltip("Check and edit your personal profile."));
		help.getHelpButton().setTooltip(new Tooltip("Get some help."));
		btLogout.setTooltip(new Tooltip("Logout of your EagleListings account."));
		tfSearchBar.setTooltip(new Tooltip("Enter your search term here."));
		btSearch.setTooltip(new Tooltip("Search for an item."));
	}
	
	
	/**
	 * Helper function - takes in a button and sets its CSS style.
	 * Makes the buttons rounded.<br>
	 * Used by other classes that contain a NavBar instance.
	 * 
	 * @param bt
	 */
	protected void setButtonStyleRound(Button bt)
	{
		bt.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.15em; -fx-background-radius: 10,10,10,10; -fx-background-insets: 10,1,2,10");
		bt.setEffect(dropShadowButton);
	}
	
	
	/**
	 * Helper function - takes in a button and sets its CSS style.
	 * Makes the button sharp-cornered.<br>
	 * Used by other classes that contain a NavBar instance.
	 * 
	 * @param bt
	 */
	protected void setButtonStyleSharp(Button bt)
	{
		bt.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.15em; -fx-background-insets: 0,0,0,0");
		bt.setEffect(dropShadowButton);
	}
	
	
	/**
	 * Helper function - takes in a textfield and sets its CSS style.
	 * Sharpens the corners of textfields.<br>
	 * Used by other classes that contain a NavBar instance.
	 * 
	 * @param tf 
	 */
	protected void setTextFieldStyle(TextField tf)
	{
		tf.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-background-radius: 0,0,0,0");
		tf.setEffect(dropShadowButton);
	}
	
	
	/**
	 * Helper function - takes in a combobox and sets its CSS style.
	 * Sharpens the corners of comboboxes.<br>
	 * Used by other classes that contain a NavBar instance.
	 * 
	 * @param cb
	 */
	protected void setComboBoxStyle(ComboBox<?> cb)
	{
		cb.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em;  -fx-background-radius: 0,0,0,0");
		cb.setEffect(dropShadowButton);
	}
	
	
	/**
	 * Getter
	 * 
	 * @return vbNav
	 */
	protected VBox getNavBar()
	{
		return vbNav;
	}
}
