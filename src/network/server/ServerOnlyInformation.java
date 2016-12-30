package network.server;

import java.net.Socket;

/**
 * Created by Paul on 11.06.2015.
 */
public class ServerOnlyInformation {

    private String groupName;
    private Socket clientSocket;

    public ServerOnlyInformation(String group, Socket socket) {
        this.clientSocket = socket;
        this.groupName = group;
    }


    //SETTER & GETTER
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


}
