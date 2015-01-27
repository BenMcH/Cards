package com.cards.framework.gamestates;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cards.framework.BoardGame;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.interfaces.Drawable;
import com.cards.framework.managers.GameStateManager;

/**
 * GameState offers an easy, quick, and efficient way to handle multiple screens
 * within the game without problems of memory management or the like.
 *
 */
public abstract class GameState implements Drawable {
	private final GameStateManager gameStateManager;
	private static int nextZ = 0;
	private ArrayList<GamePiece> entities;
	private boolean touched = false;
	private boolean holding;
	private GamePiece heldPiece;
	private boolean sTouched;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	
	/**
	 * Creates a GameState object that saves its GameStateManager
	 * 
	 * @param gsm
	 */
	public GameState(GameStateManager gsm, Vector2 gravity, boolean sleep) {
		this.gameStateManager = gsm;
		entities = new ArrayList<GamePiece>();
		world = new World(gravity, sleep);
		debugRenderer = new Box2DDebugRenderer();
		init();
	}

	/**
	 * Initializes the game state
	 */
	public abstract void init();

	/**
	 * Handles the user input and acts accordingly
	 */
	public abstract void handleInput(float deltaTime);

	/**
	 * Updates the game logic based on deltaTime
	 * 
	 * @param deltaTime
	 */
	public abstract void update(float deltaTime);

	/**
	 * Draws the Game State
	 */
	public abstract void draw(SpriteBatch batch);

	/**
	 * Disposes of all of the used objects
	 */
	public abstract void dispose();

	/**
	 * returns the gamestatemanager
	 * 
	 * @return
	 */
	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}

	/**
	 * Returns and increments the next z axis
	 * 
	 * @return
	 */
	public static int getNextZ() {
		return nextZ++;
	}

	public static void resetNextZ() {
		nextZ = 0;
	}

	/**
	 * Returns the entity that is on top at a given location
	 * 
	 * @param vector
	 * @return
	 */
	public GamePiece getTopEntityAtPosition(Vector3 vector) {
		Collections.sort(entities);
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).containsPoint(vector))
				return entities.get(i);
		}
		return null;
	}

	/**
	 * adds an entity to the gamestate
	 * 
	 * @param piece
	 */
	public void addEntity(GamePiece piece) {
		entities.add(piece);
	}

	/**
	 * Returns the arraylist of entities
	 * 
	 * @return
	 */
	public ArrayList<GamePiece> getEntities() {
		Collections.sort(entities);
		return entities;
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
	 * This is the private isJustTouched method specifically for the isHeld Method
	 * @return
	 */
	private boolean isJustTouchedH(){
		if (Gdx.input.isTouched() && sTouched)
			return false;
		sTouched = Gdx.input.isTouched();
		return sTouched;
		
	}
	
	public boolean isHeld(float deltaTime) {
		if(isJustTouchedH()){
			heldPiece = (getTopEntityAtPosition(getMousePositionWithinCamera()));
		}
		if(heldPiece == null)
			return false;
		holding = Gdx.input.isTouched();
		if (holding) {
			return true;
		}
		heldPiece = null;
		return false;
	}

	/**
	 * Returns the mouse position in a vector2 object. This coordinate system is
	 * converted to the same coordinate plane as the drawn entities
	 * 
	 * @return
	 */
	public Vector3 getMousePositionWithinCamera() {
		return BoardGame.camera.unproject(new Vector3(Gdx.input.getX(),
				Gdx.input.getY(), 0));
	}
	
	public Vector3 getMousePositionOnScreen(){
		return new Vector3(Gdx.input.getX(), BoardGame.WINDOW_HEIGHT - Gdx.input.getY(), 0);
	}

	public World getWorld() {
		return world;
	}

	public Box2DDebugRenderer getDebugRenderer() {
		return debugRenderer;
	}

}
