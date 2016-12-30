package loginView;

/**
 * class CreditView displays the names of the participating students
 * @author Wittmann Rainer, Maurice 
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import language.LanguageManager;

public class CreditView extends VBox {
	
	public Button menu;
	
	private Label credits;
	private Label rang;
	private Label wittmann;
	private Label huebner;
	private Label grill;
	private Label mayer;
	private Label muser;
	private Label steinke;

	public CreditView(){
		initStyle();
	}
	
	private void initStyle(){
		
		//setzen und stylen des Credit Labels
		setCredits();
		
		//setzen und stylen des Buttons
		setButtons();

		//setzen und stylen der Developer
		setDevelopers();
		
		//setzen und stylen der vBox
		setVBox();

		//Elemente zur Pane adden
		this.getChildren().addAll(credits,rang,wittmann,grill,mayer,muser,huebner,steinke,menu);
	}
	/**
	 * setzt und stylet Credits Label
	 * @author Wittmann Rainer
	 */
	public void setCredits(){
		credits = new Label("CREDITS");
		credits.setTextFill(Color.DARKRED);
		credits.setFont(Font.font("Verdana",FontPosture.ITALIC,100));
	}
	/**
	 * setzt und stylet die Buttons
	 * @author Wittmann Rainer
	 */
	public void setButtons(){
		menu = new Button();
		menu.textProperty().bind(LanguageManager.getStringProperty("back"));
		menu.setId("AVElements");
	
	}
	/**
	 * setzt und stylet die Developer labels
	 * @author Wittmann Rainer
	 */
	public void setDevelopers(){
		rang = new Label("Maurice Rang");
		wittmann = new Label("Rainer Wittmann");
		huebner = new Label("Roxanna Hübner");
		grill = new Label("Ludwig Grill");
		mayer = new Label("Paul Mayer");
		muser = new Label("Sarah Muser");
		steinke = new Label("Jonas Steinke");
		
		rang.setTextFill(Color.WHITE);
		wittmann.setTextFill(Color.WHITE);
		grill.setTextFill(Color.WHITE);
		mayer.setTextFill(Color.WHITE);
		muser.setTextFill(Color.WHITE);
		huebner.setTextFill(Color.WHITE);
		steinke.setTextFill(Color.WHITE);
		
		rang.setFont(Font.font(40));
		wittmann.setFont(Font.font(40));
		grill.setFont(Font.font(40));
		mayer.setFont(Font.font(40));
		muser.setFont(Font.font(40));
		huebner.setFont(Font.font(40));
		steinke.setFont(Font.font(40));
	}
	public void setVBox(){
		this.setAlignment(Pos.CENTER);
		this.setSpacing(4);
	}
}
