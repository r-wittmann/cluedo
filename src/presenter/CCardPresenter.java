package presenter;

import model.Card;
import model.Gamefield;
import model.Player.Playerstate;
import view.GlobalView;

public class CCardPresenter implements RenderPresenterInterface{

	private Gamefield model;
	private GlobalView view;
	
	 public CCardPresenter(Gamefield model,GlobalView view) {
		 this.model = model;
		 this.view = view;
	 }
	
	@Override
	public void render() {
		this.view.cCardView.renderPlayer();
		this.view.cCardView.setCard(this.model.suspicion.getDisproveCard());
	}
	public void highlightPlayer(){
		for(int i = 0; i < model.getPlayerList().size(); i++){
			if(model.getPlayerList().get(i).getPlayerstates().contains(Playerstate.ROLL_DICE))
				view.cCardView.highlightPlayer(i);
		}
	}
	public void setCard(Card card){
		view.cCardView.setCard(card);
	}

}
