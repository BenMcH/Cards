package com.cards.framework.entities;

/**
 * The Rollable interface implements two methods to allow for quick and easy implementation of things that can be rolled or flipped.
 *
 */
public interface Rollable {
	
	/**
	 * Meant for getting number of all available outcomes. 
	 * @return
	 */
	public int getNumberOfPossibleOutcomes();
	
	/**
	 *  Rolls an object that is based on which side it lands on.
	 * @return
	 */
	public int roll();
}
