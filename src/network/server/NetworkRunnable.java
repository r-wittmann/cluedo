package network.server;

import javafx.beans.property.SimpleStringProperty;
import json.org.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by Paul on 26.06.2015.
 */

/**
 * @author paul
 */
public class NetworkRunnable implements Runnable {
    private static final Logger log = LogManager.getLogger(NetworkRunnable.class);
    public SimpleStringProperty newJSON = new SimpleStringProperty();
    private volatile boolean run = true;
    private Socket socket;
    private JSONObject jsonObject;


    public NetworkRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (run) {
            try {
                receiveJSON();
            } catch (IOException e) {
                run = false;
                log.error(e);
            }
        }
    }

    /**
     * Schlie�t die Socket-Connection zum Server
     *
     * @throws IOException
     */

    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            log.info("Connection closed.");
        }

    }

    /**
     * @return Json vom Server
     * @throws IOException bei Fehler vom Server
     */
    public void receiveJSON() throws IOException {
    	if (socket != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String line = new String();
            line = in.readLine();
            if (line != null) {
                log.info("FROM CLIENT: " + " " + line);
                //Schleife, die die Belegung der JSON Property verzögert, bis der Listener drauf gesetzt wurde
                //NOTLÖSUNG. BITTE NICHT LÖSCHEN!!!
                int winningTime = 1;
                for(int i = 1; i < 10000000; i++){
                	winningTime += i;
                	int j = winningTime / i;
                }
                newJSON.setValue(line);
            }
        }
    }

    public void sendJSONtoThisClient(JSONObject jsonObject) {
        try {
            sendJSON(jsonObject, this.socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendJSONtoSocket(JSONObject jsonObject, Socket socket) {
        try {
            sendJSON(jsonObject, socket);
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * @param jsonObject wird an Server geschickt.
     * @throws IOException bei Fehler vom Server
     */
    public void sendJSON(JSONObject jsonObject, Socket socket) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        out.write(jsonObject.toString() + "\n");
        out.flush();
        log.info("TO CLIENT: " + " " + jsonObject.toString());
    }


}



