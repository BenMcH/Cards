package com.cards.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.cards.framework.managers.GameStateManager;

public class CardGame extends ApplicationAdapter {
	
	public static int WIDTH;
	public static int HEIGHT;
	
	public static OrthographicCamera camera;
	
	public static GameStateManager gsm;
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);
		camera.update();
		
		gsm = new GameStateManager();
		gsm.setState(GameStateManager.PLAY);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
	}
}
