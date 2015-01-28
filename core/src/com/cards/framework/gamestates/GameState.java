package com.cards.framework.gamestates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.cards.framework.BoardGame;

public abstract class GameState implements Screen {
	private OrthographicCamera camera;
	public static World world;
	
	@Override
	public void show() {
		camera = new OrthographicCamera(1.5f, 1.5f);
		camera.setToOrtho(false, 1.5f, 1.5f);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		BoardGame.batch.setProjectionMatrix(camera.combined);
		world = new World(new Vector2(0,-9.81f), true);
		init();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		dispose();
	}
	
	public OrthographicCamera getCamera(){
		return camera;
	}
	
	public void setCameraSize(float width, float height){
		camera.viewportHeight = height;
		camera.viewportWidth = width;
	}
	
	public World getWorld() {
		return world;
	}
	
	public static float pixelsToMeters(float pixels) {
		return pixels / 800;
	}
	
	public static float metersToPixels(float meters) {
		return meters * 800;
	}
	
	public void render(float deltaTime){
		handleInput(deltaTime);
		update(deltaTime);
		renderPiece(deltaTime);
	}

	public abstract void renderPiece(float deltaTime);

	public abstract void init();

	public abstract void handleInput(float deltaTime);
	public abstract void update(float deltaTime);
}
