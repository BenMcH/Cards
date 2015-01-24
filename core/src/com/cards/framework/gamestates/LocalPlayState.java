package com.cards.framework.gamestates;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.entities.Card;
import com.cards.framework.entities.CardSuit;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.managers.GameStateManager;

/**
 * This is the local game. It will allow users to interact with GamePiece
 * objects and play with them.
 *
 */
public class LocalPlayState extends GameState {

	public LocalPlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		addEntity(new Card(1, CardSuit.SPADES));
		addEntity(new Card(2, CardSuit.HEARTS));
		getEntities().get(1).move(new Vector3(50, 50, 1));
	}

	@Override
	public void handleInput() {
		if (isJustTouched()) {
			Vector2 touchLoc = getMousePosition();
			GamePiece piece = getTopEntityAtPosition(touchLoc);
			if (piece == null)
				return;
			if (piece instanceof Card) {
				// ((Card) piece).flipCard();
				piece.move(new Vector3(0, 0, GameStateManager.gameState
						.getNextZ()));
			}
		}
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
	}

	@Override
	public void draw() {
		for (GamePiece piece : getEntities())
			piece.drawPiece();
	}

	@Override
	public void dispose() {

	}

}
