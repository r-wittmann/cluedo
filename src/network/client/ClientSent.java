package network.client;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import json.org.JSONObject;
import model.*;
import network.jsonprotocol.ClientJson;
import network.jsonprotocol.JsonProtocollConstants;
import network.udp.UdpRunnable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import presenter.DialogPresenter;
import presenter.GUIReactToServer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sends commands to server
 * @author Ludwig
 */
public class ClientSent implements ClientSentInterface, JsonProtocollConstants, Runnable {
    private static final Logger log = LogManager.getLogger(ClientSent.class);
    
    private final static String[] supportedExpansions = new String[]{Expansions.CHAT.toString()};
    
    private final Global global;  // reference to global model
    private Network network;
    private final ClientJson json;
    private final ClientReceive clientReceive;
    private final ReactToServer reactToServer;
    
    /**
     * Constructor
     * @param global        reference to global model
     * @param reactToServer reaction for messages from server
     * @author Ludwig
     */
    public ClientSent(Global global, ReactToServer reactToServer) {
        this.global = global;
        this.reactToServer = reactToServer;
        json = new ClientJson();
        clientReceive = new ClientReceive(global, reactToServer);
        getUdpBroadcasts();
    }
    
    @Override
    public void run() {
    }

    /**
     * @author Paul, Ludwig
     */
    @Override
    public void getUdpBroadcasts() {
        try {
            UdpRunnable udpRunnable = new UdpRunnable();
            Thread udpThread = new Thread(udpRunnable);
            udpThread.setName("UPDThread");
            udpThread.start();
            udpRunnable.returnString.addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue o, Object oldVal, Object newVal) {
                    JSONObject jsonObject = new JSONObject((String) newVal);
            
                    if (reactToServer instanceof GUIReactToServer) {
                        // put json object to parse in queue to javafx thread
                        // because parsing of json object cause presenter calls
                        // thats only possible in javafx thread
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                log.debug("got json from udp thread");
                        
                                // parse jsonobject
                                clientReceive.interpretJson(jsonObject);
                            }
                        });
                    } else {
                        // need if KI or testing
                        clientReceive.interpretJson(jsonObject);
                    }
                }
            });
            JSONObject broadcastMessage = json.bcUDPClient(GROUPNAME);
            udpRunnable.broadcast(broadcastMessage);
        } catch (Exception e) {
            log.fatal(e);
        }
    }

    /**
     * @author Ludwig
     */
    @Override
    public void login(String ip, int port, String nick, String group) {
        try {
            network = new Network(clientReceive, ip, port);
            log.info("try ip: " + ip + " port: " + port);
            global.setMyNick(nick);
            network.send(json.login(nick, group, supportedExpansions));
        } catch (NullPointerException ex) {
            DialogPresenter dial = new DialogPresenter();
            dial.handleWrongIP();
        }

    }
    
    /**
     * @author Ludwig
     */
    @Override
    public void createGame(Counter.Color color) {
        network.send(json.createGame(color));
    }

    /**
     * @author Ludwig, Maurice
     */
    @Override
    public void joinGame(int gameID, Counter.Color color) {
        network.send(json.joinGame(gameID, color));
    }

    @Override
    public void startGame(int gameID) {
        network.send(json.startGame(gameID));
    }

    @Override
    public void rollDice(int gameID) {
        network.send(json.rollDice(gameID));
    }

    @Override
    public void move(int gameID, int x, int y) {
        network.send(json.move(gameID, x, y));
    }

    @Override
    public void secretPassage(int gameID) {
        network.send(json.secretPassage(gameID));
    }

    @Override
    public void suspect(int gameID, Suspicion statement) {
        network.send(json.suspect(gameID, statement));
    }

    @Override
    public void disprove(int gameID, Card card) {
        network.send(json.disprove(gameID, card));
    }

    @Override
    public void accuse(int gameID, Suspicion statement) {
        network.send(json.accuse(gameID, statement));
    }

    @Override
    public void endTurn(int gameID) {
        network.send(json.endTurn(gameID));
    }

    @Override
    public void leaveGame(int gameID) {
        network.send(json.leaveGame(gameID));
    }
    
    /**
     * @author Ludwig
     */
    @Override
    public String disconnect() {
        // disconnect from every game you watch or play
        HashMap<Integer, Gamefield> gamefieldHashMap = global.getGamefields();
        for (Integer gameID : gamefieldHashMap.keySet()) {
            Gamefield gamefield = gamefieldHashMap.get(gameID);
            
            // check if player
            ArrayList<Player> players = gamefield.getPlayerList();
            for (Player player : players) {
                if (player.getName().equals(global.getMyNick())) {
                    log.info("leave played game " + gameID);
                    leaveGame(gameID);
                }
            }
            
            // check if watcher
            ArrayList<String> watchers = gamefield.getGameInfo().getWatchers();
            for (String watcher : watchers) {
                if (watcher.equals(global.getMyNick())) {
                    log.info("leave watched game " + gameID);
                    leaveGame(gameID);
                }
            }
        }
        
        // sende disconnect Nachricht an Server
        if (network != null)
            network.send(json.disconnect());
        
        // give 1 second for network thread to receive rest of communication
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        // todo ?
        /*
        // erhalte Antwort
        JSONObject answer = network.receive();
        // check
        if (answer.getString(TYPE) != DISCONNECTED)
            return null;
        // disconnect from server
        try {
            thisThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return answer.getString(MESSAGE);*/
        return "";
    }

    @Override
    public void watchGame(int gameID) {
        network.send(json.watchGame(gameID));
    }

    @Override
    public void chat(String message, long timestamp, int gameID, String nick) {
        network.send(json.chat(message, timestamp, gameID, nick));
    }


}
