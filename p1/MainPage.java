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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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

	/** Gridpane containing book listings. */
	private GridPane gpBooks = new GridPane();
	
	/** Gridpane containing vehicle listings. */
	private GridPane gpVehicles = new GridPane();
	
	/** Gridpane containing furniture listings. */
	private GridPane gpFurniture = new GridPane();
	
	/** Gridpane containg rooms listings. */
	private GridPane gpRooms = new GridPane();
	
	/** Button for books. */
	private Button btBooks = new Button("Books");
	
	/** Button for vehicles. */
	private Button btVehicles = new Button("Vehicles");
	
	/** Button for furniture. */
	private Button btFurniture = new Button("Furniture");
	
	/** Button for rooms. */
	private Button btRooms = new Button("Rooms");
	
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
	
		// Method to set up the grid panes
		setUpGridPanes();
		
		// Make buttons pretty
		nav.setButtonStyleRound(btBooks);
		nav.setButtonStyleRound(btVehicles);
		nav.setButtonStyleRound(btFurniture);
		nav.setButtonStyleRound(btRooms);
		
		// Add all children to the main VBox and set up its looks
		vbMainPage.getChildren().addAll(nav.getNavBar(), gpBooks, btBooks, gpVehicles, btVehicles, gpFurniture, btFurniture, gpRooms, btRooms);
		vbMainPage.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.DARKGREY, null, null)}, 
				new BackgroundImage[]{new BackgroundImage(new Image("p1/img/EmbryRiddleEagles.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)}));
		vbMainPage.setAlignment(Pos.TOP_CENTER);
		vbMainPage.setSpacing(5);
		
		// Display everything in the stage, editing its properties
		stagePrimary.setResizable(true);
		stagePrimary.setMinWidth(STAGE_WIDTH);
		stagePrimary.setMinHeight(STAGE_HEIGHT);
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
	 * Sets up the main page's gridpane with the most recent items of each category.
	 */
	private void setUpGridPanes()
	{	
		// Set up the gridpanes
		// TODO
		// This is where the most recent item images would be displayed
		gpBooks.add(new Circle(50, Color.DARKCYAN), 0, 0);
		gpBooks.add(new Circle(50, Color.BURLYWOOD), 1, 0);
		gpBooks.add(new Circle(50, Color.DARKGOLDENROD), 2, 0);
		gpBooks.add(new Circle(50, Color.DARKORCHID), 3, 0);
		gpBooks.add(new Circle(50, Color.BLANCHEDALMOND), 4, 0);
		gpBooks.setHgap(10);
		gpBooks.setPadding(new Insets(10, 0, 10, 0));
		gpBooks.setMinWidth(600);
		gpBooks.setAlignment(Pos.CENTER);
		gpBooks.setGridLinesVisible(true);
		
		gpVehicles.add(new Circle(50, Color.FUCHSIA), 0, 0);
		gpVehicles.add(new Circle(50, Color.GAINSBORO), 1, 0);
		gpVehicles.add(new Circle(50, Color.GOLD), 2, 0);
		gpVehicles.add(new Circle(50, Color.LIGHTSALMON), 3, 0);
		gpVehicles.add(new Circle(50, Color.DEEPPINK), 4, 0);
		gpVehicles.setHgap(10);
		gpVehicles.setPadding(new Insets(10, 0, 10, 0));
		gpVehicles.setMinWidth(500);
		gpVehicles.setAlignment(Pos.CENTER);
		gpVehicles.setGridLinesVisible(true);
		
		gpFurniture.add(new Circle(50, Color.HOTPINK), 0, 0);
		gpFurniture.add(new Circle(50, Color.KHAKI), 1, 0);
		gpFurniture.add(new Circle(50, Color.MEDIUMSLATEBLUE), 2, 0);
		gpFurniture.add(new Circle(50, Color.MAGENTA), 3, 0);
		gpFurniture.add(new Circle(50, Color.BISQUE), 4, 0);
		gpFurniture.setHgap(10);
		gpFurniture.setPadding(new Insets(10, 0, 10, 0));
		gpFurniture.setMinWidth(500);
		gpFurniture.setAlignment(Pos.CENTER);
		gpFurniture.setGridLinesVisible(true);
		
		gpRooms.add(new Circle(50, Color.SADDLEBROWN), 0, 0);
		gpRooms.add(new Circle(50, Color.OLIVEDRAB), 1, 0);
		gpRooms.add(new Circle(50, Color.ROSYBROWN), 2, 0);
		gpRooms.add(new Circle(50, Color.SEAGREEN), 3, 0);
		gpRooms.add(new Circle(50, Color.PLUM), 4, 0);
		gpRooms.setHgap(10);
		gpRooms.setPadding(new Insets(10, 0, 10, 0));
		gpRooms.setMinWidth(500);
		gpRooms.setAlignment(Pos.CENTER);
		gpRooms.setGridLinesVisible(true);
	}
	
	/**
	 * Functionality for Books button click
	 */
	private void booksButtonClick()
	{
		ItemPage obj = new ItemPage("Books");
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
