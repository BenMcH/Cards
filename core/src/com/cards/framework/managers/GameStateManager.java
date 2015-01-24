package com.cards.framework.managers;

import com.cards.framework.CardGame;
import com.cards.framework.gamestates.GameState;
import com.cards.framework.gamestates.LocalPlayState;

/**
 * Manages the current state of the game. This will update what screen is being
 * shown, rendered, and handling input.
 *
 */
public class GameStateManager {

	// current game state
	public static GameState gameState;

	public static final int MENU = 0;
	public static final int PLAY = 1;

	/**
	 * Creates a new GameState manager and defaults to the LocalPlayState
	 */
	public GameStateManager() {
		gameState = new LocalPlayState(this);
	}

	/**
	 * Sets the state to a new GameState
	 * 
	 * @param state
	 */
	public void setState(int state) {

		if (gameState != null)
			gameState.dispose();
		if (state == MENU) {
			// gameState = new MenuState(this);
		}
		if (state == PLAY) {
			gameState = new LocalPlayState(this);
		}
	}

	/**
	 * updates the game logic
	 * 
	 * @param dt
	 */
	public void update(float dt) {
		gameState.update(dt);
	}

	/**
	 * Renders the screen
	 */
	public void draw() {
		gameState.draw(CardGame.batch);
	}

}
