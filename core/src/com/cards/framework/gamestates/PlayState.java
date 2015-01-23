package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.entities.Card;
import com.cards.framework.entities.CardSuit;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.managers.GameStateManager;

public class PlayState extends GameState {

	public PlayState(GameStateManager gsm) {
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
			Vector2 touchLoc = new Vector2(Gdx.input.getX(), Gdx.input.getY());
			GamePiece piece = getTopEntityAtPosition(touchLoc);
			if (piece == null)
				return;
			if (piece instanceof Card){
				//((Card) piece).flipCard();
				piece.move(new Vector3(0,0,GameStateManager.gameState.getNextZ()));
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
