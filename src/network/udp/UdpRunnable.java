package network.udp;

import javafx.beans.property.SimpleStringProperty;
import json.org.JSONObject;
import network.jsonprotocol.JsonProtocollConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Enumeration;

/**
 * UDP Thread to find server
 * @author Paul, Ludwig
 */
public class UdpRunnable implements Runnable, JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(UdpRunnable.class);
    
    private boolean isConnected = false;
    public SimpleStringProperty returnString = new SimpleStringProperty();
    private volatile boolean run;
    DatagramSocket datagramSocket;
    
    

    public UdpRunnable() {
        run = true;
        try {
            //datagramSocket = new DatagramSocket(30303, InetAddress.getByName("0.0.0.0"));
            datagramSocket = new DatagramSocket(null);
            datagramSocket.setBroadcast(true);
            
            // activate reuse of port -> more than 1 program can use this port
            datagramSocket.setReuseAddress(true);
            
            // set address
            InetSocketAddress inetSocketAddress = new InetSocketAddress("0.0.0.0", 30303);
            datagramSocket.bind(inetSocketAddress);
            
            isConnected = true;
        } catch (SocketException  e) {
            log.fatal(e);
        } finally {
            if (isConnected)
                log.info("udp started");
        }
    }
    
    @Override
    public void run() {
        while (isConnected&&run) {
            try {
                byte[] data = new byte[16000];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                datagramSocket.receive(packet);
                String inString = new String(packet.getData()).trim();
                log.info("got new UDP-Broadcast from " + packet.getAddress().getHostAddress() + " : " + inString);
                
                // extend protocol with ip address
                JSONObject jsonObject = new JSONObject(inString);
                jsonObject.put(IP, packet.getAddress().getHostAddress());
                inString = jsonObject.toString();
                
                returnString.set(inString);
            } catch (IOException e) {
                log.fatal(e);
            }
        }
    }

    public void broadcast(JSONObject jsonObject) {
        if (!isConnected){
            log.error("not connected - can not broadcast");
            return;
        }
        try {
            Enumeration interfaces = null;
            try {
                interfaces = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException e) {
                log.error(e);
            }
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface)interfaces.nextElement();
                try {
                    if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                        continue; // Don't want to broadcast to the loopback interface
                    }
                } catch (SocketException e) {
                    log.error(e);
                }
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }


                    DatagramPacket packet = new DatagramPacket(jsonObject.toString().getBytes("UTF-8"), jsonObject.toString().length(),broadcast,30303);

                    try {
                        datagramSocket.send(packet);
                    } catch (IOException e) {
                        log.error(e);
                    }
                }
            }
        } catch (UnsupportedEncodingException e){
            log.error(e);
        }
    }

    public void killUDP(){
        run=false;
    }
}
