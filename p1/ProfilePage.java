/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 10/2017
 * 
 * UI class for a user profile display.<br>
 * Only accessible if the user is logged in.
 * 
 */


package p1;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class ProfilePage
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	//TODO
	/* Testing new profile layout
	private GridPane gpProfile = new GridPane();
	private Button btGeneral = new Button("General");
	private Button btSettings = new Button("Settings");
	private Button btHelp = new Button("Help");
	private VBox vbInfo = new VBox();
	private Label lblItemsSold = new Label("Items sold:");
	private Label lblGoodRatings = new Label("Items sold:");
	private Label lblBadRatings = new Label("Items sold:");
	*/
	
	/** Container VBox for profile page */	
	private VBox vbProfile = new VBox(50);
	
	/** Navigation bar instance */
	private NavBar nav = new NavBar();
	
    /** Label for the username. */
	private Label lblUsername = new Label("Username:");
	
	/** Label for the user's info. */
	private Label lblInfo = new Label();
	
	/** Image for the user's avatar. */
	private Image img;
	
	/** Wrapper for user's avatar image. */
	private ImageView imgProfile = new ImageView();

	/** Timeline for gameloop method. */
	private Timeline timeline;
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */

	/**
	 * Default constructor for Profile page.
	 */
	public ProfilePage()
	{	
		// Display the logged-in user's name
		lblUsername.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		lblUsername.setText("Welcome, " + MainPage.sqlm.getUser().getName() + ".");
		
		// Will update 
		/*
		lblInfo.setText("Email:" + MainPage.sqlm.getUser().getLoggedInEmail() + " \nPassword: " + MainPage.sqlm.getUser().getLoggedInPassword()
		                + "\nGood ratings:" + MainPage.sqlm.getUser().getRatingGood() + "\nBad ratings:" + MainPage.sqlm.getUser().getRatingBad()
		                + "\nItems sold: " + MainPage.sqlm.getUser().getItemsSold() + "\nRegistration date: " + MainPage.sqlm.getUser().getRegDate()); 
		*/
		lblInfo.setText("placeholder");
		lblInfo.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold");
		
		//TODO
		// Grab the user's profile image URI from the User class and set an ImageView object to contain this Image
		//String imgURI = MainPage.sqlm.getUser().getPictureURI().toString();
		
		// For now I've just set it to a default one until retrieval from Carson's server can be handled
		String imgURI = "p1/img/default_profile.png";
		img = new Image(imgURI, 350, 350, true, true);
		imgProfile.setImage(img);
		
		// Set VBox for use
		vbProfile.setAlignment(Pos.TOP_CENTER);
		vbProfile.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbProfile.setMinSize(MainPage.STAGE_WIDTH, MainPage.STAGE_HEIGHT);
		vbProfile.getChildren().addAll(nav.getNavBar(), lblUsername, lblInfo, imgProfile);
		
		
		//TODO
		// Testing new profile layout
		/*
		vbProfile.getChildren().addAll(nav.getNavBar(), gpProfile);
		btGeneral.setBackground(null);
		btSettings.setBackground(null);
		btHelp.setBackground(null);
		btGeneral.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");
		btSettings.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");
		btHelp.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold");

		gpProfile.add(btGeneral, 0, 0);
		gpProfile.add(btSettings, 0, 1);
		gpProfile.add(btHelp, 0, 2);
		vbInfo.getChildren().addAll(lblItemsSold, lblGoodRatings, lblBadRatings);
		vbInfo.setMinSize(400, 600);
		GridPane.setRowSpan(vbInfo, 3);
		gpProfile.add(vbInfo, 1, 0);
		gpProfile.add(imgProfile, 2, 1);
		GridPane.setRowSpan(imgProfile, 3);
		gpProfile.setAlignment(Pos.CENTER);
		gpProfile.setGridLinesVisible(true);
		*/
		
		
		// Start gameloop to update the navbar
		gameLoop();
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
	 * Getter
	 *
	 * @return vbProfile
	 */
	public VBox getRootPane()
	{
		return vbProfile;
	}	
}
