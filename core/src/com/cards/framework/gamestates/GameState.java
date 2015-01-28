package com.cards.framework.gamestates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameState implements Screen {
	private OrthographicCamera camera;
	public static World world;
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
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
