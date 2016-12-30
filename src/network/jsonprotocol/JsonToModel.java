package network.jsonprotocol;
import json.org.JSONArray;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
/**
 * Static class to parse json to model
 * @author Ludwig
 */
public class JsonToModel implements JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(JsonToModel.class);
    
    /**
     * Parse json playerstate to model playerstate (enumset)
     * @param jsonArray json playerstate
     * @return model playerstate
     * @author Ludwig
     */
    public static EnumSet<Player.Playerstate> convertSpielerzustandToModel(JSONArray jsonArray) {
        if (jsonArray == null) {
            log.info("no playerstate found");
            return null;
        }
        EnumSet<Player.Playerstate> playerstates = EnumSet.noneOf(Player.Playerstate.class);
        for (int i = 0; i < jsonArray.length(); i++) {
            String str = jsonArray.getString(i);
            if (str.equals(Spielerzustand.DO_NOTHING.toString())) playerstates.add(Player.Playerstate.DO_NOTHING);
            if (str.equals(Spielerzustand.ROLL_DICE.toString())) playerstates.add(Player.Playerstate.ROLL_DICE);
            if (str.equals(Spielerzustand.USE_SECRET_PASSAGE.toString()))
                playerstates.add(Player.Playerstate.USE_SECRET_PASSAGE);
            if (str.equals(Spielerzustand.MOVE.toString())) playerstates.add(Player.Playerstate.MOVE);
            if (str.equals(Spielerzustand.SUSPECT.toString())) playerstates.add(Player.Playerstate.SUSPECT);
            if (str.equals(Spielerzustand.ACCUSE.toString())) playerstates.add(Player.Playerstate.ACCUSE);
            if (str.equals(Spielerzustand.DISPROVE.toString())) playerstates.add(Player.Playerstate.DISPROVE);
            if (str.equals(Spielerzustand.END_TURN.toString())) playerstates.add(Player.Playerstate.END_TURN);
        }
        return playerstates;
    }
    
    /**
     * Parse json gamestate to model gamestate
     * @param string json gamestate
     * @return model gamestate
     * @author Ludwig
     */
    public static GameInfo.Gamestate parseGamestate(String string) {
        if (string.equals(Spielzustand.NOT_STARTED.toString())) return GameInfo.Gamestate.NOT_STARTED;
        if (string.equals(Spielzustand.STARTED.toString())) return GameInfo.Gamestate.STARTED;
        if (string.equals(Spielzustand.ENDED.toString())) return GameInfo.Gamestate.ENDED;
        log.error("no gamestate found");
        return GameInfo.Gamestate.ENDED;
    }
    
    /**
     * Parse json color of counter to model color of counter
     * @param farbe json color counter
     * @return model color counter
     * @author Ludwig
     */
    public static Counter.Color convertFarbeToModel(String farbe) {
        if (farbe.equals(Farbe.RED.toString())) return Counter.Color.RED;
        if (farbe.equals(Farbe.YELLOW.toString())) return Counter.Color.YELLOW;
        if (farbe.equals(Farbe.WHITE.toString())) return Counter.Color.WHITE;
        if (farbe.equals(Farbe.GREEN.toString())) return Counter.Color.GREEN;
        if (farbe.equals(Farbe.BLUE.toString())) return Counter.Color.BLUE;
        if (farbe.equals(Farbe.PURPLE.toString())) return Counter.Color.PURPLE;
        log.error("no color found");
        return Counter.Color.PURPLE;
    }
    
    /**
     * Parse type of weapon from json to model
     * @param waffe json weapon type
     * @return model weapon type
     * @author Ludwig
     */
    public static Weapon.Type convertWaffeToModel(String waffe) {
        if (waffe.equals(Waffe.DAGGER.toString())) return Weapon.Type.DAGGER;
        if (waffe.equals(Waffe.CANDLESTICK.toString())) return Weapon.Type.CANDLESTICK;
        if (waffe.equals(Waffe.REVOLVER.toString())) return Weapon.Type.REVOLVER;
        if (waffe.equals(Waffe.ROPE.toString())) return Weapon.Type.ROPE;
        if (waffe.equals(Waffe.PIPE.toString())) return Weapon.Type.PIPE;
        if (waffe.equals(Waffe.SPANNER.toString())) return Weapon.Type.SPANNER;
        log.error("no weapon found");
        return Weapon.Type.SPANNER;
    }
    
    /**
     * Parse person card type from json to model
     * @param farbe json person card type
     * @return model person card type
     * @author Ludwig
     */
    public static PersonCard.Type convertFarbeToCardModel(String farbe) {
        if (farbe.equals(Farbe.RED.toString())) return PersonCard.Type.RED;
        if (farbe.equals(Farbe.YELLOW.toString())) return PersonCard.Type.YELLOW;
        if (farbe.equals(Farbe.WHITE.toString())) return PersonCard.Type.WHITE;
        if (farbe.equals(Farbe.GREEN.toString())) return PersonCard.Type.GREEN;
        if (farbe.equals(Farbe.BLUE.toString())) return PersonCard.Type.BLUE;
        if (farbe.equals(Farbe.PURPLE.toString())) return PersonCard.Type.PURPLE;
        //log.info("no person card type found");
        return null;
    }
    
    /**
     * Parse weapon card type from json to model
     * @param waffe json weapon card type
     * @return model weapon card type
     * @author Ludwig
     */
    public static WeaponCard.Type convertWaffeToCardModel(String waffe) {
        if (waffe.equals(Waffe.DAGGER.toString())) return WeaponCard.Type.DAGGER;
        if (waffe.equals(Waffe.CANDLESTICK.toString())) return WeaponCard.Type.CANDLESTICK;
        if (waffe.equals(Waffe.REVOLVER.toString())) return WeaponCard.Type.REVOLVER;
        if (waffe.equals(Waffe.ROPE.toString())) return WeaponCard.Type.ROPE;
        if (waffe.equals(Waffe.PIPE.toString())) return WeaponCard.Type.PIPE;
        if (waffe.equals(Waffe.SPANNER.toString())) return WeaponCard.Type.SPANNER;
        //log.info("no weapon card tpye found");
        return null;
    }
    
    /**
     * Parse room card type from json to model
     * @param raum json room card type
     * @return model room card type
     * @author Ludwig
     */
    public static RoomCard.Type convertRaumToCardModel(String raum) {
     /*for (Raum r: Raum.values()){
     if (raum.equals(r.toString())) return RoomCard.Type.valueOf(r.toString());
     }*/
        if (raum.equals(Raum.HALL.toString())) return RoomCard.Type.HALL;
        if (raum.equals(Raum.LOUNGE.toString())) return RoomCard.Type.LOUNGE;
        if (raum.equals(Raum.DININGROOM.toString())) return RoomCard.Type.DININGROOM;
        if (raum.equals(Raum.KITCHEN.toString())) return RoomCard.Type.KITCHEN;
        if (raum.equals(Raum.BALLROOM.toString())) return RoomCard.Type.BALLROOM;
        if (raum.equals(Raum.CONSERVATORY.toString())) return RoomCard.Type.CONSERVATORY;
        if (raum.equals(Raum.BILLIARD.toString())) return RoomCard.Type.BILLIARD;
        if (raum.equals(Raum.LIBRARY.toString())) return RoomCard.Type.LIBRARY;
        if (raum.equals(Raum.STUDY.toString())) return RoomCard.Type.STUDY;
        if (raum.equals(Raum.POOL.toString())) return RoomCard.Type.POOL;
        //log.info("no room card type found");
        return null;
    }
    
    /**
     * Parse card from json to model
     * @param karte json card
     * @return model card
     * @author Ludwig
     */
    public static Card convertKarteToModel(String karte) {
        if (convertFarbeToCardModel(karte) != null) return ((Card) new PersonCard(convertFarbeToCardModel(karte)));
        if (convertWaffeToCardModel(karte) != null) return ((Card) new WeaponCard(convertWaffeToCardModel(karte)));
        if (convertRaumToCardModel(karte) != null) return ((Card) new RoomCard(convertRaumToCardModel(karte)));
        log.fatal("no card found");
        return null;
    }
}
