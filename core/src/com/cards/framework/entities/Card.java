package com.cards.framework.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.CardGame;

/**
 * A class for holding any standard card. Will have a child class to hold custom
 * cards.
 * 
 * @author Ben
 *
 */
public class Card extends GamePiece {
	private int value;
	private CardSuit suit;
	private boolean front;
	private Vector3 location;
	private AtlasRegion frontTexture;
	private AtlasRegion backTexture;

	public Card(int value, CardSuit suit) {
		super();
		this.value = value;
		this.suit = suit;
		front = true;
		frontTexture = GamePiece.CARDS_SHEET.findRegion("card" + suit.getSuit()
				+ getDisplayValue());
		backTexture = GamePiece.CARDS_SHEET.findRegion("cardBack_red1");
		this.location = new Vector3(CardGame.WIDTH / 2
				- frontTexture.originalWidth / 2, CardGame.HEIGHT / 2
				- frontTexture.originalHeight / 2, 0);
	}

	/**
	 * Returns the numerical value of the card (1-13) Ace low
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Returns the string representation of the suit.
	 * 
	 * @return
	 */
	public String getSuit() {
		return suit.getSuit();
	}

	/**
	 * Will flip the current card to its other side
	 */
	public void flipCard() {
		front = !front;
	}

	/**
	 * Checks if the card is facing the front
	 * 
	 * @return
	 */
	public boolean isFront() {
		return front;
	}

	/**
	 * returns the number or letter for display. E.G. "K" for king
	 * 
	 * @return
	 */
	public String getDisplayValue() {
		if (value > 1 && value < 11)
			return value + "";

		switch (value) {
		case (1):
			return "A";
		case (11):
			return "J";
		case (12):
			return "Q";
		case (13):
			return "K";
		default:
			return "";
		}
	}

	@Override
	public String toString() {
		return getDisplayValue() + " of " + getSuit();
	}

	/**
	 * Documentation in the GamePiece Class
	 */
	@Override
	public void drawPiece() {
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		batch.draw(front ? frontTexture : backTexture, location.x, location.y);
		batch.end();
	}

	@Override
	public void setSize(int width, int height) {
	}

	@Override
	public Vector3 getSize() {

		return null;
	}

	public Vector3 getLocation() {
		return location;
	}

	public void setLocation(Vector3 location) {
		this.location = location;
	}

}
