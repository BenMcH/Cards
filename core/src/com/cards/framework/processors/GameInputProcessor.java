package com.cards.framework.processors;

import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {

		return true;
	}

	@Override
	public boolean keyTyped(char character) {

		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return true;
	}

	@Override
	public boolean scrolled(int amount) {

		return true;
	}

}
