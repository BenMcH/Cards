package com.cards.framework.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.CardGame;
import com.cards.framework.managers.GameStateManager;

/**
 * GamePiece is a parent class meant to be used to quickly generate different
 * pieces.
 *
 */
public abstract class GamePiece implements Comparable<GamePiece> {

	// public static ColorPath[] avalibleColors;
	public static final TextureAtlas CARDS_SHEET = new TextureAtlas(
			Gdx.files.internal("cards.pack"));
	private Vector3 location = new Vector3(0, 0, 0);
	private Vector3 size = new Vector3();

	/**
	 * Used to draw the game piece from within the class
	 * 
	 * @param batch
	 */
	public abstract void drawPiece(SpriteBatch batch);

	/**
	 * Sets the size of the component
	 * 
	 * @param width
	 * @param height
	 */
	public void setSize(float width, float height) {
		this.size.x = width;
		this.size.y = height;
	}

	/**
	 * Returns the size of the component
	 * 
	 * @return size
	 */
	public Vector3 getSize() {
		return size;
	}

	/**
	 * Gets the location of the component
	 * 
	 * @return vector3
	 */
	public Vector3 getLocation() {
		return location;
	}

	/**
	 * Sets the location based on the x and y coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void setLocation(float x, float y) {
		location.set(x, y, GameStateManager.gameState.getNextZ());
	}

	/**
	 * Sets the location based on a Vector3
	 * 
	 * @param loc
	 */
	public void setLocation(Vector3 loc) {
		this.location = loc;
	}

	/**
	 * Moves the component by the values in the vector
	 * 
	 * @param amount
	 */
	public void move(Vector3 amount) {
		location.x += amount.x;
		location.y += amount.y;
		location.z = amount.z;
	}

	/**
	 * Moves the object by the amounts specified in the three variables
	 * 
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void move(float dx, float dy, float dz) {
		move(new Vector3(dx, dy, dz));
	}

	/**
	 * Returns whether or not the game piece contains a specific point on screen
	 * 
	 * @param point
	 * @return
	 */
	public boolean containsPoint(Vector3 point) {

		Vector3 loc = new Vector3(location.x, location.y, 0);
		Vector3 loc2 = new Vector3(location.x + size.x, location.y + size.y, 0);
		CardGame.camera.project(point);
		Gdx.app.log("X", location.x + "-" + (location.x + size.x) + " " + point.x);
		Gdx.app.log("Y", location.y + "-" + (location.y + size.y) + " " + point.y);
		if (point.x >= this.location.x && point.x <= location.x + size.x)
			if (point.y >= this.location.y && point.y <= location.y + size.y)
				return true;
		return false;
	}

	@Override
	public int compareTo(GamePiece o) {
		if (o.getLocation().z == getLocation().z)
			return 0;
		return getLocation().z < o.getLocation().z ? -1 : 1;
	}
}
