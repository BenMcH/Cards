package com.cards.framework.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cards.framework.interfaces.Rollable;

public class Coin extends GamePiece implements Rollable {
	private int coin;
	public static final int HEADS = 0, TAILS = 1;

	public Coin() {
		super();
		coin = 0;
	}

	/**
	 * Documentation for these methods in Rollable
	 */
	@Override
	public int getNumberOfPossibleOutcomes() {
		return 2;
	}

	@Override
	public int Roll() {
		Random side = new Random();
		coin = side.nextInt(2);
		return coin;
	}

	/**
	 * Returns heads (0) or tails (1)
	 * 
	 * @return
	 */
	public int getCoin() {
		return coin;
	}

	@Override
	public void drawPiece(SpriteBatch batch) {
	}

}
