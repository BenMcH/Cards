package com.cards.framework.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cards.framework.interfaces.Rollable;

/**
 * Adds the dice game piece for games like Yahtzee or Monopoly. TODO: Allow
 * custom dice.
 * 
 *
 */

public class Dice extends GamePiece implements Rollable {
	private int dice;
	private int size;

	public static final int[] availableSizes = { 6 };

	/**
	 * If object is given no attributes, if creates a default white, 6-sided
	 * die.
	 */
	public Dice() {
		dice = 1;
		size = 6;

	}

	/**
	 * Creates the die with the given color and size.
	 * 
	 * @param size
	 * @param color
	 */
	public Dice(int size) {
		this();
		this.size = size;
	}

	/**
	 * Documentation for these are in the implemented interface "Rollable"
	 */
	@Override
	public int getNumberOfPossibleOutcomes() {
		return size;
	}

	@Override
	public int Roll() {
		Random side = new Random();
		dice = side.nextInt(size) + 1;
		return dice;
	}

	@Override
	public void drawPiece(SpriteBatch batch) {
	}

}
