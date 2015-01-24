package com.cards.framework.entities;

import java.util.Random;

import com.badlogic.gdx.math.Vector3;

/**
 * Coin creates a coin that can be flipped and shown.
 *
 */
public class Coin extends GamePiece implements Rollable {
	private int side;
	public static final int HEADS = 0, TAILS = 1;

	/**
	 * Creates a coin with the default of heads being up
	 */
	public Coin() {
		super();
		side = 0;
	}

	@Override
	public int getNumberOfPossibleOutcomes() {
		return 2;
	}

	@Override
	public int roll() {
		Random ran = new Random();
		side = ran.nextInt(2);
		return side;
	}

	/**
	 * Returns heads (0) or tails (1)
	 * 
	 * @return
	 */
	public int getCoin() {
		return side;
	}

	@Override
	public void drawPiece() {
	}
	
	@Override
	public Vector3 getSize() {

		return null;
	}

}
