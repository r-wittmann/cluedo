package model;

import model.Counter.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

/**
 * implements the whole playing board including
 * <ul>
 * <li>the 6 Counters that represent the suspects
 * <li>two dice with initial values (1, 1)
 * <li>a board with the squares and rooms on it
 * <li>and the 22 cards, with 6 personCards, 6 WeaponCards and 10 RoomCards
 * </ul>
 *
 * @author Roxanna
 */
public class Gamefield {
    private static final Logger log = LogManager.getLogger(Gamefield.class);

    public Dices dices;
    public Board board;
    public Suspicion suspicion;
    public Accusation accusation;
    public String currentPlayer;

    public Murder murder;
    private GameInfo gameInfo;
    private String myNick = "";

    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private ArrayList<Counter> counterList = new ArrayList<Counter>();
    private ArrayList<Card> poolCards = new ArrayList<Card>();
    private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();


    //@author Paul
    private ArrayList<String> watcherList = new ArrayList<>();

    /**
     * implements the whole playing board including
     * <ul>
     * <li>the 6 Counters that represent the suspects
     * <li>two dice with initial values (1, 1)
     * <li>a board with the squares and rooms on it
     * <li>and the 21 cards, with 6 personCards, 6 WeaponCards and 9 RoomCards
     * </ul>
     */
    public Gamefield(int gameID, String myNick) {

        this.gameInfo = new GameInfo(gameID);
        this.myNick = myNick;

        this.dices = new Dices();
        this.board = new Board();
        this.suspicion = new Suspicion();
        this.accusation = new Accusation();

        // Same order like page 6 protocol
        counterList
                .add(new Counter(Counter.Color.RED, 16, 0, "Fräulein Gloria"));
        counterList.add(new Counter(Counter.Color.YELLOW, 23, 7,
                "Oberst von Gatow"));
        counterList.add(new Counter(Counter.Color.WHITE, 14, 24, "Frau Weiß"));
        counterList
                .add(new Counter(Counter.Color.GREEN, 9, 24, "Reverend Grün"));
        counterList.add(new Counter(Counter.Color.BLUE, 0, 18,
                "Baronin von Porz"));
        counterList.add(new Counter(Counter.Color.PURPLE, 0, 5,
                "Professor Bloom"));

        initCards();
        initWeapons();
        initMurder();
    }

    /**
     * Creates 21 Cards - 6 PersonCards, 6 WeaponCards and 9 RoomCards - <br>
     * and adds them to cardList.
     */
    public void initCards() {
        cardList.add(new PersonCard(PersonCard.Type.RED));
        cardList.add(new PersonCard(PersonCard.Type.YELLOW));
        cardList.add(new PersonCard(PersonCard.Type.WHITE));
        cardList.add(new PersonCard(PersonCard.Type.GREEN));
        cardList.add(new PersonCard(PersonCard.Type.PURPLE));
        cardList.add(new PersonCard(PersonCard.Type.BLUE));

        cardList.add(new WeaponCard(WeaponCard.Type.DAGGER));
        cardList.add(new WeaponCard(WeaponCard.Type.CANDLESTICK));
        cardList.add(new WeaponCard(WeaponCard.Type.REVOLVER));
        cardList.add(new WeaponCard(WeaponCard.Type.ROPE));
        cardList.add(new WeaponCard(WeaponCard.Type.PIPE));
        cardList.add(new WeaponCard(WeaponCard.Type.SPANNER));

        cardList.add(new RoomCard(RoomCard.Type.HALL));
        cardList.add(new RoomCard(RoomCard.Type.LOUNGE));
        cardList.add(new RoomCard(RoomCard.Type.DININGROOM));
        cardList.add(new RoomCard(RoomCard.Type.KITCHEN));
        cardList.add(new RoomCard(RoomCard.Type.BALLROOM));
        cardList.add(new RoomCard(RoomCard.Type.CONSERVATORY));
        cardList.add(new RoomCard(RoomCard.Type.BILLIARD));
        cardList.add(new RoomCard(RoomCard.Type.LIBRARY));
        cardList.add(new RoomCard(RoomCard.Type.STUDY));
    }

