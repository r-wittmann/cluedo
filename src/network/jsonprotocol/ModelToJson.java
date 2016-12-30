package network.jsonprotocol;

import json.org.JSONArray;
import json.org.JSONObject;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Basisfunktionen um Daten vom Model in Json zu konvertieren
 *
 * @author Ludwig
 */
public class ModelToJson implements JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(ModelToJson.class);

    /**
     * Erzeugt eine Info zu einem Spiel
     *
     * @param gamefield Spiel aus dem die Info erzeugt werden soll
     * @author Ludwig
     */
    public static JSONObject generateGameInfo(Gamefield gamefield) {
        JSONObject j = new JSONObject();
        j.put(GAMEID, "todo"); // todo
        j.put(GAMESTATE, "todo");
        j.put(PLAYERS, getNameListFromPlayers(gamefield.getPlayerList()));
        j.put(WATCHERS, "todo");
        j.put(PERSON_POSITIONS, generatePersonPositions(gamefield.getCounterList()));

        ArrayList<Weapon> testWeapons = new ArrayList<Weapon>(); // todo
        //testWeapons.add(new Weapon(Weapon.Type.CANDLESTICK, "muh"));
        j.put(WEAPON_POSITIONS, generateWeaponPositions(testWeapons));
        //j.put(WEAPON_POSITIONS, generateWeaponPositions(gamefield.getWeaponList()));
        return j;
    }

    /**
     * Generiert eine Namensliste aus einer Spielerliste
     *
     * @param playerList Spielerliste aus der Namensliste werden soll
     * @author Ludwig
     */
    public static ArrayList<String> getNameListFromPlayers(ArrayList<Player> playerList) {
        ArrayList<String> players = new ArrayList<String>();
        for (Player p : playerList) {
            players.add(p.getName());
        }
        return players;
    }

    /**
     * Generiert Spielerinfo
     *
     * @param player Spieler zu dem die Info erzeugt werden soll
     * @author Ludwig
     */
    public static JSONObject generatePlayerInfo(Player player) {
        log.error("DO NOT USE: generatePlayerInfo");
        JSONObject j = new JSONObject();
        j.put(NICK, player.getName()); // todo - shit was liefert diese methode??
        j.put(COLOR, convertColorToJson(player.getCounter().getColor()));
        j.put(PLAYERSTATE, ""); //todo
        return j;
    }

    /**
     * Generiert eine Aussage für eine Verdächtigung, Anklage oder Spielende
     *
     * @param suspicion Aussage aus dem Model
     * @author Ludwig
     */
    public static JSONObject generateStatement(Suspicion suspicion) {
        JSONObject j = new JSONObject();
        j.put(PERSON, convertPersonCardToJson(suspicion.getSuspCounter()));
        j.put(WEAPON, convertWeaponCardToJson(suspicion.getSuspWeapon()));
        if (suspicion.getSuspRoom() != null)
            j.put(ROOM, convertRoomCardToJson(suspicion.getSuspRoom()));
        return j;
    }

    /**
     * Generiert eine Personen-Positions json Array aus einer Spielfigurenliste
     *
     * @param counters Spielfigurenliste
     * @author Ludwig
     */
    public static JSONArray generatePersonPositions(ArrayList<Counter> counters) {
        JSONArray jsonArray = new JSONArray();
        for (Counter counter : counters) {
            jsonArray.put(generatePersonPosition(counter.getColor(), counter.getPositionX(), counter.getPositionY()));
        }
        return jsonArray;
    }

    /**
     * Generiert Personenposition
     *
     * @param color Farbe der Person
     * @param x     x Koordinate der Person auf dem Spielfeld
     * @param y     y Koordinate der Person auf dem Spielfeld
     * @author Ludwig
     */
    public static JSONObject generatePersonPosition(Counter.Color color, int x, int y) {
        JSONObject j = new JSONObject();
        j.put(PERSON, convertColorToJson(color));
        j.put(FIELD, generateField(x, y));
        return j;
    }

    /**
     * Generiert eine Waffen-Positions json Array aus einer Waffenliste
     *
     * @param weapons Waffenliste
     * @author Ludwig
     */
    public static JSONArray generateWeaponPositions(ArrayList<Weapon> weapons) {
        JSONArray jsonArray = new JSONArray();
        for (Weapon weapon : weapons) {
            jsonArray.put(generateWeaponPosition(weapon.getCardType(), weapon.getPosX(), weapon.getPosY()));
        }
        return jsonArray;
    }

    /**
     * Generiert Waffenposition
     *
     * @param type Typ der Waffe
     * @param x    x Koordinate der Waffe auf dem Spielfeld
     * @param y    y Koordinate der Waffe auf dem Spielfeld
     * @author Ludwig
     */
    public static JSONObject generateWeaponPosition(Weapon.Type type, int x, int y) { // todo wenn in model fertig
        JSONObject j = new JSONObject();
        j.put(PERSON, convertWeaponToJson(type));
        j.put(FIELD, generateField(x, y));
        return j;
    }

    /**
     * Generiert ein Feld
     *
     * @param x x Koordinate
     * @param y y Koordinate
     * @author Ludwig
     */
    public static JSONObject generateField(int x, int y) {
        JSONObject j = new JSONObject();
        j.put(X, x);
        j.put(Y, y);
        return j;
    }

    /**
     * Konvertiert Spielfigurenfarbe Model->JsonProtokol
     *
     * @param color Spielfigurenfarbe
     * @author Ludwig
     */
    public static Farbe convertColorToJson(Counter.Color color) {
        switch (color) {
            case RED:
                return Farbe.RED;
            case YELLOW:
                return Farbe.YELLOW;
            case WHITE:
                return Farbe.WHITE;
            case GREEN:
                return Farbe.GREEN;
            case BLUE:
                return Farbe.BLUE;
            case PURPLE:
                return Farbe.PURPLE;
            default:
                log.error("no according json for Counter.Color found");
                return Farbe.PURPLE;
        }
    }

    /**
     * @param state Playerstate
     * @author Paul
     */
    public static String convertStateToJson(Player.Playerstate state) {
        switch (state) {
            case DO_NOTHING:
                return Spielerzustand.DO_NOTHING.toString();
            case ROLL_DICE:
                return Spielerzustand.ROLL_DICE.toString();
            case USE_SECRET_PASSAGE:
                return Spielerzustand.USE_SECRET_PASSAGE.toString();
            case MOVE:
                return Spielerzustand.MOVE.toString();
            case SUSPECT:
                return Spielerzustand.SUSPECT.toString();
            case ACCUSE:
                return Spielerzustand.ACCUSE.toString();
            case DISPROVE:
                return Spielerzustand.DISPROVE.toString();
            case END_TURN:
                return Spielerzustand.END_TURN.toString();
            default:
                log.error("no according json for Player.PlayerState found");
                return null;
        }
    }

    /**
     * Konvertiert Waffentyp Model->JsonProtokol
     *
     * @param type Waffentyp
     * @author Ludwig
     */
    public static Waffe convertWeaponToJson(Weapon.Type type) {
        switch (type) {
            case DAGGER:
                return Waffe.DAGGER;
            case CANDLESTICK:
                return Waffe.CANDLESTICK;
            case REVOLVER:
                return Waffe.REVOLVER;
            case ROPE:
                return Waffe.ROPE;
            case PIPE:
                return Waffe.PIPE;
            case SPANNER:
                return Waffe.SPANNER;
            default:
                log.error("no according json for Waffe.Type found");
                return Waffe.SPANNER;
        }
    }

    /**
     * Konvertiert ein Karten-Array zu einem JsonArray
     *
     * @param cards Karten-Array
     * @author Ludwig
     */
    public static JSONArray convertCardsToJson(Card[] cards) {
        JSONArray jsonArray = new JSONArray();
        for (Card card : cards) {
            jsonArray.put(convertCardToJson(card));
        }
        return jsonArray;
    }

    /**
     * Konvertiert ein Karten-Objekt zu einer JsonProtokol Konstante
     *
     * @param card Karten-Objekt
     * @author Ludwig
     */
    public static String convertCardToJson(Card card) {
        if (card instanceof PersonCard) {
            return convertPersonCardToJson(((PersonCard) card).getCardValue()).toString();
        } else if (card instanceof RoomCard) {
            return convertRoomCardToJson(((RoomCard) card).getCardValue()).toString();
        } else if (card instanceof WeaponCard) {
            return convertWeaponCardToJson(((WeaponCard) card).getCardValue()).toString();
        }
        log.error("no according json for Card found");
        return "not found";
    }

    /**
     * Konvertiert Personen-Karten-Typ Model->JsonProtokol
     *
     * @param card Personen-Karten-Typ
     * @author Ludwig
     */
    public static Farbe convertPersonCardToJson(PersonCard.Type card) {
        switch (card) {
            case RED:
                return Farbe.RED;
            case YELLOW:
                return Farbe.YELLOW;
            case WHITE:
                return Farbe.WHITE;
            case GREEN:
                return Farbe.GREEN;
            case BLUE:
                return Farbe.BLUE;
            case PURPLE:
                return Farbe.PURPLE;
            default:
                log.error("no according json for PersonCard found");
                return Farbe.PURPLE;
        }
    }

    /**
     * Konvertiert Raum-Karten-Typ Model->JsonProtokol
     *
     * @param card Raum-Karten-Typ
     * @author Ludwig
     */
    public static Raum convertRoomCardToJson(RoomCard.Type card) {
        switch (card) {
            case HALL:
                return Raum.HALL;
            case LOUNGE:
                return Raum.LOUNGE;
            case DININGROOM:
                return Raum.DININGROOM;
            case KITCHEN:
                return Raum.KITCHEN;
            case BALLROOM:
                return Raum.BALLROOM;
            case CONSERVATORY:
                return Raum.CONSERVATORY;
            case BILLIARD:
                return Raum.BILLIARD;
            case LIBRARY:
                return Raum.LIBRARY;
            case STUDY:
                return Raum.STUDY;
            case POOL:
                log.info("RoomCard pool found - maybe error?");
                return Raum.POOL;
            default:
                log.error("no according json for RoomCard found");
                return Raum.POOL;
        }
    }

    /**
     * Konvertiert Waffen-Karten-Typ Model->JsonProtokol
     *
     * @param card Waffen-Karten-Typ
     * @author Ludwig
     */
    public static Waffe convertWeaponCardToJson(WeaponCard.Type card) {
        switch (card) {
            case DAGGER:
                return Waffe.DAGGER;
            case CANDLESTICK:
                return Waffe.CANDLESTICK;
            case REVOLVER:
                return Waffe.REVOLVER;
            case ROPE:
                return Waffe.ROPE;
            case PIPE:
                return Waffe.PIPE;
            case SPANNER:
                return Waffe.SPANNER;
            default:
                log.error("no according json for WeaponCard found");
                return Waffe.SPANNER;
        }
    }

    /**
     * GameState -> String
     * @author Paul
     * @param gameState
     * @return String mit gameState
     */

    public static String convertGameStateToString(Gamefield gameState) {
        GameInfo.Gamestate state = gameState.getGameInfo().getGamestate();
        switch (state) {
            case NOT_STARTED:
                return JsonProtocollConstants.Spielzustand.NOT_STARTED.toString();
            case ENDED:
                return JsonProtocollConstants.Spielzustand.ENDED.toString();
            case STARTED:
                return JsonProtocollConstants.Spielzustand.STARTED.toString();
            default:
                log.error("no according String for Gamefield found");
                return null;
        }
    }
}
