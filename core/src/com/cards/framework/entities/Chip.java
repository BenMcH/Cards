package com.cards.framework.entities;

import com.badlogic.gdx.math.Vector3;

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
		this.value = value;
		// TODO: Make it take a color argument instead.
	}

	/**
	 * Sets the color of the chip based on it value.
	 * 
	 * @param value
	 */

	// Getters //

	public int getValue() {
		return value;
	}

	/**
	 * This method is what is used to draw the piece from the Game State
	 */
	@Override
	public void drawPiece() {
	}

	/**
	 * returns the size of the Game Piece
	 */
	@Override
	public Vector3 getSize() {

		return null;
	}

}
