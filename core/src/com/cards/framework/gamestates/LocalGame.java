package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.cards.framework.processors.GameInputProcessor;

public class LocalGame extends GameState {

	@Override
	public void init() {
		Gdx.input.setInputProcessor(new GameInputProcessor());
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
