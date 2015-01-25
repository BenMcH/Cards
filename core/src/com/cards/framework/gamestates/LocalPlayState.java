package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.CardGame;
import com.cards.framework.entities.Card;
import com.cards.framework.entities.CardSuit;
import com.cards.framework.entities.Deck;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.managers.GameStateManager;

/**
 * This is the local game. It will allow users to interact with GamePiece
 * objects and play with them.
 *
 */
public class LocalPlayState extends GameState {
	private Vector3 lastTouch;
	private GamePiece piece;

	public static int LIFT_HEIGHT = 3;

	public LocalPlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		addEntity(new Card(1, CardSuit.SPADES));
		addEntity(new Card(2, CardSuit.HEARTS));
		getEntities().get(1).move(new Vector3(50, 50, GameState.getNextZ()));
	}

	@Override
	public void handleInput(float deltaTime) {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			CardGame.camera.zoom += 0.01;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
			CardGame.camera.zoom -= 0.01;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			CardGame.camera.translate(-30, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			CardGame.camera.translate(30, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			CardGame.camera.translate(0, -30, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			CardGame.camera.translate(0, 30, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			CardGame.camera.rotate(-0.7f, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			CardGame.camera.rotate(0.7f, 0, 0, 1);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			addEntity(Deck.getRandomCard());
		}

		if (isHeld(deltaTime)) {
			Vector3 touchLoc = (getMousePosition());
			if (piece == null)
				piece = getTopEntityAtPosition(touchLoc);
			
			if (piece == null)
				return;

			piece.move(new Vector3(touchLoc.x - lastTouch.x, touchLoc.y
					- lastTouch.y, GameState.getNextZ()));
			
			if (piece instanceof Card) {
				if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
					((Card) piece).flipCard();
				}
			}
			
		} else
			piece = null;

		lastTouch = getMousePosition();

	}

	@Override
	public void update(float deltaTime) {
		handleInput(deltaTime);
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (GamePiece piece : getEntities())
			piece.drawPiece(batch);
	}

	@Override
	public void dispose() {

	}

}
