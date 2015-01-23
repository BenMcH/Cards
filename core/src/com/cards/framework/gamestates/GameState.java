package com.cards.framework.gamestates;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.managers.GameStateManager;

public abstract class GameState {
	private final GameStateManager gameStateManager;
	private int nextZ = 0;
	private ArrayList<GamePiece> entities;
	private boolean touched = false;

	public GameState(GameStateManager gsm) {
		this.gameStateManager = gsm;
		entities = new ArrayList<GamePiece>();
		init();
	}

	public abstract void init();

	public abstract void handleInput();

	public abstract void update(float deltaTime);

	public abstract void draw();

	public abstract void dispose();

	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}

	public int getNextZ() {
		return nextZ++;
	}

	public GamePiece getTopEntityAtPosition(Vector2 vector) {
		GamePiece top = null;
		for (GamePiece piece : entities) {
			if (piece.containsPoint(vector))
				if (top == null)
					top = piece;
				else if (piece.getLocation().z > top.getLocation().z)
					top = piece;
		}
		return top;
	}

	public void addEntity(GamePiece piece) {
		entities.add(piece);
	}

	public ArrayList<GamePiece> getEntities() {
		Collections.sort(entities);
		return entities;
	}

	public boolean isJustTouched() {
		if (Gdx.input.isTouched() && touched)
			return false;
		touched = Gdx.input.isTouched();
		return touched;
	}

}
