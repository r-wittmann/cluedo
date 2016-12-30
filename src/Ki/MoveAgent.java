package Ki;

import model.Gamefield;
import model.RoomCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Calculates best possible move for KI
 * @author Jonas
 */
public class MoveAgent {
	private static final Logger log = LogManager.getLogger(MoveAgent.class);
	
	private int x;
	private int y;
	private Ki ki;
	
	private int poolFlag = 0;
	
	public MoveAgent(Ki ki){
		this.ki = ki;
	}
	
	/**
	 * Calculates the destination
	 * @param model gamefield where posible moves should be calculated
	 * @param pips  the number of steps your allowed to move 1<pips<13
	 * @return an Array first the x second the y coordinate
	 * @author Jonas, Ludwig
	 */
	public int[] move(Gamefield model, int pips) {
		x = model.getPlayer().getCounter().getPositionX();
		y = model.getPlayer().getCounter().getPositionY();
		
		log.debug("got pimps: " + pips + " X: " + x + " Y: " + y + " color: " + model.getPlayer().getCounter().getColor().toString() );
		
		ArrayList<ArrayList<Integer>> moves = model.board.possibleMove(x, y, pips);
		log.debug("possible moves:");
		for (ArrayList<Integer> field : moves) {
			log.debug("  X: " + field.get(0) + " Y: " + field.get(1));
		}

		ArrayList<Integer> room = isRoomPossible(moves , model.getGameInfo().getGameID());
		ArrayList<Integer> poolPos = isPoolPossible(moves);

		//if pool is possible
		if (poolPos.size() != 0) {
			x = poolPos.get(0);
			y = poolPos.get(1);
		} 
		// if a room is possible
		else if (room.size() != 0) {
			x = room.get(0);
			y = room.get(1);
		} 
		//random field
		else {
			Random r = new Random();
			int temp = r.nextInt(moves.size());
			x = moves.get(temp).get(0);
			y = moves.get(temp).get(1);
		}
		return new int[] { x, y };
	}
	
	/**
	 * @return an ArrayList with x, y values of an interesting Room,
	 * <br>if (and only if) a room is in the possible moves.
	 * <br>Otherwise it returns an empty ArrayList.
	 * @author Jonas
	 */
	private ArrayList<Integer> isRoomPossible(ArrayList<ArrayList<Integer>> moves, int gameID) {
		log.debug("check rooms");
		ArrayList<Integer> roomPos = new ArrayList<Integer>();
		ArrayList<RoomCard> knownRooms = ki.getPossibleRooms(gameID);
		for (int i = 0; i < moves.size(); i++) {
			x = moves.get(i).get(0);
			y = moves.get(i).get(1);

			// Study Room
			if (knownRooms.contains(RoomCard.Type.STUDY) && x == 6 && y == 3) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
				if(x==6 && y==3)
			{
					roomPos.add(x);
					roomPos.add(y);
			}
			// Hall
			if (knownRooms.contains(RoomCard.Type.HALL) && (x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6)) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
			if(x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
			// Salon
			if (knownRooms.contains(RoomCard.Type.LOUNGE) && x == 17 && y == 5) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
			if(x == 17 && y == 5)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
			// Libary
			if (knownRooms.contains(RoomCard.Type.LIBRARY) && (x == 6 && y == 8 || x == 3 && y == 10)) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
			if(x == 6 && y == 8 || x == 3 && y == 10)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
			// Dining Room
			if (knownRooms.contains(RoomCard.Type.DININGROOM) && (x == 17 && y == 9 || x == 16 && y == 12)) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
			if(x == 17 && y == 9 || x == 16 && y == 12)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
			// Chess Room
			if (knownRooms.contains(RoomCard.Type.BILLIARD) && (x == 1 && y == 12 || x == 5 && y == 15)) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
			if(x == 1 && y == 12 || x == 5 && y == 15)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
			// Winter Garden
			if (knownRooms.contains(RoomCard.Type.CONSERVATORY) && x == 4 && y == 19) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
			if(x == 4 && y == 19)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
			// Music Room
			if (knownRooms.contains(RoomCard.Type.BALLROOM) && (x == 8 && y == 19 || x == 9 && y == 17 || x == 14 && y == 17
					|| x == 15 && y == 19)) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			} else
			if(x == 8 && y == 19 || x == 9 && y == 17 || x == 14 && y == 17
					|| x == 15 && y == 19)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
			// Kitchen
			if (knownRooms.contains(RoomCard.Type.KITCHEN) && x == 18 && y == 19) {
				roomPos.clear();
				roomPos.add(x);
				roomPos.add(y);
				return roomPos;
			}
			else if(x == 18 && y == 19)
			{
				roomPos.add(x);
				roomPos.add(y);
			}
		}
		if (roomPos.isEmpty()){
			log.debug("no room found");
			return new ArrayList<>();
		}
		
		Random r = new Random();
		int temp = r.nextInt(roomPos.size()/2);
		int tempX = roomPos.get(temp*2);
		int tempY = roomPos.get(temp*2 + 1);
		roomPos.clear();
		roomPos.add(tempX);
		roomPos.add(tempY);
		
		return roomPos;
	}
	
	/**
	 * @return an ArrayList with x, y values of the first Pooldoor,
	 * <br>if (and only if) the Pool is in the possible moves.
	 * <br>Otherwise it returns an empty ArrayList.
	 * @author Jonas
	 */
	private ArrayList<Integer> isPoolPossible(ArrayList<ArrayList<Integer>> moves){
		ArrayList<Integer> poolPos = new ArrayList<Integer>();
		
		if(poolFlag == 0)
		{
			for(int i = 0; i < moves.size(); i++){
				x = moves.get(i).get(0);
				y = moves.get(i).get(1);
				// Swimming Pool
				if (x == 11 && y == 8 || x == 11 && y == 14 || x == 13 && y == 9
						|| x == 9 && y == 12) {
					poolFlag = 1;
					poolPos.add(x);
					poolPos.add(y);
					break;
				}
			}
		}
		return poolPos;
	}
}
