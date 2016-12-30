package presenter;

import javafx.scene.input.MouseEvent;
import view.GlobalView;

public class DuBistDranPresenter {
	
	private GlobalView view;
	
	public DuBistDranPresenter(GlobalView view){
		this.view = view;
	}
	
	public void handleOk(MouseEvent e){
		this.view.gameView.duBistDranView.setVisible(false);
	}
}
