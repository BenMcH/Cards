package com.cards.framework.processors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.cards.framework.BoardGame;
import com.cards.framework.entities.Deck;
import com.cards.framework.entities.Inventory;
import com.cards.framework.gamestates.GameState;

public class GameInputProcessor implements InputProcessor {
	private float zoom = 0f;
	private float translateX = 0f;
	private float translateY = 0f;
	private float rotation = 0f;
	private boolean inventory;
	private Inventory inventoryObj;
	private GameState state;
	
	public GameInputProcessor(GameState state) {
		this.state = state;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (!inventory)
			switch (keycode) {
			case Keys.CONTROL_LEFT:
				zoom = .01f;
				break;
			case Keys.ALT_LEFT:
				zoom = -.01f;
				break;
			case Keys.A:
				translateX = -30;
				break;
			case Keys.D:
				translateX = 30;
				break;
			case Keys.S:
				translateY = -30;
				break;
			case Keys.W:
				translateY = 30;
				break;
			case Keys.Q:
				rotation = -(BoardGame.ROTATE_SPEED);
				break;
			case Keys.E:
				rotation = BoardGame.ROTATE_SPEED;
				break;
			case Keys.TAB:
				state.addEntity(Deck.getRandomCard());
			}
		return true;
		
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {

		case Keys.CONTROL_LEFT:
			zoom = 0;
			break;
		case Keys.ALT_LEFT:
			zoom = 0;
			break;
		case Keys.A:
			translateX = 0;
			break;
		case Keys.D:
			translateX = 0;
			break;
		case Keys.S:
			translateY = 0;
			break;
		case Keys.W:
			translateY = 0;
			break;
		case Keys.Q:
			rotation = 0;
			break;
		case Keys.E:
			rotation = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		if ((character + "").equalsIgnoreCase("i")) {
			inventory = !inventory;
		}
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (inventory)
			if (inventoryObj != null) {
				inventoryObj.scroll(amount);
			}

		return true;
	}

	public float getZoom() {
		return zoom;
	}

	public float getTranslateX() {
		return translateX;
	}

	public float getTranslateY() {
		return translateY;
	}

	public float getRotation() {
		return rotation;
	}

	public boolean getInventory() {
		return inventory;
	}

	public Inventory getInventoryObj() {
		return inventoryObj;
	}

	public void setInventoryObj(Inventory inventoryObj) {
		this.inventoryObj = inventoryObj;
	}

}
