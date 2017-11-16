/**
 * 
 * @author Tyler Procko
 * @date 09/2017 - 10/2017
 * 
 * UI class for user login.<br>
 * Only accessible if the user is not logged in.
 * 
 */

package p1;
import java.io.File;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginPage 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Characters allowed in the email. */
	private static final int EMAIL_CHARS_ALLOWED = 100;
	
	/** Characters allowed in the username. */
	private static final int NAME_CHARS_ALLOWED = 100;
	
	/** Characters allowed in the password. */
	private static final int PASSWORD_CHARS_ALLOWED = 150;
	
	/** Container VBox for login page */
	private VBox vbLogin = new VBox(50);
	
	/** Navigation bar instance */
	private NavBar nav = new NavBar();
	
	/** Title label for login page */
	private Label lblLogin = new Label("Login, Eagle.");
	
	/** Gridpane to hold login functionality. */
	private GridPane gpUserPass = new GridPane();
	
	/** Label for bad user entry. */
	private Label lblBadEntry = new Label();
	
	/** Textfield to take in user email. */
	private TextField tfEmail = new TextField();
	
	/** Textfield to take in user password. */
	private PasswordField pfPassword = new PasswordField();

	/** Button to log the user in. */
	private Button btLogin = new Button("Login");
	
	/** Button to create a new user account */
	private Button btNewUser = new Button("New?");

	/** Confirmatory login sound. */
	private Media soundLogin = new Media(new File("p1/sound/login.wav").toURI().toString());
	
	/** Wrapper for login sound (JavaFX necessary). */
	private MediaPlayer mpLogin = new MediaPlayer(soundLogin);
	
	/**
	 * Needed to edit the user's selected image URI (otherwise cannot edit its value
     * outside of the .setOnAction block in the new user method).<br>
	 * Lambda expressions are quite annoying when editing an outside variable is necessary.
	 */
	private String imgPath;
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Default constructor for Login page.
	 */
	public LoginPage()
	{
		// Set title label's look
		lblLogin.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		
		// Set the bad entry label's properties (min width is necessary as it only shows if bad input is given,
		// updating the existing GUI in such a way as to cause it to move unattractively)
		lblBadEntry.setMinWidth(220);
		lblBadEntry.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold; -fx-text-fill: RED");
		
		// Set all textfields' properties
		tfEmail.setPromptText("Enter email...");
		pfPassword.setPromptText("Enter password...");
		nav.setTextFieldStyle(tfEmail);
		nav.setTextFieldStyle(pfPassword);		
		
		// Set buttons' styles
		nav.setButtonStyleSharp(btLogin);
		nav.setButtonStyleRound(btNewUser);
	
		// Add all nodes to the gridpane
		gpUserPass.add(lblBadEntry, 0, 0);
		gpUserPass.add(tfEmail, 0, 1);
		gpUserPass.add(pfPassword, 0, 2);
		gpUserPass.add(btLogin, 1, 2);
		gpUserPass.add(btNewUser, 0, 3);
		gpUserPass.setVgap(20);
		gpUserPass.setMaxWidth(300);
		gpUserPass.setAlignment(Pos.CENTER);
		// Necessary to get the new user button to center
		GridPane.setColumnSpan(btNewUser, 2);
		GridPane.setHalignment(btNewUser, HPos.CENTER);
		
		// Set VBox for use
		vbLogin.getChildren().addAll(nav.getNavBar(), lblLogin, gpUserPass);
		vbLogin.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbLogin.setAlignment(Pos.TOP_CENTER);
		
		// Validate input text and limit length
		SQLManager.tfTextValidator(tfEmail, false);
		SQLManager.tfTextValidator(pfPassword, false);
		SQLManager.tfLengthLimiter(tfEmail, EMAIL_CHARS_ALLOWED);
		SQLManager.tfLengthLimiter(pfPassword, PASSWORD_CHARS_ALLOWED);
		
		// Button functionality
		btLogin.setOnAction(e -> loginButtonClick());
		btNewUser.setOnAction(e -> newUserButtonClick());
		
		// Apply a listener to the navbar buttons
		nav.navListener(false);
	}
	
	
	/**
	 * Functionality for Login button click.
	 */
	private void loginButtonClick()
	{
		// Attempt login (refer to the login method in the User class)
		MainPage.sqlm.getUser().login(tfEmail.getText(), pfPassword.getText());
		
		// If the user is not logged in after the login attempt, then the email and password must not exist (or do not match)
		if (!MainPage.sqlm.getUser().isLoggedIn())
		{
			lblBadEntry.setText("Email and password not found!");
		}
		
		// Otherwise, we are logged in, so play the login sound and return to the main page
		else
		{
			mpLogin.setVolume(.025);
			mpLogin.play();
			MainPage.instance.getStage().getScene().setRoot(MainPage.instance.getMainVBox());
		}
	}
	
	
	/**
	 * Functionality for New User button click.
	 * Sets up a new stage that the user cannot click off of.
	 */
	private void newUserButtonClick()
	{
		// Define and set up all necessary containers
		VBox vbNewUser = new VBox(15);
		vbNewUser.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbNewUser.setAlignment(Pos.CENTER);
		Scene sceneNewUser = new Scene(vbNewUser, 300, 400);
		Stage stageNewUser = new Stage();
		stageNewUser.setTitle("Create your account!");
		stageNewUser.setResizable(false);
		// This line is very important, as it does not allow the user to click on ANYTHING else except the new stage
		// (in the application)... other desktop windows can be accessed
		stageNewUser.initModality(Modality.APPLICATION_MODAL);
		
		// Title label
		Label lblTitle = new Label("Create account");
		lblTitle.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 3em; -fx-font-weight: bold");
		
		// Bad entry label
		Label lblWarning = new Label();
		lblWarning.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.2em; -fx-font-weight: bold; -fx-text-fill: RED");
		
		// Textfield for the new user's name
		TextField tfName = new TextField();
		tfName.setPromptText("Enter your name...");
		tfName.setMaxWidth(280);
		nav.setTextFieldStyle(tfName);
		
		// Textfield for the new user's email
		TextField tfEmail = new TextField();
		tfEmail.setPromptText("Enter your email...");
		tfEmail.setMaxWidth(280);
		nav.setTextFieldStyle(tfEmail);
		
		// Two textfields for the new user's password (to verify)
		TextField tfPassword1 = new TextField(), tfPassword2 = new TextField();
		tfPassword1.setMaxWidth(280);
		tfPassword2.setMaxWidth(280);
		tfPassword1.setPromptText("Enter a password...");
		tfPassword2.setPromptText("Re-enter your password...");
		nav.setTextFieldStyle(tfPassword1);
		nav.setTextFieldStyle(tfPassword2);

		// Button for choosing a profile image
		Button btChooseImg = new Button("Choose profile image");
		nav.setButtonStyleRound(btChooseImg);

		// Final button
		Button btOk = new Button("OK");
		nav.setButtonStyleRound(btOk);
		
		// Set VBox for use, set scene
		vbNewUser.getChildren().addAll(lblTitle, lblWarning, tfName, tfEmail, tfPassword1, tfPassword2, btChooseImg, btOk);
		stageNewUser.setScene(sceneNewUser);
		
		// Text validation and limiting
		SQLManager.tfTextValidator(tfName, false);
		SQLManager.tfTextValidator(tfEmail, false);
		SQLManager.tfTextValidator(tfPassword1, false);
		SQLManager.tfTextValidator(tfPassword2, false);
		SQLManager.tfLengthLimiter(tfName, NAME_CHARS_ALLOWED);
		SQLManager.tfLengthLimiter(tfEmail, EMAIL_CHARS_ALLOWED);
		SQLManager.tfLengthLimiter(tfPassword1, PASSWORD_CHARS_ALLOWED);
		SQLManager.tfLengthLimiter(tfPassword2, PASSWORD_CHARS_ALLOWED);
		
		//TODO
		// Sending it to Carson's server
		// Image choosing button functionality
		btChooseImg.setOnAction(e -> 
		{
			// Create a filechooser and allow the user to only select .png and .jpg files
			FileChooser imgChooser = new FileChooser();
			imgChooser.setTitle("Select your avatar");
			imgChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
			File selectedFile = imgChooser.showOpenDialog(stageNewUser);
			// If all is well with the file, set the path to its system URI
			if (selectedFile != null) 
			{
				imgPath = selectedFile.toURI().toString();
				System.out.println(imgPath);
				System.out.println("Selected profile image successfully!");
			}
		});
		
		// OK button functionality
		btOk.setOnAction(e ->  
		{
			// Bad entry strings
			String emptyField = "One or more of the fields is empty!";
			String badPasswords = "Passwords do not match!";
			
			// If the image path was not previously set, i.e. the user did not select a profile image,
			// give it the default image's path 
			// Needs prefix file:/
			if (imgPath == null)
			{
				imgPath = "file:/p1/img/default_profile.png";
			}
			
			// If any field is equal AND the passwords do not match, set the bad entry label's text
			if ((tfName.getText().equals("") || tfEmail.getText().equals("") || tfPassword1.getText().equals("") || tfPassword2.getText().equals("")) 
			     && (!tfPassword1.getText().equals(tfPassword2.getText())))
			{
				lblWarning.setText(emptyField + "\n" + badPasswords);
			}
			
			// If any field is empty, set the bad entry label's text
			else if(tfName.getText().equals("") || tfEmail.getText().equals("") || tfPassword1.getText().equals("") || tfPassword2.getText().equals(""))
			{
				lblWarning.setText(emptyField);
			}
			
			// If the passwords do not match, set the bad entry label's text
			else if (!tfPassword1.getText().equals(tfPassword2.getText()))
			{
				lblWarning.setText(badPasswords);
			}
			
			// Otherwise, all is well, so close the stage and create a new user, given the user's input
			else
			{
				stageNewUser.close();
				MainPage.sqlm.getUser().createNewUser(tfName.getText(), tfEmail.getText(), tfPassword1.getText());
			}
		});
		
		// Such try
		try
		{
			// Show the stage and wait for some user response (OK button closes it, see above)
			stageNewUser.showAndWait();
		}
		
		// Very catch
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			System.err.println("Could not display UI to create new account!");
		}
	}
	
	
	/**
	 * Getter
	 * 
	 * @return vbLogin
	 */
	public VBox getRootPane()
	{
		return vbLogin;
	}
}
