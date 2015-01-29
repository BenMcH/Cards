package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cards.framework.BoardGame;
import com.cards.framework.config.KeyBindings;
import com.cards.framework.entities.Card;
import com.cards.framework.entities.CardSuit;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.processors.GameInputProcessor;

public class LocalGame extends GameState {
	public static final float BOARD_WIDTH = 1.5f;
	public static final float BOARD_HEIGHT = 1.5f;
	private Sprite boardSprite;
	private SpriteBatch batch;
	private Card card;

	@Override
	public void init() {
		Gdx.input.setInputProcessor(new GameInputProcessor());
		setCameraSize(1.5f, 1.5f);
		boardSprite = new Sprite(new Texture(Gdx.files.internal("board.jpg")));
		boardSprite.setPosition(0, 0);
		boardSprite.setSize(BOARD_WIDTH * BoardGame.contrastRatio, BOARD_HEIGHT);
		batch = new SpriteBatch();
		card = new Card(1, CardSuit.SPADES);
		addEntity(card);
		addEntity(new Card(10, CardSuit.CLUBS));

	}

	@Override
	public void renderPiece(float delta) {
		batch.setProjectionMatrix(getCamera().combined);
		batch.begin();
		boardSprite.draw(batch);
		for (GamePiece piece : getEntities()) {
			piece.drawPiece(batch);
		}
		batch.end();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void handleInput(float deltaTime) {
		if (Gdx.input.isKeyJustPressed(KeyBindings.FLIP))
			for (GamePiece piece : getEntities()) {
				piece.getBody().applyForce(.5f, .5f, 0, 0, true);
			}
	}

	@Override
	public void update(float deltaTime) {
	}

}
