package presenter;

import javafx.scene.input.MouseEvent;
import view.GlobalView;
import model.Gamefield;

public class CardsPresenter {
	
	public Gamefield model;
	public GlobalView view;
	
	public CardsPresenter(Gamefield model, GlobalView view){
		this.model = model;
		this.view = view;
	}
	
	//Opens and Closes CardsView
	public void handle(MouseEvent e){
		if(!this.view.gameView.cardsView.isVisible()){
	
			this.view.gameView.cardsView.setVisible(true);
		}
		else{this.view.gameView.cardsView.setVisible(false);}
	}

}
