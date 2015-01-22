package com.cards.framework.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;

public abstract class GamePiece {

	//public static ColorPath[] avalibleColors;
	public static final TextureAtlas CARDS_SHEET = new TextureAtlas(Gdx.files.internal("cards.pack"));
	public GamePiece() {

	}

	public abstract void drawPiece();

	public abstract void setSize(int width, int height);

	public abstract Vector3 getSize();

}
