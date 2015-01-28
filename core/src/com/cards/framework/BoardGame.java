package com.cards.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cards.framework.gamestates.GameState;
import com.cards.framework.gamestates.LocalGame;

/**
 * This is the main class of the LibGDX game that we have created. It will be
 * the only class of this type because of the GameStateManager
 *
 */
public class BoardGame extends ApplicationAdapter {

	public static int WINDOW_WIDTH;
	public static int WINDOW_HEIGHT;
	
	public static float contrastRatio = 16f/9f;

	public static SpriteBatch batch;
	public static Sprite boardSprite;

	@Override
	public void create() {
		boardSprite = new Sprite(new Texture(Gdx.files.internal("board.jpg")));
		boardSprite.setPosition(0, 0);
		boardSprite.setSize(LocalGame.BOARD_WIDTH, LocalGame.BOARD_HEIGHT);
		
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		boardSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}
}
