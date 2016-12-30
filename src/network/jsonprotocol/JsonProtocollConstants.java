package network.jsonprotocol;
/**
 * Hier sind alle Attribute die im JSON-Protokoll verwendet als Konstanten definiert
 * Basierend auf der Protokoll Version 1.2.1 von Sebastian Bschorer vom 17.06.2015
 * @author Ludwig
 */
public interface JsonProtocollConstants {
    
    // Seite 4
    // Kapitel 5.1
    String UDP_SERVER = "udp server";
    String GROUP = "group";
    String GROUPNAME = "muffigemotten";
    String TCP_PORT = "tcp port";
    String IP = "ip"; // self added, not in spec
    //
    String UDP_CLIENT = "udp client";
    // Kapitel 5.2
    String TYPE = "type";
    String LOGIN = "login";
    String NICK = "nick";
    String VERSION = "version";
    String VERSION_NUMBER = "1.2.1";
    String EXPANSIONS = "expansions";
    
    enum Expansions {
        CHAT("Chat"),
        FAST("Fast");
        
        private final String str;
        
        Expansions(String str) {
            this.str = str;
        }
        
        @Override
        public String toString() {
            return str;
        }
    }
    
    // Seite 5
    String LOGIN_SUCCESSFUL = "login successful";
    String NICK_ARRAY = "nick array";
    String GAME_ARRAY = "game array";
    //
    String USER_ADDED = "user added";
    //
    String DISCONNECT = "disconnect";
    //
    String DISCONNECTED = "disconnected";
    String MESSAGE = "message";
    //
    String USER_LEFT = "user left";
    
    // Seite 7
    // Kapitel 6.2
    String X = "x";
    String Y = "y";
    
    // Kapitel 6.3
    enum Farbe {
        RED("red"),
        YELLOW("yellow"),
        WHITE("white"),
        GREEN("green"),
        BLUE("blue"),
        PURPLE("purple");
        
        private final String str;
        
        Farbe(String str) {
            this.str = str;
        }
        
        @Override
        public String toString() {
            return str;
        }
    }
    
    // Kapitel 6.4
    // Person äuqivalent zu Farbe
    
    // Seite 8
    enum Waffe {
        DAGGER("dagger"),
        CANDLESTICK("candlestick"),
        REVOLVER("revolver"),
        ROPE("rope"),
        PIPE("pipe"),
        SPANNER("spanner");
        
        private final String str;
        
        Waffe(String str) {
            this.str = str;
        }
        
        @Override
        public String toString() {
            return str;
        }
    }
    
    enum Raum {
        HALL("hall"),
        LOUNGE("lounge"),
        DININGROOM("diningroom"),
        KITCHEN("kitchen"),
        BALLROOM("ballroom"),
        CONSERVATORY("conservatory"),
        BILLIARD("billiard"),
        LIBRARY("library"),
        STUDY("study"),
        POOL("pool");
        
        private final String str;
        
        Raum(String str) {
            this.str = str;
        }
        
        @Override
        public String toString() {
            return str;
        }
    }
    
    // Kapitel 6.5
    String PERSON = "person";
    String WEAPON = "weapon";
    String ROOM = "room";
    
    // Kapitel 6.6
    String WATCHERS = "watchers";
    String PLAYERS = "players";
    String PERSON_POSITIONS = "person positions";
    String WEAPON_POSITIONS = "weapon positions";
    
    // Seite 9
    enum Spielzustand {
        NOT_STARTED("not started"),
        STARTED("started"),
        ENDED("ended");
        
        private final String str;
        
        Spielzustand(String str) {
            this.str = str;
        }
        
        @Override
        public String toString() {
            return str;
        }
    }
    
    // Kapitel 6.7
    String PLAYERSTATE = "playerstate";
    
    enum Spielerzustand {
        DO_NOTHING("do nothing"),
        ROLL_DICE("roll dice"),
        USE_SECRET_PASSAGE("use secret passage"),
        MOVE("move"),
        SUSPECT("suspect"),
        ACCUSE("accuse"),
        DISPROVE("disprove"),
        END_TURN("end turn");
        
        private final String str;
        
        Spielerzustand(String str) {
            this.str = str;
        }
        
        @Override
        public String toString() {
            return str;
        }
    }
    
    // Kapitel 6.8
    String FIELD = "field";
    // Kapitel 6.9
    
    // Seite 10
    // Kapitel 7.1
    String OK = "ok";
    String ERROR = "error";
    String JSONID = "jsonID";
    // Kapitel 7.2
    // expansion chat
    String CHAT = "chat";
    String TIMESTAMP = "timestamp";
    //
    String SENDER = "sender";
    
    // Seite 11
    // Kapitel 8.1
    String CREATE_GAME = "create game";
    String COLOR = "color";
    //
    String GAME_CREATED = "game created";
    String GAMEID = "gameID";
    String PLAYER = "player";
    // Kapitel 8.2
    String JOIN_GAME = "join game";
    //
    String PLAYER_ADDED = "player added";
    // Kapitel 8.3
    String WATCH_GAME = "watch game";
    
    // Seite 12
    String WATCHER_ADDED = "watcher added";
    String GAMEINFO = "gameinfo";
    String GAME = "game";
    // Kapitel 8.4
    String LEAVE_GAME = "leave game";
    //
    String LEFT_GAME = "left game";
    //
    String GAME_DELETED = "game deleted";
    // Kapitel 8.5
    String START_GAME = "start game";
    //
    String PLAYER_CARDS = "player_cards";
    String CARDS = "cards";
    
    // Seite 13
    String GAME_STARTED = "game started";
    String GAMESTATE = "gamestate";
    String ORDER = "order";
    //
    String STATEUPDATE = "stateupdate";
    // Kapitel 8.6
    String GAME_ENDED = "game ended";
    String STATEMENT = "statement";
    
    // Seite 14
    // Kapitel 9.1
    // Kapitel 9.2
    String DICE_RESULT = "dice result";
    String RESULT = "result";
    // Kapitel 9.3
    String MOVED = "moved";
    String PERSON_POSITION = "person position";
    //
    String POOLCARDS = "poolcards";
    
    // Seite 15
    // Kapitel 9.4
    String SUSPICION = "suspicion";
    //
    String DISPROVED = "disproved";
    String CARD = "card";
    //
    String NO_DISPROVE = "no disprove";
    // Kapitel 9.5
    String WRONG_ACCUSATION = "wrong accusation";
    
    // Seite 16
    // Kapitel 10.1
    String ROLL_DICE = "roll dice";
    // Kapitel 10.2
    String MOVE = "move";
    // Kapitel 10.3
    String SECRET_PASSAGE = "secret passage";
    // Kapitel 10.4
    String SUSPECT = "suspect";
    
    // Seite 16
    // Kapitel 10.5
    String DISPROVE = "disprove";
    // Kapitel 10.6
    String END_TURN = "end turn";
    // Kapitel 10.7
    String ACCUSE = "accuse";
}
