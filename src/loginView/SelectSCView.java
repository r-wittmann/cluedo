package loginView;

/**
 * class SelectSCView
 * this view contains just two button to select whether the user wants 
 * to start a server or register as a client on an already existing server.
 * Starting a server will launch a new window.
 * 
 * @author Wittmann Rainer
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import language.LanguageManager;

public class SelectSCView extends StackPane{
	
	//erstelle Grafikelemente
	public Button server;
	public Button client;
	public Button help;
	
	private Label cluedo;
	private Label txt;
	
	private VBox vBox;
	
	public SelectSCView(){
		initStyle();
	}

	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
		
		
		
		//setzen und Stylen des Cluedo Labels
		setCluedo();
		
		//setzen und stylen der Buttons
		setButtons();
		
		//setzen und stylen des txt Labels
		setLabel();
		
		//setzen und stylen der vBox
		setVBox();
		
		help = new Button("?");
		help.setAlignment(Pos.TOP_RIGHT);
		help.setMaxSize(20,20);
		help.setTranslateX(-585);
		help.setTranslateY(315);
		
		//Elemente zur Pane adden
		this.getChildren().addAll(cluedo,vBox,help);
	}
	
	/**
	 * setzt und stylet Cluedo Label
	 * @author Wittmann Rainer
	 */
	public void setCluedo(){
		cluedo = new Label("CLUEDO");
		cluedo.setId("cluedo");
		cluedo.setTranslateY(-230);
	}
	/**
	 * setzt und stylet die Buttons
	 * @author Wittmann Rainer
	 */
	public void setButtons(){
		server = new Button("Server");
		client = new Button("Client");
	}
	/**
	 * setzt und stylet die vBox
	 * @author Wittmann Rainer
	 */
	public void setVBox(){
		vBox = new VBox(20);
		vBox.setId("vbox");	
		
		vBox.getChildren().addAll(txt,server,client);
	}
	/**
	 * setzt und stylet txt Label
	 * @author Wittmann Rainer
	 */
	public void setLabel(){
		txt = new Label();
		txt.textProperty().bind(LanguageManager.getStringProperty("chooseServerOrClient"));
		txt.setId("txt");
	}
}
