package network.client;
import model.Card;
import model.Counter;
import model.Suspicion;
import network.jsonprotocol.JsonProtocollConstants;
/**
 * Interface für alle Netzwerkfunktionen die der Client senden können muss
 * @author Ludwig
 */
public interface ClientSentInterface extends JsonProtocollConstants {
    
    /**
     * Suche im lokalen Netzwerk mittels UDP nach vorhanden Server
     */
    void getUdpBroadcasts();
    
    /**
     * Login an Server
     * @param ip    IP des Servers
     * @param port  Port des Servers
     * @param nick  Name des Spielers
     * @param group Gruppenname (nicht explizit im Spiel verwendet)
     */
    void login(String ip, int port, String nick, String group);
    
    /**
     * Erstellt ein Spiel auf dem Server
     * @param color Farbe der Spielfigur die der Spieler haben will
     */
    void createGame(Counter.Color color);
    
    /**
     * Tritt einem erstellten Spiel bei
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param color  Farbe der Spielfigur die der Spieler haben will
     */
    void joinGame(int gameID, Counter.Color color);
    
    /**
     * Startet das Spiel, funktioniert nur wenn mehr als 3 Spieler vorhanden sind
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    void startGame(int gameID);
    
    // Spielzüge =======================================================================================================
    
    /**
     * Rollt den Würfel
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    void rollDice(int gameID);
    
    /**
     * Gibt an wohin der Spieler seine Spielfigur ziehen will
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param x      Spielfeldkoordinaten: 0 <= X <= 23
     * @param y      Spielfeldkoordinaten: 0 <= Y <= 24
     */
    void move(int gameID, int x, int y);
    
    /**
     * Nutze den Geheimgang
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    void secretPassage(int gameID);
    
    /**
     * Äußere eine Verdächtigung gegen eine Person
     * @param gameID    aktuelle gameID des Spiels, muss größer 0 sein
     * @param statement Muss eine vollständige Aussage über die Verdächtigung sein
     */
    void suspect(int gameID, Suspicion statement);
    
    /**
     * Widerlege eine Verdächtigung
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     * @param card   Karte die die Verdächtigung widerlegt
     */
    void disprove(int gameID, Card card);
    
    /**
     * Anklage gegen eine Person erheben
     * @param gameID    aktuelle gameID des Spiels, muss größer 0 sein
     * @param statement uss eine vollständige Aussage über die Anklage sein
     */
    void accuse(int gameID, Suspicion statement);
    
    /**
     * Beendet Spielzug
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    void endTurn(int gameID);
    // Spielzüge ende ==================================================================================================
    
    /**
     * Verlasse ein Spiel
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    void leaveGame(int gameID);
    
    /**
     * Disconnected vom Server
     */
    String disconnect();
    
    /**
     * Schaue einem Spiel zu
     * @param gameID aktuelle gameID des Spiels, muss größer 0 sein
     */
    void watchGame(int gameID);
    
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
    void chat(String message, long timestamp, int gameID, String nick);
}
