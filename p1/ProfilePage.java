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
import java.sql.SQLException;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ProfilePage
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
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
	
	/** Hbox containing change buttons. */
	private HBox hbChange = new HBox(15);
	
	/** Button to change email. */
	private Button btChangeEmail = new Button("Change email");
	
	/** Button to change password. */
	private Button btChangePassword = new Button("Change password");

	
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
		
		// Display basic user info
		lblInfo.setText("Email: " + MainPage.sqlm.getUser().getLoggedInEmail() + " \nPassword: " + MainPage.sqlm.getUser().getLoggedInPassword()
				+ "\nItems sold: " + MainPage.sqlm.getUser().getItemsSold()); 
		lblInfo.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold");
		
		//TODO
		// Grab the user's profile image URI from the User class and set an ImageView object to contain this Image
		//String imgURI = MainPage.sqlm.getUser().getPictureURI().toString();
		
		// For now I've just set it to a default one until retrieval from Carson's server can be handled
		String imgURI = "p1/img/default_profile.png";
		img = new Image(imgURI, 350, 350, true, true);
		imgProfile.setImage(img);
		
		// Set button's style
		nav.setButtonStyleSharp(btChangeEmail);
		nav.setButtonStyleSharp(btChangePassword);
		
		// Add change buttons to Hbox
		hbChange.getChildren().addAll(btChangeEmail, btChangePassword);
		hbChange.setAlignment(Pos.CENTER);
		
		// Set VBox for use
		vbProfile.setAlignment(Pos.TOP_CENTER);
		vbProfile.setMinSize(500, 500);
		vbProfile.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbProfile.setMinSize(MainPage.STAGE_WIDTH, MainPage.STAGE_HEIGHT);
		vbProfile.getChildren().addAll(nav.getNavBar(), lblUsername, lblInfo, imgProfile, hbChange);
		
		// Button functionality
		btChangeEmail.setOnAction(e -> changeEmailButtonClick());
		btChangePassword.setOnAction(e -> changePasswordButtonClick());
		
		MainPage.sqlm.getUser().getIsLoggedInProperty().addListener((observable) ->
		{
			nav.setDisables(MainPage.sqlm.getUser().isLoggedIn(), false);
		});
		MainPage.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.sqlm.getUser().isLoggedIn());
		MainPage.sqlm.getUser().getIsLoggedInProperty().set(!MainPage.sqlm.getUser().isLoggedIn());
	}
	
	
	/**
	 * Provides functionality for changing user email.
	 */
	private void changeEmailButtonClick()
	{
		newChangeStage("Change your email!", "email", 300, 400);
	}
	
	
	/**
	 * Provides functionality for changing user password.
	 */
	private void changePasswordButtonClick()
	{
		newChangeStage("Change your password!", "password", 300, 400);
	}
	
	
	// TODO reuse this in Login page for new user?
	/**
	 * Helper function - creates a new stage.
	 * In this implementation, allows the user to change either their password or email.
	 * 
	 * @param title
	 */
	private void newChangeStage(String title, String field, int width, int height)
	{
		// Define and set up all necessary containers
		VBox vb = new VBox(15);
		vb.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, width, height);
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.setResizable(false);
		// This line is very important, as it does not allow the user to click on ANYTHING else except the new stage
		// (in the application)... other desktop windows can be accessed
		stage.initModality(Modality.APPLICATION_MODAL);
		
		// Title label
		Label lblTitle = new Label("Change " + field);
		lblTitle.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		
		// Bad entry label
		Label lblWarning = new Label();
		lblWarning.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold; -fx-text-fill: RED");
		
		// Text fields for changing a field
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		tf1.setPromptText("Enter your current " + field + "...");
		tf2.setPromptText("Enter your new " + field + "...");
		tf1.setMaxWidth(280);
		tf2.setMaxWidth(280);
		nav.setTextFieldStyle(tf1);
		nav.setTextFieldStyle(tf2);
		
		// Ok button to close stage 
		Button btOk = new Button("Ok");
		nav.setButtonStyleRound(btOk);
		
		// Set up Vbox and set the scene
		vb.getChildren().addAll(lblTitle, lblWarning, tf1, tf2, btOk);
		stage.setScene(scene);
		
		// Okay button functionality
		// The if statements are redundant but it doesn't work right any other way
		btOk.setOnAction(e -> 
		{
			// If the passed in field is email, check for changing email
			if (Objects.equals(field, "email"))
			{
				if (tf1.getText().isEmpty() || tf2.getText().isEmpty())
				{
					lblWarning.setText("One or more of the fields is empty!");
				}
				
				else if (!Objects.equals(tf1.getText(), MainPage.sqlm.getUser().getLoggedInEmail()))
				{
					lblWarning.setText("Current email does not match!");
				}
				// All good? Change email
				else
				{
					// Such try
					try 
					{
						MainPage.sqlm.getStatement().executeUpdate(
								"UPDATE users SET email = ('"+tf2.getText()+"') WHERE id = ("+MainPage.sqlm.getUser().getLoggedInID()+")");
					} 
					
					// Very catch
					catch (SQLException e1) 
					{
						System.err.println("Could not update email!");
						e1.printStackTrace();
					}
					
					stage.close();
				}
			}
			
			// If the passed in field is password, check for changing password
			if (Objects.equals(field, "password"))
			{
				if (tf1.getText().isEmpty() || tf2.getText().isEmpty())
				{
					lblWarning.setText("One or more of the fields is empty!");
				}
				
				else if (!Objects.equals(tf1.getText(), MainPage.sqlm.getUser().getLoggedInEmail()))
				{
					lblWarning.setText("Current email does not match!");
				}
				
				// All good? Change password
				else
				{
					// Such try
					try 
					{
						MainPage.sqlm.getStatement().executeUpdate(
							"UPDATE users SET password = ('"+tf2.getText()+"') WHERE id = ("+MainPage.sqlm.getUser().getLoggedInID()+")");
					} 
					
					// Very catch
					catch (SQLException e1) 
					{
						System.err.println("Could not update password!");
						e1.printStackTrace();
					}
					
					stage.close();
				}
			}
		});
		
		// Such try
		try
		{
			// Show the stage and wait for some user response (OK button closes it, see above)
			stage.showAndWait();
		}
		
		// Very catch
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			System.err.println("Could not display UI to change your " + field + "!");
		}
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
