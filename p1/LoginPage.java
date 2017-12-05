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
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

	// RIP login sound... :((((( does not like .jar files :(((((
	/** Confirmatory login sound. */
	//private Media soundLogin = new Media(new File("p1/sound/login.wav").toURI().toString());
	
	/** Wrapper for login sound (JavaFX necessary). */
	//private MediaPlayer mpLogin = new MediaPlayer(soundLogin);

	
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
		vbLogin.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.DARKGREY, null, null)}, 
				new BackgroundImage[]{new BackgroundImage(new Image("p1/img/EmbryRiddleEagles.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)}));
		vbLogin.setAlignment(Pos.TOP_CENTER);
		
		// Validate input text and limit length
		TextValidation.tfTextValidator(tfEmail, false);
		TextValidation.tfTextValidator(pfPassword, false);
	    TextValidation.tfLengthLimiter(tfEmail, EMAIL_CHARS_ALLOWED);
	    TextValidation.tfLengthLimiter(pfPassword, PASSWORD_CHARS_ALLOWED);
		
		// Button functionality
		btLogin.setOnAction(e -> loginButtonClick());
		btNewUser.setOnAction(e -> newUserButtonClick());
		
		// Enter key on login textfield
		pfPassword.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				btLogin.fire();
			}
		});
		// Enter key on login textfield
		tfEmail.addEventFilter(KeyEvent.KEY_PRESSED, e ->
		{
			if(e.getCode() == KeyCode.ENTER)
			{
				btLogin.fire();
			}
		});
		
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
			//mpLogin.setVolume(.025);
			//mpLogin.play();
			// Create a confirmation alert for contact information
			Alert alertLogin = new Alert(AlertType.INFORMATION, "You have successfully logged in. Welcome, " + MainPage.sqlm.getUser().getName() + ".");
			alertLogin.setTitle("Logged in!");
			alertLogin.setHeaderText(null);
			alertLogin.initModality(Modality.APPLICATION_MODAL);
			alertLogin.showAndWait();
			
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

		// Final button
		Button btOk = new Button("OK");
		nav.setButtonStyleRound(btOk);
		
		// Set VBox for use, set scene
		vbNewUser.getChildren().addAll(lblTitle, lblWarning, tfName, tfEmail, tfPassword1, tfPassword2, btOk);
		stageNewUser.setScene(sceneNewUser);
		
		// Text validation and limiting
		TextValidation.tfTextValidator(tfName, false);
		TextValidation.tfTextValidator(tfEmail, false);
		TextValidation.tfTextValidator(tfPassword1, false);
		TextValidation.tfTextValidator(tfPassword2, false);
		TextValidation.tfLengthLimiter(tfName, NAME_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfEmail, EMAIL_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfPassword1, PASSWORD_CHARS_ALLOWED);
		TextValidation.tfLengthLimiter(tfPassword2, PASSWORD_CHARS_ALLOWED);
		
		// OK button functionality
		btOk.setOnAction(e ->  
		{
			// Bad entry strings
			String emptyField = "One or more of the fields is empty!";
			String badPasswords = "Passwords do not match!";
			
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
