package com.cards.framework.gamestates;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cards.framework.BoardGame;
import com.cards.framework.entities.GamePiece;

public abstract class GameState implements Screen {
	private OrthographicCamera camera;
	public static World world;
	private Box2DDebugRenderer debugRenderer;
	private ArrayList<GamePiece> entities;
	private boolean touched;
	private boolean sTouched;

	@Override
	public void show() {
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(1.5f, 1.5f);
		camera.setToOrtho(false, 1.5f * BoardGame.contrastRatio, 1.5f);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		if (BoardGame.batch == null)
			BoardGame.batch = new SpriteBatch();
		BoardGame.batch.setProjectionMatrix(camera.combined);
		world = new World(new Vector2(0, 0f), true);
		entities = new ArrayList<GamePiece>();
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

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCameraSize(float width, float height) {
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

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getWorld().step(deltaTime, 6, 2);
		handleInput(deltaTime);
		update(deltaTime);
		renderPiece(deltaTime);
		debugRenderer.render(world, getCamera().combined);
	}

	public abstract void renderPiece(float deltaTime);

	public abstract void init();

	public abstract void handleInput(float deltaTime);

	public abstract void update(float deltaTime);

	public ArrayList<GamePiece> getEntities() {
		return entities;
	}

	public void addEntity(GamePiece piece) {
		entities.add(piece);
	}

	/**
	 * Returns true only on the first loop of the mouse being held.
	 * 
	 * @return
	 */
	public boolean isJustTouched() {
		if (Gdx.input.isTouched() && touched)
			return false;
		touched = Gdx.input.isTouched();
		return touched;
	}

	/**
	 * This is the private isJustTouched method specifically for the isHeld
	 * Method
	 * 
	 * @return
	 */
	private boolean isJustTouchedH() {
		if (Gdx.input.isTouched() && sTouched)
			return false;
		sTouched = Gdx.input.isTouched();
		return sTouched;

	}

	public boolean isHeld(float deltaTime) {
		/*
		 * if(isJustTouchedH()){ heldPiece =
		 * (getTopEntityAtPosition(getMousePositionWithinCamera())); }
		 * if(heldPiece == null) return false; holding = Gdx.input.isTouched();
		 * if (holding) { return true; } heldPiece = null;
		 */
		return false;
	}

	public Vector2 getMousePositionWithinCamera() {
		Vector3 vec =camera.unproject(new Vector3(Gdx.input.getX(),
				Gdx.input.getY(),0)); 
		return new Vector2(vec.x, vec.y);
	}

	public Vector2 getMousePositionOnScreen() {
		return new Vector2(Gdx.input.getX(), BoardGame.WINDOW_HEIGHT - Gdx.input.getY());
	}

}
