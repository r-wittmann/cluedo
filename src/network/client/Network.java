package network.client;

import javafx.application.Platform;
import json.org.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import presenter.GUIReactToServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Handle Network connection
 * Sent messages to server and generates listener thread for background messages and notify javafx application
 * @author Paul, Ludwig
 */
public class Network{
    private static final Logger log = LogManager.getLogger(Network.class);
    
    private Socket socket;
    private boolean isConnected = false;
    private BufferedReader in;
    private final ClientReceive clientReceive;
    
    /**
     * @param host IP-Adresse des Servers
     * @param port offener Port des Servers
     * @author Paul, Ludwig
     */
    public Network(ClientReceive clientReceive, String host, int port) {
        this.clientReceive = clientReceive;
    
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            log.info("Connected...");
            
            // create listener on network to receive any time messages from network
            Thread socketListenerThread = new SocketListenerThread();
            socketListenerThread.setName("NetworkThread");
            socketListenerThread.start();
            
            isConnected = true;
        } catch (IOException e) {
            log.fatal("Konnte Socket nicht erstellen");
            log.fatal(e);
        }
    }
    
    /**
     * Is network connected?
     * @return true if connected
     * @author Ludwig
     */
    public boolean isConnected() {
        return isConnected;
    }
    
    /**
     * Observes the network connection for new json data
     * @author Ludwig, Paul
     */
    private class SocketListenerThread extends Thread {
        @Override
        public void run() {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    log.debug("received msg from network");
                    // handle received message string
                    onMessage(line);
                }
            } catch (IOException e) {
                log.fatal(e);
            } finally {
                try {
                    Thread.currentThread().join();
                    log.info("network thread killed");
                } catch (InterruptedException e) {
                    log.fatal(e);
                }
            }
        }
    }
    
    /**
     * Handle received message string to javafx thread
     * @param s received message
     * @author Ludwig
     */
    private void onMessage(final String s) {
        // convert string to jsonobject
        JSONObject jsonObject = new JSONObject(s);
        log.info("FROM SERVER: " + " " + jsonObject.toString(2));
    
        if (clientReceive.getReactToServer() instanceof GUIReactToServer) {
            // put json object to parse in queue to javafx thread
            // because parsing of json object cause presenter calls
            // thats only possible in javafx thread
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    log.debug("got json from network thread");
                
                    // parse jsonobject
                    clientReceive.interpretJson(jsonObject);
                }
            });
        } else {
            // need if KI or testing
            clientReceive.interpretJson(jsonObject);
        }
    }
    
    /**
     * Send jsonobject
     * @param jsonObject to send
     * @author Paul, Ludwig
     */
    public void send(JSONObject jsonObject) {
        if (!isConnected) {
            log.error("could not send - no network connection");
            return;
        }
        try {
            this.sendJSON(jsonObject);
        } catch (IOException e) {
            log.error("Senden nicht erfolgreich.");
            log.fatal(e);
        }
    }

    /**
     * Schließt die Socket-Connection zum Server
     * @author Paul, Ludwig
     */
    public void close() {
        if (!isConnected) {
            log.error("already closed network connection");
            return;
        }
        try {
            this.socket.close();
            isConnected = false;
            log.info("Connection closed.");
        } catch (IOException e) {
            log.fatal(e);
        }
    }

    /**
     * @param jsonObject wird an Server geschickt.
     * @throws IOException bei Fehler vom Server
     */
    private void sendJSON(JSONObject jsonObject) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        out.write(jsonObject.toString() + "\n");
        out.flush();
        log.info("TO SERVER: " + " " + jsonObject.toString(2));
    }


}
