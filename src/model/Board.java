package model;

import java.util.ArrayList;

import model.RoomCard.Type;

/**
 * Class provides the board with its fields and the values of the fields
 * @author Jonas, Sarah, Roxanna
 *
 */
public class Board {

	/**
	 * Field represent the board, the explanation of the numbers: -1, this
	 * position is not allowed 0, this position is free 1, this position is
	 * reserved
	 */
	private int[][] board = new int[24][25];

	/**
	 * class constructor which sets up the matrix for the board
	 * 
	 * @author Jonas
	 */
	public Board() {

		for (int i = 0; i <= 23; i++) {
			for (int j = 0; j <= 24; j++) {
				board[i][j] = 0; // whole board is free
			}
		}

		for (int i = 0; i <= 6; i++) { // mark the workroom
			for (int j = 0; j <= 3; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 9; i <= 14; i++) { // mark the entrance
			for (int j = 0; j <= 6; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 17; i <= 23; i++) { // mark the salon
			for (int j = 0; j <= 5; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 0; i <= 5; i++) { // mark the libary
			for (int j = 6; j <= 10; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 9; i <= 13; i++) { // mark the pool
			for (int j = 8; j <= 14; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 16; i <= 23; i++) { // mark the dining room
			for (int j = 9; j <= 14; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 0; i <= 5; i++) { // mark the billiard room
			for (int j = 12; j <= 16; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 0; i <= 4; i++) { // mark the winter garden
			for (int j = 19; j <= 24; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 8; i <= 15; i++) { // mark the music room
			for (int j = 17; j <= 22; j++) {
				board[i][j] = -1;
			}
		}
		for (int i = 10; i <= 13; i++) { // addition music room
			for (int j = 23; j <= 24; j++) {
				board[i][j] = -1;
			}
		}

		for (int i = 18; i <= 23; i++) { // mark the kitchen
			for (int j = 18; j <= 24; j++) {
				board[i][j] = -1;
			}
		}

		// the missing pieces

		board[8][0] = -1;
		board[15][0] = -1;
		board[0][4] = -1;
		board[23][6] = -1;
		board[6][7] = -1;
		board[23][8] = -1;
		board[6][9] = -1;
		board[0][11] = -1;
		board[19][15] = -1;
		board[20][15] = -1;
		board[21][15] = -1;
		board[22][15] = -1;
		board[23][15] = -1;
		board[23][16] = -1;
		board[0][17] = -1;
		board[5][20] = -1;
		board[5][21] = -1;
		board[5][22] = -1;
		board[5][23] = -1;
		board[6][23] = -1;
		board[17][23] = -1;
		board[5][24] = -1;
		board[6][24] = -1;
		board[7][24] = -1;
		board[8][24] = -1;
		board[15][24] = -1;
		board[16][24] = -1;
		board[17][24] = -1;
		board[6][8] = -1;

		// the position of the Counter
		board[16][0] = 1; // red
		board[23][7] = 1; // yellow
		board[14][24] = 1; // white
		board[9][24] = 1; // green
		board[0][18] = 1; // blue
		board[0][5] = 1; // purpel
	}

	/**
	 * Checks for accessible fields next to position on board <br>
	 * and returns an ArrayList filled with these fields as Integer values.
	 * 
	 * @param x
	 *            x coordinate you like to find the neighbor fields
	 * @param y
	 *            y coordinate you like to find the neighbor fields
	 * @return neighbors the ArrayList contains tupel of x and y koordinates
	 *         wich represent the neighbor fields
	 * @author Jonas, Roxanna, Sarah
	 */
	public ArrayList<ArrayList<Integer>> checkNeighbors(int x, int y) {
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		// Raumfelder und Felder vor deren Türen werden als gegenseitige
		// Nachbarn zugewiesen
		// erste if: von Feld vor Tür in Raum, zweite if: von Raum wieder auf
		// Feld

		// Working room
		// in
		if (x == 6 && y == 4) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(6);
			a.add(3);
			neighbors.add(a);
		} else
		// out
		if (x == 6 && y == 3) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(6);
			a.add(4);
			neighbors.add(a);
		} else

		// Salon
		// in
		if (x == 17 && y == 6) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(17);
			a.add(5);
			neighbors.add(a);
		} else
		// out
		if (x == 17 && y == 5) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(17);
			a.add(6);
			neighbors.add(a);
		} else

		// Winter garden
		// in
		if (x == 5 && y == 19) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(4);
			a.add(19);
			neighbors.add(a);
		} else
		// out
		if (x == 4 && y == 19) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(5);
			a.add(19);
			neighbors.add(a);
		} else

		// Hall
		// in
		if (x == 8 && y == 4) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(9);
			a.add(4);
			neighbors.add(a);
		} else if (x == 12 && y == 7) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(12);
			a.add(6);
			neighbors.add(a);
		} else if (x == 11 && y == 7) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();
			a.add(11);
			a.add(6);
			b.add(11); // +pool
			b.add(8);
			neighbors.add(a);
			neighbors.add(b);
		} else
		// out
		if (x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();
			ArrayList<Integer> c = new ArrayList<Integer>();

			a.add(8);
			a.add(4);
			b.add(11);
			b.add(7);
			c.add(12);
			c.add(7);

			neighbors.add(a);
			neighbors.add(b);
			neighbors.add(c);
		} else

