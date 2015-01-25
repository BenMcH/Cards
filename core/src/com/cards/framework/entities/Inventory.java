package com.cards.framework.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.BoardGame;
import com.cards.framework.gamestates.GameState;

public class Inventory extends GamePiece {
	private int scroll;
	private ArrayList<GamePiece> entities;
	private static int shownNum;
	private GameState state;
	private BitmapFont font;

	/**
	 * Sets up the inventory.
	 * @param state
	 */
	public Inventory(GameState state) {
		scroll = 0;
		entities = new ArrayList<GamePiece>();
		entities.addAll(Deck.getStandardDeck());
		shownNum = 5;
		this.state = state;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		setLocation(0, 0);
		setSize(100, 100);
	}

	/**
	 * Handles mouse clicks...Needs work. A LOT of work.
	 * Can we get help?
	 * Stephen Hawking?
	 * Pls?
	 */
	public void handleInput() {
		Vector3 input = state.getMousePositionOnScreen();
		//input.y = BoardGame.WINDOW_HEIGHT - input.y;
		if (state.isJustTouched())
			if (input.x >= getLocation().x
					&& input.x <= getLocation().x + getSize().x) {
				if (input.y >= getLocation().y
						&& input.y <= getLocation().y + getSize().y) {
					// CardGame.camera.unproject(input);
					int loc = (int) input.y / 20;
					GamePiece piece = entities.get(scroll + (shownNum - loc - 1));
					piece.centerPiece();
					state.addEntity(piece);
				}

			}
	}

	/**
	 * Scrolls through the list :D
	 * @param scroll
	 */
	public void scroll(int scroll) {
		this.scroll += scroll;
		this.scroll = MathUtils.clamp(this.scroll, 0, entities.size()
				- (shownNum + 1));
	}

	/**
	 * Draws strings up the left side of the screen that specify the entities.
	 */
	@Override
	public void drawPiece(SpriteBatch batch) {
		for (int i = 0; i < shownNum; i++) {
			
			font.draw(batch, entities.get(scroll + i).toString(), 0f,
					getSize().y - (getSize().y / shownNum) * i);
		}
	}

}
