/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 11/2017
 * 
 * Main page for the Eaglelistings desktop application.<br>
 * 
 * All attributes are listed in top down order, in the order
 * they will appear in the JavaFX UI.<br>
 * 
 */


package p1;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainPage extends Application
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Stage width constant. */
	public static final int STAGE_WIDTH = 850;
	
	/** Stage height constant. */
	public static final int STAGE_HEIGHT = 850;
	
	/** Stage reference for other classes. */
	private Stage stage;
	/** Instance of this class for other classes to reference. */
	public static MainPage instance = null;
	
	/** Scene for the main page. */ 
	private Scene sceneMainPage;
	
	/** Overall VBox for the main page. */
	private VBox vbMainPage = new VBox();
	
	/** NavBar instance, for navigating to other pages. */
	private NavBar nav = new NavBar();

	/** Gridpane containing all listing info. */
	private GridPane gpItems = new GridPane();
	
	/** Button for books. */
	private Button btBooks = new Button("Books");
	
	/** Button for vehicles. */
	private Button btVehicles = new Button("Vehicles");
	
	/** Button for furniture. */
	private Button btFurniture = new Button("Furniture");
	
	/** Button for rooms. */
	private Button btRooms = new Button("Rooms");
	
	/** Disclaimer label */
	private Label lblDisclaimer = new Label();
	
	/** 
	 * Public instance of the SQLManager class.<br>
	 * Created when the program starts MainPage.java, run only once.
	 */
	public static SQLManager sqlm = new SQLManager();
	
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Start method where all JavaFX begins.
	 */
	@Override
	public void start(Stage stagePrimary)
	{
		// Set for use (so we can return to MainPage from other pages)
		stage = stagePrimary;
		instance = this;
		
		// Scene to contain all nodes
		sceneMainPage = new Scene(vbMainPage, STAGE_WIDTH, STAGE_HEIGHT);

		// Set up gpItems
		setUpGridPane();
		
		// Make buttons pretty
		nav.setButtonStyleRound(btBooks);
		nav.setButtonStyleRound(btVehicles);
		nav.setButtonStyleRound(btFurniture);
		nav.setButtonStyleRound(btRooms);
		VBox.setMargin(btVehicles, new Insets(0, 0, 12, 0));
		VBox.setMargin(btFurniture, new Insets(0, 0, 5, 0));
		
		// Set up disclaimer label
		lblDisclaimer.setText("© Group JJCAT - SE300, Fall 2017");
		lblDisclaimer.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.1em");
		VBox.setMargin(lblDisclaimer, new Insets(65, 0, 0, 0));

		// Add all children to the main VBox and set up its looks
		//vbMainPage.getChildren().addAll(nav.getNavBar(), gpBooks, btBooks, gpVehicles, btVehicles, gpFurniture, btFurniture, gpRooms, btRooms, lblDisclaimer);
		vbMainPage.getChildren().addAll(nav.getNavBar(), gpItems, lblDisclaimer);
		vbMainPage.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.DARKGREY, null, null)}, 
				new BackgroundImage[]{new BackgroundImage(new Image("p1/img/EmbryRiddleEagles.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)}));
		vbMainPage.setAlignment(Pos.TOP_CENTER);
		vbMainPage.setSpacing(5);
		
		// Display everything in the stage, editing its properties
		stagePrimary.setMinWidth(STAGE_WIDTH);
		stagePrimary.setMinHeight(STAGE_HEIGHT);
		stagePrimary.setResizable(false);
		stagePrimary.getIcons().add(new Image("p1/img/logo.jpg"));
		stagePrimary.setTitle("EagleListings - Buying and Selling for Eagles");
		stagePrimary.setScene(sceneMainPage);
		stagePrimary.show();
		
		// Button functionality
		btBooks.setOnAction(e -> booksButtonClick());
		btVehicles.setOnAction(e -> vehiclesButtonClick());
		btFurniture.setOnAction(e -> furnitureButtonClick());
		btRooms.setOnAction(e -> roomsButtonClick());
		
		// Apply a listener to the navbar buttons
		nav.navListener(true);
	}
	
	
	/** 
	 * Sets up the big gridpane in this page.
	 */
	private void setUpGridPane()
	{
		gpItems.add(new ImageView(new Image("p1/img/books.png", 240, 240, true, true)), 0, 0);
		gpItems.add(new ImageView(new Image("p1/img/vehicles.png", 240, 240, true, true)), 1, 0);
		gpItems.add(btBooks, 0, 1);
		gpItems.add(btVehicles, 1, 1);
		GridPane.setHalignment(btBooks, HPos.CENTER);
		GridPane.setHalignment(btVehicles, HPos.CENTER);
		gpItems.add(new ImageView(new Image("p1/img/furniture.png", 240, 240, true, true)), 0, 2);
		gpItems.add(new ImageView(new Image("p1/img/rooms.png", 240, 240, true, true)), 1, 2);
		gpItems.add(btFurniture, 0, 3);
		gpItems.add(btRooms, 1, 3);
		GridPane.setHalignment(btFurniture, HPos.CENTER);
		GridPane.setHalignment(btRooms, HPos.CENTER);
		gpItems.setHgap(175);
		gpItems.setVgap(40);
		gpItems.setAlignment(Pos.CENTER);
	}
	
	
	/**
	 * Functionality for Books button click
	 */
	private void booksButtonClick()
	{
		ItemPage obj = new ItemPage("Books");
		//SpecificItemPage obj = new SpecificItemPage("Books");
		stage.getScene().setRoot(obj.getRootPane());
	}
	
	
	/**
	 * Functionality for Vehicles button click
	 */
	private void vehiclesButtonClick()
	{
		ItemPage obj = new ItemPage("Vehicles");
		stage.getScene().setRoot(obj.getRootPane());
	}
	
	
	/**
	 * Functionality for Furniture button click
	 */
	private void furnitureButtonClick()
	{
		ItemPage obj = new ItemPage("Furniture");
		stage.getScene().setRoot(obj.getRootPane());
	}
	
	
	/**
	 * Functionality for Rooms button click
	 */
	private void roomsButtonClick()
	{
		ItemPage obj = new ItemPage("Rooms");
		stage.getScene().setRoot(obj.getRootPane());
	}

	
	/**
	 * Getter
	 * 
	 * @return stage
	 */
	public Stage getStage()
	{
		return stage;
	}
	
	
	/**
	 * Getter
	 * 
	 * @return vbMainPage
	 */
	public VBox getMainVBox()
	{
		return vbMainPage;
	}
	
	
	/**
	 * Getter
	 * 
	 * @return nav
	 */
	public NavBar getNavBar()
	{
		return nav;
	}
	
	
	/**
	 * Needed to run JavaFX without command line
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// Launch the start method
	    MainPage.launch(args);
	    // Or just launch(args);
	    
	    // Close the SQL database connection if the application is closing
	    if(Platform.isImplicitExit())
	    {
	    	// Such try						
			try 
	    	{				
				sqlm.getUser().logout();
				sqlm.getConnection().close();
				System.out.println("Closed database connection!");
			} 
	    	
			// Very catch
	    	catch (SQLException e) 
	    	{
				e.printStackTrace();
				System.err.println("Could not close connection to database!");
			}
	    } 
	}
}