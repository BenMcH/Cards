package com.cards.framework.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.cards.framework.gamestates.GameState;
import com.cards.framework.managers.GameStateManager;

/**
 * A class for holding any standard card. Will have a child class to hold custom
 * cards.
 *
 */
public class Card extends GamePiece {
	private int value;
	private CardSuit suit;
	private boolean front;
	private Sprite frontTexture;
	private Sprite backTexture;

	public Card(int value, CardSuit suit) {
		super();
		this.value = value;
		this.suit = suit;
		front = true;
		frontTexture = GamePiece.CARDS_SHEET.createSprite("card" + suit.getSuit() + getDisplayValue());
		backTexture = GamePiece.CARDS_SHEET.createSprite("cardBack_red1");

		setSize(.07f, .09f); // Dimensions of a standard playing card are 64mm
								// by 89mm
		frontTexture.setSize(getSize().x * 2, getSize().y * 2);
		frontTexture.setOrigin(frontTexture.getWidth()/2, frontTexture.getHeight()/2);
		backTexture.setSize(getSize().x * 2, getSize().y * 2);
		frontTexture.setOrigin(backTexture.getWidth()/2, backTexture.getHeight()/2);
		BodyDef body = new BodyDef();
		body.position.set(0, 0);
		body.type = BodyType.DynamicBody;

		PolygonShape cardShape = new PolygonShape();
		cardShape.setAsBox(getSize().x, getSize().y);

		FixtureDef fixture = new FixtureDef();
		fixture.friction = .25f;
		fixture.shape = cardShape;
		setBody(GameStateManager.gameState.getWorld().createBody(body));
		setFixture(getBody().createFixture(fixture));
		setLocation(GameState.camera.unproject(new Vector3((GameState.camera.viewportWidth) / 2, (GameState.camera.viewportHeight) / 2, 0)));
		cardShape.dispose();
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
		frontTexture.setPosition(getBody().getPosition().x - frontTexture.getWidth() / 2, getBody().getPosition().y - frontTexture.getHeight() / 2);
		frontTexture.setRotation(getBody().getAngle() * MathUtils.radiansToDegrees);
		backTexture.setPosition(getBody().getPosition().x - backTexture.getWidth() / 2, getBody().getPosition().y - backTexture.getHeight() / 2);
		backTexture.setRotation(getBody().getAngle() * MathUtils.radiansToDegrees);
		if (front)
			frontTexture.draw(batch);
		else
			backTexture.draw(batch);
		// batch.draw(front ? frontTexture : backTexture);
		// batch.draw(front ? frontTexture : backTexture, getLocation().x,
		// getLocation().y);
	}

	@Override
	public Vector3 getSize() {
		return super.getSize();
	}
	

}
