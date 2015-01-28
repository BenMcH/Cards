package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
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
		super(gsm, new Vector2(0,0), true);
	}

	@Override
	public void init() {
		Gdx.input.setInputProcessor((processor = new GameInputProcessor(this)));
		addEntity(new Card(1, CardSuit.SPADES));
	//	addEntity(new Card(2, CardSuit.HEARTS));
	//	getEntities().get(1).move(new Vector3(2, 2, GameState.getNextZ()));
		
		//inventory = new Inventory(this);
		//processor.setInventoryObj(inventory);
		
	}

	@Override
	public void handleInput(float deltaTime) {
		GameState.camera.translate(processor.getTranslateX(), processor.getTranslateY(), 0);
		GameState.camera.rotate(processor.getRotation(), 0, 0, 1);
		GameState.camera.rotate(processor.getRotation(), 0, 0, 1);

		float effectiveViewportWidth = GameState.camera.viewportWidth * GameState.camera.zoom;
		float effectiveViewportHeight = GameState.camera.viewportHeight * GameState.camera.zoom;

		GameState.camera.zoom = MathUtils.clamp(GameState.camera.zoom + processor.getZoom(), 0.5f, BoardGame.BOARD_HEIGHT / GameState.camera.viewportWidth);
		GameState.camera.position.x = MathUtils.clamp(GameState.camera.position.x, effectiveViewportWidth / 2f, BoardGame.BOARD_WIDTH - effectiveViewportWidth / 2f);
		GameState.camera.position.y = MathUtils.clamp(GameState.camera.position.y, effectiveViewportHeight / 2f, BoardGame.BOARD_HEIGHT - effectiveViewportHeight / 2f);
		
		if (processor.getInventory()) inventory.handleInput();
		
		if (isHeld(deltaTime)) {
			Vector3 touchLoc = (getMousePositionWithinCamera());
			if (piece == null) piece = getTopEntityAtPosition(touchLoc);

			if (piece != null) {
				piece.move(new Vector2(touchLoc.x - lastTouch.x, touchLoc.y - lastTouch.y));
				
				float clampedX = MathUtils.clamp(piece.getLocation().x, 0, (BoardGame.BOARD_WIDTH - piece.getSize().x));
				float clampedY = MathUtils.clamp(piece.getLocation().y , 0, (BoardGame.BOARD_HEIGHT - piece.getSize().y));
				
				piece.setLocation(clampedX, clampedY);
				
				// Only for the card game piece
				if (piece instanceof Card) {
					if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
						((Card) piece).flipCard();
					}
				}
				int rotate = processor.getScroll();
				System.out.println(piece.getRotation() * MathUtils.radiansToDegrees);
				piece.rotate(rotate);
				
			}

		} else piece = null;

		lastTouch = getMousePositionWithinCamera();
		if(Gdx.input.isKeyJustPressed(Keys.J))
			for(GamePiece p : getEntities()){
				p.getBody().applyForceToCenter(10, 10, true);
			}
	}

	@Override
	public void update(float deltaTime) {
		handleInput(deltaTime);
		getWorld().step(deltaTime, 3, 8);
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
			shapeRenderer.rect(inventory.getLocation().x, inventory.getLocation().y, inventory.getSize().x, inventory.getSize().y);
			shapeRenderer.end();
			
			SpriteBatch batch1 = new SpriteBatch(1);
			
			batch1.begin();
			inventory.draw(batch1);
			batch1.end();
			
		}
		getDebugRenderer().render(getWorld(), GameState.camera.combined);

	}

	@Override
	public void dispose() {

	}

}
