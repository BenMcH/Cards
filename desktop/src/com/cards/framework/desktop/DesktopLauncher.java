package com.cards.framework.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cards.framework.BoardGame;

public class DesktopLauncher {
	/**
	 * Creates the TableTop Game
	 * @param arg
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Cards";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.x = -1;
		cfg.y = -1;
		
		cfg.foregroundFPS = 60;
		cfg.resizable = false;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new BoardGame(), cfg);
	}
}
