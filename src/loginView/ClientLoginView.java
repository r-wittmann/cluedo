package loginView;

/**
 * class ClientLoginView
 * this class allows a client to login on a privius created server
 * client has to enter name, group, ip and port for login
 *
 * @author Wittmann Rainer
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;

import language.LanguageManager;

public class ClientLoginView extends StackPane{
	private static final Logger log = LogManager.getLogger(ClientLoginView.class);
	
	//erstelle TextFields
	public TextField nametxt;
	public TextField grouptxt;
	public TextField iptxt;
	public TextField porttxt;
	
	//erstelle SaveButton
	public Button submit = new Button();
	public Button help;
	
	
	//Elemente für Server Liste erstellen
	Label tempip;
	Label tempport;
	Label tempgroup;
	VBox serverVBox = new VBox(1);
	VBox valueVBox = new VBox(1);
	HBox serverHeader = new HBox(1);
	
	
	public ClientLoginView(){
		initStyle();
    }

    /**
     * allows to display an up to date ServerList
     *
     * @author Wittmann Rainer
     */
    public void updateServer(String ip, int port, String group) {
		
		tempip = new Label(" " + ip);
		tempport = new Label(" " + Integer.toString(port));
		tempgroup = new Label(" " + group);
		HBox temphBox = new HBox(1);
		temphBox.getChildren().addAll(tempip, tempport, tempgroup);
		valueVBox.getChildren().add(temphBox);
		
		initServerStyle();
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
	
	    //Help Button erstellen
        help = new Button("?");
        help.setAlignment(Pos.TOP_RIGHT);
        help.setMaxSize(20, 20);
        help.setTranslateX(-585);
        help.setTranslateY(315);
		
		//erstelle und gestalten der Textfelder, Label und des Buttons
		Label name = new Label();
		name.textProperty().bind(LanguageManager.getStringProperty("nameColon"));
		Label group = new Label();
		group.textProperty().bind(LanguageManager.getStringProperty("group"));
		Label ip = new Label();
		ip.textProperty().bind(LanguageManager.getStringProperty("ip"));
		Label port = new Label();
		port.textProperty().bind(LanguageManager.getStringProperty("port"));
		
		name.setAlignment(Pos.CENTER_LEFT);
		group.setAlignment(Pos.CENTER_LEFT);
		ip.setAlignment(Pos.CENTER_LEFT);
		port.setAlignment(Pos.CENTER_LEFT);
		
		name.setMinSize(70, 30);
		group.setMinSize(70, 30);
		ip.setMinSize(70, 30);
		port.setMinSize(70, 30);
		
		name.setTextFill(Color.WHITE);
		group.setTextFill(Color.WHITE);
		ip.setTextFill(Color.WHITE);
		port.setTextFill(Color.WHITE);
		
		nametxt = new TextField();
		grouptxt = new TextField();
		iptxt = new TextField();
		porttxt = new TextField();
		
		nametxt.setMaxSize(155, 30);
		grouptxt.setMaxSize(155, 30);
		iptxt.setMaxSize(155, 30);
		porttxt.setMaxSize(155, 30);
		
		nametxt.setStyle("-fx-background-color:DARKRED;"
				+ "-fx-text-fill:WHITE");
		grouptxt.setStyle("-fx-background-color:DARKRED;"
				+ "-fx-text-fill:WHITE");
		iptxt.setStyle("-fx-background-color:DARKRED;"
				+ "-fx-text-fill:WHITE");
		porttxt.setStyle("-fx-background-color:DARKRED;"
				+ "-fx-text-fill:WHITE");
		
		Date date = new Date();
		String random = String.valueOf(date.getTime());
		random = random.substring(random.length() - 3);
		
		nametxt.setText("DefaultName" + random);
		grouptxt.setText("muffigemotten");
		//iptxt.setText("vanuabalavu.pms.ifi.lmu.de");
		
		   try {
            iptxt.setText(Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            log.error("could not set ip to own ip");
            log.error(e);
            iptxt.setText("localhost");
        }
        porttxt.setText("30305");
		
		submit.textProperty().bind(LanguageManager.getStringProperty("submit"));
		submit.setStyle("-fx-background-color:DARKRED");
		submit.setTextFill(Color.WHITE);
		
		submit.setMinSize(230, 40);
		submit.setMaxSize(230, 40);
		
		Label cluedo = new Label("CLUEDO");
		cluedo.setTextFill(Color.DARKRED);
		cluedo.setFont(Font.font("Verdana",FontPosture.ITALIC,100));
		cluedo.setTranslateY(-230);
		
		//erstellen von V und HBoxes zum gruppieren und positionieren der Elemente
		HBox hname = new HBox(5);
		
		hname.setAlignment(Pos.CENTER);
		hname.getChildren().addAll(name,nametxt);
		
		HBox hgroup = new HBox(5);
		
		hgroup.setAlignment(Pos.CENTER);
		hgroup.getChildren().addAll(group,grouptxt);
		
		HBox hip = new HBox(5);
		
		hip.setAlignment(Pos.CENTER);
		hip.getChildren().addAll(ip,iptxt);
		
		HBox hport = new HBox(5);
		
		hport.setAlignment(Pos.CENTER);
		hport.getChildren().addAll(port,porttxt);
		
		
		VBox vBox = new VBox(20);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(hname,hgroup,hip,hport,submit);
		
		//Serverteil gestalten
		Label ipHeader = new Label();
		ipHeader.textProperty().bind(LanguageManager.getStringProperty("ip"));
		Label portHeader = new Label();
		portHeader.textProperty().bind(LanguageManager.getStringProperty("port"));
		Label groupHeader = new Label();
		groupHeader.textProperty().bind(LanguageManager.getStringProperty("group"));
		
		ipHeader.setMinSize(120, 30);
		ipHeader.setStyle("-fx-background-color:#404040;"
				+ "-fx-text-fill:WHITE");
		
		portHeader.setMinSize(60, 30);
		portHeader.setStyle("-fx-background-color:#404040;"
				+ "-fx-text-fill:WHITE");
		
		groupHeader.setMinSize(120, 30);
		groupHeader.setStyle("-fx-background-color:#404040;"
				+ "-fx-text-fill:WHITE");
		
		serverHeader.getChildren().addAll(ipHeader, portHeader, groupHeader);

		valueVBox.setMinHeight(210);
        valueVBox.setStyle("-fx-background-color:DARKRED");

        ScrollPane valueSP = new ScrollPane();
        valueSP.setMaxSize(302, 210);
        valueSP.setMinSize(302, 210);
        valueSP.setContent(valueVBox);
        valueSP.setStyle("-fx-background:DARKRED");

        serverVBox.setMinHeight(240);
        serverVBox.setStyle("-fx-background-color:DARKRED;"
                + "-fx-border-color:DARKRED;"
                + "-fx-border-width:2px");

        serverVBox.getChildren().addAll(serverHeader, valueSP);

        //erstellen einer HBox
        HBox hbox = new HBox(20);

        hbox.setStyle("-fx-background-color:#585858;"
                + "-fx-border-color:DARKRED;"
                + "-fx-border-width:10px;"
                + "-fx-border-radius:3px;"
                + "-fx-background-radius:3px");
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(15, 15, 15, 15));
        hbox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        hbox.getChildren().addAll(vBox, serverVBox);

        this.getChildren().addAll(cluedo, hbox, help);
    }

 //getter und setter für TextFields
    public String getNametxt() {
        return nametxt.getText();
    }

    public String getGrouptxt() {
        return grouptxt.getText();
    }

    public String getIptxt() {
        return iptxt.getText();
    }

    public int getPorttxt() {
        return Integer.parseInt(porttxt.getText());
    }

    public void initServerStyle() {
        tempip.setMinSize(119, 20);
        tempip.setStyle("-fx-background-color:#a0a0a0");

        tempport.setMinSize(60, 20);
        tempport.setStyle("-fx-background-color:#a0a0a0");

        tempgroup.setMinSize(106, 20);
        tempgroup.setStyle("-fx-background-color:#a0a0a0");
    }
}