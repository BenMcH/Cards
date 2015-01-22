package com.cards.framework.gamestates;

import com.cards.framework.managers.GameStateManager;

public abstract class GameState {
	private final GameStateManager gameStateManager;
	private int nextZ = 0;
	public GameState(GameStateManager gsm) {
		this.gameStateManager = gsm;
		init();
	}

	public abstract void init();

	public abstract void handleInput();

	public abstract void update(float deltaTime);

	public abstract void draw();

	public abstract void dispose();

	public GameStateManager getGameStateManager(){
		return gameStateManager;
	}
	
	public int getNextZ(){
		return nextZ++;
	}
}
