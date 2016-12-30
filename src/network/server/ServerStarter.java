package network.server;

import model.Global;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main für Server Test
 *
 * @author Paul
 */
public class ServerStarter {
    private static final Logger log = LogManager.getLogger(ServerStarter.class);
    public static void main(String[] args) {

        Global global = new Global();
        Server s = new Server(global, 30305);
        new Thread(s).start();
    }
}
