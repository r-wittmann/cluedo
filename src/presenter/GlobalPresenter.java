package presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Gamefield;
import network.client.ClientSent;
import view.GlobalView;

public class GlobalPresenter {
    private static final Logger log = LogManager.getLogger(GlobalPresenter.class);
    
    private Gamefield model;
    public GlobalView globalView;
    private ClientSent clientSent;
    
    private FieldPresenter fieldPres;
    private DicePresenter dicePres;
    private NotePresenter notePres;
    private SuspicionPresenter susPres;
    private CardsPresenter cardPres;
    private AccusationPresenter accuPres;
    private AnklagePresenter anklPres;
    private WinnerPresenter winnerPres;
    private LoosePresenter loosePres;
    private PoolPresenter poolPres;
    private GeheimgangPresenter geheimPres;
    private DisproveSuspicionPresenter disprPres;
    private PlayerStatePresenter playerStatePresenter;
    private DuBistDranPresenter duBistDranPresenter;
    private GameStartPresenter gameStartPres;
    private SpielzugPresenter spielZugPresenter;
    private CCardPresenter cCardPresenter;
    private NoDisprovePresenter noDisprovePresenter;
    private VerdaechtigungErhobenPresenter verdaechtigungErhobenPresenter;
    private SuspicionDisprovedPresenter suspicionDisprovedPres;
    private ErgebnisPresenter ergebnisPres;

	public void setErgebnisPres(ErgebnisPresenter ergebnisPres) {
		this.ergebnisPres = ergebnisPres;
	}

	private WeaponPresenter weaponPres;
    
	
    public GlobalPresenter(Gamefield model, GlobalView globalView, ClientSent clientSent, int gameID) {
        this.model = model;
        this.globalView = globalView;
        this.setClientSent(clientSent);
        
        //Presenter erstellen
        this.fieldPres = new FieldPresenter(model, globalView, clientSent);
        this.dicePres = new DicePresenter(model, globalView, clientSent, fieldPres);
        this.notePres = new NotePresenter(globalView);
        this.susPres = new SuspicionPresenter(model, globalView, clientSent);
        this.cardPres = new CardsPresenter(model, globalView);
        this.accuPres = new AccusationPresenter(model, globalView, clientSent);
        this.anklPres = new AnklagePresenter(model, globalView);
        this.winnerPres = new WinnerPresenter(model, globalView, clientSent);
        this.loosePres = new LoosePresenter(model, globalView, clientSent);
        this.poolPres = new PoolPresenter(globalView);
        this.geheimPres = new GeheimgangPresenter(model, globalView, clientSent);
        this.disprPres = new DisproveSuspicionPresenter(model, globalView, clientSent);
        this.playerStatePresenter = new PlayerStatePresenter(model, globalView);
        this.duBistDranPresenter = new DuBistDranPresenter(globalView);
        this.gameStartPres = new GameStartPresenter(gameID, globalView, clientSent);
        this.spielZugPresenter = new SpielzugPresenter(gameID, globalView, clientSent);
		this.cCardPresenter = new CCardPresenter(model, globalView);
		this.noDisprovePresenter = new NoDisprovePresenter(globalView);
		this.verdaechtigungErhobenPresenter = new VerdaechtigungErhobenPresenter(globalView);
		this.suspicionDisprovedPres = new SuspicionDisprovedPresenter(this.globalView);
		this.ergebnisPres = new ErgebnisPresenter();
		this.weaponPres = new WeaponPresenter(this.globalView);
        
        //MouseEvents
        globalView.gameView.dice.setOnMouseClicked(dicePres::handle);
        globalView.gameView.note.setOnMouseClicked(notePres::handle);
        globalView.gameView.verdaechtigungsView.submit.setOnMouseClicked(susPres::makeSuspicion);
        globalView.gameView.anklageView.submit.setOnMouseClicked(accuPres::saveAccused);
        globalView.gameView.cards.setOnMouseClicked(cardPres::handle);
        globalView.gameView.anklage.setOnMouseClicked(accuPres::handle);
        globalView.gameView.anklageView.close.setOnMouseClicked(accuPres::handleClose);
        globalView.gameView.winnerView.exit.setOnMouseClicked(winnerPres::exitGame);
        globalView.gameView.looseView.exit.setOnMouseClicked(loosePres::exitGame);
        globalView.gameView.gameStartView.startGame.setOnMouseClicked(gameStartPres::handleGameStart);
        globalView.gameView.poolView.close.setOnMouseClicked(poolPres::handle);
        globalView.gameView.geheimgangView.ja.setOnMouseClicked(geheimPres::handleYes);
        globalView.gameView.geheimgangView.nein.setOnMouseClicked(geheimPres::handleNo);
        globalView.gameView.selbstWiderlegenView.ja.setOnMouseClicked(disprPres::handleYes);
        globalView.gameView.selbstWiderlegenView.nein.setOnMouseClicked(disprPres::handleNo);
        globalView.gameView.duBistDranView.ok.setOnMouseClicked(duBistDranPresenter::handleOk);
        globalView.gameView.stop.setOnMouseClicked(spielZugPresenter::handleEndTurn);
        globalView.gameView.noDisproveView.ok.setOnMouseClicked(noDisprovePresenter::handleClose);
 //       globalView.gameView.verdaechtigungErhobenView.close.setOnMouseClicked(verdaechtigungsPresenter::handleClose);
        globalView.gameView.widerlegtView.close.setOnMouseClicked(suspicionDisprovedPres::handleCloseDisproved);
        globalView.gameView.dialogView.ok.setOnMouseClicked(susPres::handleClose);
    }

