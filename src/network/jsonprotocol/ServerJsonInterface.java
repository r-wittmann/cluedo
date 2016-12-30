package network.jsonprotocol;
import json.org.JSONArray;
import json.org.JSONObject;
import model.Card;
import model.Counter;
import model.Player;
import model.Suspicion;
/**
 * Interface für JSON Objekte die der Server an den Client sendet
 * @author Ludwig
 */
interface ServerJsonInterface extends JsonProtocollConstants {
    /**
     * Erzeugt eine OK Nachricht, dadurch wird das erfolgreiche Ausführen eines Befehls bestätigt
     */
    JSONObject replyOK();
    
    /**
     * Falls ein Fehler beim Ausführen eines Befehls auftritt wird ein Fehler zurückgeben
     * @param message Nachricht mit Details zu dem Fehler
     */
    JSONObject replyError(String message);
    
    /**
     * Broadcast, gibt über UDP den TCP-Port vom Server im Netzwerk bekannt
     * @param group    Gruppenname (z.B. des Entwicklerteams)
     * @param tcp_port TCP-Port des Servers
     */
    JSONObject bcUDPServer(String group, int tcp_port);
    
    /**
     * Antwort auf login eines Client auf den Server; Beinhaltet Expansion-, Spieler- und Spiele-Info
     * @param expansions Array mit Expansionbezeichnungen die der Server vom Client unterstützt
     * @param nick_array Array aller auf dem Server eingeloggten Clients
     * @param game_array Array aller vorhandenen Spiele + Spielinfo
     */
    JSONObject replyLogin(String[] expansions, String[] nick_array, /*Spielinfo*/ JSONArray game_array);
    
    /**
     * Broadcast über den neu verbunden Client
     * @param nick Name des Client
     */
    JSONObject bcLogin(String nick);
    
    /**
     * Broadcast über das neu erstellte Spiel und welcher Spieler verbunden ist
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param player Spieler der das Spiel erstellt hat
     */
    JSONObject bcGameCreated(int gameID, Player player);
    
    /**
     * Broadcast, das sich ein Spieler zu einem Spiel verbunden hat
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param player Spieler der hinzugefügt wurde
     */
    JSONObject bcPlayerAdded(int gameID, Player player);
    
    /**
     * Sendet jedem Spieler nach Spielstart seine zugewiesenen Karten zu
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param cards  Array über die dem Spieler zugeteilten Karten
     */
    JSONObject sentPlayerCards(int gameID, Card[] cards);
    
    /**
     * Broadcast, dass ein Spiel gestarted wurde und Bekanntgabe der Spielerreihenfolge
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param order  Reihenfolge der Spieler
     */
    JSONObject bcGameStarted(int gameID, String[] order);
    
    /**
     * Statusupdate eines Spielers
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject bcStateupdate(int gameID, Player player); // todo
    
    /**
     * Broadcast über die Würfelergebnisse
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param result Array mit den zwei Würfelergebnissen, Element (Augenzahl) je von 1 bis 6
     */
    JSONObject bcDiceResult(int gameID, int[] result);
    
    /**
     * Broadcast über die diesen Spielzug bewegten Spielfiguren
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param color  Farbe der bewegten Spielfigur
     * @param x      Spielfeldkoordinaten: 0 <= X <= 23
     * @param y      Spielfeldkoordinaten: 0 <= Y <= 24
     */
    JSONObject bcMoved(int gameID, Counter.Color color, int x, int y);
    
    /**
     * Rückantwort falls der Raum Pool betreten wurde, über die darin enthaltenen Karten
     * @param cards Karten im Raum Pool
     */
    JSONObject replyPoolcards(Card[] cards);
    
    /**
     * Broadcast über eine Verdächtigung gegen eine Person
     * @param gameID    aktuelle gameID des Spiels, muss größer 0 sein
     * @param statement vollständige Aussage über eine Verdächtigung
     */
    JSONObject bcSuspicion(int gameID, Suspicion statement);
    
    /**
     * Broadcast, Verdächtigung konnte widerlegt werden
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param nick   Nickname der die Verdächtigung widerlegt
     * @param card   Karte welche die Verdächtigung widerlegt
     */
    JSONObject bcDisproved(int gameID, String nick, Card card);
    
    /**
     * Broadcast, Verdächtigung konnte nicht widerlegt werden
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject bcNoDisprove(int gameID);
    
    /**
     * Broadcast, dass ein Spieler eine falsche Anklage erhoben hat
     * @param gameID    aktuelle gameID des Spiels, muss größer 0 sein
     * @param statement vollständige Aussage über die falsche Anklage
     */
    JSONObject bcWrongAccusation(int gameID, Suspicion statement);
    
    /**
     * Broadcast, dass ein Spiel beendet ist
     * @param gameID    aktuelle gameID des Spiels, muss größer 0 sein
     * @param nick      Nickname des Gewinners
     * @param statement vollständige Aussage über eine erfolgreiche Anklage
     */
    JSONObject bcGameEnded(int gameID, String nick, Suspicion statement);
    
    /**
     * Broadcast, dass ein Spieler das Spiel verlassen hat
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param nick   Nickname des Spielers der das Spiel verlassen hat
     */
    JSONObject bcLeftGame(int gameID, String nick);
    
    /**
     * Broadcast, dass ein Spiel gelöscht wurde
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject bcGameDeleted(int gameID);
    
    /**
     * Antwort/Bestätigung auf disconnect-Nachricht des Clients
     * @param message Letzte Nachricht an den Client, z.B. "Bis zum nächsten Mal"
     */
    JSONObject replyDisconnected(String message);
    
    /**
     * Broadcast ein Client hat den Server verlassen
     * @param nick Nickname des Clients der den Server verlassen hat
     */
    JSONObject bcUserLeft(String nick);
    
    /**
     * Zuschauer hinzugefügt
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param nick   Nickname des Zuschauers
     */
    JSONObject bcWatcherAdded(int gameID, String nick);
    
    /**
     * Sendet eine Nachricht an einen Spieler
     * @param message   Nachricht an Spieler auf dem Server, Spiel oder direkt
     * @param sender    (optional) Nickname des Absenders; kann null sein, dann ist der Versender direkt der Server
     */
    JSONObject sentChat(String message, String sender);
}