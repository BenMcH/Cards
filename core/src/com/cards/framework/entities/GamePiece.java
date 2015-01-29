package com.cards.framework.entities;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

public abstract class GamePiece {
	private Body body;
	private Fixture fixture;

	public GamePiece() {
	}

	/**
	 * Paint the piece onto the screen
	 * 
	 * @param g
	 */
	public abstract void drawPiece(SpriteBatch batch);

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

	public boolean containsPoint(Vector2 point) {
		Array<Fixture> fixtures = getBody().getFixtureList();
		Iterator<Fixture> iterator = fixtures.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().testPoint(point))
				return true;
		}
		return false;
	}

}
