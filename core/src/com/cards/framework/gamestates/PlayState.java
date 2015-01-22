package com.cards.framework.gamestates;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.entities.Card;
import com.cards.framework.entities.CardSuit;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.managers.GameStateManager;

public class PlayState extends GameState {
	ArrayList<GamePiece> cards;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		cards = new ArrayList<GamePiece>();
		cards.add(new Card(1, CardSuit.SPADES));
	}

	@Override
	public void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
			for (GamePiece card : cards) {
				if(card instanceof Card){
					((Card)card).flipCard();
				}
			}
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
	}

	@Override
	public void draw() {
		for (GamePiece piece : cards)
			piece.drawPiece();
	}

	@Override
	public void dispose() {

	}

}
