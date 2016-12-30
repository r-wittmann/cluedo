package network.server;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import json.org.JSONArray;
import json.org.JSONObject;
import language.LanguageManager;
import model.*;
import network.jsonprotocol.JsonProtocollConstants;
import network.jsonprotocol.ModelToJson;
import network.jsonprotocol.ServerJson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import presenter.DialogPresenter;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

import static network.jsonprotocol.JsonToModel.*;

/**
 * @author paul
 */

public class ServerClientThreadRunnable implements Runnable,
		JsonProtocollConstants {
	private static final Logger log = LogManager
			.getLogger(ServerClientThreadRunnable.class);

	// get Seed for Wuerfel
	Random wurf = new Random();
	int[] lastWuerfel = new int[2];

	// Socket dieses Threads
	private Socket clientSocket = null;

	// Nick dieses Threads/Clients
	private String Nick;

	// local Copy of Global-Info
	private Global global;

	// Liste aller Sockets(Threads) mit jeweiligem Nick als ID
	private Hashtable<String, ServerOnlyInformation> storage;

	// ServerJson for Communication
	private ServerJson SJ = new ServerJson();

	// Sent & Recieve Methods
	private NetworkRunnable network;
	private Thread networkThread;

	// Initialiseriung des Threads
	ServerClientThreadRunnable(Global global, Socket socket,
			Hashtable<String, ServerOnlyInformation> storage) {
		this.global = global;
		this.clientSocket = socket;
		this.storage = storage;

		network = new NetworkRunnable(socket);
		Thread networkThread = new Thread(network);
		networkThread.start();
	}

	// Wird ausgeführt um den Thread zu starten.
	@Override
	public void run() {
		network.newJSON.addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue o, Object oldVal, Object newVal) {
				inputCheck(new JSONObject(newVal.toString()));
			}
		});
	}

	/**
	 * check incoming JSON and put in right function
	 */
	private void inputCheck(JSONObject jsonObject) {
		log.info("Check Input for Type");
		switch (jsonObject.getString(TYPE)) {
		case LOGIN:
			loginHandler(jsonObject);
			break;
		case CREATE_GAME:
			createGameHandler(jsonObject);
			break;
		case JOIN_GAME:
			joinGameHandler(jsonObject);
			break;
		case START_GAME:
			startGameHandler(jsonObject);
			break;
		case ROLL_DICE:
			rollDiceHandler(jsonObject);
			break;
		case MOVE:
			moveHandler(jsonObject);
			break;
		case SECRET_PASSAGE:
			secretHandler(jsonObject);
			break;
		case SUSPECT:
			suspectHandler(jsonObject);
			break;
		case DISPROVE:
			disproveHandler(jsonObject);
			break;
		case END_TURN:
			endTurnHandler(jsonObject);
			break;
		case ACCUSE:
			accuseHandler(jsonObject);
			break;
		case LEAVE_GAME:
			leaveGameHandler(jsonObject);
			break;
		case DISCONNECT:
			disconnectHandler();
			break;
		case WATCH_GAME:
			watchGameHandler(jsonObject);
			break;
		case CHAT:
			chatHandler(jsonObject);
			break;
		default:
			returnError("JsonProtocol no corresponding 'type' found");
			log.error("JsonProtocol no corresponding 'type' found");
		}

	}

	// ALL HANDLERS IN ORDER

	private void loginHandler(JSONObject jsonObject) {
		log.info("loginHandler");
		// Check for Same Protocol-Version
		checkVersion(jsonObject);
		this.Nick = jsonObject.getString(NICK);
		// If Nick is Available
		if (checkNick(this.Nick)) {
			log.info("Nick available");

			String groupName = jsonObject.getString(GROUP);

			// Save this Thread-Info in Storage an make it available for other
			// Threads
			storage.put(this.Nick, new ServerOnlyInformation(groupName,
					this.clientSocket));
			// Handle Reply to all other Sockets. (New Player Connected)

			String[] expansions = { "chat" };
			String[] nickArray = getAllPlayerStringArray();

			// Reply to Client with LOGIN SUCCESSFUL
			JSONObject loginReply = this.SJ.replyLogin(expansions, nickArray,
					getGameArray());
			notifyThisNick(loginReply);

			// Tell other Clients new Client added
			JSONObject newPlayerAlert = this.SJ.bcLogin(this.Nick);
			notifyOtherClientsBUTThis(this.Nick, newPlayerAlert);
			// Print latest CLient List
			log.info("New Client: " + this.Nick);

			logAllClients();

			// reply to Client with Type:OK
			returnOK();
		} else {
			// Error to Client: Nick not available
			// TODO richtige Dialogboxen
			JSONObject NickError = SJ.replyError("Nick ist schon vergeben.");
			notifyThisNick(NickError);
			closeSocket();
		}
	}

	private void createGameHandler(JSONObject jsonObject) {
		log.info("createGameHandler");
		// Create Game
		global.createGamefield();

		// get gameID of created Game
		int gameID = global.getLatestGameID();
		Gamefield tempGame = global.getGamefield(gameID);

		// createPlayer
		Counter.Color tempFarbe = convertFarbeToModel(jsonObject.get(COLOR)
				.toString());
		tempGame.createPlayer(this.Nick, tempFarbe);

		// answer to all Clients
		JSONObject gameCreatedAnswer = new JSONObject();
		gameCreatedAnswer.put(TYPE, GAME_CREATED);
		gameCreatedAnswer.put(GAMEID, gameID);
		gameCreatedAnswer.put(PLAYER,
				getPlayerInfo(tempGame.getPlayer(this.Nick)));

		notifyAllClients(gameCreatedAnswer);

		log.info("Spiel mit GameID " + gameID + " erstellt");
		// reply to Client with Type:OK
		returnOK();

	}

	private void joinGameHandler(JSONObject jsonObject) {
		log.info("joinGameHandler");
		// get GAMEID
		int tempGameID = Integer.parseInt(jsonObject.get(GAMEID).toString());

		Gamefield tempGame = global.getGamefield(tempGameID);
		try {
			ArrayList<Player> playerList = tempGame.getPlayerList();

			// Check if Color is available
			Counter.Color tempFarbe = convertFarbeToModel(jsonObject.get(COLOR)
					.toString());
			if (colorAvailable(tempFarbe, playerList)
					&& nameAvailable(this.Nick, playerList)) {
				log.info("Farbe noch nicht vergeben. Spielbeitritt möglich");
				tempGame.createPlayer(this.Nick, tempFarbe);

				JSONObject joinAnswer = new JSONObject();
				joinAnswer.put(TYPE, PLAYER_ADDED);
				joinAnswer.put(GAMEID, tempGameID);
				joinAnswer.put(PLAYER,
						getPlayerInfo(tempGame.getPlayer(this.Nick)));

				notifyAllClients(joinAnswer);
				// reply to Client with Type:OK
				returnOK();

				log.info("Player " + this.Nick + " zu Spiel " + tempGameID
						+ " hinzugefügt");

			} else {
				// Error to Client: Nick not available
				// TODO richtige Dialogboxen
				JSONObject NickError = SJ
						.replyError("Farbe oder Nick ist schon vergeben.");
				notifyThisNick(NickError);

				log.error("Farbe oder Nick schon vergeben!");
			}

		} catch (NullPointerException e) {

			JSONObject NoGameError = SJ.replyError(LanguageManager
					.getString("noGameError"));
			network.sendJSONtoThisClient(NoGameError);

			log.error("Kein Spiel ausgewählt!");

		}

	}

	private void startGameHandler(JSONObject jsonObject) {
		log.info("startGameHandler");
		int tempGameID = Integer.parseInt(jsonObject.get(GAMEID).toString());
		Gamefield tempGame = global.getGamefield(tempGameID);
		ArrayList<Player> playerList = tempGame.getPlayerList();
		// TODO nach debbuging auf 3 setzten
		// if (playerList.size() >= 3) {
		if (playerList.size() >= 2) {

			tempGame.getGameInfo().setGamestate(GameInfo.Gamestate.STARTED);
			tempGame.spreadCards();
			// 1.An alle Spieler einzeln die Karten -> WER HAT WELCHE KARTEN ?
			for (int i = 0; i < playerList.size(); i++) {
				// Cards per Player
				ArrayList<Card> tempPlayerCards = tempGame
						.getPlayerCards(playerList.get(i).getName());
				JSONObject answerPlayerCards = new JSONObject();
				answerPlayerCards.put(TYPE, PLAYER_CARDS);
				answerPlayerCards.put(GAMEID, tempGameID);
				// CARDS TO JSON
				JSONArray cardArray = new JSONArray();
				for (int j = 0; j < tempPlayerCards.size(); j++) {
					Card tempCard = tempPlayerCards.get(j);
					cardArray.put(tempCard.getCardType().toLowerCase());
				}
				answerPlayerCards.put(CARDS, cardArray);
				notifyONE(playerList.get(i).getName(), answerPlayerCards);
			}

			// RANdomize ORDER randomizeOrder()
			tempGame.randomizeOrder();

			// 2. An alle Spieler und Zuschauer GameStart -> DAS SPIEL IST JETZT
			// GESTARTET
			ArrayList<Player> tempPlayerList = tempGame.getPlayerList();
			String[] tempArray = new String[tempPlayerList.size()];
			for (int j = 0; j < tempArray.length; j++) {
				tempArray[j] = tempPlayerList.get(j).getName();
			}
			// SJ.bcGameStarted funktioniert
			JSONObject gameStarted = SJ.bcGameStarted(tempGameID, tempArray);
			notifyAllClientsInGame(tempGame, gameStarted);

			// 3. ERSTER SPIELZUG
			JSONObject firstStateUpdate = new JSONObject();
			firstStateUpdate.put(TYPE, STATEUPDATE);
			firstStateUpdate.put(GAMEID, tempGameID);

			// SET STATE IN GAMEFIELD
			// Sure Reset
			setAllPlayerStateToDoNothing(tempGame);
			EnumSet<Player.Playerstate> tempState = EnumSet.of(
					Player.Playerstate.ACCUSE, Player.Playerstate.ROLL_DICE);

			Player firstPlayer = tempGame
					.getPlayer(tempGame.getCurrentPlayer());
			firstPlayer.setPlayerstates(tempState);

			firstStateUpdate.put(PLAYER, getPlayerInfo(firstPlayer));

			notifyAllClientsInGame(tempGame, firstStateUpdate);

			// reply to Client with Type:OK
			returnOK();

		} else {
			// Error to Client: Nick not available
			// TODO richtige Dialogboxen
			JSONObject NickError = SJ.replyError("Du brauchst min 3 Spieler");
			notifyThisNick(NickError);
			log.error("Noch nicht genug Spieler");
		}
	}

	private void rollDiceHandler(JSONObject jsonObject) {
		log.info("rollDiceHandler");
		int tempGameID = Integer.parseInt(jsonObject.get(GAMEID).toString());
		Gamefield tempGame = global.getGamefield(tempGameID);

		// DICE RESULT
		JSONObject diceAnswer = new JSONObject();
		diceAnswer.put(TYPE, DICE_RESULT);
		diceAnswer.put(GAMEID, tempGameID);
		lastWuerfel = wuerfel();
		JSONArray tempJsonArray = new JSONArray();
		tempJsonArray.put(lastWuerfel[0]);
		tempJsonArray.put(lastWuerfel[1]);
		diceAnswer.put(RESULT, tempJsonArray);
		network.sendJSONtoThisClient(diceAnswer);

		// STATEUPDATE
		EnumSet<Player.Playerstate> states = EnumSet
				.of(Player.Playerstate.MOVE);
		setPlayerStateWithNotify(tempGame, this.Nick, states);

	}

	private void secretHandler(JSONObject jsonObject) {
		int tempGameID = jsonObject.getInt(GAMEID);
		Gamefield tempGame = global.getGamefield(tempGameID);
		Player tempPlayer = tempGame.getPlayer(tempGame.currentPlayer);
		int x = tempPlayer.getCounter().getPositionX();
		int y = tempPlayer.getCounter().getPositionY();
		if (x == 17 && y == 5) {
			tempGame.getPlayer(this.Nick).getCounter().setPosition(4, 19);
		}
		if (x == 4 && y == 19) {
			tempGame.getPlayer(this.Nick).getCounter().setPosition(17, 5);
		}
		if (x == 19 && y == 18) {
			tempGame.getPlayer(this.Nick).getCounter().setPosition(6, 3);
		}
		if (x == 6 && y == 3) {
			tempGame.getPlayer(this.Nick).getCounter().setPosition(19, 18);
		}
		JSONObject moveAnswer = new JSONObject();
		moveAnswer.put(TYPE, MOVED);
		moveAnswer.put(GAMEID, tempGameID);
		moveAnswer.put(PERSON_POSITION,
				getPlayerPosition(tempGame.getPlayer(this.Nick)));
		notifyAllClientsInGame(tempGame, moveAnswer);
		EnumSet<Player.Playerstate> states = EnumSet.of(
				Player.Playerstate.ACCUSE, Player.Playerstate.END_TURN,
				Player.Playerstate.SUSPECT);
		setPlayerStateWithNotify(tempGame, this.Nick, states);

	}

	private void moveHandler(JSONObject jsonObject) {
		log.info("moveHandler");
		int tempGameID = Integer.parseInt(jsonObject.get(GAMEID).toString());
		Gamefield tempGame = global.getGamefield(tempGameID);
		int[] ziel = new int[2];
		JSONObject field = jsonObject.getJSONObject(FIELD);
		ziel[0] = field.getInt(X);
		ziel[1] = field.getInt(Y);

		// CHECK-MOVE
		if (tempGame.checkMove(this.Nick, ziel[0], ziel[1], lastWuerfel[0]
				+ lastWuerfel[1])) {

			tempGame.getPlayer(this.Nick).getCounter()
					.setPosition(ziel[0], ziel[1]);
			JSONObject moveAnswer = new JSONObject();
			moveAnswer.put(TYPE, MOVED);
			moveAnswer.put(GAMEID, tempGameID);

			moveAnswer.put(PERSON_POSITION,
					getPlayerPosition(tempGame.getPlayer(this.Nick)));
			notifyAllClientsInGame(tempGame, moveAnswer);
			returnOK();

			EnumSet<Player.Playerstate> states = EnumSet.of(
					Player.Playerstate.ACCUSE, Player.Playerstate.END_TURN);
			// Fallunterscheidung wenn in Raum -> SUSPECT ist möglich
			if (ziel[0] == 1 && ziel[1] == 12 || ziel[0] == 3 && ziel[1] == 10
					|| ziel[0] == 4 && ziel[1] == 19 || ziel[0] == 5
					&& ziel[1] == 15 || ziel[0] == 6 && ziel[1] == 3
					|| ziel[0] == 6 && ziel[1] == 8 || ziel[0] == 8
					&& ziel[1] == 19 || ziel[0] == 9 && ziel[1] == 4
					|| ziel[0] == 9 && ziel[1] == 17 || ziel[0] == 11
					&& ziel[1] == 6 || ziel[0] == 12 && ziel[1] == 6
					|| ziel[0] == 14 && ziel[1] == 17 || ziel[0] == 15
					&& ziel[1] == 19 || ziel[0] == 16 && ziel[1] == 12
					|| ziel[0] == 17 && ziel[1] == 5 || ziel[0] == 17
					&& ziel[1] == 9 || ziel[0] == 19 && ziel[1] == 18) {
				states.clear();
				states.add(Player.Playerstate.ACCUSE);
				states.add(Player.Playerstate.SUSPECT);
				states.add(Player.Playerstate.END_TURN);
			}
			if (ziel[0] == 9 && ziel[1] == 12 || ziel[0] == 11 && ziel[1] == 8
					|| ziel[0] == 11 && ziel[1] == 14 || ziel[0] == 13
					&& ziel[1] == 9) {
				states.clear();
				states.add(Player.Playerstate.ACCUSE);
				states.add(Player.Playerstate.END_TURN);
				JSONObject poolcardAnswer = new JSONObject();
				poolcardAnswer.put(TYPE, POOLCARDS);
				poolcardAnswer.put(GAMEID, tempGameID);

				JSONArray poolcardArray = new JSONArray();
				ArrayList<Card> tempPoolCards = tempGame.getPoolCards();
				for (int i = 0; i < tempPoolCards.size(); i++) {
					poolcardArray.put(ModelToJson
							.convertCardToJson(tempPoolCards.get(i)));
				}
				poolcardAnswer.put(CARDS, poolcardArray);
				notifyThisNick(poolcardAnswer);
			}

			// Sent New State

			setPlayerStateWithNotify(tempGame, this.Nick, states);
			returnOK();
		} else {
			JSONObject error = new JSONObject();
			error.put(TYPE, ERROR);
			error.put(MESSAGE, "Zug nicht möglich");
			notifyThisNick(error);
		}

	}

	private void suspectHandler(JSONObject jsonObject) {

		int tempGameID = jsonObject.getInt(GAMEID);
		Gamefield tempGame = global.getGamefield(tempGameID);

		// 1.{"type" : "suspicion","gameID" : int,"statement" : Aussage}
		// Verdächtigung allen mitteilen

		String suspectingPlayerName = global.getGamefield(tempGameID)
				.getCurrentPlayer();
		Player suspectingPlayer = global.getGamefield(tempGameID).getPlayer(
				suspectingPlayerName);
		RoomCard.Type suspicionRoom = global.getGamefield(tempGameID).board
				.getRoomOfPerson(suspectingPlayer);

		JSONObject suspicionToAll = new JSONObject();
		suspicionToAll.put(TYPE, SUSPICION);
		suspicionToAll.put(GAMEID, tempGameID);
		JSONObject statement = new JSONObject();
		statement.put(WEAPON,
				jsonObject.getJSONObject(STATEMENT).getString(WEAPON));
		statement.put(PERSON,
				jsonObject.getJSONObject(STATEMENT).getString(PERSON));
		statement.put(ROOM, suspicionRoom.toString().toLowerCase());
		suspicionToAll.put(STATEMENT, statement);
		notifyAllClientsInGame(tempGame, suspicionToAll);

		// SENT DISPROVESTATE TO THE PLAYER WHO HAS TO DISPROVE
		WeaponCard.Type weapon = convertWaffeToCardModel(jsonObject
				.getJSONObject(STATEMENT).getString(WEAPON));
		PersonCard.Type person = convertFarbeToCardModel(jsonObject
				.getJSONObject(STATEMENT).getString(PERSON));
		RoomCard.Type room = suspicionRoom;

		int[] ziel = tempGame.roomToKoordinaten(room);
		String tempNick = getPlayerByCounterColor(person, tempGame);
		if (tempNick != null) {
			tempGame.getPlayer(tempNick).getCounter()
					.setPosition(ziel[0], ziel[1]);
		}

		ArrayList<Player> playerList = tempGame
				.getPlayerListAfterPlayer(this.Nick);
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).checkHandContainsCard(weapon)
					|| playerList.get(i).checkHandContainsCard(person)
					|| playerList.get(i).checkHandContainsCard(room)) {
				EnumSet<Player.Playerstate> states = EnumSet
						.of(Player.Playerstate.DISPROVE);
				setPlayerStateWithNotify(tempGame, playerList.get(i).getName(),
						states);
				global.setTemp(playerList.get(i));
				return;
			}
		}
		JSONObject noDisprove = new JSONObject();
		noDisprove.put(TYPE, NO_DISPROVE);
		noDisprove.put(GAMEID, tempGameID);
		notifyAllClientsInGame(tempGame, noDisprove);
	}

	private void disproveHandler(JSONObject jsonObject) {
		int tempGameID = jsonObject.getInt(GAMEID);
		String suspectingPlayerName = global.getGamefield(tempGameID)
				.getCurrentPlayer();
		Gamefield tempGame = global.getGamefield(tempGameID);
		JSONObject disprovedToAll = new JSONObject();
		disprovedToAll.put(TYPE, DISPROVED);
		disprovedToAll.put(GAMEID, tempGameID);
		disprovedToAll.put(NICK, this.Nick);
		notifyOtherClientsBUTThisWithGame(tempGame,suspectingPlayerName, disprovedToAll);

		disprovedToAll.put(CARD, jsonObject.get(CARD));
		notifyONE(suspectingPlayerName, disprovedToAll);
		EnumSet<Player.Playerstate> states = EnumSet.of(
				Player.Playerstate.ACCUSE, Player.Playerstate.END_TURN);
		setPlayerStateWithNotify(tempGame, tempGame.getCurrentPlayer(), states);
	}

	private void accuseHandler(JSONObject jsonObject) {

		// {"gameID":1,"statement":{"weapon":"dagger","person":"red","room":"hall"},"type":"accuse"}

		int tempGameID = jsonObject.getInt(GAMEID);
		Gamefield tempGame = global.getGamefield(tempGameID);
		WeaponCard.Type weapon = convertWaffeToCardModel(jsonObject
				.getJSONObject(STATEMENT).getString(WEAPON));
		PersonCard.Type person = convertFarbeToCardModel(jsonObject
				.getJSONObject(STATEMENT).getString(PERSON));
		RoomCard.Type room = convertRaumToCardModel(jsonObject.getJSONObject(
				STATEMENT).getString(ROOM));
		if (tempGame.checkMurder(weapon, room, person)) {
			JSONObject end = new JSONObject();
			end.put(TYPE, GAME_ENDED);
			end.put(GAMEID, tempGameID);
			end.put(NICK, this.Nick);
			end.put(STATEMENT, jsonObject.get(STATEMENT));
			notifyAllClientsInGame(tempGame, end);
			global.removeGamefield(tempGameID);
		} else {
			JSONObject error = new JSONObject();
			error.put(TYPE, ERROR);

			// TODO richtige Dialogboxen
			error.put(MESSAGE, "Da hast du den falschen verdächtigt.");
			notifyThisNick(error);

		}

	}

	private void endTurnHandler(JSONObject jsonObject) {
		int tempGameID = jsonObject.getInt(GAMEID);
		Gamefield tempGame = global.getGamefield(tempGameID);
		tempGame.nextPlayer();
		EnumSet<Player.Playerstate> states = EnumSet.of(
				Player.Playerstate.ROLL_DICE, Player.Playerstate.ACCUSE);
		if (checkForGeheimgang(tempGame)) {
			states.add(Player.Playerstate.USE_SECRET_PASSAGE);
		}
		setPlayerStateWithNotify(tempGame, tempGame.currentPlayer, states);
		returnOK();
	}

	private void leaveGameHandler(JSONObject jsonObject) {
		int tempGameID = jsonObject.getInt(GAMEID);
		Gamefield tempGame = global.getGamefield(tempGameID);
		if (tempGame != null && tempGame.isPlayer(this.Nick)) {
			System.out.println(tempGameID);
			JSONObject end = new JSONObject();
			end.put(TYPE, GAME_ENDED);
			end.put(GAMEID, tempGameID);
			WeaponCard.Type murderWeapon = tempGame.murder.getWeapon()
					.getCardValue();
			PersonCard.Type murderer = tempGame.murder.getMurderer()
					.getCardValue();
			RoomCard.Type murderRoom = tempGame.murder.getScene()
					.getCardValue();
			JSONObject statement = new JSONObject();
			statement
					.put(PERSON, ModelToJson.convertPersonCardToJson(murderer));
			statement.put(WEAPON,
					ModelToJson.convertWeaponCardToJson(murderWeapon));
			statement.put(ROOM, ModelToJson.convertRoomCardToJson(murderRoom));
			end.put(STATEMENT, statement);
			notifyAllClientsInGame(tempGame, end);
			global.removeGamefield(tempGameID);
		}
	}

	private void watchGameHandler(JSONObject jsonObject) {
		int tempGameID = jsonObject.getInt(GAMEID);
		Gamefield tempGame = global.getGamefield(tempGameID);
		try {
			tempGame.addWatcher(this.Nick);

			JSONObject watcherAnswer = new JSONObject();
			watcherAnswer.put(TYPE, WATCHER_ADDED);
			watcherAnswer.put(GAMEID, tempGameID);
			watcherAnswer.put(NICK, this.Nick);
			notifyAllClientsInGame(tempGame, watcherAnswer);

			JSONObject watcherGame = new JSONObject();
			watcherGame.put(TYPE, GAMEINFO);
			watcherGame.put(GAME, getGameInfo(tempGameID));
			notifyThisNick(watcherGame);
		} catch (NullPointerException e) {
			JSONObject NoGameError = SJ.replyError(LanguageManager
					.getString("noGameError"));
			network.sendJSONtoThisClient(NoGameError);

			log.error("Kein Spiel ausgewählt!");
		}
	}

	/**
	 * @author Ludwig
	 **/
	private void disconnectHandler() {
		log.info("disconnectHandler");
		// TODO richtige Dialogboxen
		notifyThisNick(this.SJ.replyDisconnected("Bis zum nächsten Mal"));
		JSONObject userLeft = this.SJ.bcUserLeft(this.Nick);
		notifyOtherClientsBUTThis(this.Nick, userLeft);
		storage.remove(this.Nick);
		logAllClients();
		closeSocket();
	}

	// HELPER CLASSES
	// ______________________________________________________________________

	/**
	 * checks if color is avaiable in current Game
	 *
	 * @param playerList
	 * @return if Color ist Available
	 * @author Paul
	 */
	private boolean colorAvailable(Counter.Color farbe,
			ArrayList<Player> playerList) {
		for (int i = 0; i < playerList.size(); i++) {
			if (ModelToJson.convertColorToJson(playerList.get(i).getCounter()
					.getColor()) == (ModelToJson.convertColorToJson(farbe))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * checks if NICK is avaiable in current Game
	 *
	 * @param playerList
	 * @return if Color ist Available
	 * @author Paul
	 */
	private boolean nameAvailable(String nick, ArrayList<Player> playerList) {
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getName() == nick) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the Version of the ServerProtocol and ClientProtocol are equal
	 * do not accept other client versions
	 *
	 * @param jsonObject
	 * @autor: Ludwig
	 */
	private void checkVersion(JSONObject jsonObject) {
		if (!jsonObject.getString(VERSION).equals(VERSION_NUMBER)) {
			log.fatal("wrong client version - found: "
					+ jsonObject.getString(VERSION) + " expected:"
					+ VERSION_NUMBER);
			JSONObject loginReply = this.SJ
					.replyError("wrong client version - found: "
							+ jsonObject.getString(VERSION) + " expected:"
							+ VERSION_NUMBER);
			notifyThisNick(loginReply);
			closeSocket();
		}
	}

	/**
	 * Check if the Nick is already used by another Client.
	 *
	 * @param nick
	 * @autor: Paul
	 */
	private boolean checkNick(String nick) {
		String[] allPlayer = getAllPlayerStringArray();
		for (int i = 0; i < allPlayer.length; i++) {
			if (nick.equals(allPlayer[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Logt die Aktuelle PlayerListe des Servers
	 *
	 * @author: Paul
	 */
	private void logAllClients() {
		String[] allPlayers = getAllPlayerStringArray();
		String tmp = "";
		for (String str : allPlayers) {
			tmp += str + ", ";
		}
		log.info("Aktuelle Client-Liste: " + tmp);
	}

	/**
	 * @param message
	 * @author: Paul Schickt das JSON an alle Clients außer @param player
	 */
	private void notifyOtherClientsBUTThisWithGame(Gamefield tempGame, String player, JSONObject message) {
		ArrayList<String> allPlayers = tempGame.getPlayerNames();
		for (int i = 0; i < allPlayers.size(); i++) {
			if (allPlayers.get(i) != player) {
				Socket tempSock = storage.get(allPlayers.get(i))
						.getClientSocket();
				network.sendJSONtoSocket(message, tempSock);
			}
		}

	}
	
	 private void notifyOtherClientsBUTThis(String player, JSONObject message) {
	        log.info("To Player BUT : " + player);
	        String[] allPlayers = getAllPlayerStringArray();
	        for (int i = 0; i < allPlayers.length; i++) {
	            if (allPlayers[i] != player) {
	                Socket tempSock = storage.get(allPlayers[i]).getClientSocket();
	                network.sendJSONtoSocket(message, tempSock);
	            }
	        }
	    }


	/**
	 * Schickt das JSON an alle Clients außer @param player
	 *
	 * @param message
	 *            chat nachricht
	 * @author Paul, Ludwig
	 */
	private void notifyThisNick(JSONObject message) {
		String player = this.Nick;
		String[] allPlayers = getAllPlayerStringArray();
		for (String allPlayer : allPlayers) {
			if (allPlayer.equals(player)) {
				Socket tempSock = storage.get(allPlayer).getClientSocket();
				network.sendJSONtoSocket(message, tempSock);
			}
		}
	}

	/**
	 * Schickt das JSON an alle Clients außer @param player
	 *
	 * @param message
	 *            chat nachricht
	 * @author Paul, Ludwig
	 */
	private void notifyONE(String player, JSONObject message) {
		log.info("To ONLY Player : " + player);
		String[] allPlayers = getAllPlayerStringArray();
		for (String allPlayer : allPlayers) {
			if (allPlayer.equals(player)) {
				Socket tempSock = storage.get(allPlayer).getClientSocket();
				network.sendJSONtoSocket(message, tempSock);
			}
		}
	}

	/**
	 * @param message
	 * @author: Paul Schickt das JSON an alle Clients und Watcher
	 */
	private void notifyAllClientsInGame(Gamefield tempGame, JSONObject message) {
		ArrayList<String> allPlayers = tempGame.getPlayerNames();
		for (int i = 0; i < allPlayers.size(); i++) {
			Socket tempSock = storage.get(allPlayers.get(i)).getClientSocket();
			network.sendJSONtoSocket(message, tempSock);
		}
		String[] allWatchers = tempGame.getWatcherStringArray();
		for (int i = 0; i < allWatchers.length; i++) {
			Socket tempSock = storage.get(allWatchers[i]).getClientSocket();
			network.sendJSONtoSocket(message, tempSock);
		}
	}

	/**
	 * @param message
	 * @author: Paul Schickt das JSON an alle Client
	 */
	private void notifyAllClients(JSONObject message) {
		String[] allPlayers = getAllPlayerStringArray();
		for (int i = 0; i < allPlayers.length; i++) {
			Socket tempSock = storage.get(allPlayers[i]).getClientSocket();
			network.sendJSONtoSocket(message, tempSock);
		}
	}

	/**
	 * Alle Spieler auf diesem Server in einem String-Array
	 *
	 * @return all Player on Server
	 * @author Paul
	 */
	public String[] getAllPlayerStringArray() {
		Set<String> keys = storage.keySet();
		String[] allPlayers = new String[storage.size()];
		int counter = 0;
		for (String key : keys) {
			allPlayers[counter] = key;
			counter++;
		}
		return allPlayers;
	}

	/**
	 * @author Ludwig
	 **/
	private void returnOK() {
		log.info("Return OK");
		JSONObject replyOK = this.SJ.replyOK();
		notifyThisNick(replyOK);
	}

	/**
	 * @author Ludwig
	 **/
	private void returnError(String message) {
		JSONObject replyError = this.SJ.replyError("error: " + message);
		notifyThisNick(replyError);
		closeSocket();
	}

	/**
	 * @author Paul, Ludwig
	 */
	private void chatHandler(JSONObject jsonObject) {
		// TODO sent do game??
		JSONObject chat = jsonObject;
		chat.put(SENDER, this.Nick);

		// private message
		if (!jsonObject.optString(NICK).isEmpty()) {
			notifyONE(this.Nick, chat);
			notifyONE(jsonObject.getString(NICK), chat);
		} else {
			notifyAllClients(chat);
		}
	}

	private void morePlayer(JSONObject jsonObject) {

	}

	/**
	 * schließe die Verbindung zum Client.
	 */
	public void closeSocket() {
		try {
			clientSocket.close();
			log.info("User '" + this.Nick + "' abgemeldet. Port: "
					+ clientSocket.getPort() + " IP: "
					+ clientSocket.getInetAddress() + " Disconnected");

		} catch (IOException e) {
			log.error("Socket closing nicht erfolgreich");
		}
	}

	/**
	 * Loggt in die Konsole den Typen des Parameter
	 *
	 * @param obj
	 */
	public void checker(Object obj) {
		Class cls = obj.getClass();
		// System.out.println("The type of the object is: " + cls.getName());
	}

	/**
	 * Würfeln
	 */
	public int[] wuerfel() {
		int[] random = new int[2];
		random[0] = wurf.nextInt(6) + 1;
		random[1] = wurf.nextInt(6) + 1;
		return random;
	}

	/**
	 * Close NetworkThread
	 */
	public void killNetwork() {
		network.close();
		try {
			networkThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------getGameArray()-------------------------------------//

	/**
	 * SpielinfoJSONArray / gamearray für alle Spiele auf Server [spielinfo]
	 *
	 * @return JSONArray
	 */
	public JSONArray getGameArray() {
		log.info("getGameArray");
		JSONArray gameArray = new JSONArray();
		HashMap<Integer, Gamefield> gamefields = global.getGamefields();
		if (gamefields.size() != 0) {
			for (Map.Entry<Integer, Gamefield> entry : gamefields.entrySet()) {
				int key = entry.getKey();
				gameArray.put(getGameInfo(key));
			}
			log.info("@getGameArray verfügbare Spiele: " + gamefields.size());
			return gameArray;

		} else {
			return gameArray;
		}
	}

	/**
	 * Spielinfo für einzelnes Game
	 * <p>
	 * { "gameID" : int, "gamestate" : Spielzustand, "players" : [ Spielerinfo
	 * ], "watchers" : [ String ], "person positions" : [ Personenposition ]
	 * "weapon positions" : [ Waffenposition ] }
	 *
	 * @param GameID
	 * @return JSON
	 */
	public JSONObject getGameInfo(int GameID) {
		Gamefield tempGame = global.getGamefield(GameID);
		JSONObject getGameInfoAnswer = new JSONObject();
		getGameInfoAnswer.put(GAMEID, GameID);
		getGameInfoAnswer.put(GAMESTATE,
				ModelToJson.convertGameStateToString(tempGame));
		getGameInfoAnswer.put(PLAYERS, getPlayerInfoArray(tempGame));
		getGameInfoAnswer.put(WATCHERS,
				new JSONArray(tempGame.getWatcherStringArray()));
		getGameInfoAnswer.put(PERSON_POSITIONS,
				getPlayerPositionArray(tempGame));
		getGameInfoAnswer.put(WEAPON_POSITIONS,
				getWeaponPositionArray(tempGame));
		return getGameInfoAnswer;
	}

	/**
	 * SpielerInfo für alle Spieler in Array // players: -> in einzelnem Game
	 * als JSONARRAY
	 *
	 * @param game
	 * @return JSONArray
	 */
	public JSONArray getPlayerInfoArray(Gamefield game) {
		JSONArray playerInfoArray = new JSONArray();
		ArrayList<Player> playerList = game.getPlayerList();
		for (int i = 0; i < playerList.size(); i++) {
			Player tempPlayer = playerList.get(i);
			JSONObject playerInfoJson = getPlayerInfo(tempPlayer);
			playerInfoArray.put(playerInfoJson);
		}
		return playerInfoArray;
	}

	/**
	 * SpielerInfo für einzelnen Spieler in Array
	 *
	 * @param player
	 * @return JSONArray
	 */
	public JSONObject getPlayerInfo(Player player) {
		// log.info("getPlayerInfo");
		JSONObject playerInfo = new JSONObject();
		playerInfo.put(NICK, player.getName());
		playerInfo.put(COLOR,
				ModelToJson.convertColorToJson(player.getCounter().getColor()));
		if (getPlayerStateArray(player) != null) {
			playerInfo.put(PLAYERSTATE, getPlayerStateArray(player));
		}
		return playerInfo;
	}

	/**
	 * SpielerPosition // person positions: -> in einzelnem Game als JSONARRAY
	 *
	 * @param game
	 * @return JSONArray
	 */
	public JSONArray getPlayerPositionArray(Gamefield game) {
		// log.info("getPlayerPositionArray");
		ArrayList<Player> playerList = game.getPlayerList();
		JSONArray playerPosArray = new JSONArray();
		for (int i = 0; i < playerList.size(); i++) {
			JSONObject playerPos = getPlayerPosition(playerList.get(i));
			playerPosArray.put(playerPos);
		}
		return playerPosArray;
	}

	public JSONObject getPlayerPosition(Player player) {
		JSONObject playerPos = new JSONObject();
		playerPos.put(PERSON,
				ModelToJson.convertColorToJson(player.getCounter().getColor()));
		playerPos.put(FIELD, getPlayerPositionField(player.getCounter()));
		return playerPos;
	}

	/**
	 * Get the Position for a single Player
	 *
	 * @param counter
	 * @return
	 */
	private JSONObject getPlayerPositionField(Counter counter) {
		JSONObject playerPos = new JSONObject();
		playerPos.put(X, counter.getPositionX());
		playerPos.put(Y, counter.getPositionY());
		return playerPos;
	}

	/**
	 * Alle WaffenPositionen in einem Game
	 *
	 * @param game
	 * @return JSONArray
	 */
	public JSONArray getWeaponPositionArray(Gamefield game) {
		// log.info("getWeaponPositionArray");
		ArrayList<Weapon> weaponList = game.getWeaponList();
		JSONArray weaponPosArray = new JSONArray();
		for (int i = 0; i < weaponList.size(); i++) {
			weaponPosArray.put(getWeaponJSON(weaponList.get(i)));
		}
		return weaponPosArray;
	}

	/**
	 * SpielerZUSTAND/playerState für einzelne Spieler in einem Game
	 *
	 * @return JSON
	 */
	public JSONArray getPlayerStateArray(Player player) {
		// log.info("getPlayerStateArray");
		JSONArray playerStateArray = new JSONArray();
		EnumSet<Player.Playerstate> playerstates = player.getPlayerstates();
		if (playerstates == null) {
			log.info("getPlayerStateArray return leer");
			return playerStateArray;
		}
		for (Iterator it = playerstates.iterator(); it.hasNext();) {
			Player.Playerstate temp = (Player.Playerstate) it.next();
			playerStateArray.put(ModelToJson.convertStateToJson(temp));
		}
		return playerStateArray;
	}

	/**
	 * WeaponPosition für einzelne Waffe
	 *
	 * @param weapon
	 * @return JSON
	 */
	public JSONObject getWeaponJSON(Weapon weapon) {
		JSONObject weaponPos = new JSONObject();
		weaponPos.put("x", weapon.getPosX());
		weaponPos.put("y", weapon.getPosY());

		JSONObject weaponNameAndPos = new JSONObject();
		weaponNameAndPos.put("weapon",
				ModelToJson.convertWeaponToJson(weapon.getCardType()));
		weaponNameAndPos.put("field", weaponPos);
		return weaponNameAndPos;
	}

	/**
	 * Set all PlayerStates to DoNothing
	 *
	 * @param gamefield
	 */
	public void setAllPlayerStateToDoNothing(Gamefield gamefield) {
		ArrayList<Player> playerList = gamefield.getPlayerList();
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).setPlayerstate(Player.Playerstate.DO_NOTHING);
		}
	}

	/**
	 * Send actual Player State
	 */
	public void setPlayerStateWithNotify(Gamefield game, String nick,
			EnumSet<Player.Playerstate> state) {

		setAllPlayerStateToDoNothing(game);
		game.getPlayer(nick).setPlayerstates(state);

		JSONObject stateUpdate = new JSONObject();
		stateUpdate.put(TYPE, STATEUPDATE);
		stateUpdate.put(GAMEID, game.getGameInfo().getGameID());
		stateUpdate.put(PLAYER, getPlayerInfo(game.getPlayer(nick)));

		notifyAllClientsInGame(game, stateUpdate);

	}

	private String getPlayerByCounterColor(PersonCard.Type person,
			Gamefield game) {
		String playername = null;
		for (Player player1 : game.getPlayerList()) {
			if (player1.getCounter().getColor().toString()
					.equals(person.toString())) {
				playername = player1.getName();
			}
		}
		return playername;
	}

	// Check if Room has Geheimgang

	private boolean checkForGeheimgang(Gamefield tempGame) {
		Player player = tempGame.getPlayer(tempGame.getCurrentPlayer());
		int x = player.getCounter().getPositionX();
		int y = player.getCounter().getPositionY();
		if (x == 17 && y == 5 || x == 4 && y == 19 || x == 19 && y == 18
				|| x == 6 && y == 3) {
			return true;
		}
		return false;
	}
}
