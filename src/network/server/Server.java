package network.server;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import json.org.JSONObject;
import model.Global;
import network.jsonprotocol.ServerJson;
import network.udp.UdpRunnable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

/**
 * @author Paul
 */
public class Server implements Runnable {
    private static final Logger log = LogManager.getLogger(Server.class);

    private final static String[] supportedExpansions = new String[]{"test1", "test2", "test3"};

    private volatile boolean run;

    private Hashtable<ServerClientThreadRunnable, Thread> allClients;

    private UdpRunnable udpRunnable;

    //SERVERSEITIGE INFORMATIONEN aller Sockets(Threads) mit jeweiligem Nick als ID
    private Hashtable<String, ServerOnlyInformation> storage;

    private ServerSocket serverSocket = null;

    private ServerJson SJ = new ServerJson();

    private int port;

    private Global global;


    /**
     * Erstellt einen Server
     *
     * @param global
     * @param port
     */
    public Server(Global global, int port) {
        this.global = global;
        this.port = port;
        //for local testing
        broadcastUdp();
    }

    @Override
    public void run() {
        run = true;
        storage = new Hashtable<String, ServerOnlyInformation>();
        allClients = new Hashtable<ServerClientThreadRunnable, Thread>();
        try {
            connect(this.port);
        } finally {
            try {
                Thread.currentThread().join();
                log.info("Server Thread Ende");
            } catch (InterruptedException e) {
                log.info(e);
            }
        }
    }


    private void broadcastUdp() {
        udpRunnable = new UdpRunnable();
        Thread udpThread = new Thread(udpRunnable);
        udpThread.start();
        udpRunnable.returnString.addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                                Object newVal) {
            }
        });
        JSONObject broadcastMessage = SJ.bcUDPServer("muffigemotten", 30305);
        udpRunnable.broadcast(broadcastMessage);

    }

    /**
     * Erstellt und startet einen neuen Thread.
     * F�r jeden neuen Client wird ein neuer Thread erstellt.
     * Alle Threads werden dem Storage �bergeben, damit der Server immer auf alle Threads zugreifen kann.
     *
     * @throws IOException
     */
    public void connect(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
            log.info("Server established on port " + port + " ... listening for connections...");
        } catch (IOException e) {
            log.error(e);
        }
        while (run) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                log.info("No more Listening for connections...");
            }
            ServerClientThreadRunnable clientRunnable = new ServerClientThreadRunnable(global, clientSocket, storage);
            Thread clientThread = new Thread(clientRunnable);
            clientThread.start();
            allClients.put(clientRunnable, clientThread);

        }

    }

    public ArrayList<ArrayList<String>> getClientList() {
        ArrayList<ArrayList<String>> clientList = new ArrayList<ArrayList<String>>();
        Set<String> keys = storage.keySet();
        for (String key : keys) {
            ArrayList<String> oneClient = new ArrayList<String>();
            oneClient.add(key);
            ServerOnlyInformation temp = storage.get(key);
            oneClient.add(temp.getGroupName());
            clientList.add(oneClient);
        }
        //log.info("Aktuelle ClientListe: " + clientList);
        return clientList;
    }

    public void kill() {
        if (udpRunnable != null) {
            udpRunnable.killUDP();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error(e);
        }
        run = false;
        if (allClients.size() != 0) {
            allClients.forEach(
                    (serverClientThreadRunnable, thread) -> serverClientThreadRunnable.killNetwork()
            );
        }
    }
}


