package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.CardGame;
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
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            CardGame.camera.zoom += 0.015;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
            CardGame.camera.zoom -= 0.015;
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
            CardGame.camera.rotate(-1, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            CardGame.camera.rotate(1, 0, 0, 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
        	addEntity(new Card(10, CardSuit.CLUBS));
        }
        
        float effectiveViewportWidth = CardGame.camera.viewportWidth * CardGame.camera.zoom;
        float effectiveViewportHeight = CardGame.camera.viewportHeight * CardGame.camera.zoom;

        CardGame.camera.zoom = MathUtils.clamp(CardGame.camera.zoom, 0.1f, CardGame.BOARD_HEIGHT/CardGame.camera.viewportWidth);
        CardGame.camera.position.x = MathUtils.clamp(CardGame.camera.position.x, effectiveViewportWidth / 2f, (CardGame.BOARD_WIDTH - 1) - effectiveViewportWidth / 2f);
        CardGame.camera.position.y = MathUtils.clamp(CardGame.camera.position.y, effectiveViewportHeight / 2f, (CardGame.BOARD_HEIGHT - 1) - effectiveViewportHeight / 2f);
		
		if (isJustTouched()) {
			Vector3 touchLoc = new Vector3(getMousePosition(), 0);
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
	public void draw(SpriteBatch batch) {
		for (GamePiece piece : getEntities())
			piece.drawPiece(batch);
	}

	@Override
	public void dispose() {

	}

}
