/**
 * 
 * @author Tyler Procko
 * @date 11/02/17
 * 
 * Class to create a custom stage for displaying helpful info to a user.
 * 
 */

package p1;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class HelpScene 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	/** Button to initiate help scene. */
	private Button btHelp = new Button("Help");

	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * HelpScene constructor. Works through a button instance in the NavBar class.<br>
	 * Displays a helpful list of information for potentially lost users.
	 */
	public HelpScene()
	{
		// Set button style
		btHelp.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.3em; -fx-font-weight: bold; -fx-text-fill: WHITE");
		btHelp.setBackground(null);
		
		// Define and set up all necessary containers
		ScrollPane spHelp = new ScrollPane();
		spHelp.setMinSize(325, 425);
		spHelp.setMaxSize(325, 425);
		// This centers stuff inside the scrollpane
		spHelp.setFitToWidth(true);
		spHelp.setFitToHeight(true);
		spHelp.setStyle("-fx-background: DarkGray");
		
		// Labels have trouble working in scrollpanes...
		// Text is just easier 
		Text txtHelp = new Text("General -\n  Welcome to the EagleListings desktop application. Here you can buy and sell items with "
				+ "other students of ERAU Daytona. If you need more specific help, please refer to the sections below. If you are not "
				+ "a student of Embry-Riddle Aeronautical University in Daytona beach, then kindly refrain from using this service.\n\n"
				
				+ "Login -\n  To login to your EagleListings profile, click the Login button near the top of the application window and "
				+ "enter your appropriate email and password, making sure to capitalize where necessary.\n\n"
				
				+ "Profile -\n  To access your EagleListings profile, click the Profile button near the top of the application window and "
				+ "view your profile. All of your statistics will be displayed.\n\n"
				
				+ "Buying -\n  To view items for sale, click the drop-down box near the top of the application, or enter a search term in "
				+ "the search bar.\n\n"
				
				+ "Selling -\n  To list an item for sale, first create an account from the Login page and login to your account. Then "
				+ "click the Selling button near the top of the application window to open the Selling page. While there, list your "
				+ "item appropriately.\n\n"
				
				+ "DISCLAIMER -\n  EagleListings and its affiliates assume no liability for any events, detrimental or otherwise, that "
				+ "occur once you, the user, performs an action while utilizing this service.");
		
		// Necessary to align the text to the stage... otherwise it aligns to its own max text width
		//txtHelp.wrappingWidthProperty().bind(sceneHelp.widthProperty());
		txtHelp.setWrappingWidth(320);
		txtHelp.setTextAlignment(TextAlignment.JUSTIFY);
		txtHelp.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 1.15em");
		
		// Set the scene and stage for use
		Scene sceneHelp = new Scene(spHelp);
		Stage stageHelp = new Stage();
		stageHelp.setTitle("Need some help?");
		stageHelp.setResizable(false);
		stageHelp.initModality(Modality.APPLICATION_MODAL);

		// Set scrollpane content and set the stage's scene
		spHelp.setContent(txtHelp);
		stageHelp.setScene(sceneHelp);
		
		// Help button functionality
		btHelp.setOnAction(e -> 
		{
			// Such try
			try
			{
				stageHelp.show();
			}
			
			// Very catch
			catch (IllegalStateException exc)
			{
				exc.printStackTrace();
				System.err.println("Could not display UI for help page!");
			}
		});
	}

	
	/**
	 * Getter
	 * 
	 * @return btHelp
	 */
	protected Button getHelpButton()
	{
		return btHelp;
	}
}
