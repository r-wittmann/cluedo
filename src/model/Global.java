package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Contains all games of a server
 * used by client and server
 *
 * @author Ludwig
 */
public class Global {
    private static final Logger log = LogManager.getLogger(Global.class);

    private HashMap<Integer, Gamefield> gamefieldHashMap = new HashMap<>();
    private int latestGameID;
    private String myNick;


    //@author Paul
    private Player Temp;
    private String TempSuspect;
    private ArrayList<String> clientList = new ArrayList<>();
    private int serverPort = 0;
    private String serverIp = "";

    public Global() {
        latestGameID = 0;
        myNick = "";
    }

    /**
     * Creates a mew empty gamefield
     * only for server
     *
     * @return gameID of new gamefield
     * @author Ludwig
     */
    public int createGamefield() {
        int temp = latestGameID + 1;
        while (existsGameID(temp))
            temp++;
        Gamefield gamefield = new Gamefield(temp, this.getMyNick());
        return addNewGamefield(gamefield);
    }

    /**
     * Add a already created gamefield which is not added already
     * only for server
     *
     * @param gamefield gamefield which should be added
     * @return gameID of gamefield
     * @author Ludwig
     */
    public int addNewGamefield(Gamefield gamefield) {
        latestGameID++;
        gamefieldHashMap.put(latestGameID, gamefield);
        return latestGameID;
    }

    /**
     * Add a already created gamefield which is not added already
     * only for client
     *
     * @param gameID    gameID of gamefield
     * @param gamefield gamefield which should be added
     * @author Ludwig
     */
    public void addGamefield(int gameID, Gamefield gamefield) {
        if (!existsGameID(gameID)) {
            gamefieldHashMap.put(gameID, gamefield);
            latestGameID = gameID;
        }
    }

    /**
     * Get reference to requested gamefield
     *
     * @param gameID wanted gamefield
     * @return requested gamefield
     * @author Ludwig
     */
    public Gamefield getGamefield(int gameID) {
        if (!existsGameID(gameID)) {
            log.fatal("gameID not found");
            return null;
        }
        return gamefieldHashMap.get(gameID);
    }

    public HashMap<Integer, Gamefield> getGamefields() {
        return gamefieldHashMap;
    }

    /**
     * Removes a gamefield
     *
     * @param gameID gameID of gamefield
     * @author Ludwig
     */
    public void removeGamefield(int gameID) {
        if (existsGameID(gameID)) {
            gamefieldHashMap.remove(gameID);
        }
    }

    /**
     * Checks if the requested gameID exists
     *
     * @param gameID request gameID
     * @return Exists or Not
     * @author Ludwig
     */
    public boolean existsGameID(int gameID) {
        return gamefieldHashMap.containsKey(gameID);
    }

    /**
     * adds a client
     *
     * @param str nickname
     * @author Ludwig
     */
    public void addClient(String str) {
        clientList.add(str);
    }

    /**
     * Removes a client
     *
     * @param str nickname
     * @author Ludwig
     */
    public void removeClient(String str) {
        clientList.remove(str);
    }

    /**
     * Checks if a client already exists
     *
     * @param str nickname
     * @return Exists or Not
     * @author Ludwig
     */
    public boolean existsClient(String str) {
        return (clientList.indexOf(str) != -1);
    }

    /**
     * Returns clients on server
     *
     * @return clients on server
     * @author Ludwig
     */
    public ArrayList<String> getClients() {
        return clientList;
    }

    /**
     * Retruns nickname of self
     *
     * @return nickname of self
     * @author Ludiwg
     */
    public String getMyNick() {
        return this.myNick;
    }

    /**
     * Set nickname of myself
     *
     * @param str nickname of self
     * @author Ludwig
     */
    public void setMyNick(String str) {
        if (str == null) return;
        this.myNick = str;
    }

    /**
     * For debug use
     *
     * @return latest insert gameID
     * @author Ludwig
     */
    public int getLatestGameID() {
        return latestGameID;
    }

    //TODO
    //Allgemeine Infos
    //für alle anderen Klassen: Bei Start: Richtige Referenz auf das richtige Spiel

    public String[] getGamesIDStringArray() {
        Set<Integer> keys = gamefieldHashMap.keySet();
        String[] answer = new String[gamefieldHashMap.size()];
        int counter = 0;
        for (Integer key : keys) {
            answer[counter] = key.toString();
            counter++;
        }
        return answer;
    }


    /**
     * @return
     * @authos Paul
     */
    public Player getTemp() {
        return Temp;
    }

    /**
     * @return
     * @authos Paul
     */
    public void setTemp(Player temp) {
        Temp = temp;
    }

    /**
     * @return
     * @authos Paul
     */
    public String getTempSuspect() {
        return TempSuspect;
    }

    /**
     * @return
     * @authos Paul
     */
    public void setTempSuspect(String tempSuspect) {
        TempSuspect = tempSuspect;
    }

    /**
     * @return
     * @authos Paul
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * @return
     * @authos Paul
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    /**
     * @return
     * @authos Paul
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * @return
     * @authos Paul
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
