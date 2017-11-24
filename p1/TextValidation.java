/**
 * @author Tyler Procko
 * @date 11/2017
 * 
 * Class containing static methods to 'validate' text input into a variety
 * of JavaFX nodes (TextFields, TextAreas, etc.). Essentially, these methods
 * allow a node's entered text to be limited to a specified regex.
 */


package p1;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class TextValidation 
{
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	/**
	 * Limits user input in a textfield to a specified length.
	 * Very important, as the DB columns have specified sizes.
	 * 
	 * @param tf
	 * @param length
	 */
	public static void tfLengthLimiter(TextField tf, int length)
	{
		tf.textProperty().addListener((var, oldText, newText) -> 
		{
			if (newText.length()-1 >= length)
			{
				tf.setText(oldText);
			}
     	});
	}
	
	
	/**
	 * Prevents SQL injection by limiting user input in JavaFX textfields.
	 * Allows for a regular limitation or numbers only.
	 * 
	 * @param tf
	 */
	public static void tfTextValidator(TextField tf, boolean numbersOnly)
	{
		// Numbers only
		if (numbersOnly)
		{
			// Check if it matches the regex 0-9
			tfRegexChecker(tf, "[0-9]");
		}
		
		// Regular limitation
		else
		{
			// Check if it matches the regex A-Z, a-z, 0-9, periods, at symbols and exclamation points
			tfRegexChecker(tf, "[a-z A-Z 0-9 . @ !]");
		}
	}
	
	
	/**
	 * Prevents SQL injection by limiting user input in JavaFX textareas.
	 * 
	 * @param ta
	 */
	public static void taTextValidator(TextArea ta)
	{
		// Listener bound to the textfield
		ta.textProperty().addListener((var, oldText, newText) ->
		{
			if(newText.length() > 0)
			{
				// Essentially checking the last character (what the user is inputting REAL-TIME),
				// to see if it matches the regex A-Z, a-z, 0-9 and periods
				if (!(newText.substring(newText.length() - 1)).matches("[a-z A-Z 0-9 .]"))
				{
					ta.setText(oldText);
				}
			}
		});
	}
	
	
	/**
	 * Private method used in the tfTextValidator method in this class.
	 * 
	 * @param tf
	 * @param regex
	 */
	private static void tfRegexChecker(TextField tf, String regex)
	{
		tf.textProperty().addListener((var, oldText, newText) ->
		{
			if(newText.length() > 0)
			{
				// Essentially checking the last character (what the user is inputting REAL-TIME),
				// to see if it matches the given regex
				// If the character entered is bad, reset the textfield to the previous
				if (!(newText.substring(newText.length() - 1)).matches(regex))
				{
					tf.setText(oldText);
				}
			}
		});
	}
}