    /**
     * Creates the weapons <br>
     * and adds them to weaponList
     */
    public void initWeapons() {
        weaponList.add(new Weapon(Weapon.Type.CANDLESTICK));
        weaponList.add(new Weapon(Weapon.Type.DAGGER));
        weaponList.add(new Weapon(Weapon.Type.PIPE));
        weaponList.add(new Weapon(Weapon.Type.REVOLVER));
        weaponList.add(new Weapon(Weapon.Type.ROPE));
        weaponList.add(new Weapon(Weapon.Type.SPANNER));
    }

    /**
     * moves the weapon to x,y <br>
     * if another weapon is in the room with coordinates x, y, <br>
     * then both weapons will swap their position.
     *
     * @param type wich weapon should be moved
     * @param x    the destination x koordinate
     * @param y    the destination y koordinate
     */
    public void setWeapon(Weapon.Type type, int x, int y) {
        for (int i = 0; i < weaponList.size(); i++) {
            if (weaponList.get(i).getCardType() == type) {
                // runs through weaponList and checks if there is already a
                // weapon in the room
                // If so, this weapon is set its position at the recent position
                // of the weapon that should be moved
//				for (int j = 0; j < weaponList.size(); j++) {
//					if (weaponList.get(j).getPosX() == x
//							&& weaponList.get(j).getPosY() == y) {
//						weaponList.get(j).setPos(weaponList.get(i).getPosX(),
//								weaponList.get(i).getPosY());
//					}
//				}
                weaponList.get(i).setPos(x, y);
                break;
            }
        }
    }

    /**
     * Find the Counter for a specific color
     *
     * @param color the color of the counter
     * @return the counter itself
     */
    private Counter findCounter(Counter.Color color) {
        ArrayList<Counter> list = getCounterList();
        Counter counter = null;
        for (Counter c : list) {
            if (c.isColor(color))
                counter = c;
        }
        if (counter == null)
            log.error("could not move - color not found");
        return counter;
    }

    /**
     * Creates Player and add to the List
     *
     * @param name  the Name of the Player
     * @param color the color of the Player
     * @author Jonas
     */
    public void createPlayer(String name, Counter.Color color) {
        Counter c = findCounter(color);
        playerList.add(new Player(name, c));
        playerList.get(playerList.size() - 1).setPosition(playerList.size() - 1);
    }

    /**
     * Creates Player and add to the List
     *
     * @param name         the Name of the Player
     * @param color        the color of the Player
     * @param playerstates actual states of the player, can be null
     * @author Ludwig
     */
    public void createPlayer(String name, Counter.Color color, EnumSet<Player.Playerstate> playerstates) {
        createPlayer(name, color);
        if (playerstates != null)
            getPlayer(name).setPlayerstates(playerstates);
    }

    /**
     * Removes a Player from gamefield
     *
     * @param name the Name of the Player
     * @author Ludwig
     */
    public void removePlayer(String name) {
        // get player
        Player player = getPlayer(name);
        if (player == null) return;
        // lookup position in array
        int i = playerList.indexOf(player);
        if (i == -1) return;
        // remove
        playerList.remove(i);

        // update strange code for order
        for (i = 0; i < playerList.size(); i++) {
            playerList.get(i).setPosition(i);
        }
    }

