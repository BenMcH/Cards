package com.cards.framework.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.gamestates.GameState;

public class Inventory extends GamePiece {
	private int scroll;
	private ArrayList<GamePiece> entities;
	private static int shownNum;
	private GameState state;
	private BitmapFont font;
	private HashMap<String, ArrayList<? extends GamePiece>> menus;
	private boolean isInMenu = false;
	private String menu;

	/**
	 * Sets up the inventory.
	 * 
	 * @param state
	 */
	public Inventory(GameState state) {
		scroll = 0;
		menus = new HashMap<String, ArrayList<? extends GamePiece>>();
		addMenu("Cards", (Deck.getStandardDeck()));
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
	 * Handles mouse clicks...Needs work. A LOT of work. Can we get help?
	 * Stephen Hawking? Pls?
	 */
	public void handleInput() {
		Vector3 input = state.getMousePositionOnScreen();
		// input.y = BoardGame.WINDOW_HEIGHT - input.y;
		if (Gdx.input.isKeyJustPressed(Keys.BACKSPACE))
			isInMenu = false;
		if (state.isJustTouched())
			if (input.x >= getLocation().x && input.x <= getLocation().x + getSize().x) {
				if (input.y >= getLocation().y && input.y <= getLocation().y + getSize().y) {
					// CardGame.camera.unproject(input);
					int loc = (int) input.y / 20;
					if (isInMenu) {
						GamePiece piece = entities.get(scroll + (shownNum - loc - 1));
						piece.centerPiece();
						state.addEntity(piece);
					} else {
						int menu = scroll + (shownNum - loc - 1);
						Set<String> keys = menus.keySet();
						Iterator<String> it = keys.iterator();
						int toMenu = 0;
						while (it.hasNext() && toMenu < menu - 1) {
							it.next();
						}
						if (it.hasNext())
							this.menu = (it.next());
						else
							return;
						isInMenu = true;
					}
				}

			}
	}

	/**
	 * Scrolls through the list :D
	 * 
	 * @param scroll
	 */
	public void scroll(int scroll) {
		this.scroll += scroll;
		if (isInMenu)
			this.scroll = MathUtils.clamp(this.scroll, 0, menus.get(menu)
					.size() - (shownNum + 1));
		else
			this.scroll = MathUtils.clamp(this.scroll, 0, menus.size()
					- (shownNum + 1));

	}

	/**
	 * Draws strings up the left side of the screen that specify the entities.
	 */
	@Override
	public void drawPiece(SpriteBatch batch) {
		if (isInMenu) {
			if (menu == null)
				isInMenu = false;
			for (int i = 0; i < shownNum; i++) {
				font.draw(batch, menus.get(menu).get(scroll + i).toString(),
						0f, getSize().y - (getSize().y / shownNum) * i);
			}
		} else {
			Set<String> set = menus.keySet();
			Iterator<String> iterator = set.iterator();
			for (int i = 0; i < scroll && iterator.hasNext(); i++) {
				iterator.next();
			}
			for (int i = 0; i < shownNum && iterator.hasNext(); i++) {
				font.draw(batch, iterator.next(), 0f, getSize().y
						- (getSize().y / shownNum) * i);
			}

		}

	}

	public void addMenu(String menuName, ArrayList<? extends GamePiece> choices) {
		this.menus.put(menuName, choices);
	}

}
