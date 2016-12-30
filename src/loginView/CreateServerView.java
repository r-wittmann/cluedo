package loginView;

/**
 * class CreateServerView
 * this class opens a new window to display ip, port and loged in players for created server
 * no entrys needed. The window is just to display the serverdata
 * 
 * @author Wittmann Rainer
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import language.LanguageManager;

public class CreateServerView extends GridPane{
	
	public Button kill;
	
	Label ipLbl;
	Label portLbl;
	
	public TextField iptxt;
	public TextField porttxt;

	//Elemente für updateClient erstellen
	Label tempname;
	Label tempgroup;

	private VBox clientVBox = new VBox(1);
	private VBox clientValue = new VBox(1);
	private HBox vBoxHeader = new HBox(1);
	
	
	public CreateServerView(){
		initStyle();
	}
	
	/**
	 * allows to display ip address and port in their textfields
	 * @param ip
	 * @param port
	 * @author Wittmann Rainer
	 */
	@SuppressWarnings("unused")
	private void updateServer(String ip, int port){
		this.iptxt.setText(ip);
		this.porttxt.setText(Integer.toString(port));
	}
	
	/**
	 * allows to display clients and update them
	 * @param clientList
	 * @author Wittmann Rainer
	 */
	public void updateClient(ArrayList<ArrayList<String>> clientList){
		
		clientValue.getChildren().clear();
		for(int i = 0; i < clientList.size(); i++){
			tempname = new Label(" " + clientList.get(i).get(0));
			tempgroup = new Label(" " + clientList.get(i).get(1));
			HBox temphBox = new HBox(1);
			temphBox.getChildren().addAll(tempname,tempgroup);
			clientValue.getChildren().add(temphBox);
			
			initClientStyle();
		}		
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	public void initStyle(){

		//CreateServerView gestalten
		this.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
		this.setPadding(new Insets(20,20,20,20));
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px");
		
		//Textfelder erstellen und gestalten
		initTextStyle();
	
		//Button setzen und gestalten
		initButtonStyle();
		
		//vBoxHeader gestalten
		Label nameHeader = new Label();
		nameHeader.textProperty().bind(LanguageManager.getStringProperty("nameColon"));
		Label groupHeader = new Label();
		groupHeader.textProperty().bind(LanguageManager.getStringProperty("group"));
		
		nameHeader.setMinSize(148, 30);
		nameHeader.setStyle("-fx-background-color:#404040;"
				+ "-fx-text-fill:WHITE");
		
		groupHeader.setMinSize(147, 30);
		groupHeader.setStyle("-fx-background-color:#404040;"
				+ "-fx-text-fill:WHITE");
		
		vBoxHeader.getChildren().addAll(nameHeader,groupHeader);
		
		//VBox zum positionieren der linken Elemente
		VBox left = new VBox(10);
		left.getChildren().addAll(ipLbl,iptxt,portLbl,porttxt,kill);
		
		
		//clientVBox
		clientVBox.setMinSize(300, 190);
		clientVBox.setMaxSize(300, 190);
		clientVBox.setStyle("-fx-border-width:2px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-background-color:DARKRED");
		
		clientValue.setStyle("-fx-background-color:DARKRED");
		
		ScrollPane valueSP = new ScrollPane();
		valueSP.setMaxSize(295, 155);
		valueSP.setMinSize(295, 155);
		valueSP.setContent(clientValue);
		
		clientValue.setMinHeight(155);
		
		clientVBox.getChildren().addAll(vBoxHeader,valueSP);
		
		//HBox zum positionieren
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(left,clientVBox);
		
		this.getChildren().addAll(hBox);
	}
	
	/**
	 * setzt und stylet die Textfelder und Labels
	 * @author Wittmann Rainer
	 */
	
	private void initTextStyle() {

		iptxt = new TextField();
		porttxt = new TextField();
		
		ipLbl = new Label();
		ipLbl.textProperty().bind(LanguageManager.getStringProperty("ip"));
		portLbl = new Label();
		portLbl.textProperty().bind(LanguageManager.getStringProperty("port"));
		
		//Size
		iptxt.setMinSize(150, 30);
		porttxt.setMinSize(150, 30);
		
		ipLbl.setMinSize(150, 30);
		portLbl.setMinSize(150, 30);
		
		//Alignment
		ipLbl.setAlignment(Pos.CENTER);
		portLbl.setAlignment(Pos.CENTER);
		
		//Style
		iptxt.setStyle("-fx-background-color:DARKRED;"
				+ "-fx-text-fill:WHITE;");
		iptxt.setEditable(false);
		porttxt.setStyle("-fx-background-color:DARKRED;"
				+ "-fx-text-fill:WHITE;");
		porttxt.setEditable(false);
		
		ipLbl.setStyle("-fx-text-fill:WHITE");
		portLbl.setStyle("-fx-text-fill:WHITE");
	}
	/**
	 * setzt und stylet den Button
	 * @author Wittmann Rainer
	 */
	public void initButtonStyle(){
		kill = new Button("Kill Server");
		kill.setMinSize(150, 30);
		kill.setTextFill(Color.WHITE);
		kill.setStyle("-fx-background-color:DARKRED");
	}
	/**
	 * method styles the output of clientUpdate
	 * @author Wittmann Rainer
	 */
	public void initClientStyle(){
		tempname.setMinSize(147, 20);
		tempname.setStyle("-fx-background-color:#a0a0a0");
		
		tempgroup.setMinSize(130, 20);
		tempgroup.setStyle("-fx-background-color:#a0a0a0");
	}
}