    /**
     * moves the counter to a specific field
     *
     * @param color the counter which should move
     * @param x     the x coordinate of the destination
     * @param y     the y coordinate of the destination
     * @author Jonas
     */
    public void setCounter(Counter.Color color, int x, int y) {
        Counter counter = findCounter(color);
        // when moving to a room, do not block the room
        // study room
        if (x == 6 && y == 3) {
            // starting point release
            board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
            // refresh the data within the counter
            counter.setPositionX(x);
            counter.setPositionY(y);
        } else
            // Hall
            if (x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6) {
                // starting point release
                board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                // refresh the data within the counter
                counter.setPositionX(x);
                counter.setPositionY(y);
            } else
                // Salon
                if (x == 17 && y == 5) {
                    // starting point release
                    board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                    // refresh the data within the counter
                    counter.setPositionX(x);
                    counter.setPositionY(y);
                } else
                    // Libary
                    if (x == 6 && y == 8 || x == 3 && y == 10) {
                        // starting point release
                        board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                        // refresh the data within the counter
                        counter.setPositionX(x);
                        counter.setPositionY(y);
                    } else
                        // swimming pool
                        if (x == 11 && y == 8 || x == 13 && y == 9 || x == 11 && y == 14 || x == 12 && y == 9) {
                            // starting point release
                            board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                            // refresh the data within the counter
                            counter.setPositionX(x);
                            counter.setPositionY(y);
                        } else
                            // dining room
                            if (x == 17 && y == 9 || x == 16 && y == 12) {
                                // starting point release
                                board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                                // refresh the data within the counter
                                counter.setPositionX(x);
                                counter.setPositionY(y);
                            } else
                                // chess room
                                if (x == 1 && y == 12 || x == 5 && y == 15) {
                                    // starting point release
                                    board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                                    // refresh the data within the counter
                                    counter.setPositionX(x);
                                    counter.setPositionY(y);
                                } else
                                    // winter garden
                                    if (x == 4 && y == 19) {
                                        // starting point release
                                        board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                                        // refresh the data within the counter
                                        counter.setPositionX(x);
                                        counter.setPositionY(y);
                                    } else
                                        // music room
                                        if (x == 19 && y == 8 || x == 9 && y == 17 || x == 14 && y == 17 || x == 15 && y == 19) {
                                            // starting point release
                                            board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                                            // refresh the data within the counter
                                            counter.setPositionX(x);
                                            counter.setPositionY(y);
                                        } else
                                            // kitchen
                                            if (x == 19 && y == 18) {
                                                // starting point release
                                                board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                                                // refresh the data within the counter
                                                counter.setPositionX(x);
                                                counter.setPositionY(y);
                                            } else {

                                                // standard procedure
                                                // starting point release
                                                board.getBoard()[counter.getPositionX()][counter.getPositionY()] = 0;
                                                // refresh the data within the counter
                                                counter.setPositionX(x);
                                                counter.setPositionY(y);
                                                // move the counter on the board
                                                board.getBoard()[x][y] = 1;
                                            }
    }

    // getter methods

    /**
     * @return List list of all the Player
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * @return List of all the cards
     */
    public ArrayList<Card> getCardList() {
        return cardList;
    }

    /**
     * @return List of all the counter
     */
    public ArrayList<Counter> getCounterList() {
        return counterList;
    }

    /**
     * picks a random card from the List and remove it
     *
     * @return a random Card from Cardlist
     * @author Jonas
     */
    private Card pickRandCard() {
        Random r = new Random();
        Card card = null;
        int temp = r.nextInt(cardList.size());
        card = cardList.get(temp);
        cardList.remove(temp);
        return card;
    }

    /**
     * Picks a random murderer, room and weapon and creates a murder.
     *
     * @author Jonas
     */
    public void initMurder() {
        WeaponCard weapon = null;
        RoomCard scene = null;
        PersonCard murderer = null;
        Card temp;
        while (weapon == null) {
            temp = pickRandCard();
            if (temp instanceof WeaponCard)
                weapon = (WeaponCard) temp;
            else
                cardList.add(temp);
        }

        while (scene == null) {
            temp = pickRandCard();
            if (temp instanceof RoomCard)
                scene = (RoomCard) temp;
            else
                cardList.add(temp);
        }

        while (murderer == null) {
            temp = pickRandCard();
            if (temp instanceof PersonCard)
                murderer = (PersonCard) temp;
            else
                cardList.add(temp);
        }
        this.murder = new Murder(scene, murderer, weapon);
    }

