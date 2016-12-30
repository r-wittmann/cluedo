package loginView;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import language.CustomComboBox;

/**
 * class contains chatwindow 
 * 
 * @author Maurice
 *
 */

public class ChatView extends GridPane{
	
	public Button send;
	
	public TextArea chat;
	public TextArea eingabe;
	
	public ComboBox<String> dropdown;
	
	public ChatView(){
		initStyle();
		
	}

	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle() {
		
		//set textarea for chat
		chat = new TextArea();
		chat.setId("chat");
		chat.setEditable(false);
		chat.setWrapText(true);
		
		
		//set textfield for message
		eingabe = new TextArea();
		eingabe.setId("eingabe");
		
		//set button to send message
		send = new Button("Send");
		send.setId("send");
		
		//set dropdown menu for potencial players
			dropdown = new CustomComboBox(new String[]{"all"},"sendTo");
			dropdown.setId("dropdown");
		
		//set position of elements
		GridPane.setConstraints(chat, 0, 0, 2, 1);
		GridPane.setConstraints(eingabe, 0, 2, 2, 1);
		GridPane.setConstraints(dropdown, 0, 1);
		GridPane.setConstraints(send, 1, 1);
		
		
		//add elements 
		this.getChildren().addAll(chat,eingabe,send,dropdown);
	}
	
	/**
	 * Hängt eine neue Nachricht ans Chatfenster an mit entsprechender anzeige
	 * von wem und an wen eine Nachricht gesendet wird.
	 * @param sender
	 * @param message
	 * @param sendTo
	 * @param gameID
	 * @author Wittmann Rainer
	 */
	public void newChatMessage(String sender, String message, String sendTo, int gameID){
		if(sendTo != "") chat.appendText(sender + "@" + sendTo +  ": " + message + "\n");
		else if (gameID != 0) chat.appendText(sender + "@Spiel " + gameID +  ": " + message + "\n");
		else chat.appendText(sender + "@all" +  ": " + message + "\n");
	}
	
	
	/**
	 * Method generates Values for DropDown to select 
	 * @param chatList
	 * @author: Rainer
	 */
	public void addDropdownValue(ArrayList<ArrayList<String>> chatList){
		dropdown.getItems().clear();
		
		dropdown.getItems().add("Alle");
		//Spiele ins Dropdown schreiben
		if(chatList.get(0) != null){
			for(int i = 0; i < chatList.get(0).size(); i++){
				dropdown.getItems().add("GameID: " + chatList.get(0).get(i));
			}
		}
		//Spieler ins Dropdown schreiben
		if(chatList.get(1) != null){
			for(int j = 0; j < chatList.get(1).size(); j++){
				dropdown.getItems().add(chatList.get(1).get(j));
			}
		}
	}
	
}
