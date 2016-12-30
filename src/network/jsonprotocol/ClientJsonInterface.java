package network.jsonprotocol;
import json.org.JSONObject;
import model.Card;
import model.Counter;
import model.Suspicion;
/**
 * Interface für JSON Objekte die der Server an den Client sendet
 * @author Ludwig
 */
interface ClientJsonInterface extends JsonProtocollConstants {
    /**
     * Broadcast, frägt über UDP ob ein Server im Netzwerk vorhanden ist
     * @param group Gruppenname (z.B. des Entwicklerteams)
     */
    JSONObject bcUDPClient(String group);
    
    /**
     * Login an Server
     * @param nick       Name
     * @param group      Gruppenname (nicht im Spiel verwendet)
     * @param expansions Array mit Expansionbezeichnungen
     */
    JSONObject login(String nick, String group, String[] expansions);
    
    /**
     * Erstellt ein Spiel auf dem Server
     * @param color Farbe der Spielfigur die der Spieler haben will
     */
    JSONObject createGame(Counter.Color color);
    
    /**
     * Tritt einem erstellten Spiel bei
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param color  Farbe der Spielfigur die der Spieler haben will
     */
    JSONObject joinGame(int gameID, Counter.Color color);
    
    /**
     * Startet das Spiel, funktioniert nur wenn mehr als 3 Spieler vorhanden sind
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject startGame(int gameID);
    
    // Spielzüge =======================================================================================================
    
    /**
     * Rollt den Würfel
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject rollDice(int gameID);
    
    /**
     * Gibt an wohin der Spieler seine Spielfigur ziehen will
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param x      Spielfeldkoordinaten: 0 <= X <= 23
     * @param y      Spielfeldkoordinaten: 0 <= Y <= 24
     */
    JSONObject move(int gameID, int x, int y);
    
    /**
     * Nutze den Geheimgang
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject secretPassage(int gameID);
    
    /**
     * Äußere eine Verdächtigung gegen eine Person
     * @param gameID    aktuelle gameID des Spiels, muss größer 0 sein
     * @param statement Muss eine vollständige Aussage über die Verdächtigung sein
     */
    JSONObject suspect(int gameID, Suspicion statement);
    
    /**
     * Widerlege eine Verdächtigung
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param card   Karte die die Verdächtigung widerlegt
     */
    JSONObject disprove(int gameID, Card card);
    
    /**
     * Anklage gegen eine Person erheben
     * @param gameID    aktuelle gameID des Spiels, muss größer 0 sein
     * @param statement uss eine vollständige Aussage über die Anklage sein
     */
    JSONObject accuse(int gameID, Suspicion statement);
    
    /**
     * Beendet Spielzug
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject endTurn(int gameID);
    // Spielzüge ende ==================================================================================================
    
    /**
     * Verlasse ein Spiel
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject leaveGame(int gameID);
    
    /**
     * Disconnected vom Server
     */
    JSONObject disconnect();
    
    /**
     * Schaue einem Spiel zu
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    JSONObject watchGame(int gameID);
    
    /**
     * Sendet eine Nachricht im Chat
     * wenn nur message, an alle Spieler auf dem Server
     * wenn gameID, an alle Spieler in einem Spiel
     * wenn nick, nur an diesen Spieler
     * @param message   Nachricht an Spieler auf dem Server, Spiel oder direkt
     * @param timestamp Unixtime im ms als die Nachricht verschickt wurde
     * @param gameID    (optional) ID des Spiels an dessen Spieler die Nachricht geschickt werden soll
     * @param nick      (optional) Nickname des Empfängers der Direktnachricht
     */
    JSONObject chat(String message, long timestamp, int gameID, String nick);
}