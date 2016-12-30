package network.client;
import json.org.JSONArray;
import json.org.JSONObject;
import model.*;
import network.jsonprotocol.JsonProtocollConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumSet;

import static network.jsonprotocol.JsonToModel.*;
/**
 * Class parse/interprets received jsonobjects and modifies model or notice presenter
 * @author Ludwig
 */
public class ClientReceive implements JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(ClientReceive.class);

    private final Global global; // reference to global model
    private ReactToServer reactToServer = new EmptyReactToServer();
    
    /**
     * Contructor
     * @param global reference to global model
     * @author Paul, Ludwig
     */
    public ClientReceive(Global global, ReactToServer reactToServer) {
        this.global = global;
        this.reactToServer = reactToServer;
    }

    /**
     * Interprets all received jsonobject
     * The jsonobject will be interpretet and modefies the model or causes action in the presenter
     * @param jsonObject received jsonobject
     * @author Ludwig
     */
    public void interpretJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            log.error("not a valid jsonobject from server - could not interpret");
            return;
        }
        log.debug("parse type of jsonobject");
        switch (jsonObject.getString(TYPE)) {
            case UDP_SERVER:
                handleUDP(jsonObject);
                break;
            case UDP_CLIENT:
                // discard message
                log.info("discard udp message from other client");
                break;
            case LOGIN_SUCCESSFUL:
                handleLoginSuccessful(jsonObject);
                break;
            case USER_ADDED:
                handleUserAdded(jsonObject);
                break;
            case GAME_CREATED:
                handleGameCreated(jsonObject);
                break;
            case PLAYER_ADDED:
                handlePlayerAdded(jsonObject);
                break;
            case WATCHER_ADDED:
                handleWatcherAdded(jsonObject);
                break;
            case GAMEINFO:
                handleGameinfo(jsonObject);
                break;
            case GAME_STARTED:
                handleGameStarted(jsonObject);
                break;
            case GAME_ENDED:
                handleGameEnded(jsonObject);
                break;
            case LEFT_GAME:
                handleLeftGame(jsonObject);
                break;
            case GAME_DELETED:
                handleGameDeleted(jsonObject);
                break;
            case DISCONNECTED:
                handleDisconnected(jsonObject);
                break;
            case USER_LEFT:
                hanldeUserleft(jsonObject);
                break;
            case CHAT:
                handleChat(jsonObject);
                break;
            // ingame messages
            case PLAYER_CARDS:
                handlePlayerCards(jsonObject);
                break;
            case STATEUPDATE:
                handleStateupdate(jsonObject);
                break;
            case DICE_RESULT:
                handleDiceResult(jsonObject);
                break;
            case MOVED:
                handleMoved(jsonObject);
                break;
            case POOLCARDS:
                handlePoolcards(jsonObject);
                break;
            case SUSPICION:
                handleSuspicion(jsonObject);
                break;
            case DISPROVED:
                handleDisproved(jsonObject);
                break;
            case NO_DISPROVE:
                handleNoDisprove(jsonObject);
                break;
            case WRONG_ACCUSATION:
                handleWrongAccusation(jsonObject);
                break;
            // additional NetworkRunnable controll messages
            case OK:
                handleOK(jsonObject);
                break;
            case ERROR:
                handleError(jsonObject);
                break;
            default:
                log.fatal("no according json to interpret found");
                log.fatal(jsonObject);
        }
    }
    
    /**
     * @param jsonObject received jsonobject to parse
     * @author Paul
     */
    private void handleOK(JSONObject jsonObject) {
        log.info("Answer to request ended successful with: OK");
    }
    
    private void handleError(JSONObject jsonObject) {
        log.error("received error msg: ");
        log.error(jsonObject);
        
        reactToServer.handleError(jsonObject);
    }

    /**
     * Adds by udp received server to list
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleUDP(JSONObject jsonObject) {
        reactToServer.handleUDP(jsonObject);
    }

    /**
     * Creates and adds: gamefields, clients, extensions to model
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleLoginSuccessful(JSONObject jsonObject) {
        log.info("login to server successful, start parsing server informations");
        // todo expansions
        addClientsToGlobal(jsonObject.optJSONArray(NICK_ARRAY));
        addGamefieldsToGlobal(jsonObject.optJSONArray(GAME_ARRAY));
        
        reactToServer.handleLoginSuccessful(jsonObject);
    }

    /**
     * Add clients to global model
     * Only used if login was successful
     * @param jsonArray jsonarray with string of nicknames
     * @author Ludwig
     */
    private void addClientsToGlobal(JSONArray jsonArray) {
        if (jsonArray == null) {
            log.info("no clients found");
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            if (!global.existsClient(jsonArray.getString(i)))
                global.addClient(jsonArray.getString(i));
            else {
                log.error("Client >" + jsonArray.getString(i) + "< already exists"); 
            }
        }
    }

    /**
     * Add gamefields to global model
     * Only used if login was successful
     * @param jsonArray jsonarray including all gamefields
     * @author Ludwig
     */
    private void addGamefieldsToGlobal(JSONArray jsonArray) {
        if (jsonArray == null) {
            log.info("no game(s) found");
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            addGamefieldToGlobal(jsonArray.optJSONObject(i));
        }
    }

    /**
     * Add a single gamefield to global model
     * @param jsonObject jsonobject of gamefield
     * @author Ludwig
     */
    private void addGamefieldToGlobal(JSONObject jsonObject) {
        if (jsonObject == null) {
            log.info("no game found");
            return;
        }
        if (global.existsGameID(jsonObject.getInt(GAMEID))) {
            log.error("gameId " + jsonObject.getInt(GAMEID) + " already exists");
            return;
        }

        log.info("parse gameId " + jsonObject.getInt(GAMEID));
        Gamefield gamefield = new Gamefield(jsonObject.getInt(GAMEID), global.getMyNick());
        global.addGamefield(jsonObject.getInt(GAMEID), gamefield);
        //gamestate
        gamefield.getGameInfo().setGamestate(parseGamestate(jsonObject.optString(GAMESTATE)));
        //players
        addPlayersToGamefield(gamefield, jsonObject.optJSONArray(PLAYERS));
        //watchers
        addWatchersToGamefield(gamefield, jsonObject.optJSONArray(WATCHERS));
        //person positions
        addPersonPositionsToGamefield(gamefield, jsonObject.optJSONArray(PERSON_POSITIONS));
        //weapon positions
        addWeaponPositionsToGamefield(gamefield, jsonObject.optJSONArray(WEAPON_POSITIONS));
        log.info("gameId " + jsonObject.getInt(GAMEID) + " added");
    }

    /**
     * Add players to a gamefield
     * Only used if login was successful
     * @param gamefield reference to gamefield
     * @param jsonArray jsonarray include all strings of nicknames
     * @author Ludwig
     */
    private void addPlayersToGamefield(Gamefield gamefield, JSONArray jsonArray) {
        if (jsonArray == null) {
            log.info("no player(s) found");
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            addPlayerToGamefield(gamefield, jsonArray.optJSONObject(i));
        }
    }

    /**
     * Add a single player to a gamefield
     * @param gamefield  reference to gamefield
     * @param jsonObject jsonobject of player
     * @author Ludwig
     */
    private void addPlayerToGamefield(Gamefield gamefield, JSONObject jsonObject) {
        if (jsonObject == null) {
            log.info("no player found");
            return;
        }
        gamefield.createPlayer(jsonObject.getString(NICK), convertFarbeToModel(jsonObject.getString(COLOR)), convertSpielerzustandToModel(jsonObject.optJSONArray(PLAYERSTATE)));
    }

    /**
     * Add all watchers to a gamefield
     * Only used if login was successful
     * @param gamefield reference to gamefield
     * @param jsonArray jsonarray with stings of nicknames
     * @author Ludwig
     */
    private void addWatchersToGamefield(Gamefield gamefield, JSONArray jsonArray) {
        if (jsonArray == null) {
            log.info("no watcher(s) found");
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            gamefield.getGameInfo().addWatcher(jsonArray.getString(i));
        }
    }

    /**
     * Sets all counter positions to a gamefield
     * @param gamefield reference to gamefield
     * @param jsonArray all person positions
     * @author Ludwig
     */
    private void addPersonPositionsToGamefield(Gamefield gamefield, JSONArray jsonArray) {
        if (jsonArray == null) {
            log.info("no person position(s) found");
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject person = jsonArray.optJSONObject(i);
            if (person == null) continue;
            gamefield.setCounter(convertFarbeToModel(person.getString(PERSON)), person.getJSONObject(FIELD).getInt(X), person.getJSONObject(FIELD).getInt(Y));
        }
    }

    /**
     * Sets all weapon positions to a gamefield
     * @param gamefield reference to gamefield
     * @param jsonArray all weapon positions
     * @author Ludwig
     */
    private void addWeaponPositionsToGamefield(Gamefield gamefield, JSONArray jsonArray) {
        if (jsonArray == null) {
            log.info("no weapon position(s) found");
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject weapon = jsonArray.optJSONObject(i);
            if (weapon == null) continue;
            gamefield.setWeapon(convertWaffeToModel(weapon.getString(WEAPON)), weapon.getJSONObject(FIELD).getInt(X), weapon.getJSONObject(FIELD).getInt(Y));
        }
    }

    /**
     * Add new client to global model
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleUserAdded(JSONObject jsonObject) {
        log.info("another client connected");
        if (!global.existsClient(jsonObject.getString(NICK)))
            global.addClient(jsonObject.getString(NICK));
        else
            log.error("Client >" + jsonObject.getString(NICK) + "< already exists");
        
        reactToServer.handleUserAdded(jsonObject);
    }

    /**
     * Add new gamefield to global model
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Maurice, Rainer
     */
    private void handleGameCreated(JSONObject jsonObject) {
        if (global.existsGameID(jsonObject.getInt(GAMEID))) {
            log.error("gameId " + jsonObject.getInt(GAMEID) + " already exists");
            return;
        }

        // create new gamefield
        Gamefield gamefield = new Gamefield(jsonObject.getInt(GAMEID), global.getMyNick());
        // add to global
        global.addGamefield(jsonObject.getInt(GAMEID), gamefield);
        // add player (creater)
        addPlayerToGamefield(gamefield, jsonObject.optJSONObject(PLAYER));
        // no watchers to handle
        
        log.debug("gameId " + jsonObject.getInt(GAMEID) + " created");
        reactToServer.handleGameCreated(jsonObject);
    }

    /**
     * Update gameinfo that game has been started
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Rainer
     */
    private void handleGameStarted(JSONObject jsonObject) {
        Gamefield gamefield = getGamefield(jsonObject);
       
        // update gamestate of gameinfo
        gamefield.getGameInfo().setGamestate(GameInfo.Gamestate.STARTED);
        
        // create order of players
        JSONArray jsonArray = jsonObject.getJSONArray(ORDER);
        String[] strings = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            strings[i] = jsonArray.getString(i);
        }
        // update order in gamefield
        gamefield.setOrder(strings);
        
        reactToServer.handleGameStarted(jsonObject);
    }
    
    /**
     * Add new player to gamefield
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Rainer, Paul
     */
    private void handlePlayerAdded(JSONObject jsonObject) {
        // add new player to gamefield
        addPlayerToGamefield(getGamefield(jsonObject), jsonObject.getJSONObject(PLAYER));
        
        reactToServer.handlePlayerAdded(jsonObject);
    }

    /**
     * Add new watcher to gameinfo
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleWatcherAdded(JSONObject jsonObject) {
        getGamefield(jsonObject).getGameInfo().addWatcher(jsonObject.getString(NICK));
        
        reactToServer.handleWatcherAdded(jsonObject);
    }

    /**
     * Handle gameinfo
     * Only used if client starts to watch a game
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Maurice
     */
    private void handleGameinfo(JSONObject jsonObject) {
        // remove old gamefield
        global.removeGamefield(jsonObject.getJSONObject(GAME).getInt(GAMEID));
        // add new fresh gamefield
        addGamefieldToGlobal(jsonObject.getJSONObject(GAME));
        
        reactToServer.handleGameinfo(jsonObject);
    }

    /**
     * Update gameinfo that game ended and show result in view
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Rainer, Maurice
     */
    private void handleGameEnded(JSONObject jsonObject) {
        // nothing to do anymore for players
        for (Player player : getGamefield(jsonObject).getPlayerList())
            player.setPlayerstate(Player.Playerstate.DO_NOTHING);
    
        // update gamestate of gameinfo
        getGamefield(jsonObject).getGameInfo().setGamestate(GameInfo.Gamestate.ENDED);
    
        // logg info
        if (!jsonObject.optString(NICK).equals(""))
            log.info("game ended - winner: " + jsonObject.optString(NICK));
        else
            log.info("game ended - no winner");
        
        reactToServer.handleGameEnded(jsonObject);
    }
    
    /**
     * Removes player or watcher from game if left the game
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleLeftGame(JSONObject jsonObject) {
        Gamefield gamefield = getGamefield(jsonObject);
        if (gamefield.getGameInfo().existsWatcher(jsonObject.getString(NICK))) {
            log.info("watcher >" + jsonObject.getString(NICK) + "< left gameID " + getGamefield(jsonObject).getGameInfo().getGameID());
            gamefield.getGameInfo().removeWatcher(jsonObject.getString(NICK));
        } else {
            log.info("player >" + jsonObject.getString(NICK) + "< left gameID " + getGamefield(jsonObject).getGameInfo().getGameID());
            gamefield.removePlayer(jsonObject.getString(NICK));
        }
        
        reactToServer.handleLeftGame(jsonObject);
    }

    /**
     * Removes a ended and deleted gamefield
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleGameDeleted(JSONObject jsonObject) {
        global.removeGamefield(jsonObject.getInt(GAMEID));
        
        reactToServer.handleGameDeleted(jsonObject);
    }

    /**
     * Handle successful disconnect from server
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleDisconnected(JSONObject jsonObject) {
        log.info("successful disconnected from server");
        // print byebye message
        log.info(jsonObject.getString(MESSAGE));
        // todo kill model ?
        // todo kill view, presenter, connection ?
        
        reactToServer.handleDisconnected(jsonObject);
    }

    /**
     * Removes a other disconnected client
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void hanldeUserleft(JSONObject jsonObject) {
        log.info("client >" + jsonObject.getString(NICK) + "< disconnected");
        global.removeClient(jsonObject.getString(NICK));
        
        reactToServer.hanldeUserleft(jsonObject);
    }

    /**
     * Show received chat messages from server
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleChat(JSONObject jsonObject) {
        log.debug("parse chat msg");
        
        // go to hell stupid morons
        if (jsonObject.optString(SENDER).contains("spambot")) {
            log.info("ignore spam");
            return;
        }
        
        reactToServer.handleChat(jsonObject);
    }

    /**
     * Add cards to player after game start
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handlePlayerCards(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray(CARDS);
        for (int i = 0; i < jsonArray.length(); i++) {
            Card card = convertKarteToModel(jsonArray.getString(i));
            //log.info(card);
            getGamefield(jsonObject).getPlayer().addCard(card);
            //getGamefield(jsonObject).player.addCard(convertKarteToModel(jsonArray.getString(i)));
        }
        // maybe works, not tested
        /*
        for (Object card : jsonObject.getJSONArray(CARDS)) {
            getGamefield(jsonObject).player.addCard(convertKarteToModel(card.toString()));
            System.out.println(card.toString()); // debug
        }
        */
        
        reactToServer.handlePlayerCards(jsonObject);
    }

    /**
     * Updates the playerstate of a player
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Paul, Rainer, Jonas
     */
    private void handleStateupdate(JSONObject jsonObject) {
        EnumSet<Player.Playerstate> playerstates = convertSpielerzustandToModel(jsonObject.getJSONObject(PLAYER).getJSONArray(PLAYERSTATE));
        if (playerstates == null) return;
    
        log.debug("set new playerstate of >" + jsonObject.getJSONObject(PLAYER).getString(NICK) + "<");
    
        // official test server does not send do_nothing state updates
        // some strange behaviour of specification
        // set playerstates of all players to default: do_nothing
        if (!playerstates.contains(Player.Playerstate.DO_NOTHING))
            for (Player player : getGamefield(jsonObject).getPlayerList())
                player.setPlayerstate(Player.Playerstate.DO_NOTHING);
    
        // update specific player to new playerstate
        getGamefield(jsonObject)
                .getPlayer(jsonObject.getJSONObject(PLAYER).getString(NICK))
                .setPlayerstates(playerstates);
    
        //set the current Player Variable correct
		if (playerstates.contains(Player.Playerstate.ROLL_DICE))
			getGamefield(jsonObject).setCurrentPlayer(
					jsonObject.getJSONObject(PLAYER).getString(NICK)
            );
        
        reactToServer.handleStateupdate(jsonObject);
    }

    /**
     * Set dice result from server
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Rainer
     */
    private void handleDiceResult(JSONObject jsonObject) {
        // set both dice results
        getGamefield(jsonObject).dices.setPips1(jsonObject.getJSONArray(RESULT).getInt(0));
        getGamefield(jsonObject).dices.setPips2(jsonObject.getJSONArray(RESULT).getInt(1));
        
        reactToServer.handleDiceResult(jsonObject);
    }

    /**
     * Moves a counter to new position
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Rainer
     */
    private void handleMoved(JSONObject jsonObject) {
        getGamefield(jsonObject).setCounter(
                convertFarbeToModel(jsonObject.getJSONObject(PERSON_POSITION).getString(PERSON)),
                jsonObject.getJSONObject(PERSON_POSITION).getJSONObject(FIELD).getInt(X),
                jsonObject.getJSONObject(PERSON_POSITION).getJSONObject(FIELD).getInt(Y)
        );
        
        reactToServer.handleMoved(jsonObject);
    }

    /**
     * Add pool cards to model
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handlePoolcards(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray(CARDS);
        ArrayList<Card> cards = new ArrayList<>();

        // convert to arraylist
        for (int i = 0; i < jsonArray.length(); i++) {
            cards.add(convertKarteToModel(jsonArray.getString(i)));
        }

        // set poolcards
        getGamefield(jsonObject).setPoolCards(cards);
        
        reactToServer.handlePoolcards(jsonObject);
    }

    /**
     * Adds actual suspicion to model
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Sarah, Roxanna
     */
    private void handleSuspicion(JSONObject jsonObject) {
        // search last suspecting person
        Player suspectingPlayer = null;
        for (Player player : getGamefield(jsonObject).getPlayerList()) {
            if (player.hasPlayerstate(Player.Playerstate.SUSPECT))
                suspectingPlayer = player;
        }
        if (suspectingPlayer == null) {
            log.fatal("no suspecting player found");
            return;
        }
        
        JSONObject statement = jsonObject.getJSONObject(STATEMENT);
        getGamefield(jsonObject).getSuspicion().setSuspCounter(convertFarbeToCardModel(statement.getString(PERSON)));
        getGamefield(jsonObject).getSuspicion().setSuspWeapon(convertWaffeToCardModel(statement.getString(WEAPON)));
        
        // supectingPlayer is the player suspecting stuff
        RoomCard.Type suspicionRoom = getGamefield(jsonObject).board.getRoomOfPerson(suspectingPlayer); 
        getGamefield(jsonObject).getSuspicion().setSuspRoom(suspicionRoom);
        
        // update person position by suspicion
        log.debug("update person position by suspicion");
        getGamefield(jsonObject).setCounter(convertFarbeToModel(statement.getString(PERSON)),
                suspectingPlayer.getCounter().getPositionX(),
                suspectingPlayer.getCounter().getPositionY()
        );
        
        // update weapon position by suspicion
        log.debug("update weapon position by suspicion");
        getGamefield(jsonObject).setWeapon(convertWaffeToModel(statement.getString(WEAPON)),
                suspectingPlayer.getCounter().getPositionX(),
                suspectingPlayer.getCounter().getPositionY()
        );
        
        reactToServer.handleSuspicion(jsonObject);
    }

    /**
     * Handle suspicions from other player
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Sarah, Rainer
     */
    private void handleDisproved(JSONObject jsonObject) {        
        reactToServer.handleDisproved(jsonObject);
    }

    /**
     * Handle not disproved suspicion
     * @param jsonObject received jsonobject to parse
     * @author Ludwig, Sarah
     */
    private void handleNoDisprove(JSONObject jsonObject) {
        // todo model ?
        
        reactToServer.handleNoDisprove(jsonObject);
    }

    /**
     * Handle wrong accusation of other player or self
     * @param jsonObject received jsonobject to parse
     * @author Ludwig
     */
    private void handleWrongAccusation(JSONObject jsonObject) {
        JSONObject statement = jsonObject.getJSONObject(STATEMENT);
        statement.getString(PERSON);
        statement.getString(WEAPON);
        statement.getString(ROOM);
        // todo to model?
        
        reactToServer.handleWrongAccusation(jsonObject);
    }

    /**
     * Helper to get gamefield shorter
     * @param jsonObject jsonobject containing gameId
     * @return reference to according gamefield
     * @author Ludwig
     */
    private Gamefield getGamefield(JSONObject jsonObject) {
        return global.getGamefield(jsonObject.getInt(GAMEID));
    }
    
    /**
     * Returns reactToServer
     * @return Reference to reactToServer
     * @author Ludwig
     */
    public ReactToServer getReactToServer() {
        return reactToServer;
    }
}
