package com.cards.framework.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.cards.framework.gamestates.GameState;
import com.cards.framework.interfaces.Drawable;

/**
 * GamePiece is a parent class meant to be used to quickly generate different
 * pieces.
 *
 */
public abstract class GamePiece implements Comparable<GamePiece>, Drawable {

	// public static ColorPath[] avalibleColors;
	public static final TextureAtlas CARDS_SHEET = new TextureAtlas(Gdx.files.internal("cards.pack"));
	private Vector3 location = new Vector3(0, 0, 0);
	private Vector3 size = new Vector3();
	// private int rotation = 0;
	private Body body;
	private Fixture fixture;

	/**
	 * Used to draw the game piece from within the class
	 * 
	 * @param batch
	 */
	public abstract void drawPiece(SpriteBatch batch);

	@Override
	public final void draw(SpriteBatch batch) {
		drawPiece(batch);
	}

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
		if (getBody() != null)
			getBody().setTransform(x, y, getBody().getAngle());
		// location.set(x, y, GameState.getNextZ());
	}

	/**
	 * Sets the location based on a Vector3
	 * 
	 * @param loc
	 */
	public void setLocation(Vector3 loc) {
		body.setTransform(new Vector2(loc.x, loc.y), getBody().getAngle());
		// this.location = loc;
	}

	/**
	 * Returns the rotation as a float
	 * 
	 * @return
	 */
	public float getRotation() {
		return getBody().getAngle();
	}

	/**
	 * Will set a rotation for the game piece
	 * 
	 * @param rotation
	 */
	private void setRotation(float rotation) {
		getBody().setTransform(getBody().getPosition(), (rotation * MathUtils.degreesToRadians));
	}

	/**
	 * Will rotate the game piece by a specified amount
	 * 
	 * @param amount
	 */
	public void rotate(float amount) {
		setRotation(getBody().getAngle() + (getBody().getAngle() * MathUtils.degreesToRadians));
	}

	/**
	 * Moves the component by the values in the vector
	 * 
	 * @param amount
	 */
	public void move(Vector2 amount) {
		getBody().setTransform(new Vector2(getBody().getPosition().x, getBody().getPosition().y + amount.y), getBody().getAngle());
	}

	/**
	 * Moves the object by the amounts specified in the three variables
	 * 
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void move(float dx, float dy) {
		move(new Vector2(dx, dy));
	}

	/**
	 * Returns whether or not the game piece contains a specific point on screen
	 * 
	 * @param point
	 * @return
	 */
	public boolean containsPoint(Vector3 point) {
		Rectangle r = new Rectangle();
		Polygon shape = new Polygon();

		if (point.x >= this.location.x && point.x <= location.x + size.x * 2)
			if (point.y >= this.location.y && point.y <= location.y + size.y * 2)
				return true;
		return false;
	}

	@Override
	public int compareTo(GamePiece o) {
		if (o.getLocation().z == getLocation().z)
			return 0;
		return getLocation().z < o.getLocation().z ? -1 : 1;
	}

	/**
	 * This method centers the piece within the current View Port
	 */
	public void centerPiece() {
		setLocation(GameState.camera.unproject(new Vector3((GameState.camera.viewportWidth) / 2, (GameState.camera.viewportHeight) / 2, 0)));
	}

	public Body getBody() {
		return body;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

}
