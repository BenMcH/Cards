package com.cards.framework.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cards.framework.CardGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Cards";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.x = -1;
		cfg.y = -1;
		
		cfg.resizable = false;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new CardGame(), cfg);
	}
}
