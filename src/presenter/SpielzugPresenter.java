package presenter;

import javafx.scene.input.MouseEvent;
import network.client.ClientSent;
import view.GlobalView;

/**
 * beinhaltet Funktionen zum Spielzug
 * @author Maurice
 *
 */
public class SpielzugPresenter {
	
	private GlobalView view;
	private int gameID;
	private ClientSent clientSent;
	
	public SpielzugPresenter(int gameID, GlobalView view, ClientSent clientSent){
		this.gameID = gameID;
		this.view = view;
		this.clientSent = clientSent;
	}
	
	/**
	 * Funktion,um Spielzug zu beenden
	 * @param e
	 */
	 public void handleEndTurn(MouseEvent e){
	    	clientSent.endTurn(gameID);
	    	view.cCardView.setCard(null);
	    }
}
