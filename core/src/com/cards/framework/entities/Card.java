package com.cards.framework.entities;

import java.awt.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.cards.framework.gamestates.GameState;

/**
 * A class for holding any standard card. Will have a child class to hold custom
 * cards.
 * 
 *
 */
public class Card extends GamePiece {
	public static final TextureAtlas CARDS_SHEET = new TextureAtlas(Gdx.files.internal("cards.pack"));

	private int value;
	private CardSuit suit;
	private boolean front;
	private Sprite frontSprite;
	private Sprite backSprite;

	public Card(int value, CardSuit suit) {
		this.value = value;
		this.suit = suit;
		front = true;
		BodyDef body = new BodyDef();
		body.type = BodyType.DynamicBody;
		
		PolygonShape cardShape = new PolygonShape();
		cardShape.setAsBox(.07f/2, .09f/2);
		
		FixtureDef def = new FixtureDef();
		def.density = .25f;
		def.shape = cardShape;
		def.friction = .25f;
		def.restitution = 0.1f;
		frontSprite = CARDS_SHEET.createSprite("card" + suit.getSuit() + getDisplayValue());
		backSprite = CARDS_SHEET.createSprite("cardBack_red1");

		setBody(GameState.world.createBody(body));
		setFixture(getBody().createFixture(def));
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
	public void filpCard() {
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
	public void drawPiece(Graphics g) {
	}

}
