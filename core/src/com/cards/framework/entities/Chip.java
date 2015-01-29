package com.cards.framework.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class is for the chip game piece that will be used for games like poker
 * or checkers.
 * 
 *
 */

public class Chip extends GamePiece {
	private int value;

	/**
	 * Creates a chip that is assigned an available color then given a default
	 * value.
	 * 
	 * @param value
	 */

	public Chip(int value) {
		super();
		this.value = value;
	}

	// Getters //

	public int getValue() {
		return value;
	}

	@Override
	public void drawPiece(SpriteBatch batch) {
	}

}
