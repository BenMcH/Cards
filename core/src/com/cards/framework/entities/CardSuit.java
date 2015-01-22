package com.cards.framework.entities;

/**
 * Handles the suits for a standard deck
 * @author Ben
 *
 */
public enum CardSuit {

	SPADES("Spades"), 
	CLUBS("Clubs"), 
	DIAMONDS("Diamonds"), 
	HEARTS("Hearts");

	private String suit;
	
	/**
	 * Initializes the Enum Object
	 * @param suit
	 */
	private CardSuit(String suit) {
		this.suit = suit;
	}
	
	/**
	 * Returns the suit in a string
	 * @return
	 */
	public String getSuit(){
		return suit;
	}
}