		// Pool, eine eingangstür schon in der Hall abgefangen
		// in
		if (x == 14 && y == 9) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(13);
			a.add(9);
			neighbors.add(a);
		} else

		if (x == 11 && y == 15) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(11);
			a.add(14);
			neighbors.add(a);
		} else

		if (x == 8 && y == 12) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(9);
			a.add(12);
			neighbors.add(a);
		} else

		// out
		if (x == 11 && y == 8 || x == 13 && y == 9 || x == 11 && y == 14
				|| x == 9 && y == 12) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();
			ArrayList<Integer> c = new ArrayList<Integer>();
			ArrayList<Integer> d = new ArrayList<Integer>();

			a.add(11);
			a.add(7);
			b.add(14);
			b.add(9);
			c.add(11);
			c.add(15);
			d.add(8);
			d.add(12);
			neighbors.add(a);
			neighbors.add(b);
			neighbors.add(c);
			neighbors.add(d);

		} else

		// Library
		// in
		if (x == 7 && y == 8) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(6);
			a.add(8);
			neighbors.add(a);
		} else if (x == 3 && y == 11) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(3);
			a.add(10);
			neighbors.add(a);
		} else
		// out
		if (x == 6 && y == 8 || x == 3 && y == 10) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();

			a.add(7);
			a.add(8);
			b.add(3);
			b.add(11);

			neighbors.add(a);
			neighbors.add(b);
		} else

		// Chess room
		// in
		if (x == 1 && y == 11) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(1);
			a.add(12);
			neighbors.add(a);
		} else if (x == 6 && y == 15) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(5);
			a.add(15);
			neighbors.add(a);
		} else
		// out
		if (x == 1 && y == 12 || x == 5 && y == 15) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();

			a.add(1);
			a.add(11);
			b.add(6);
			b.add(15);
			neighbors.add(a);
			neighbors.add(b);
		} else

		// Music room
		// in
		if (x == 7 && y == 19) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(8);
			a.add(19);
			neighbors.add(a);
		} else if (x == 9 && y == 16) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(9);
			a.add(17);
			neighbors.add(a);
		} else if (x == 14 && y == 16) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(14);
			a.add(17);
			neighbors.add(a);
		} else if (x == 16 && y == 19) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(15);
			a.add(19);
			neighbors.add(a);
		} else
		// out
		if (x == 8 && y == 19 || x == 9 && y == 17 || x == 14 && y == 17
				|| x == 15 && y == 19) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();
			ArrayList<Integer> c = new ArrayList<Integer>();
			ArrayList<Integer> d = new ArrayList<Integer>();

			a.add(7);
			a.add(19);
			b.add(9);
			b.add(16);
			c.add(14);
			c.add(16);
			d.add(16);
			d.add(19);
			neighbors.add(a);
			neighbors.add(b);
			neighbors.add(c);
			neighbors.add(d);
		} else

		// Kitchen
		// in
		if (x == 19 && y == 17) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(19);
			a.add(18);
			neighbors.add(a);
		} else
		// out
		if (x == 19 && y == 18) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(19);
			a.add(17);
			neighbors.add(a);
		} else

		// Dining room
		// in
		if (x == 17 && y == 8) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(17);
			a.add(9);
			neighbors.add(a);
		} else if (x == 15 && y == 12) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(16);
			a.add(12);
			neighbors.add(a);
		} else
		// out
		if (x == 16 && y == 12 || x == 17 && y == 9) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			ArrayList<Integer> b = new ArrayList<Integer>();

			a.add(15);
			a.add(12);
			b.add(17);
			b.add(8);

			neighbors.add(a);
			neighbors.add(b);
		}

		// check if fields around are empty
		// check field left
		if (x > 0 && board[x - 1][y] == 0) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(x - 1);
			a.add(y);
			if (neighbors.contains(a) == false) {
				neighbors.add(a);
			}
		}

		// check field above
		if (y > 0 && board[x][y - 1] == 0) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(x);
			a.add(y - 1);
			if (neighbors.contains(a) == false) {
				neighbors.add(a);
			}
		}

		// check field right
		if (x < 23 && board[x + 1][y] == 0) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(x + 1);
			a.add(y);
			if (neighbors.contains(a) == false) {
				neighbors.add(a);
			}
		}

		// check field beneath
		if (y < 24 && board[x][y + 1] == 0) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(x);
			a.add(y + 1);
			if (neighbors.contains(a) == false) {
				neighbors.add(a);
			}
		}

		return neighbors;
	}

	/**
	 * check if the field specified by x and y is a room or not
	 * 
	 * @param x
	 *            koordinate
	 * @param y
	 *            koordinate
	 * @return if this field is a room or not
	 * @author Jonas, Sarah
	 */
	private ArrayList<ArrayList<Integer>> isRoom(int x, int y) {
		ArrayList<ArrayList<Integer>> erg = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		// Study Room
		if (x == 6 && y == 3) {
			temp.add(6);
			temp.add(3);
			erg.add(temp);
			return erg;
		} else
		// Hall
		if (x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6) {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			temp.add(9);
			temp.add(4);
			erg.add(temp);

			temp1.add(11);
			temp1.add(6);
			erg.add(temp1);

			temp2.add(12);
			temp2.add(6);
			erg.add(temp2);
			return erg;
		} else
		// Salon
		if (x == 17 && y == 5) {
			temp.add(17);
			temp.add(5);
			erg.add(temp);
			return erg;
		}
		// Libary
		if (x == 6 && y == 8 || x == 3 && y == 10) {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			temp.add(6);
			temp.add(8);
			erg.add(temp);

			temp1.add(3);
			temp1.add(10);
			erg.add(temp1);
			return erg;
		} else
		// Swimming Pool
		if (x == 11 && y == 8 || x == 11 && y == 14 || x == 13 && y == 9
				|| x == 9 && y == 12) {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			ArrayList<Integer> temp3 = new ArrayList<Integer>();
			temp.add(11);
			temp.add(8);
			erg.add(temp);

			temp1.add(11);
			temp1.add(14);
			erg.add(temp1);

			temp2.add(13);
			temp2.add(9);
			erg.add(temp2);

			temp3.add(9);
			temp3.add(12);
			erg.add(temp3);
			return erg;
		} else
		// Diner Room
		if (x == 17 && y == 9 || x == 16 && y == 12) {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			temp.add(17);
			temp.add(9);
			erg.add(temp);

			temp1.add(16);
			temp1.add(12);
			erg.add(temp1);
			return erg;
		} else
		// Chess Room
		if (x == 1 && y == 12 || x == 5 && y == 15) {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			temp.add(1);
			temp.add(12);
			erg.add(temp);

			temp1.add(5);
			temp1.add(15);
			erg.add(temp1);
			return erg;
		} else
		// Winter Garden
		if (x == 4 && y == 19) {
			temp.add(4);
			temp.add(19);
			erg.add(temp);
			return erg;
		} else
		// Music Room
		if (x == 8 && y == 19 || x == 9 && y == 17 || x == 14 && y == 17
				|| x == 15 && y == 19) {
			ArrayList<Integer> temp1 = new ArrayList<Integer>();
			ArrayList<Integer> temp2 = new ArrayList<Integer>();
			ArrayList<Integer> temp3 = new ArrayList<Integer>();
			temp.add(8);
			temp.add(19);
			erg.add(temp);

			temp1.add(9);
			temp1.add(17);
			erg.add(temp1);

			temp2.add(14);
			temp2.add(17);
			erg.add(temp2);

			temp3.add(15);
			temp3.add(19);
			erg.add(temp3);
			return erg;
		} else
		// Kitchen
		if (x == 18 && y == 19) {
			temp.add(18);
			temp.add(19);
			erg.add(temp);
			return erg;
		}
		return null;
	}

	/**
	 * calculate all accessible Fields in one turn
	 * 
	 * @param x
	 *            the initial x coordinate
	 * @param y
	 *            the initial y coordinate
	 * @param pips
	 *            the dice pips,
	 * @return an ArrayList which contains the coordinates of reachable fields
	 * @author Jonas
	 */
	public ArrayList<ArrayList<Integer>> possibleMove(int x, int y, int pips) {
		// Liste an Räumen die auf dem weg gefunden wurden wo man anhalten kann
		ArrayList<ArrayList<Integer>> rooms = new ArrayList<ArrayList<Integer>>();
		// startfelder Falls in einem Raum gestartet wurde
		ArrayList<ArrayList<Integer>> startRoom = isRoom(x, y);

		// ergebnisarray wird angelegt
		ArrayList<ArrayList<Integer>> erg = new ArrayList<ArrayList<Integer>>();
		// temporaere ArrayList um x,y in eine ArrayList zu packen
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(x);
		a.add(y);
		// LastMove enthält das Ergebnis der letzten schleifendurchgangs
		ArrayList<ArrayList<Integer>> lastMove = new ArrayList<ArrayList<Integer>>();
		lastMove.add(a);
		// pips schleifendurchläufe
		for (int i = 0; i < pips; i++) {
			// damit nur echte nachbarn vom letzten Schleifendurchlauf gezählt
			// werden
			erg.clear();
			// von jedem Ergebnis des letzten Durchlaufs die Nachbarn berechnen
			for (int j = 0; j < lastMove.size(); j++) {
				ArrayList<ArrayList<Integer>> temp = checkNeighbors(lastMove
						.get(j).get(0), lastMove.get(j).get(1));
				// Für jedes Tupel in ArrayList
				for (int k = 0; k < temp.size(); k++) {
					// falls ein nachbar ein Raum ist
					if (isRoom(temp.get(k).get(0), temp.get(k).get(1)) != null) {
						if (startRoom != null && startRoom.contains(temp.get(k))) {
						} else {
							// falls er noch nicht in room steht
							if (rooms.contains(temp.get(k)) == false) {
								rooms.add(temp.get(k));
							}
						}
						temp.remove(k);
						k--;
					}
				}
				// Für jedes Element der CheckN fkt schauen ob es im erg ist
				for (int h = 0; h < temp.size(); h++) {
					if (erg.contains(temp.get(h)) == false)
						erg.add(temp.get(h));
				}
				temp.clear();
			}
			lastMove.clear();
			// erg übertragen in LastMove
			for (int l = 0; l < erg.size(); l++) {
				lastMove.add(erg.get(l));
			}
		}
		// rooms zum ergebnis hinzufügen
		for (int p = 0; p < rooms.size(); p++) {
			erg.add(rooms.get(p));
		}
		// startRoom aus dem erg löschen
		if (startRoom != null) {
			for (int q = 0; q < startRoom.size(); q++) {
				if (erg.contains(startRoom.get(q)))
					erg.remove(erg.indexOf(startRoom.get(q)));
			}
		}
		return erg;
	}

	// getter methods
	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}
	
	/**
	 * returns RoomCard as Type
	 * @param player
	 * @return
	 * @author Maurice, Sarah
	 */
	public RoomCard.Type getRoomOfPerson(Player player) {
	int x = player.getCounter().getPositionX();
	int y = player.getCounter().getPositionY();
	
	if(x == 6 && y == 3){
		return Type.STUDY;
	}
	
	else if(x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6){
		return Type.HALL;
	}
	
	else if(x == 17 && y == 5){
		return Type.LOUNGE;
	}
	
	else if(x == 6 && y == 8 || x == 3 && y == 10){
		return Type.LIBRARY;
	}
	
	else if(x == 11 && y == 8 || x == 11 && y == 14 || x == 13 && y == 9 || x == 9 && y == 12){
		return Type.POOL;
	}
	
	else if(x == 17 && y == 9 || x == 16 && y == 12){
		return Type.DININGROOM;
	}
	
	else if(x == 1 && y == 12 || x == 5 && y == 15){
		return Type.BILLIARD;
	}
	
	else if(x == 4 && y == 19){
		return Type.CONSERVATORY;
	}
	
	else if(x == 8 && y == 19 || x == 9 && y == 17 || x == 14 && y == 17 || x == 15 && y == 19){
		return Type.BALLROOM;
	}
	
	else if(x == 18 && y == 19){
		return Type.KITCHEN;
	}
	
	else{
		return null;
	}
	}

}
