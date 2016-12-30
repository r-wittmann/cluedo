package model;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

/**
 * Creates two dice with SimpleIntegerProperties 
 * <br>for the pips from dice1 and the pips from dice2
 * <br>
 * <br>provides the method roll() which generates random values for the dice.
 * @author Roxanna
 */
public class Dices {
	
	private SimpleIntegerProperty pips1 = new SimpleIntegerProperty();
	private SimpleIntegerProperty pips2 = new SimpleIntegerProperty();
	
	//Constructor
	
	/**
	 * Sets values of pips1 and pips2
	 */
	public Dices() {
		this.pips1.setValue(0);
		this.pips2.setValue(0);
	}
	
	/**
	 * generates int values from 1 to 6 at random
	 * and sets pips1, pips2 and the sum of pips1 and pips2
	 */
	public void roll() {
		Random r = new Random();
		int number1 = 1 + r.nextInt(6);
		int number2 = 1 + r.nextInt(6);
		this.setPips1(number1);
		this.setPips2(number2);
	}
	
	//getter methods
	
	/**
	 * @return pips <br>(the sum of the values of pips1 and pips2)
	 */
	public int getPips() {
		return getPips1() + getPips2();
	}
	
	/**
	 * @return pips1
	 */
	public SimpleIntegerProperty getPips1Property() {
		return pips1;
	}
	
	/**
	 * @return pips2
	 */
	public SimpleIntegerProperty getPips2Property() {
		return pips2;
	}
	
	/**
	 * @return value of pips1
	 */
	public int getPips1() {
		return pips1.getValue();
	}
	
	/**
	 * @return value of pips2
	 */
	public int getPips2() {
		return pips2.getValue();
	}
	
	//setter methods
	
	/**
	 * Sets value of pips1
	 * @param pips
	 */
	public void setPips1(int pips) {
		this.pips1.setValue(pips);
	}
	
	/**
	 * Sets value of pips2
	 * @param pips
	 */
	public void setPips2(int pips) {
		this.pips2.setValue(pips);
	}
}
