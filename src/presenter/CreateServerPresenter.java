package presenter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import loginView.CreateServerView;
import model.Global;
import network.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by paul on 17.06.15.
 */
public class CreateServerPresenter {
    private static final Logger log = LogManager.getLogger(CreateServerPresenter.class);

    CreateServerView createServerView;
    Server server;
    Thread serverThread;

    public CreateServerPresenter(CreateServerView SV) {
        this.createServerView = SV;
        SV.kill.setOnMouseClicked(this::handleKill);
        //Initialisiere global (zu Errinnerung: Die Informationen f�r die Clients.
        Global global = new Global();

        //Set Server-Port
        int serverPort = 30305;

        //create Server-Runnable
        server = new Server(global, serverPort);

        //Start Server
        serverThread = new Thread(server);
        serverThread.start();

        //Show Server-Port in View
        SV.porttxt.setText(Integer.toString(serverPort));

        //Show Server-IP in View
        try {
            SV.iptxt.setText(Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //Update Client-Table on Interval
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                //Get all Client-Info on Server and show it in View
                ae -> SV.updateClient(server.getClientList())));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    
    /**
     * handles if server is killed
     * @param e
     * @author Paul,Maurice
     */
    public void handleKill(MouseEvent e){
    	log.info("Server heruntergefahren");
    	server.kill();
    	Window window = createServerView.getScene().getWindow();
    	if(window instanceof Stage){
    		((Stage) window).close();
    	}
    }


}

