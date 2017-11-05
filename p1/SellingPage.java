/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 11/2017 
 * 
 * UI class for listing items for sale.<br>
 * 
 */


package p1;
import java.io.File;
import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;


public class SellingPage 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Container VBox for selling page. */	
	private VBox vbSelling = new VBox(10);
	
	/** Navigation bar instance. */
	private NavBar nav = new NavBar();
	
	/** Title label. */
	private Label lblSelling = new Label("Selling");

	/** Label for bad user entry. */
	private Label lblBadEntry = new Label();
	
	/** Textfield for item title. */
	private TextField tfTitle = new TextField();
	
	/** Textarea for item description. */
	private TextArea taDescription = new TextArea();
	
	/** Textfield for item price. */
	private TextField tfPrice = new TextField();
	
	/** Button for image selction. */
	private Button btImage = new Button("Select Image");
	
	/** Drop-down box for item category. */
	private ComboBox<String> cbItemCategory = new ComboBox<>();
	
	/** Drop-down box for item condition. */
	private ComboBox<String> cbProductCondition = new ComboBox<>();
	
	// TODO
	// specific listing-case nodes
	private String imgPath;
	private TextField tfCoursePrefix = new TextField();
	
	/** Button to list item. */
	private Button btOk = new Button("OK");
	
	/** Timeline for gameloop method. */
	private Timeline timeline;
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Default constructor for Selling page.
	 */
	public SellingPage()
	{
		// Set up the title label's properties
		lblSelling.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		
		// Set up the bad entry label's properties
		lblBadEntry.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold; -fx-text-fill: RED");
		
		// Set up the textfield for the item's title
		tfTitle.setPromptText("Enter the item's title...");
		tfTitle.setMaxWidth(600);
		tfTitle.setEffect(nav.dropShadowButton);
		
		// CHECKME potential to do listener on characters left???
		// Set up the textfield for the item's description
		taDescription.setPromptText("Enter the item's description (400 characters or less)...");
		taDescription.setMaxSize(600, 100);
		taDescription.setWrapText(true);
		taDescription.setEffect(nav.dropShadowButton);
		
		// Set up the textfield for the item's price
		tfPrice.setPromptText("List price...");
		tfPrice.setMaxWidth(100);
		tfPrice.setEffect(nav.dropShadowButton);
		
		// Make button pretty
		setTextStyle(btImage);
		
		// Add items to combobox
		cbItemCategory.setPromptText("Item category");
		cbItemCategory.getItems().addAll("Books", "Vehicles", "Furniture", "Rooms");
		nav.setComboBoxStyle(cbItemCategory);
		
		cbProductCondition.setPromptText("Item condition");
		cbProductCondition.getItems().addAll("New", "Like New", "Good", "Eh", "Bad");
		nav.setComboBoxStyle(cbProductCondition);
		
		// Make button pretty
		setTextStyle(btOk);
		btOk.setEffect(nav.dropShadowButton);
		
		// Set VBox for use
		vbSelling.setAlignment(Pos.TOP_CENTER);
		vbSelling.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbSelling.getChildren().addAll(nav.getNavBar(), lblSelling, lblBadEntry, tfTitle, taDescription, tfPrice, btImage, cbItemCategory, cbProductCondition, btOk);
		
		// TODO
		// Functionality for combobox drop down items
		cbItemCategory.setOnAction(e -> 
		{
			String value = cbItemCategory.getValue();
			
			if(Objects.equals(value, "Books"))
			{
				
			}
			
			if(Objects.equals(value, "Vehicles"))
			{
				
			}
			
			if(Objects.equals(value, "Furniture"))
			{
				
			}
			
			if(Objects.equals(value, "Rooms"))
			{
				
			}
		});
		
		// Functionality for OK button click
		btImage.setOnAction(e -> imageButtonClick());
		btOk.setOnAction(e -> okButtonClick());
		
		// Start gameloop to update the navbar
		gameLoop();
	}
	
	
	/**
	 * Functionality for image button click.
	 * TODO
	 * Multiple images and most importantly: IMAGE UPLOADING TO SERVER.
	 */
	private void imageButtonClick()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Select an image...");
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
		File selectedFile = fc.showOpenDialog(MainPage.instance.getStage());
		// If all is well with the file, set the path to its system URI
		if (selectedFile != null) 
		{
			imgPath = selectedFile.toURI().toString();
			System.out.println("Selected profile image successfully!");
		}
	}
	
	
	/**
	 * Functionality for OK button click.
	 */
	private void okButtonClick()
	{
		// Bad entry strings
		String longTitle = "Title is too long, please limit it to 100 characters or less.";
		String longDesc = "Description is too long, please limit it to 400 characters or less.";
		
		// Force user to upload an image
		if (imgPath == null)
		{
			lblBadEntry.setText("Pick an image!");
		}
		
		// If the title is too long AND the description is too long, set the label's text
		else if ((tfTitle.getText().length() >= 100) && (taDescription.getText().length() >= 400))
		{
			lblBadEntry.setText(longTitle + "\n" + longDesc);
			System.out.println("1");
		}
		
		// If the title is too long, set the label's text
		else if (tfTitle.getText().length() >= 100)
		{
			lblBadEntry.setText(longTitle);
			System.out.println("2");
		}
		
		// If the description is too long, set the label's text
		else if (taDescription.getText().length() >= 400)
		{
			lblBadEntry.setText(longDesc);
			System.out.println("3");
		}
		
		// Otherwise, all is well, so post the item
		else
		{
			lblBadEntry.setText("");

			//TODO
			// This is the test methods signature
			// Otherwise, the method will take in all of the user's input info
			MainPage.sqlm.getUser().createListing();
			
			// Create a new listing
			//MainPage.sqlm.user.createListing(tfTitle.getText(), taDescription.getText(), tfPrice.getText(), cbItemCategory.getValue(), imgPath, 
			//cbProductCondition.getValue(), String courseprefix, int year, String model, int miles, int bathroom, int bedroom);
			MainPage.instance.getStage().getScene().setRoot(MainPage.instance.getMainVBox());
		}
	}
	
	
	/**
	 * Simple JavaFX timeline to update the navbar.
	 */
	private void gameLoop()
	{
		timeline = new Timeline();
		// Set the game's timeline to be indefinite
		timeline.setCycleCount(Timeline.INDEFINITE);
		// Repeat the timeline cycle every 16ms, which equates to 1000ms / 16ms =~ 60fps
		// Every time it's enabled, update the navbar
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(16), e -> nav.setDisables(false, MainPage.sqlm.getUser().isLoggedIn(), false)));
		timeline.play(); 
	}
	
	
	/**
	 * Helper function- takes in a button and sets its CSS style.
	 * 
	 * @param bt
	 */
	private void setTextStyle(Button bt)
	{
		bt.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.15em; -fx-background-insets: 0,0,0,0");
		bt.setEffect(nav.dropShadowButton);
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
