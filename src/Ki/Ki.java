package Ki;

import model.*;
import network.client.ClientSent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class Ki implements Runnable {
    private static final Logger log = LogManager.getLogger(Ki.class);
    
    static final Level KILogLevel = Level.forName("KI", 399);
    static final Level KIBrainLogLevel = Level.forName("KIBrain", 399);
    
    /**
     * loggs KI thoughts or moves
     * @param a text or object
     * @author Ludwig
     */
    private void logKI(Object a) {
        log.log(KILogLevel, a);
    }
    
    ClientSent clientSent;
    Global global;
    
    
    private MoveAgent moveAgent;
    private DisproveAgent disproveAgent;
    private Suspicion_AccusationAgent sus_accAgent;
    private SaveAgent saveAgent;
    private KIReactToServer kiReactToServer = new KIReactToServer(this);
    private HashMap<Integer, KINote> kiNotes = new HashMap<>();
    
    /**
     * @author Ludwig
     */
    class KINote {
        ArrayList<PersonCard> possibleMurders = new ArrayList<PersonCard>();
        ArrayList<RoomCard> possibleRooms = new ArrayList<RoomCard>();
        ArrayList<WeaponCard> possibleWeapons = new ArrayList<WeaponCard>();
        ArrayList<Card> ownCards = new ArrayList<>();
    
        KINote() {
            possibleMurders.add(new PersonCard(PersonCard.Type.RED));
            possibleMurders.add(new PersonCard(PersonCard.Type.YELLOW));
            possibleMurders.add(new PersonCard(PersonCard.Type.WHITE));
            possibleMurders.add(new PersonCard(PersonCard.Type.GREEN));
            possibleMurders.add(new PersonCard(PersonCard.Type.PURPLE));
            possibleMurders.add(new PersonCard(PersonCard.Type.BLUE));
            
            possibleWeapons.add(new WeaponCard(WeaponCard.Type.DAGGER));
            possibleWeapons.add(new WeaponCard(WeaponCard.Type.CANDLESTICK));
            possibleWeapons.add(new WeaponCard(WeaponCard.Type.REVOLVER));
            possibleWeapons.add(new WeaponCard(WeaponCard.Type.ROPE));
            possibleWeapons.add(new WeaponCard(WeaponCard.Type.PIPE));
            possibleWeapons.add(new WeaponCard(WeaponCard.Type.SPANNER));
            
            possibleRooms.add(new RoomCard(RoomCard.Type.HALL));
            possibleRooms.add(new RoomCard(RoomCard.Type.LOUNGE));
            possibleRooms.add(new RoomCard(RoomCard.Type.DININGROOM));
            possibleRooms.add(new RoomCard(RoomCard.Type.KITCHEN));
            possibleRooms.add(new RoomCard(RoomCard.Type.BALLROOM));
            possibleRooms.add(new RoomCard(RoomCard.Type.CONSERVATORY));
            possibleRooms.add(new RoomCard(RoomCard.Type.BILLIARD));
            possibleRooms.add(new RoomCard(RoomCard.Type.LIBRARY));
            possibleRooms.add(new RoomCard(RoomCard.Type.STUDY));
        }
    }
    
    public Ki(String ip, int port, String nick, String group) {
        this.global = new Global();
        this.moveAgent = new MoveAgent(this);
        this.disproveAgent = new DisproveAgent(this);
        this.sus_accAgent = new Suspicion_AccusationAgent(this);
        this.saveAgent = new SaveAgent(this);
        this.clientSent = new ClientSent(global, kiReactToServer);
        this.kiReactToServer.setClientSent(clientSent);
        
        logKI("Ok, let's connect to server..");
        clientSent.login(ip, port, nick, group);
    }
    
    /**
     * Init available cards which removed stepbystep to find murder
     * @param gameID gameID of the game
     */
    public void initPossibleCards(int gameID) {
        KINote kiNote = new KINote();
        kiNotes.put(gameID, kiNote);
    }

    public DisproveAgent getDisproveAgent(int gameID) {
		return disproveAgent;
    }
    
	public MoveAgent getMoveAgent() {
		return moveAgent;
	}

	public Suspicion_AccusationAgent getSus_accAgent() {
		return sus_accAgent;
	}

	public void setSus_accAgent(Suspicion_AccusationAgent sus_accAgent) {
		this.sus_accAgent = sus_accAgent;
	}
	
	public SaveAgent getSaveAgent() {
		return saveAgent;
	}

	public void setSaveAgent(SaveAgent saveAgent) {
		this.saveAgent = saveAgent;
	}

	public void setMoveAgent(MoveAgent moveAgent) {
		this.moveAgent = moveAgent;
	}

	@Override
    public void run() {
        //clientSent.createGame(Counter.Color.RED);
        //clientSent.disconnect();
    }

	public ArrayList<PersonCard> getPossibleMurders(int gameID) {
		return  kiNotes.get(gameID).possibleMurders;
	}

//	public void setPossibleMurders(ArrayList<PersonCard> possibleMurders) {
//		this.possibleMurders = possibleMurders;
//	}

	public ArrayList<RoomCard> getPossibleRooms(int gameID) {
		return  kiNotes.get(gameID).possibleRooms;
	}

//	public void setPossibleRooms(ArrayList<RoomCard> possibleRooms) {
//		this.possibleRooms = possibleRooms;
//	}

	public ArrayList<WeaponCard> getPossibleWeapons(int gameID) {
		return  kiNotes.get(gameID).possibleWeapons;
	}

//	public void setPossibleWeapons(ArrayList<WeaponCard> possibleWeapons) {
//		this.possibleWeapons = possibleWeapons;
//	}
    
    public void setOwnCards(int gameID, ArrayList<Card> ownCards){
        this.kiNotes.get(gameID).ownCards = ownCards;
    }
    
    public ArrayList<Card> getOwnCards(int gameID){
        return this.kiNotes.get(gameID).ownCards;
    }
    
    Global getGlobal(){
        return this.global;
    }
}
