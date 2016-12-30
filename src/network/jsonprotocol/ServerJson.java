package network.jsonprotocol;
import json.org.JSONArray;
import json.org.JSONObject;
import model.Card;
import model.Counter;
import model.Player;
import model.Suspicion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static network.jsonprotocol.ModelToJson.*;
/**
 * Generator-Klasse für Json-Nachrichten an den Server
 * @author Ludwig
 */
public class ServerJson implements ServerJsonInterface, JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(ServerJson.class);
    
    /**
     * Gibt ein default JSON-Objekt zurück
     * dadurch können hier leicht irgendwelche Standardattribute, die bei jedem JSON-Objekt vorhanden sein sollen, hinzugefügt werden
     * @author Ludwig
     */
    private JSONObject getDefaultJSON() {
        return new JSONObject();
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject replyOK() {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, OK);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject replyError(String message) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, ERROR);
        j.put(MESSAGE, message);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcUDPServer(String group, int tcp_port) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, UDP_SERVER);
        j.put(GROUP, group);
        j.put(TCP_PORT, tcp_port);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject replyLogin(String[] expansions, String[] nick_array, /*Spielinfo*/ JSONArray game_array) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, LOGIN_SUCCESSFUL);
        j.put(EXPANSIONS, expansions);
        j.put(NICK_ARRAY, nick_array);
        j.put(GAME_ARRAY, game_array);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcLogin(String nick) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, USER_ADDED);
        j.put(NICK, nick);
        return j;
    }

    /** @author Paul */
    @Override
    public JSONObject bcGameCreated(int gameID, Player player) {
        log.error("DO NOT USE: bcGameCreated");
        return null;
    }

    /** @author Paul */
    @Override
    public JSONObject bcPlayerAdded(int gameID, Player player) {
        log.error("DO NOT USE: bcPlayerAdded");
        return null;
    }


    /** @author Ludwig */
    @Override
    public JSONObject sentPlayerCards(int gameID, Card[] cards) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, PLAYER_CARDS);
        j.put(GAMEID, gameID);
        j.put(CARDS, cards);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcGameStarted(int gameID, String[] order) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, GAME_STARTED);
        j.put(GAMEID, gameID);
        j.put(ORDER, order);
        return j;
    }
    
    /** @author Ludwig,Paul */
    @Override
    public JSONObject bcStateupdate(int gameID, Player player) {
        log.error("DO NOT USE : bcStateupdate");
        return null;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcDiceResult(int gameID, int[] result) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, DICE_RESULT);
        j.put(GAMEID, gameID);
        j.put(RESULT, result);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcMoved(int gameID, Counter.Color color, int x, int y) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, MOVED);
        j.put(GAMEID, gameID);
        j.put(COLOR, convertColorToJson(color));
        j.put(FIELD, generateField(x, y));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject replyPoolcards(Card[] cards) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, POOLCARDS);
        j.put(CARDS, convertCardsToJson(cards));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcSuspicion(int gameID, Suspicion statement) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, SUSPICION);
        j.put(GAMEID, gameID);
        j.put(STATEMENT, generateStatement(statement));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcDisproved(int gameID, String nick, Card card) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, DISPROVED);
        j.put(GAMEID, gameID);
        j.put(NICK, nick);
        j.put(CARD, convertCardToJson(card));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcNoDisprove(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, NO_DISPROVE);
        j.put(GAMEID, gameID);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcWrongAccusation(int gameID, Suspicion statement) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, WRONG_ACCUSATION);
        j.put(GAMEID, gameID);
        j.put(STATEMENT, generateStatement(statement));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcGameEnded(int gameID, String nick, Suspicion statement) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, GAME_ENDED);
        j.put(GAMEID, gameID);
        j.put(NICK, nick);
        j.put(STATEMENT, generateStatement(statement));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcLeftGame(int gameID, String nick) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, LEFT_GAME);
        j.put(GAMEID, gameID);
        j.put(NICK, nick);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcGameDeleted(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, GAME_DELETED);
        j.put(GAMEID, gameID);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject replyDisconnected(String message) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, DISCONNECTED);
        j.put(MESSAGE, message);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcUserLeft(String nick) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, USER_LEFT);
        j.put(NICK, nick);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject bcWatcherAdded(int gameID, String nick) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, WATCHER_ADDED);
        j.put(GAMEID, gameID);
        j.put(NICK, nick);
        return j;
    }
    
    /** @author Ludwig,Paul
     * */
    @Override
    public JSONObject sentChat(String message,String sender) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, CHAT);
        j.put(MESSAGE, message);
        // Generiere Datumsobjekt für die richtige Formatieriung im Json
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm:ss.SS");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);
        String formattedDateTime1 = dateTime.format(formatter1);
        String timestamp = formattedDateTime+"T"+formattedDateTime1;
        j.put(TIMESTAMP,timestamp);
        if (sender != null) j.put(SENDER, sender);
        return j;
    }
}
