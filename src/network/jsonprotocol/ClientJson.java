package network.jsonprotocol;
import json.org.JSONObject;
import model.Card;
import model.Counter;
import model.Suspicion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static network.jsonprotocol.ModelToJson.*;
/**
 * Generator-Klasse für Json-Nachrichten an den Server
 * @author Ludwig
 */
public class ClientJson implements ClientJsonInterface, JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(ClientJson.class);
    
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
    public JSONObject bcUDPClient(String group) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, UDP_CLIENT);
        j.put(GROUP, group);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject login(String nick, String group, String[] expansions) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, LOGIN);
        j.put(NICK, nick);
        j.put(GROUP, group);
        j.put(VERSION, VERSION_NUMBER);
        j.put(EXPANSIONS, expansions);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject createGame(Counter.Color color) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, CREATE_GAME);
        j.put(COLOR, convertColorToJson(color));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject joinGame(int gameID, Counter.Color color) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, JOIN_GAME);
        j.put(GAMEID, gameID);
        j.put(COLOR, convertColorToJson(color));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject startGame(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, START_GAME);
        j.put(GAMEID, gameID);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject rollDice(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, ROLL_DICE);
        j.put(GAMEID, gameID);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject move(int gameID, int x, int y) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, MOVE);
        j.put(GAMEID, gameID);
        j.put(FIELD, generateField(x, y));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject secretPassage(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, SECRET_PASSAGE);
        j.put(GAMEID, gameID);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject suspect(int gameID, Suspicion statement) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, SUSPECT);
        j.put(GAMEID, gameID);
        j.put(STATEMENT, generateStatement(statement));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject disprove(int gameID, Card card) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, DISPROVE);
        j.put(GAMEID, gameID);
        if (card != null)
            j.put(CARD, convertCardToJson(card));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject accuse(int gameID, Suspicion statement) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, ACCUSE);
        j.put(GAMEID, gameID);
        j.put(STATEMENT, generateStatement(statement));
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject endTurn(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, END_TURN);
        j.put(GAMEID, gameID);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject leaveGame(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, LEAVE_GAME);
        j.put(GAMEID, gameID);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject disconnect() {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, DISCONNECT);
        return j;
    }
    
    /** @author Ludwig */
    @Override
    public JSONObject watchGame(int gameID) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, WATCH_GAME);
        j.put(GAMEID, gameID);
        return j;
    }
    
    
    /** @author Ludwig */
    @Override
    public JSONObject chat(String message, long timestamp, int gameID, String nick) {
        JSONObject j = getDefaultJSON();
        j.put(TYPE, CHAT);
        j.put(MESSAGE, message);
    
        // Generiere Datumsobjekt für die richtige Formatieriung im Json
        LocalDateTime time;
        if (timestamp == 0) {
            log.debug("found no valid chat message time, replace with actual time");
            time = LocalDateTime.ofEpochSecond(Instant.now().getEpochSecond(), 10000, ZoneOffset.UTC);
        } else {
            time = LocalDateTime.ofEpochSecond(timestamp, 10000, ZoneOffset.UTC);
        }
        j.put(TIMESTAMP, time.toString().substring(0, 23));
    
        if (gameID != 0) j.put(GAMEID, gameID);
        if (nick != null) j.put(NICK, nick);
        return j;
    }
}