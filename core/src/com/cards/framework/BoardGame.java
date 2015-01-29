package com.cards.framework;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cards.framework.gamestates.LocalGame;

/**
 * This is the main class of the LibGDX game that we have created. It will be
 * the only class of this type because of the GameStateManager
 *
 */
public class BoardGame extends Game {

	public static int WINDOW_WIDTH;
	public static int WINDOW_HEIGHT;
	
	public static float contrastRatio = 16f/9f;

	public static SpriteBatch batch;
	public static Sprite boardSprite;

	@Override
	public void create() {
		LocalGame game = new LocalGame();
		setScreen(game);
		//dispose();
	}

}
