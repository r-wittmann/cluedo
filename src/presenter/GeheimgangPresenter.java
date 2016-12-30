package presenter;

import javafx.scene.input.MouseEvent;
import network.client.ClientSent;
import view.GlobalView;
import model.Gamefield;

public class GeheimgangPresenter implements RenderPresenterInterface {
	
	private Gamefield model;
	private GlobalView view;
	private ClientSent clientSent;
	
	public GeheimgangPresenter(Gamefield model, GlobalView view, ClientSent clientSent){
		this.model = model;
		this.view = view;
		this.clientSent = clientSent;
	}
	
	public void handleYes(MouseEvent event){
		//TODO Raum, zu dem der geheimgang führt, aufleuchten lassen und begehbar machen (counter position verschieben)
		// send to server
		clientSent.secretPassage(model.getGameInfo().getGameID());
		this.view.gameView.dice.setDisable(false);
		this.view.gameView.geheimgangView.setVisible(false);
	}
	
	public void handleNo(MouseEvent event){
		this.view.gameView.dice.setDisable(false);
		this.view.gameView.geheimgangView.setVisible(false);
	}

	@Override
	public void render() {
		this.view.gameView.fieldImageView.unsetShadow();
		this.view.gameView.fieldView.setField();
		this.view.gameView.counterView.initStyle();
	}

}
