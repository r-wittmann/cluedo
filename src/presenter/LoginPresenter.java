
package presenter;

/**
 * class Presenter
 * this class handels all click events for the login process
 * also it starts the new window for every joined or created game.
 *
 * @author Wittmann Rainer, Maurice
 */

import Ki.Ki;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import language.LanguageManager;
import language.LanguageManager.SupportedLanguages;
import loginView.CreateServerView;
import loginView.GlobalLoginView;
import model.Gamefield;
import model.Global;
import model.Player;
import network.client.ClientSent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.GlobalView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class LoginPresenter {

    private static final Logger log = LogManager.getLogger(LoginPresenter.class);

    public GlobalLoginView globalLoginView;
    private GlobalView globalView;
    private Global clientGlobal;
    private ClientSent clientSent;
    private HashMap<Integer, GlobalPresenter> presenterHashMap;
    
    private String ip = new String();
    private int port = 0;

    public LoginPresenter(GlobalLoginView globalLoginView) {
        this.globalLoginView = globalLoginView;
        presenterHashMap = new HashMap<Integer, GlobalPresenter>();
        clientGlobal = new Global();
    }

    //CreateServerPresenter
    public void handleServer(MouseEvent event) {
//    	Global wird im CreateServerPresenter erstellt
//		TODO Global im Server Construktor erstellen

        CreateServerView createServerView = new CreateServerView();
        CreateServerPresenter createServerPresenter = new CreateServerPresenter(createServerView);
        //neues Fenster öffnen

        Scene scene = new Scene(createServerView);
        Stage createServerViewStage = new Stage();
        createServerViewStage.setScene(scene);
        createServerViewStage.show();
        createServerViewStage.requestFocus();

    }
    //StartClientPresenter
    public void handleClient(MouseEvent event) {
        log.info("Client Pressed");

        globalLoginView.selectSCView.setVisible(false);
        globalLoginView.clientLoginView.setVisible(true);

        //CREATE AND START CLIENT IN BACKGROUND
        clientGlobal = new Global();
        GUIReactToServer guiReactToServer = new GUIReactToServer(this);
        clientSent = new ClientSent(clientGlobal, guiReactToServer);
        //Thread clientThread = new Thread(clientSent);
        //fix
        //Platform.runLater(clientThread);
        //clientThread.start();
    }
    
    /**
     * Return Client Sent
     * only use by Client!
     * @return client sent
     * @author Ludwig
     */
    public ClientSent getClientSent(){
        return this.clientSent;
    }
    
    //LoginPresenter
    public void handleSubmit(MouseEvent event) {
        log.info("Submit Pressed");

        ip = globalLoginView.clientLoginView.getIptxt();
        port = globalLoginView.clientLoginView.getPorttxt();
        String nick = globalLoginView.clientLoginView.getNametxt();
        String group = globalLoginView.clientLoginView.getGrouptxt();

        log.info(ip + " " + port + " " + nick + " " + group);
        clientGlobal.setServerIp(ip);
        clientGlobal.setServerPort(port);
        //LOGIN TO SERVER
        clientSent.login(ip, port, nick, group);
    }

    //Server->LOGIN Successful
    public void loginClientReady() {
        //log.info("loginClientReady");
        globalLoginView.clientLoginView.setVisible(false);
        globalLoginView.lobbyView.setVisible(true);
        globalLoginView.imgiv.setVisible(false);

        updateGamesPresenter();

        globalLoginView.lobbyView.updatePlayer(clientGlobal.getClients());


//        //LobbyPresenter Update Player / Games View -----> LobbyView
//        //Update Player-Table on Interval
//        Timeline timeline2 = new Timeline(new KeyFrame(
//                Duration.millis(5000),
//                //Get all Player-Info on Server and show it in View
//                ae2 -> globalLoginView.lobbyView.updatePlayer(clientGlobal.getClients())));
//        timeline2.setCycleCount(Animation.INDEFINITE);
//        timeline2.play();
//
//        //Update Games-Table on Interval
//        Timeline timeline1 = new Timeline(new KeyFrame(
//                Duration.millis(5000),
//                //Get all Game-Info on Server and show it in View
//                ae1 -> updateGamesPresenter()));
//        timeline1.setCycleCount(Animation.INDEFINITE);
//        timeline1.play();
        
        


    }
    
    public void updatePlayer() {
        globalLoginView.lobbyView.updatePlayer(clientGlobal.getClients());
    }
    
    public void updateGamesPresenter() {
        //log.info("updateGamesPresenter");
        ArrayList<ArrayList<String>> gameList = new ArrayList<ArrayList<String>>();
        HashMap<Integer, Gamefield> gamefields = clientGlobal.getGamefields();
        for (int gameID : gamefields.keySet()) {
            ArrayList<String> temp = new ArrayList<String>();
            Gamefield gamefield = gamefields.get(gameID);
            temp.add(String.valueOf(gameID));
            temp.add(String.valueOf(gamefield.getPlayerList().size()));
            temp.add(gamefield.getGameInfo().getGamestate().toString());
            gameList.add(temp);
        }
        globalLoginView.lobbyView.updateGames(gameList);
    }
    
    public void addUDPServer(String ip, int port, String group){
        globalLoginView.clientLoginView.updateServer(ip, port, group);
    }

    //LobbyPresenter to Server-> Create Game
    public void handleCreate(MouseEvent event) {
        try {
            String temp = globalLoginView.lobbyView.getColor2().getValue().toString();

            model.Counter.Color color = null;

            switch (temp) {
                case "Rot": case "Red": case "Rouge": case "Rosso": case "Rojo":
                    color = model.Counter.Color.valueOf("RED");
                    break;
                case "Grün": case "Vert": case "Verde": 
                    color = model.Counter.Color.valueOf("GREEN");
                    break;
                case "Blau": case "Blue": case "Bleu": case "Blu": case "Azul":
                    color = model.Counter.Color.valueOf("BLUE");
                    break;
                case "Lila": case "Purple": case "Pourpre": case "Viola": case "Púrpura": 
                    color = model.Counter.Color.valueOf("PURPLE");
                    break;
                case "Gelb": case "Yellow": case "Jaune": case "Giallo": case "Amarillo":
                    color = model.Counter.Color.valueOf("YELLOW");
                    break;
                case "Weiß": case "White": case "Blanc": case "Bianco": case "Blanco":
                    color = model.Counter.Color.valueOf("WHITE");
                    break;
                default:
                    color = model.Counter.Color.valueOf("RED");
                    break;
            }

            clientSent.createGame(color);

        } catch (NullPointerException ex) {
            DialogPresenter dial = new DialogPresenter();
            dial.noColorCreateHandle(event);
        	  
        }
        //		int gameFieldID = clientGlobal.addNewGamefield(new Gamefield());//TODO Auf Nachricht des Servers warten?
        //		Gamefield model = clientGlobal.getGamefield(gameFieldID);		//TODO oder schon erstelltes Gamefield mit dem vom Server synchronisieren
        //
    }

    /**
     * Called if client joins as watcher
     * @param model
     * @param gameID
     * @author Maurice, Ludwig
     */
    public void createGameAsWatcher(Gamefield model, int gameID) {
        createGame(model, gameID);

        globalView.gameView.fieldImageView.setVisible(true);
        globalView.gameView.fieldView.setVisible(true);
        globalView.diceView.setVisible(true);
        globalView.gameView.counterView.setVisible(true);
        globalView.gameView.anklageView.setVisible(false);
        globalView.gameView.cardsView.setVisible(false);
        globalView.gameView.noteView.setVisible(false);
        globalView.gameView.verdaechtigungsView.setVisible(false);
        globalView.gameView.winnerView.setVisible(false);
        globalView.gameView.looseView.setVisible(false);
        globalView.gameView.widerlegtView.setVisible(false);
    	globalView.gameView.gameStartView.setVisible(false);

        updateGamesPresenter();
    }
    
    /**
     * Called if client joins game as player
     * @author Maurice, Ludwig
     * @param model
     * @param gameID
     */
    public void createGameAsJoiner(Gamefield model, int gameID) {
        createGame(model, gameID);
    }
    
    /**
     * Called if player creates a game
     * @author Paul, Maurice, Ludwig
     * @param model
     * @param gameID
     */
    public void createGame(Gamefield model, int gameID) {
    	globalView = new GlobalView(model);

        //neues Fenster öffnen
        Scene scene = new Scene(globalView);
        Stage globalViewStage = new Stage();
        globalViewStage.setScene(scene);
        globalViewStage.show();
        globalViewStage.requestFocus();
    
        // show gameID to know which game you play
        if (model.getPlayer() == null)
            globalViewStage.setTitle("Spiel Nr: " + gameID + " - Zuschauer - Nick " + model.getMyNick());
        else
            globalViewStage.setTitle("Spiel Nr: " + gameID + " - Farbe " + model.getPlayer().getCounter().getColor().toString() + " - Nick " + model.getMyNick());
        
        // kill window and send leave game
        globalViewStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                log.info("kill gamefield view");
                // leave game to server
                clientSent.leaveGame(gameID);
            }
        });

        presenterHashMap.put(gameID, new GlobalPresenter(model, globalView, clientSent, gameID));

        globalView.gameView.fieldImageView.setVisible(true);
        globalView.gameView.fieldView.setVisible(true);
        globalView.diceView.setVisible(true);
        globalView.gameView.counterView.setVisible(true);
        globalView.gameView.anklageView.setVisible(false);
        globalView.gameView.cardsView.setVisible(false);
        globalView.gameView.noteView.setVisible(false);
        globalView.gameView.verdaechtigungsView.setVisible(false);
        globalView.gameView.winnerView.setVisible(false);
        globalView.gameView.looseView.setVisible(false);
        globalView.gameView.widerlegtView.setVisible(false);
    	globalView.gameView.gameStartView.setVisible(true);

        updateGamesPresenter();
    }


    public void handleJoin(MouseEvent e) {
        try {
            String temp = globalLoginView.lobbyView.getColor().getValue().toString();

            model.Counter.Color color = null;

            switch (temp) {
            case "Rot": case "Red": case "Rouge": case "Rosso": case "Rojo":
                color = model.Counter.Color.valueOf("RED");
                break;
            case "Grün": case "Vert": case "Verde": case "Green": 
                color = model.Counter.Color.valueOf("GREEN");
                break;
            case "Blau": case "Blue": case "Bleu": case "Blu": case "Azul":
                color = model.Counter.Color.valueOf("BLUE");
                break;
            case "Lila": case "Purple": case "Pourpre": case "Viola": case "Púrpura": 
                color = model.Counter.Color.valueOf("PURPLE");
                break;
            case "Gelb": case "Yellow": case "Jaune": case "Giallo": case "Amarillo":
                color = model.Counter.Color.valueOf("YELLOW");
                break;
            case "Weiß": case "White": case "Blanc": case "Bianco": case "Blanco":
                color = model.Counter.Color.valueOf("WHITE");
                break;
            default:
                color = model.Counter.Color.valueOf("RED");
                break;
            }

            int gameID = globalLoginView.lobbyView.getGameID();
            clientSent.joinGame(gameID, color);
        } catch (NullPointerException ex) {
            DialogPresenter dial = new DialogPresenter();
            dial.noColorCreateHandle(e);
        }

    }
    
    /**
     * exit game
     * @param e
     */
    public void handleExit(MouseEvent e) {
    	log.info("exit menue/lobby, kill program");
        Platform.exit();
        System.exit(0);
    }

    public void handleStart(MouseEvent e) {
        globalLoginView.startView.setVisible(false);
        globalLoginView.selectSCView.setVisible(true);
    }

    public void handleCredits(MouseEvent e) {
        globalLoginView.creditView.setVisible(true);
        globalLoginView.startView.setVisible(false);
    }

    public void handleCreditsExit(MouseEvent e) {
        globalLoginView.creditView.setVisible(false);
        globalLoginView.startView.setVisible(true);
    }

    public void handleAnleitung(MouseEvent e) {
        globalLoginView.anleitungsView.setVisible(true);
    }

    public void handleAnleitungExit(MouseEvent e) {
        globalLoginView.anleitungsView.setVisible(false);
    }
    public void handleHelp(MouseEvent e){
    	globalLoginView.anleitungsView.setVisible(true);
    }
    
    //Sarah
    public void handleLanguage(ActionEvent e){
    	
    	//noch nach Auswahl View updaten lassen

    	if(globalLoginView.startView.language.getSelectionModel().getSelectedItem().toString().equals("Deutsch")){
    		LanguageManager.setLanguage(SupportedLanguages.de);
    	}
    			
    	else if(globalLoginView.startView.language.getSelectionModel().getSelectedItem().toString().equals("English")){
    		LanguageManager.setLanguage(SupportedLanguages.en);
    	} 
    	else if(globalLoginView.startView.language.getSelectionModel().getSelectedItem().toString().equals("Francais")){
    		LanguageManager.setLanguage(SupportedLanguages.fr);
    	} 
    	else if(globalLoginView.startView.language.getSelectionModel().getSelectedItem().toString().equals("Italiano")){
    		LanguageManager.setLanguage(SupportedLanguages.it);
    	}
    	else if(globalLoginView.startView.language.getSelectionModel().getSelectedItem().toString().equals("Español")){
    		LanguageManager.setLanguage(SupportedLanguages.es);
    	}
    }
    
    /**
     * function to observe game
     * @author Maurice
     */
    public void handleSpectator(MouseEvent e){
    	 int gameID = globalLoginView.lobbyView.getGameID();
         clientSent.watchGame(gameID);         
    }
    
    /**
     * Shows Message in Chatwindow when type Enter
     * @param e
     * @author Maurice
     */
    public void handleEnterChat(KeyEvent e){
    	globalLoginView.lobbyView.chatView.eingabe.setOnKeyReleased(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.ENTER)){
					 if (globalLoginView.lobbyView.chatView.eingabe.getText().isEmpty()) return;
			        // get actual time to sent
			        Date actualTime = new Date();
			    
			        int gameID = 0;
			        String nick = null;
			        String sentTo = null;
			        try {
			            sentTo = globalLoginView.lobbyView.chatView.dropdown.getValue();
			        } catch (NullPointerException npe) {
			        }
			    
			        if (sentTo != null) {
			            if (sentTo.contains("GameID"))
			                gameID = Integer.parseInt(sentTo.substring(7).trim());
			            else if (!sentTo.contains("Alle"))
			                nick = sentTo;
			        }
			        
			        // sent to server
			        clientSent.chat(globalLoginView.lobbyView.chatView.eingabe.getText().trim(), actualTime.getTime() / 1000, gameID, nick);
			    
			        // clear input field
			        globalLoginView.lobbyView.chatView.eingabe.clear();
				   }
				}
    		});
    	}

    /**
     * shows message in chatwindow
     * @param e mouse event
     * @author Maurice, Ludwig
     */
    public void handleSend(MouseEvent e) {
        if (globalLoginView.lobbyView.chatView.eingabe.getText().isEmpty()) return;
        // get actual time to sent
        Date actualTime = new Date();
    
        int gameID = 0;
        String nick = null;
        String sentTo = null;
        try {
            sentTo = globalLoginView.lobbyView.chatView.dropdown.getValue();
        } catch (NullPointerException npe) {
        }
    
        if (sentTo != null) {
            if (sentTo.contains("GameID"))
                gameID = Integer.parseInt(sentTo.substring(7).trim());
            else if (!sentTo.contains("Alle"))
                nick = sentTo;
        }
        
        // sent to server
        clientSent.chat(globalLoginView.lobbyView.chatView.eingabe.getText().trim(), actualTime.getTime() / 1000, gameID, nick);
    
        // clear input field
        globalLoginView.lobbyView.chatView.eingabe.clear();
    }
    
    /**
     * Sendet eine Nachricht im Chat
     * wenn nur message, an alle Spieler auf dem Server
     * wenn gameID, an alle Spieler in einem Spiel
     * wenn nick, nur an diesen Spieler
     * @param message   Nachricht an Spieler auf dem Server, Spiel oder direkt
     * @param timestamp Unixtime im ms als die Nachricht verschickt wurde
     * @param gameID    (optional) ID des Spiels an dessen Spieler die Nachricht geschickt werden soll
     * @param sender    (optional) Nickname des Empfängers der Direktnachricht
     * @author Ludwig, Rainer
     */
    public void addChatMessage(String message, LocalDateTime timestamp, int gameID, String sender, String sendTo) {
        if (sender.equals("")) sender = "Server";
        globalLoginView.lobbyView.chatView.newChatMessage(sender, message, sendTo, gameID);
    }
    
    /**
     * Enables login by pressing Enter in any Textfield
     * @author Wittmann Rainer
     */
    public void handleEnterLogin(KeyEvent e){
    	if(e.getCode().equals(KeyCode.ENTER)){
    		if(!globalLoginView.clientLoginView.nametxt.getText().isEmpty() &&
    				!globalLoginView.clientLoginView.grouptxt.getText().isEmpty() &&
    				!globalLoginView.clientLoginView.iptxt.getText().isEmpty() &&
    				!globalLoginView.clientLoginView.porttxt.getText().isEmpty()){
    			log.info("Submit Pressed");

    	        String ip = globalLoginView.clientLoginView.getIptxt();
    	        int port = globalLoginView.clientLoginView.getPorttxt();
    	        String nick = globalLoginView.clientLoginView.getNametxt();
    	        String group = globalLoginView.clientLoginView.getGrouptxt();

    	        log.info(ip + " " + port + " " + nick + " " + group);

    	        //LOGIN TO SERVER
    	        clientSent.login(ip, port, nick, group);		
    		}
    	}
    }

    /**
     * Return presenter according to gameID
     * @param gameID gameID of gamefield
     * @return presenter of gameID
     * @author Ludwig
     */
    public GlobalPresenter getPresenter(int gameID) {
        if (presenterHashMap.containsKey(gameID))
            return presenterHashMap.get(gameID);
        return null;
    }
    
    /**
     * Return reference to global model
     * @return global model
     * @author Ludwig
     */
    public Global getClientGlobal(){
        return clientGlobal;
    }
    
    
    public void startGame(int gameID) {
        if (getPresenter(gameID) == null)
            log.error("no presenter with gameID" + gameID + " found");
        else 
            getPresenter(gameID).getGameStartPres().render();
    }
    /**
     * Method
     * 
     * @author Wittmann Rainer
     */
    public void handleChatDropdown(MouseEvent e){
    	//ArrayLists erstellen
    	ArrayList<String> games= new ArrayList<String>();
    	Object[] id = clientGlobal.getGamefields().keySet().toArray();
    	for(int i = 0; i < clientGlobal.getGamefields().size(); i++){
    		games.add(id[i].toString());
    	}
    	
    	ArrayList<String> clients = new ArrayList<String>();
    	clients = clientGlobal.getClients();
//    	}
    	
    	ArrayList<ArrayList<String>> chatList = new ArrayList<ArrayList<String>>();
    	chatList.add(games);
    	chatList.add(clients);
    	
    	//ArrayList an View geben
    	globalLoginView.lobbyView.chatView.addDropdownValue(chatList);
    }
    
    /**
     * sets color disable if color is chosen
     * @author Rainer
     */
    public void handleDropDownColor(MouseEvent e){
    	int selectedGame;
    	ArrayList<String> temp = new ArrayList<String>();
    	temp.add("red");
    	temp.add("green");
    	temp.add("blue");
    	temp.add("purple");
    	temp.add("yellow");
    	temp.add("white");
    	//Dropdown auslehren und neu setzen
    	//globalLoginView.lobbyView.colorbox.getItems().clear();
    	//ComboBox colorbox = new CustomComboBox(new String[]{"red","green","blue","purple","yellow","white"}, "chooseColor");
    	//colorbox.getItems().clear();
    	//colorbox.setPromptText(LanguageManager.getString("chooseColor"));		
    	//globalLoginView.lobbyView.colorbox.getItems().addAll("Rot", "Grün", "Blau", "Lila", "Gelb", "Weiß");
    	//try um den Nullpointer zu umgehen, wenn noch keine Spiel ausgewählt ist
    	try{
    		//wenn kein Spiel ausgewählt ist, passiert nichts
    		if(globalLoginView.lobbyView.tg.getSelectedToggle().getUserData().equals(null)){
    			return;
    		}
    		//löschen der nicht mehr verfügbaren Farben im Dropdown
    		else {
    			selectedGame = Integer.parseInt(globalLoginView.lobbyView.tg.getSelectedToggle().getUserData().toString());
    			ArrayList<Player> playerList = clientGlobal.getGamefield(selectedGame).getPlayerList();
    		
    			for(int i = 0; i < playerList.size(); i++){
    				switch(playerList.get(i).getCounter().getColor()){
    				case RED:
    					temp.remove("red");
    				//	globalLoginView.lobbyView.colorbox.getItems().removeAll("Rot","Red");
    					break;
    				case GREEN:
    					temp.remove("green");
//    					globalLoginView.lobbyView.colorbox.getItems().removeAll("Grün");
    					break;
    				case BLUE:
    					temp.remove("blue");
//    					globalLoginView.lobbyView.colorbox.getItems().removeAll("Blau");
    					break;
    				case YELLOW:
    					temp.remove("yellow");
//    					globalLoginView.lobbyView.colorbox.getItems().removeAll("Gelb");
    					break;
    				case WHITE:
    					temp.remove("white");
//    					globalLoginView.lobbyView.colorbox.getItems().removeAll("Weiß");
    					break;
    				default:
    					temp.remove("purple");
//    					globalLoginView.lobbyView.colorbox.getItems().removeAll("Lila");
    				} globalLoginView.lobbyView.setColorbox(temp);
    			}
    		}
    	} catch (NullPointerException NP){
    		log.info("Kein Spiel ausgewählt");
    	}
    	
    }
    public void handleKI(MouseEvent e){
    	// KI try to login
        ip = clientGlobal.getServerIp();
        port = clientGlobal.getServerPort();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(999);
        Ki ki = new Ki(ip, port, "DR.roBOTo" + randomInt, "muffigemotten");
        Thread kiTread = new Thread(ki);
        kiTread.setName("KI-Thread-" + randomInt);
        kiTread.start();
    }

}
