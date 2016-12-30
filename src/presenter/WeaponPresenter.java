package presenter;

import view.GlobalView;

public class WeaponPresenter implements RenderPresenterInterface{

	private GlobalView view;
	
	public WeaponPresenter(GlobalView view){
		this.view = view;
	}

	@Override
	public void render() {
		this.view.gameView.weaponView.initWeaponMove();
	}
	
	
}
