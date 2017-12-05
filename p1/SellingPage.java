/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 11/2017 
 * 
 * UI class for listing items for sale.<br>
 * 
 */


package p1;
import java.util.Objects;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;


public class SellingPage 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Characters allowed in the title. */
	private static final int TITLE_CHARS_ALLOWED = 30;
	
	/** Characters allowed in the description. */
	private static final int DESC_CHARS_ALLOWED = 1000;
	
	/** Characters allowed in the price. */
	private static final int PRICE_CHARS_ALLOWED = 6;
	
	/** Characters allowed in the course prefix. */
	private static final int BOOKS_COURSE_CHARS_ALLOWED = 10;
	
	/** Characters allowed in the vehicle's year. */
	private static final int VEHICLES_YEARS_CHARS_ALLOWED = 4;
	
	/** Characters allowed in the vehicle's miles. */
	private static final int VEHICLES_MILES_CHARS_ALLOWED = 6;
	
	/** Characters allowed in the vehicle's brand. */
	private static final int VEHICLES_BRAND_CHARS_ALLOWED = 18;
	
	/** Characters allowed in the room's address. */
	private static final int ROOMS_ADDRESS_CHARS_ALLOWED = 100;
	
	/** Container VBox for selling page. */	
	private VBox vbSelling = new VBox(15);
	
	/** Navigation bar instance. */
	private NavBar nav = new NavBar();
	
	/** Title label. */
	private Label lblSelling = new Label("List Your Item");

	/** Label for bad user entry. */
	private Label lblBadEntry = new Label();
	
	/** Textfield for item title. */
	private TextField tfTitle = new TextField();
	
	/** Textarea for item description. */
	private TextArea taDescription = new TextArea();
	
	/** Label for characters left in the description. */
	private Label lblCharactersLeft = new Label("Characters left: 1000");
	
	/** Textfield for item price. */
	private TextField tfPrice = new TextField();
		
	/** Drop-down box for item category. */
	private ComboBox<String> cbItemCategory = new ComboBox<>();
	
	/** Drop-down box for item condition. */
	private ComboBox<String> cbProductCondition = new ComboBox<>();
	
	/** Hbox to gather specific item info based on category selection. */
	private HBox hbItemInfo = new HBox(10);
	
	/** For BOOKS - textfield for course prefix. */
	private TextField tfCoursePrefix = new TextField();
	
	/** For VEHICLES - textfield for vehicle year. */
	private TextField tfVehicleYear = new TextField();
	
	/** For VEHICLES - textfield for vehicle miles. */
	private TextField tfVehicleMiles = new TextField();
	
	/** For VEHICLES - textfield for vehicle brand. */
	private TextField tfVehicleBrand = new TextField();
	
	/** For VEHICLES - drop-down box for vehicle type. */
	private ComboBox<String> cbVehicleType = new ComboBox<>();
	
	/** For FURNITURE - drop-down box for furniture category. */
	private ComboBox<String> cbFurnitureCategory = new ComboBox<>();
	
	/** For FURNITURE - drop-down box for furniture room category. */
	private ComboBox<String> cbFurnitureRoomCategory = new ComboBox<>();
	
	/** For ROOMS - drop-down box for number of bedrooms. */
	private ComboBox<Integer> cbRoomBedNum = new ComboBox<>();
	
	/** For ROOMS - drop-down box for number of bathrooms. */
	private ComboBox<Integer> cbRoomBathNum = new ComboBox<>();
	
	/** For ROOMS - textfield for room address. */
    private TextField tfRoomAddress = new TextField();
	
	/** Button to list item. */
	private Button btOk = new Button("OK");

	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Default constructor for Selling page.
	 */
	public SellingPage()
	{
		// Set up the title label
		lblSelling.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		
		// Set up the bad entry label
		lblBadEntry.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold; -fx-text-fill: RED");
		lblBadEntry.setVisible(false);
		
		// Set up the textfield for the item's title
		tfTitle.setPromptText("Enter the item's title...");
		tfTitle.setMaxWidth(250);
		nav.setTextFieldStyle(tfTitle);
		
		// Set up the textarea for the item's description
		taDescription.setPromptText("Enter the item's description (1000 characters or less)... Be sure to include some contact info!");
		taDescription.setMaxSize(600, 200);
		taDescription.setWrapText(true);
		taDescription.setEffect(nav.dropShadowButton);
		// Add a listener to the characters entered into the description's textarea
		taDescription.textProperty().addListener((var, oldText, newText) ->
		{
			// Update the number of characters left each time a new one is entered
			int charsLeft = DESC_CHARS_ALLOWED - taDescription.getText().length();
			
			// Set the label
			lblCharactersLeft.setText("Characters left: " + charsLeft);
			
			// If the text exceeds the number allowed, revert back to the old text
			if (newText.length() > DESC_CHARS_ALLOWED)
			{
				taDescription.setText(oldText);
			}
		});
		
		// Set characters left label style
		lblCharactersLeft.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold; -fx-background-color: WHITE");
		
		// Set up the textfield for the item's price
		tfPrice.setPromptText("List price...");
		tfPrice.setMaxWidth(100);
		nav.setTextFieldStyle(tfPrice);
		
		// Set up combobox
		cbProductCondition.setPromptText("Item condition");
		cbProductCondition.getItems().addAll("New", "LikeNew", "Good", "Eh", "Bad");
		nav.setComboBoxStyle(cbProductCondition);
		
		// Set up combobox
		cbItemCategory.setPromptText("Item category");
		cbItemCategory.getItems().addAll("Books", "Vehicles", "Furniture", "Rooms");
		nav.setComboBoxStyle(cbItemCategory);

		// SPECIFIC ITEM CATEGORY NODES
		hbItemInfo.setAlignment(Pos.CENTER);
		hbItemInfo.setVisible(false);
		
		// BOOKS
		tfCoursePrefix.setPromptText("Enter the course prefix...");
		nav.setTextFieldStyle(tfCoursePrefix);
		
		// VEHICLES
		tfVehicleYear.setPromptText("Enter the vehicle's year...");
		nav.setTextFieldStyle(tfVehicleYear);
		tfVehicleMiles.setPromptText("Enter the vehicle's miles...");
		tfVehicleMiles.setMinWidth(180);
		nav.setTextFieldStyle(tfVehicleMiles);
		tfVehicleBrand.setPromptText("Enter the vehicle's brand...");
		tfVehicleBrand.setMinWidth(185);
		nav.setTextFieldStyle(tfVehicleBrand);
		cbVehicleType.setPromptText("Vehicle type");
		cbVehicleType.getItems().addAll("Car", "Van", "Truck", "SUV", "Motorcycle", "Bike", "Board", "Other");
		nav.setComboBoxStyle(cbVehicleType);
		
		// FURNITURE
		cbFurnitureCategory.setPromptText("Furniture category");
		cbFurnitureCategory.getItems().addAll("Bed", "Chair", "Couch", "Table", "Other");
		nav.setComboBoxStyle(cbFurnitureCategory);
		cbFurnitureRoomCategory.setPromptText("Room category");
		cbFurnitureRoomCategory.getItems().addAll("Bedroom", "Bathroom", "Living", "Outdoor");
		nav.setComboBoxStyle(cbFurnitureRoomCategory);
		
		// ROOMS
		cbRoomBedNum.setPromptText("Bedroom number");
		cbRoomBedNum.getItems().addAll(1, 2, 3, 4);
		nav.setComboBoxStyle(cbRoomBedNum);
		cbRoomBathNum.setPromptText("Bathroom number");
		cbRoomBathNum.getItems().addAll(1, 2, 3, 4);
		nav.setComboBoxStyle(cbRoomBathNum);
		tfRoomAddress.setPromptText("Enter the room's address...");
		tfRoomAddress.setMinWidth(190);
		nav.setTextFieldStyle(tfRoomAddress);
		
		// Make button pretty
		nav.setButtonStyleRound(btOk);
		
		// Set VBox for use
		vbSelling.setAlignment(Pos.TOP_CENTER);
		vbSelling.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbSelling.getChildren().addAll(nav.getNavBar(), lblSelling, lblBadEntry, tfTitle, taDescription, lblCharactersLeft, tfPrice, cbProductCondition, cbItemCategory, hbItemInfo, btOk);
		
		// Text validation
		TextValidation.tfTextValidator(tfTitle, false);
		TextValidation.taTextValidator(taDescription);
		TextValidation.tfTextValidator(tfPrice, true);
		TextValidation.tfTextValidator(tfCoursePrefix, false);
		TextValidation.tfTextValidator(tfRoomAddress, false);
		TextValidation.tfTextValidator(tfVehicleBrand, false);
		TextValidation.tfTextValidator(tfVehicleMiles, true);
		TextValidation.tfTextValidator(tfVehicleYear, true);
		TextValidation.tfLengthLimiter(tfTitle, TITLE_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfPrice, PRICE_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfCoursePrefix, BOOKS_COURSE_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfVehicleYear, VEHICLES_YEARS_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfVehicleMiles, VEHICLES_MILES_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfVehicleBrand, VEHICLES_BRAND_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfRoomAddress, ROOMS_ADDRESS_CHARS_ALLOWED);

		// Functionality for nodes
		cbItemCategory.setOnAction(e -> categoryBoxClick());
		btOk.setOnAction(e -> okButtonClick());

		MainPage.sqlm.getUser().getIsLoggedInProperty().addListener((observable) ->
		{
			nav.setDisables(MainPage.sqlm.getUser().isLoggedIn(), false);
		});
		MainPage.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.sqlm.getUser().isLoggedIn());
		MainPage.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.sqlm.getUser().isLoggedIn());	
	}
	
	
	/**
	 * Functionality for category selection drop-down box.
	 */
	private void categoryBoxClick()
	{
		String value = cbItemCategory.getValue().toString();
		
		if(Objects.equals(value, "Books"))
		{
			hbItemInfo.getChildren().clear();
			hbItemInfo.getChildren().addAll(tfCoursePrefix);
			hbItemInfo.setVisible(true);
		}
		
		if(Objects.equals(value, "Vehicles"))
		{
			hbItemInfo.getChildren().clear();
			hbItemInfo.getChildren().addAll(tfVehicleYear, tfVehicleMiles, tfVehicleBrand, cbVehicleType);
			hbItemInfo.setVisible(true);

		}
		
		if(Objects.equals(value, "Furniture"))
		{
			hbItemInfo.getChildren().clear();
			hbItemInfo.getChildren().addAll(cbFurnitureCategory, cbFurnitureRoomCategory);
			hbItemInfo.setVisible(true);
		}
		
		if(Objects.equals(value, "Rooms"))
		{
			hbItemInfo.getChildren().clear();
			hbItemInfo.getChildren().addAll(cbRoomBedNum, cbRoomBathNum, tfRoomAddress);
			hbItemInfo.setVisible(true);
		}
	}
	
	
	/**
	 * Functionality for OK button click.
	 */
	private void okButtonClick()
	{
		// Handle unselected stuff
		if (tfTitle.getText().isEmpty() || taDescription.getText().isEmpty() || tfPrice.getText().isEmpty() || cbProductCondition.getSelectionModel().isEmpty() || cbItemCategory.getSelectionModel().isEmpty())
		{
			lblBadEntry.setText("One or more fields is empty!");
			lblBadEntry.setVisible(true);
		}

		// Specific item nodes being empty
		else
		{
			// Store the value of the selected item category
			String selectedCategory = cbItemCategory.getValue();
			
			if (Objects.equals(selectedCategory, "Books") && (tfCoursePrefix.getText().isEmpty()))
			{
				lblBadEntry.setText("Enter a course prefix!");
				lblBadEntry.setVisible(true);
			}
			
			else if (Objects.equals(selectedCategory, "Vehicles") && (tfVehicleYear.getText().isEmpty() || tfVehicleMiles.getText().isEmpty() || tfVehicleBrand.getText().isEmpty() || cbVehicleType.getSelectionModel().isEmpty()))
			{
				lblBadEntry.setText("One or more vehicle fields is empty!");
				lblBadEntry.setVisible(true);
			}
			
			else if (Objects.equals(selectedCategory, "Furniture") && (cbFurnitureCategory.getSelectionModel().isEmpty() || cbFurnitureRoomCategory.getSelectionModel().isEmpty()))
			{
				lblBadEntry.setText("One or more furniture fields is empty!");
				lblBadEntry.setVisible(true);
			}
			
			else if (Objects.equals(selectedCategory, "Rooms") && (cbRoomBedNum.getSelectionModel().isEmpty() || cbRoomBathNum.getSelectionModel().isEmpty() || tfRoomAddress.getText().isEmpty()))
			{
				lblBadEntry.setText("One or more room fields is empty!");
				lblBadEntry.setVisible(true);
			}
			
			// Otherwise, all is well, so post the item
			else
			{
				lblBadEntry.setText("");
	
				// Create a confirmation alert for contact information
				Alert alertPost = new Alert(AlertType.CONFIRMATION, "Did you include contact information in the item's description? "
						+ "If you want a buyer to be able to contact you, make sure this is so! Click Cancel to return to editing or OK to post your item.");
				alertPost.setTitle("Important!");
				alertPost.setHeaderText(null);		
			
				// Getting the proper functionality out of the alert
				// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html
				Optional<ButtonType> result = alertPost.showAndWait();
				
				if (result.isPresent() && result.get() == ButtonType.OK)
				{
					// Create a new listing based on the selected item category
					// FOUR CASES: Books, Vehicles, Furniture, Rooms
					if (Objects.equals(selectedCategory, "Books"))
					{
						MainPage.sqlm.getUser().createListing(tfTitle.getText(), cbItemCategory.getValue().toLowerCase(), taDescription.getText(), cbProductCondition.getValue().toLowerCase(), tfPrice.getText(), 
						null, null, null, null, tfCoursePrefix.getText(), null, null, null, null, null);
					}
					
					if (Objects.equals(selectedCategory, "Vehicles"))
					{
						MainPage.sqlm.getUser().createListing(tfTitle.getText(), cbItemCategory.getValue().toLowerCase(), taDescription.getText(), cbProductCondition.getValue().toLowerCase(), tfPrice.getText(), 
						tfVehicleYear.getText(), tfVehicleMiles.getText(), tfVehicleBrand.getText(), cbVehicleType.getValue(), null, null, null, null, null, null);
					}
					
					if (Objects.equals(selectedCategory, "Furniture"))
					{
						MainPage.sqlm.getUser().createListing(tfTitle.getText(), cbItemCategory.getValue().toLowerCase(), taDescription.getText(), cbProductCondition.getValue().toLowerCase(), tfPrice.getText(), 
						null, null, null, null, null, cbFurnitureCategory.getValue().toLowerCase(), cbFurnitureRoomCategory.getValue().toLowerCase(), null, null, null);
					}
					
					if (Objects.equals(selectedCategory, "Rooms"))
					{
						MainPage.sqlm.getUser().createListing(tfTitle.getText(), cbItemCategory.getValue().toLowerCase(), taDescription.getText(), cbProductCondition.getValue().toLowerCase(), tfPrice.getText(), 
						null, null, null, null, null, null, null, cbRoomBedNum.getValue(), cbRoomBathNum.getValue(), tfRoomAddress.getText());
					}
					
					Alert alertPosted = new Alert(AlertType.INFORMATION, "You have successfully posted your item!.");
					alertPosted.setTitle("Logged in!");
					alertPosted.setHeaderText(null);
					alertPosted.initModality(Modality.APPLICATION_MODAL);
					alertPosted.showAndWait();
					
					MainPage.instance.getStage().getScene().setRoot(MainPage.instance.getMainVBox());
				}
				
				else
				{
					alertPost.close();
				}
			}
		}
	}
	
	
	/**
	 * Getter
	 * 
	 * @return vbSelling
	 */
	public VBox getRootPane()
	{
		return vbSelling;
	}
}
