package presenter;

import javafx.scene.input.MouseEvent;
import view.GlobalView;

public class NoDisprovePresenter implements RenderPresenterInterface{

	private GlobalView view;
	
	public NoDisprovePresenter(GlobalView view){
		this.view = view;
	}
	
	public void handleClose(MouseEvent e){
		this.view.gameView.noDisproveView.setVisible(false);
	}

	@Override
	public void render() {
		//View holds no variable content
		this.view.gameView.noDisproveView.setVisible(true);
	}
}
