package com.cards.framework.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.cards.framework.BoardGame;
import com.cards.framework.config.KeyBindings;
import com.cards.framework.entities.Card;
import com.cards.framework.entities.CardSuit;
import com.cards.framework.entities.GamePiece;

public class LocalGame extends GameState {
	public static final float BOARD_WIDTH = 1.5f;
	public static final float BOARD_HEIGHT = 1.5f;
	private Sprite boardSprite;
	private SpriteBatch batch;
	private Card card;
	private GamePiece piece;
	private Vector2 lastTouch;
	private MouseJointDef jointDef;
	private MouseJoint joint;
	private Vector2 clickLoc;
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
	private QueryCallback queryCallback = new QueryCallback() {

		@Override
		public boolean reportFixture(Fixture fixture) {
			if (fixture.testPoint(clickLoc)) {
				jointDef.bodyB = fixture.getBody();
				jointDef.target.set(clickLoc);
				joint = (MouseJoint) world.createJoint(jointDef);
			} else
				return true;
			return false;
		}
	};

	@Override
	public void init() {
		Gdx.input.setInputProcessor(this);
		BodyDef def = new BodyDef();
		def.type = BodyType.StaticBody;
		jointDef = new MouseJointDef();
		jointDef.bodyA = getWorld().createBody(def);
		jointDef.collideConnected = true;
		jointDef.maxForce = 1;
		clickLoc = new Vector2();
		setCameraSize(1.5f * BoardGame.contrastRatio, 1.5f);
		boardSprite = new Sprite(new Texture(Gdx.files.internal("board.jpg")));
		boardSprite.setPosition(0, 0);
		boardSprite.setSize(BOARD_WIDTH * BoardGame.contrastRatio, BOARD_HEIGHT);
		batch = new SpriteBatch();
		card = new Card(1, CardSuit.SPADES);
		piece = card;
		addEntity(card);
		addEntity(new Card(10, CardSuit.CLUBS));
		
	}

	@Override
	public void renderPiece(float delta) {
		batch.setProjectionMatrix(getCamera().combined);
		batch.begin();
		boardSprite.draw(batch);
		for (GamePiece piece : getEntities()) {
			piece.drawPiece(batch);
		}
		batch.end();
		//debugRenderer.render(getWorld(), getCamera().combined);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void handleInput(float deltaTime) {
		if (Gdx.input.isKeyJustPressed(KeyBindings.FLIP))
			for (GamePiece piece : getEntities()) {
				piece.getBody().applyForce(.5f, .5f, 0, 0, true);
			}
		if (isHeld(deltaTime)) {
			Vector2 touchLoc = (getMousePositionWithinCamera());
			// if (piece == null) piece = getTopEntityAtPosition(touchLoc);
			if (piece != null) {
				piece.move(new Vector2(touchLoc.x - lastTouch.x, touchLoc.y - lastTouch.y));

				// float clampedX = MathUtils.clamp(piece.getLocation().x, 0,
				// (LocalGame.BOARD_WIDTH - piece.getSize().x));
				// float clampedY = MathUtils.clamp(piece.getLocation().y , 0,
				// (LocalGame.BOARD_HEIGHT - piece.getSize().y));

				// piece.setLocation(clampedX, clampedY);

				// Only for the card game piece
				if (piece instanceof Card) {
					if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
						((Card) piece).flipCard();
					}
				}
				// piece.rotate(processor.getScroll());

			}

		}

		lastTouch = getMousePositionWithinCamera();

	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 loc = getMousePositionWithinCamera();
		clickLoc = getMousePositionWithinCamera();
		getWorld().QueryAABB(queryCallback, loc.x, loc.y, loc.x, loc.y);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (joint == null)
			return false;
		joint.setTarget(getMousePositionWithinCamera());
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(joint == null)
			return false;
		world.destroyJoint(joint);
		joint = null;
		//body.setType(BodyType.StaticBody);
		//body.setType(BodyType.DynamicBody);
		return true;
	}

}
