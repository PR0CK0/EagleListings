* @author Asia Wright 
 * @date 09/2017 - 11/2017 
 * 
 * UI class for viewing a specific, selected item.<br>
 * 
 */


package p1;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

//TODO
// Everything.

public class SpecificItemPage 
{
	/* ---------------------- */
	/* ----- ATTRIBUTES ----- */
	/* ---------------------- */
	
	
	//Vboxes for the image and overall specific item page
	private VBox vbspecificItemPage= new VBox(50);
	
	private VBox imageholder= new VBox(20);
	
	///Labels that all items will relay on
	
	protected Label itemtitle;
	protected Label itemdetail;
	protected Label itemprice;
	protected Label itemcondition;
	protected Label sellerinfo;
	
	private NavBar nav= new NavBar();
	protected GridPane gpforpage=new GridPane();
	
	

	
	
	
	/* -------------------------------- */
	/* ----- METHODS/CONSTRUCTORS ----- */
	/* -------------------------------- */
	
	//this method displays the item page based on what type of item it is
	protected SpecificItemPage(String Item){
		itemtitle=new Label("Item title");
		itemdetail=new Label("Detail for item:");
		itemprice=new Label("Price:");
		itemcondition=new Label("Condition:");
		sellerinfo=new Label("seller Id");
		
		if(Item.equals("Books")){
			 Label bcourse=new Label("Course Id");
			 
			 
			 vbspecificItemPage.getChildren().addAll(bcourse);
		}
		
		else if(Item.equals("Vehicles")){
			Label Miles= new Label("Miles");
			Label Year= new Label("Year");
			Label Type=new Label("Type of car");
			Label Brand=new Label("Brand");
			
			
			vbspecificItemPage.getChildren().addAll(Miles,Year, Type, Brand);
		}
		
		else if (Item.equals("Rooms")){
			Label ambeds=new Label("amount of beds");
			Label ambath=new Label("Number of bath");
			Label address=new Label("Address");
			
			vbspecificItemPage.getChildren().addAll(ambeds, ambath,address);
		}
		
		else if (Item.equals("Furniture")){
			Label type= new Label("Room category");
			vbspecificItemPage.getChildren().addAll(type);
		}
		
		gpforpage.setAlignment(Pos.TOP_CENTER);	
		gpforpage.add(imageholder, 0, 0);
		
		
		vbspecificItemPage.setAlignment(Pos.TOP_CENTER);
		vbspecificItemPage.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		vbspecificItemPage.getChildren().addAll(itemtitle, itemprice, itemdetail, itemcondition, sellerinfo,gpforpage);
		
	}
}
