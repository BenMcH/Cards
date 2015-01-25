package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.cards.framework.BoardGame;
import com.cards.framework.entities.Card;
import com.cards.framework.entities.CardSuit;
import com.cards.framework.entities.GamePiece;
import com.cards.framework.entities.Inventory;
import com.cards.framework.managers.GameStateManager;
import com.cards.framework.processors.GameInputProcessor;

/**
 * This is the local game. It will allow users to interact with GamePiece
 * objects and play with them.
 *
 */
public class LocalPlayState extends GameState {
	private Vector3 lastTouch;
	private GamePiece piece;
	private GameInputProcessor processor;
	public static int LIFT_HEIGHT = 3;
	private Inventory inventory;

	public LocalPlayState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		Gdx.input.setInputProcessor((processor = new GameInputProcessor(this)));
		addEntity(new Card(1, CardSuit.SPADES));
		addEntity(new Card(2, CardSuit.HEARTS));
		getEntities().get(1).move(new Vector3(50, 50, GameState.getNextZ()));
		inventory = new Inventory(this);
		processor.setInventoryObj(inventory);
	}

	@Override
	public void handleInput(float deltaTime) {
		BoardGame.camera.translate(processor.getTranslateX(),
				processor.getTranslateY(), 0);
		BoardGame.camera.zoom += processor.getZoom();
		BoardGame.camera.zoom = MathUtils.clamp(BoardGame.camera.zoom,
				BoardGame.MIN_ZOOM, BoardGame.MAX_ZOOM);

		BoardGame.camera.rotate(processor.getRotation(), 0, 0, 1);
		BoardGame.camera.rotate(processor.getRotation(), 0, 0, 1);
		if (processor.getInventory())
			inventory.handleInput();
		if (isHeld(deltaTime)) {
			Vector3 touchLoc = (getMousePositionWithinCamera());
			if (piece == null)
				piece = getTopEntityAtPosition(touchLoc);

			if (piece != null) {
				piece.move(new Vector3(touchLoc.x - lastTouch.x, touchLoc.y
						- lastTouch.y, GameState.getNextZ()));

				if (piece instanceof Card) {
					if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
						((Card) piece).flipCard();
					}
				}
			}

		} else
			piece = null;

		lastTouch = getMousePositionWithinCamera();

	}

	@Override
	public void update(float deltaTime) {
		handleInput(deltaTime);
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (GamePiece piece : getEntities())
			piece.draw(batch);
		batch.end();

		if (processor.getInventory()) {
			ShapeRenderer shapeRenderer = new ShapeRenderer();
			shapeRenderer.setAutoShapeType(true);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.rect(inventory.getLocation().x,
					inventory.getLocation().y, inventory.getSize().x,
					inventory.getSize().y);
			shapeRenderer.end();
			SpriteBatch batch1 = new SpriteBatch(1);
			batch1.begin();
			inventory.draw(batch1);
			batch1.end();
		}

	}

	@Override
	public void dispose() {

	}

}