    /**
     * spreads the cards between all players
     *
     * @author Jonas, Roxi(denke ich(Jonas))
     */
    public void spreadCards() {
        while (cardList.size() >= playerList.size()) {
            for (int i = 0; i < playerList.size(); i++) {
                playerList.get(i).addCard(pickRandCard());
            }
        }
        while (cardList.size() != 0) {
            poolCards.add(cardList.get(0));
            log.info("PoolCards" + cardList.get(0).getInfo());
            cardList.remove(0);
        }
    }

    /**
     * Getter for gameinfo
     *
     * @return gameinfo of actual game/gamefield
     * @author Ludwig
     */
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    /**
     * Set poolcards to model used by client if data received by network
     *
     * @param cardList list of all poolcards
     * @author Ludwig
     */
    public void setPoolCards(ArrayList<Card> cardList) {
        poolCards.clear();
        poolCards.addAll(cardList);
    }

    /**
     * @return poolCards - the cards laying in the pool
     */
    public ArrayList<Card> getPoolCards() {
        return poolCards;
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    /**
     * Helper, get own nickname only client
     *
     * @return own nickname
     * @author Ludwig
     */
    public String getMyNick() {
        return this.myNick;
    }

    /**
     * Return latest Suspicion
     *
     * @return latest Suspicion
     * @author Ludwig
     */
    public Suspicion getSuspicion() {
        return this.suspicion;
    }

    /**
     * Get self player only client
     *
     * @return reference to player
     * @author Ludwig
     */
    public Player getPlayer() {
        return getPlayer(this.myNick);
    }

    /**
     * Search player for given nickname
     *
     * @param str nickname of player
     * @return reference to player
     * @author Ludwig
     */
    public Player getPlayer(String str) {
        ArrayList<Player> players = getPlayerList();
        for (Player player : players) {
            if (player.getName().equals(str))
                return player;
        }
        log.error("no player found for: " + str);
        return null;
    }

    public Counter.Color StringToColor(String color) {
        switch (color) {
            case "white":
                return Counter.Color.WHITE;
            case "red":
                return Counter.Color.RED;
            case "yellow":
                return Counter.Color.YELLOW;
            case "green":
                return Counter.Color.GREEN;
            case "blue":
                return Counter.Color.BLUE;
            case "purple":
                return Counter.Color.PURPLE;
        }
        return null;
    }

    /**
     * Set the playerList according to the StringArray Order and update the
     * Player position
     *
     * @param order the order of the player
     * @author Jonas, Ludwig
     */
    public void setOrder(String[] order) {
        if (order.length != playerList.size())
            log.fatal("verschieden viele Spieler in Order Nachricht als im Model gespeichert");

        ArrayList<Player> temp = new ArrayList<Player>();
        for (Player player : playerList) {
            temp.add(player);
        }
        playerList.clear();

        for (int i = 0; i < order.length; i++) {
            for (Player player : temp) {
                if (player.getName().equals(order[i])) {
                    playerList.add(player);

                    playerList.get(i).setPosition(i);
                }
            }
        }
        log.info("Reihenfolge wurde festgelegt");
    }

    /**
     * the player wants to use the secret passage,
     *
     * @param player the player who wants to use secret Passage
     * @author Jonas
     */
    public void useSecretPassage(Player player) {
        // Player player = getPlayer(nick);

        int x = player.getCounter().getPositionX();
        int y = player.getCounter().getPositionY();

        //from working room to kitcen
        if (x == 6 && y == 3) {
            player.getCounter().setPosition(19, 18);
        } else
            //from Kitchen to working room
            if (x == 19 && y == 18) {
                player.getCounter().setPosition(6, 3);
            } else
                //from salon to wintergarten
                if (x == 17 && y == 5) {
                    player.getCounter().setPosition(4, 19);
                } else
                    //from wintergarten to salon
                    if (x == 4 && y == 19) {
                        player.getCounter().setPosition(17, 5);
                    }
                    //Errorfall
                    else {
                        log.error(player.getName() + " kann durch keinen Geheimgang gehen");
                    }
    }

    /**
     * Finds the Cards according to the nick
     *
     * @param nick the nick of the Player
     * @return the cards owned by nick
     * @author Jonas
     */
    public ArrayList<Card> getPlayerCards(String nick) {

        return getPlayer(nick).getPlayerCards();
    }

    /**
     * Selects a random order for the server. gloria first, if possible
     *
     * @return random order of nicks
     * @author Jonas
     */
    public String[] randomizeOrder() {
        String[] order = new String[playerList.size()];
        ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < playerList.size(); i++) {
            temp.add(playerList.get(i).getName());
        }

        int flag = 0;
        // Gloria ist start
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getCounter().isColor(Color.RED)) {
                order[flag] = playerList.get(i).getName();
                flag++;
                temp.remove(i);
                break;
            }
        }

        Random r = new Random();
        int rand;
        while (temp.size() != 0) {
            rand = r.nextInt(temp.size());
            order[flag] = temp.get(rand);
            flag++;
            temp.remove(rand);
        }
        log.info("random order: " + order);
        setCurrentPlayer(order[0]);
        return order;
    }

    /**
     * Server function to get the current Player
     *
     * @return the nick of the current Player
     * @author Jonas
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * to set the current PLayer variable
     *
     * @param nick the nick of the Player whos turn it is
     * @author Jonas
     */
    public void setCurrentPlayer(String nick) {
        currentPlayer = nick;

    }

    /**
     * serverfunction to identify the next player
     *
     * @return the nick of the next play
     */
    public String nextPlayer() {
        for (int i = 0; i < playerList.size(); i++) {
            if (currentPlayer.equals(playerList.get(i).getName())) {
                if (i == playerList.size() - 1) {
                    currentPlayer = playerList.get(0).getName();
                } else {
                    currentPlayer = playerList.get(i + 1).getName();
                }
                break;
            }
        }
        return currentPlayer;
    }

    /**
     * serverfunction to check if a move is possible and moves the Counter
     * allowed
     *
     * @param nick the name of the player who wants to move
     * @param x    the X destination
     * @param y    the Y destination
     * @param pips the pips
     * @return if move is possible and moves
     * @author Jonas
     */
    public boolean checkMove(String nick, int x, int y, int pips) {
        Boolean ret;
        Player tempPlayer = getPlayer(nick);
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        tempList.add(x);
        tempList.add(y);
        ret = board.possibleMove(tempPlayer.getCounter().getPositionX(),
                tempPlayer.getCounter().getPositionY(), pips)
                .contains(tempList);
        if (ret == true) {
            // To set the position free where the counter was
            board.getBoard()[tempPlayer.getCounter().getPositionX()][tempPlayer
                    .getCounter().getPositionY()] = 0;
            // Moves the counter
            tempPlayer.getCounter().setPosition(x, y);
            // occupies the place
            board.getBoard()[x][y] = 1;
        }
        return ret;
    }


    /**
     * server function to get the order starting by player specifed by his name,
     *
     * @param playerName the nick of the starting player
     * @return the List of Player
     * @author Jonas
     */
    public ArrayList<Player> getPlayerListAfterPlayer(String playerName) {
        ArrayList<Player> erg = new ArrayList<Player>();
        int start = 0;
        for (int j = 0; j < playerList.size(); j++) {
            if (playerList.get(j).getName().equals(playerName)) {
                start = j;
                break;
            }

        }

        for (int i = 1; i < playerList.size(); i++) {
            erg.add(playerList.get((start + i) % playerList.size()));
        }

        return erg;
    }

    /**
     * server function to check if the 3 cards are the murdercards
     *
     * @param weapon the suspected Weapon
     * @param Room   the suspected Room
     * @param Person the Suspected Person
     * @return if the cards are the Murder or not
     * @author Jonas
     */
    public boolean checkMurder(WeaponCard.Type weapon, RoomCard.Type Room,
                               PersonCard.Type Person) {
        return (murder.getMurderer().getCardValue().equals(Person) && murder.getScene().getCardValue().equals(Room) && murder
                .getWeapon().getCardValue().equals(weapon));
    }

    /**
     * Checks, in which room the player whose nickname was given
     * <br>is and returns the rooms name.
     *
     * @param nick - the nickname of the player
     * @return the String of the name of the room the player is in
     */
    public String getPlayerRoom(String nick) {
        String room = null;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getName().equals(nick)) {
                int x = playerList.get(i).getCounter().getPositionX();
                int y = playerList.get(i).getCounter().getPositionY();

                if (x == 11 && y == 8 || x == 13 && y == 9 || x == 9 && y == 12 || x == 1 && y == 14) {
                    room = "pool";
                } else if (x == 8 && y == 19 || x == 14 && y == 17 || x == 9 && y == 17 || x == 15 && y == 19) {
                    room = "ballroom";
                } else if (x == 6 && y == 8 || x == 3 && y == 10) {
                    room = "library";
                } else if (x == 1 && y == 12 || x == 5 && y == 15) {
                    room = "billiard";
                } else if (x == 4 && y == 19) {
                    room = "conservatory";
                } else if (x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6) {
                    room = "hall";
                } else if (x == 19 && y == 18) {
                    room = "kitchen";
                } else if (x == 17 || y == 5) {
                    room = "lounge";
                } else if (x == 17 && y == 9 || x == 16 && y == 12) {
                    room = "diningroom";
                } else if (x == 6 && y == 3) {
                    room = "diningroom";
                }
            }
        }
        return room;
    }

    public int[] roomToKoordinaten(RoomCard.Type room) {
        int[] ziel = new int[2];
        if (room == RoomCard.Type.POOL) {
            ziel[0] = 11;
            ziel[1] = 8;
        }
        if (room == RoomCard.Type.BALLROOM) {
            ziel[0] = 8;
            ziel[1] = 19;
        }
        if (room == RoomCard.Type.LIBRARY) {
            ziel[0] = 6;
            ziel[1] = 8;
        }
        if (room == RoomCard.Type.BILLIARD) {
            ziel[0] = 1;
            ziel[1] = 12;
        }
        if (room == RoomCard.Type.CONSERVATORY) {
            ziel[0] = 4;
            ziel[1] = 19;
        }
        if (room == RoomCard.Type.HALL) {
            ziel[0] = 9;
            ziel[1] = 4;
        }
        if (room == RoomCard.Type.KITCHEN) {
            ziel[0] = 19;
            ziel[1] = 18;
        }
        if (room == RoomCard.Type.LOUNGE) {
            ziel[0] = 17;
            ziel[1] = 5;
        }
        if (room == RoomCard.Type.DININGROOM) {
            ziel[0] = 17;
            ziel[1] = 9;
        }
        if (room == RoomCard.Type.STUDY) {
            ziel[0] = 6;
            ziel[1] = 3;
        }
        log.info(ziel[0]);
        log.info(ziel[1]);
        return ziel;

    }

    /**
     * @return
     * @author Paul
     * Watchlist GETTER UND SETTER
     */

    public ArrayList<String> getWatcherList() {
        return watcherList;
    }

    public String[] getWatcherStringArray() {
        String[] stockArr = new String[watcherList.size()];
        stockArr = watcherList.toArray(stockArr);
        return stockArr;
    }


    public void setWatcherList(ArrayList<String> watcherList) {
        this.watcherList = watcherList;
    }

    public void addWatcher(String nick) {
        watcherList.add(nick);
    }

    public ArrayList<String> getPlayerNames() {
        ArrayList<String> playerNameList = new ArrayList<>();
        for (int i = 0; i < playerList.size(); i++) {
            playerNameList.add(playerList.get(i).getName());
        }
        return playerNameList;
    }

    public boolean isPlayer(String nick) {
        for (int i = 0; i < watcherList.size(); i++) {
            if (watcherList.get(i).equals(nick)) {
                return false;
            }
        }
        return true;
    }
}

 
