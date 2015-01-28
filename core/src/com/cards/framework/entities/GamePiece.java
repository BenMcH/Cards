package com.cards.framework.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

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

}
