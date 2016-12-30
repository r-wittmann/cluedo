package presenter;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import model.Gamefield;
import network.client.ClientSent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.GlobalView;

public class WinnerPresenter implements RenderPresenterInterface {
	
	private static final Logger log = LogManager.getLogger(WinnerPresenter.class);
	
	private GlobalView view;
	private Gamefield model;
	private ClientSent clientSent;
	
	public WinnerPresenter(Gamefield model, GlobalView view, ClientSent clientSent) {
		this.model = model;
		this.view = view;
		this.clientSent = clientSent;
	}
	
	/**
	 * Close window after win
	 * @author Ludwig
	 * @param e mouse event
	 */
	public void exitGame(MouseEvent e) {
		// todo vollständig?
		log.info("You left the game!");
		// sent to server
		clientSent.leaveGame(model.getGameInfo().getGameID());
		// kill view
		//Platform.exit(); // wrong closes complete javafx application
		((Node)(e.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void render() {
		this.view.gameView.winnerView.setVisible(true);
//		this.view.gameView.winnerView.setCards();
//		this.view.gameView.winnerView.setMurderInfo();
		
	}
	
	public void setCards(String murder, String weapon, String scene){
		this.view.gameView.winnerView.ergebnis.setCards(murder, weapon, scene);
		this.view.gameView.winnerView.ergebnis.setMurderInfo(murder,weapon,scene);
		this.view.gameView.winnerView.setVisible(true);
	}
}
