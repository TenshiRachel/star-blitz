package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.BehaviourManager;
import com.mygdx.game.CollisionManager;
import com.mygdx.game.IOManager;
import com.mygdx.game.StarBlitz;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityManager;
import com.mygdx.game.entities.Player;

public class LevelScene extends SceneManager {
	private SpriteBatch batch;
	private Texture background;
	private String scoreText = "Score: ";
	private BitmapFont font;
	private GlyphLayout layout;
	private int score;
	
	
	public IOManager ioManager = IOManager.getInstance();
	public BehaviourManager behaviourManager = BehaviourManager.getInstance();
	public EntityManager entityManager = EntityManager.getInstance();
	public CollisionManager collisionManager = CollisionManager.getInstance();
	
	public LevelScene(Game game) {
		super(game);
		batch = new SpriteBatch();
		entityManager.create();
		background = new Texture(Gdx.files.internal("background/space.png"));
		font = new BitmapFont();
		layout = new GlyphLayout();
	}
	
	@Override
	public void show() {
		// On create
		Gdx.input.setInputProcessor(new InputAdapter() {
			// Return to menu upon pressing Escape button 
			@Override
			public boolean keyDown(int keyCode) {
				if (keyCode == Input.Keys.ESCAPE) {
					game.setScreen(new MainMenuScene(game));
				}
				return true;
			}
		});

	}
	
	@Override
	public void render(float deltaTime) {
		// When running
		ScreenUtils.clear(0, 0.2f, 0, 0);
		batch.begin();
		
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// Spawn entities (Create objects)
		entityManager.spawnDroplets();
		
		// Update entities
	    entityManager.updateDroplets(deltaTime);
	    
	    // Show score on top-right hand corner of screen 
	    score = entityManager.getPlayer().getScore();
	    scoreText = "Score: " + score;
	    float x = Gdx.graphics.getWidth() - layout.width - 150;
	    float y = Gdx.graphics.getHeight() - 50;
	    
	    font.setColor(255, 255, 255, 255);
	    font.draw(batch, scoreText, x, y);
	    font.getData().setScale(2, 2);
	    
		// Render entities
		entityManager.renderPlayer(batch);
		entityManager.renderDroplets(batch);
		
		// Let IO Manager handle inputs
		ioManager.handleInput(entityManager.getPlayer());
		
		// Collisions
		collisionManager.collideBorder(entityManager.getPlayer(), -30, 30, 30, -30);
		collisionManager.collideDroplet(entityManager.getPlayer());
		
		
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void pause() {
	}
	
	@Override
	public void resume() {
	}
	
	@Override
	public void hide() {
		super.hide();
	}
	
	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
		background.dispose();
	}
}
