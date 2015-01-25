package com.cards.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cards.framework.managers.GameStateManager;

/**
 * This is the main class of the LibGDX game that we have created. It will be
 * the only class of this type because of the GameStateManager
 *
 */
public class BoardGame extends ApplicationAdapter {

	public static int WINDOW_WIDTH;
	public static int WINDOW_HEIGHT;
	public static final int BOARD_WIDTH = 2500;
	public static final int BOARD_HEIGHT = 2500;
	
	public static final float MIN_ZOOM = .5f;
	public static final float MAX_ZOOM = 1.96f;
	public static final float ROTATE_SPEED = .7f;
	public static OrthographicCamera camera;

	public static GameStateManager gsm;
	public static SpriteBatch batch;
	public static Sprite boardSprite;

	@Override
	public void create() {
		WINDOW_WIDTH = Gdx.graphics.getWidth();
		WINDOW_HEIGHT = Gdx.graphics.getHeight();

		boardSprite = new Sprite(new Texture(Gdx.files.internal("board.jpg")));
		// boardSprite.setPosition(0, 0);
		boardSprite.setSize(BOARD_WIDTH, BOARD_HEIGHT);

		camera = new OrthographicCamera();
		;
		camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);
		camera.update();

		gsm = new GameStateManager();
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();
		boardSprite.draw(batch);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
		if (batch.isDrawing())
			batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}
}
