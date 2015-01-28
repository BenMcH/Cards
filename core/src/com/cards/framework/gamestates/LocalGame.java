package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.cards.framework.processors.GameInputProcessor;

public class LocalGame extends GameState {
	public static final float BOARD_WIDTH = 1.5f;
	public static final float BOARD_HEIGHT = 1.5f;
	
	@Override
	public void init() {
		Gdx.input.setInputProcessor(new GameInputProcessor());
		setCameraSize(1.5f, 1.5f);
	}

	@Override
	public void renderPiece(float delta) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void handleInput(float deltaTime) {
	}

	@Override
	public void update(float deltaTime ) {
	}


}
