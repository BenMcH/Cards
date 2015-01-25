package com.cards.framework.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Holds a deck of cards, either standard or custom.
 *
 */
public class Deck {

	private ArrayList<Card> deck;

	/**
	 * Creates a standard 52 card deck
	 */
	public Deck() {
		deck = Deck.getStandardDeck();
	}

	/**
	 * Create a custom deck
	 * 
	 * @param deck
	 */
	public Deck(Card[] deck) {
		this();
		this.deck = new ArrayList<Card>(Arrays.asList(deck));
	}

	/**
	 * Shuffles the deck
	 */
	public void shuffle() {
		Collections.shuffle(deck, new Random(System.nanoTime()));
	}

	/**
	 * Draws a card and returns it. If all cards have been drawn, it will put
	 * them all back into the normal deck and shuffle.
	 * 
	 * @return
	 */
	public Card drawCard() {
		if (deck.size() > 0)
			return deck.remove(0);
		else {
			return null;
		}
	}
	
	/**
	 * Returns the size of the current deck
	 * @return
	 */
	public int getSize() {
		return deck.size();
	}

	/**
	 * Returns an Array List of cards that is a standard deck of cards
	 * 
	 * @return
	 */
	public static ArrayList<Card> getStandardDeck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		for (int card = 1; card <= 13; card++) {
			for (CardSuit suit : CardSuit.values())
				deck.add(new Card(card, suit));
		}
		return deck;
	}
	
	// DEBUG
	public static Card getRandomCard() {
		ArrayList<Card> deck = Deck.getStandardDeck();
		return deck.get(new Random().nextInt(deck.size()));
	}

}
