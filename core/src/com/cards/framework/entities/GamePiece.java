package com.cards.framework.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.managers.GameStateManager;

public abstract class GamePiece implements Comparable<GamePiece> {

	// public static ColorPath[] avalibleColors;
	public static final TextureAtlas CARDS_SHEET = new TextureAtlas(
			Gdx.files.internal("cards.pack"));
	private Vector3 location = new Vector3(0, 0, 0);
	private Vector3 size = new Vector3();

	public GamePiece() {

	}

	public abstract void drawPiece();

	public void setSize(float width, float height) {
		this.size.x = width;
		this.size.y = height;
	}

	public Vector3 getSize() {
		return size;
	}

	public Vector3 getLocation() {
		return location;
	}

	public void setLocation(float x, float y) {
		location.set(x, y, GameStateManager.gameState.getNextZ());
	}

	public void setLocation(Vector3 loc) {
		this.location = loc;
	}

	public void move(Vector3 amount) {
		location.x += amount.x;
		location.y += amount.y;
		location.z = amount.z;
	}

	public boolean containsPoint(Vector2 point) {
		Rectangle r = new Rectangle(location.x, location.y, size.x, size.y);
		return r.contains(point);
	}

	@Override
	public int compareTo(GamePiece o) {
		return getLocation().z < o.getLocation().z ? -1 : 1;
	}
}
