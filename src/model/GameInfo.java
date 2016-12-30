package model;

import java.util.ArrayList;
/**
 * This class contains the information which is needed for the gameinfo message
 * @author Jonas, Sarah, Ludwig
 */


public class GameInfo {
    private ArrayList<String> watchers;
    private Gamestate gamestate;
    private int gameID;
    
    public enum Gamestate {
        NOT_STARTED,
        STARTED,
        ENDED
    }
    
    /**
     * class constructor
     * with the initial data
     * @author Ludwig
     */
    public GameInfo(int gameID) {
        this.gameID = gameID;
        watchers = new ArrayList<String>();
        setGamestate(Gamestate.NOT_STARTED);
        
    }
    
    /**
     * Set state of game
     * @param gamestate actual gamestate of game
     * @author Ludwig
     */
    public void setGamestate(Gamestate gamestate) {
        this.gamestate = gamestate;
    }
    
    /**
     * Add watcher to game
     * @param str watcher name
     * @author Ludwig
     */
    public void addWatcher(String str) {
        watchers.add(str);
    }
    
    /**
     * Remove watcher from game
     * @param str watcher name
     * @author Ludwig
     */
    public void removeWatcher(String str) {
        watchers.remove(str);
    }
    
    /**
     * Checks exist of watcher
     * @param str nickname
     * @return true if nickname as watcher exists
     * @author Ludwig
     */
    public boolean existsWatcher(String str) {
        return watchers.indexOf(str) != -1;
    }
    
    /**
     * Get string array of watcher of a game
     * @author Ludwig
     */
    public ArrayList<String> getWatchers() {
        return watchers;
    }
    
    
    /**
     * Get gamestate of the game
     * @author Ludwig
     */
    public Gamestate getGamestate() {
        return gamestate;
    }
    
    /**
     * get GameID of the game
     * @return gameID
     * @author Jonas
     */
    public int getGameID() {
        return gameID;
    }
    
    /**
     * Only for debug
     * @author Ludwig
     */
    public String toString() {
        return "Gamestate: " + gamestate.toString() + ", Watchers: " + getWatchers().toString();
    }
}