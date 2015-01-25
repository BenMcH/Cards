package com.cards.framework.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.BoardGame;

/**
 * A class for holding any standard card. Will have a child class to hold custom
 * cards.
 *
 */
public class Card extends GamePiece {
	private int value;
	private CardSuit suit;
	private boolean front;
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

		setLocation(BoardGame.camera.unproject(new Vector3((BoardGame.camera.viewportWidth) / 2, (BoardGame.camera.viewportHeight) / 2, 0)));

		setSize(frontTexture.originalWidth, frontTexture.originalHeight);
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

	@Override
	public void drawPiece(SpriteBatch batch) {
		batch.draw(front ? frontTexture : backTexture, getLocation().x,
				getLocation().y);
	}

	@Override
	public Vector3 getSize() {

		return null;
	}

}