    /**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public FieldPresenter getFieldPres() {
		return fieldPres;
	}

	/**
	 * get WeaponPresenter
	 * @return
	 * @author Roxanna
	 */
	public WeaponPresenter getWeaponPres(){
		return weaponPres;
	}
	
	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public DicePresenter getDicePres() {
		return dicePres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public NotePresenter getNotePres() {
		return notePres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public SuspicionPresenter getSusPres() {
		return susPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public CardsPresenter getCardPres() {
		return cardPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public AccusationPresenter getAccuPres() {
		return accuPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public AnklagePresenter getAnklPres() {
		return anklPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public WinnerPresenter getWinnerPres() {
		return winnerPres;
	}
	
	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public LoosePresenter getLoosePres() {
		return loosePres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public PoolPresenter getPoolPres() {
		return poolPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public GeheimgangPresenter getGeheimPres() {
		return geheimPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public DisproveSuspicionPresenter getDisprPres() {
		return disprPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public PlayerStatePresenter getPlayerStatePresenter() {
		return playerStatePresenter;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public DuBistDranPresenter getDuBistDranPresenter() {
		return duBistDranPresenter;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public GameStartPresenter getGameStartPres() {
		return gameStartPres;
	}

	/**
     * get Presenter
     * @return
     * @author Wittmann Rainer
     */
	public SpielzugPresenter getSpielZugPresenter() {
		return spielZugPresenter;
	}
	
	/**
	 * get Presenter
	 * @return
	 * @author Ludwig
	 */
	public CCardPresenter getcCardPresenter() {
		return this.cCardPresenter;
	}

	/**
	 * get Presenter
	 * @return
	 * @author Maurice
	 */
	public NoDisprovePresenter getNoDisprovePresenter() {
		return noDisprovePresenter;
	}
	
	/**
	 * get Presenter
	 * @author Maurice
	 * @return
	 */
	public VerdaechtigungErhobenPresenter getVerdaechtigungErhobenPresenter() {
		return verdaechtigungErhobenPresenter;
	}
	
	/**
	 * get Presenter
	 * @return
	 * @author Ludwig
	 */
	public SuspicionDisprovedPresenter getSuspicionDisprovedPres(){
		return suspicionDisprovedPres;
	}
	
	 public ErgebnisPresenter getErgebnisPres() {
			return ergebnisPres;
		}

	public ClientSent getClientSent() {
		return clientSent;
	}

	public void setClientSent(ClientSent clientSent) {
		this.clientSent = clientSent;
	}
	
}