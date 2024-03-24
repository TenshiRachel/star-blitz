package com.mygdx.game.GameLayer.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.IOManager;
import com.mygdx.game.LifeCycleManager;
import com.mygdx.game.GameEngine.Collision.CollisionManager;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.mygdx.game.GameEngine.Scene.SceneManager;
import com.mygdx.game.GameLayer.Entities.Player;

public class LevelScene extends SceneManager {
	private SpriteBatch batch;
	private Texture background, health;
	private String scoreText = "Score: ";
	private BitmapFont font;
	private GlyphLayout layout;
	public int currentScore, highScore;
	private Music playingSong;
	private Music playerDieSound;
	private AudioSettings audioSettings = new AudioSettings();
	
	private float swaptimer = 0;
    private float swapInterval = 5; // 5 seconds
    private float bulletTimer = 0;
	
	public IOManager ioManager = IOManager.getInstance();
	public BehaviourManager behaviourManager = BehaviourManager.getInstance();
	public EntityManager entityManager = EntityManager.getInstance();
	public CollisionManager collisionManager = CollisionManager.getInstance();
	
	public LevelScene(Game game) {
		super(game);
		batch = new SpriteBatch();
		entityManager.create();
		background = new Texture(Gdx.files.internal("background/space.png"));
		health = new Texture(Gdx.files.internal("entities/heart.png"));
		font = new BitmapFont();
		layout = new GlyphLayout();
	}
	
	
	@Override
	public void show() {
        AssetsManager.queueLevelMusic();
        AssetsManager.queuePlayerDieMusic();
        AssetsManager.getManager().finishLoading();
        playerDieSound = AssetsManager.getManager().get(AssetsManager.playerDieSound);
        playingSong = AssetsManager.getManager().get(AssetsManager.playingSongPath);
        
        playingSong.setVolume(audioSettings.getAudioVolume());
        playingSong.setLooping(true);
        if (audioSettings.isAudioEnabled()) {
            playingSong.play();
        }
		// On create
		Gdx.input.setInputProcessor(new InputAdapter() {
			// Return to menu upon pressing Escape button 
			@Override
			public boolean keyDown(int keyCode) {
				if (keyCode == Input.Keys.ESCAPE) {
					pause();
				}
				return true;
			}
		});

		entityManager.spawnEnemy();

	}
	
	@Override
	public void render(float deltaTime) {
		// When running
		ScreenUtils.clear(0, 0.2f, 0, 0);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		swaptimer += deltaTime;
		bulletTimer += deltaTime;
		
		
		// Spawn entities (Create objects)
		entityManager.spawnEnemyBullet(deltaTime);
		
		
		// Update entities
		entityManager.updatePlayerBullet(deltaTime);
		entityManager.updateEnemies(deltaTime);
		entityManager.updateEnemyBullet(deltaTime);
		entityManager.updateGreenBullet(deltaTime);
		entityManager.updateYellowBullet(deltaTime);

		
		// entityManager.swapEnemyRow(deltaTime);
	    if (swaptimer >= swapInterval) 
	    {
	    	entityManager.swapEnemyRow(deltaTime);
	        swaptimer = 0; // Reset the timer
	    }
	    
		
		if (entityManager.getPlayer().getPlayerHealth() == 0)
		{
			// Get current score
			currentScore = entityManager.getPlayer().getScore();
			playerDieSound.setVolume(audioSettings.getSoundVolume());
	        if (audioSettings.isSoundEnabled()) {
	        	playerDieSound.play();
	        }
			// Proceed to Game Over Scene once player is dead
			game.setScreen(new GameOverScene(game, currentScore, highScore));
			System.out.println("Player is dead");
		}
		
		if (entityManager.getEnemyList().isEmpty() == true) {
			// Proceed to Game Over Scene once player has killed all enemies
			entityManager.respawnEnemies();
			entityManager.spawnEnemy();
		}
		
		
		if (entityManager.spaceWordExist() == 0)
		{
			entityManager.respawnEnemies();
			entityManager.spawnEnemy();
		}		
	    
	    // Show score on top-right hand corner of screen 
	    float x = Gdx.graphics.getWidth() - layout.width - 200;
	    float y = Gdx.graphics.getHeight() - 50;
	    currentScore = entityManager.getPlayer().getScore();
	    scoreText = "Score: " + currentScore;
	    
	    font.setColor(255, 255, 255, 255);
	    font.draw(batch, scoreText, x, y);
	    font.getData().setScale(2, 2);
	    
	    // Display enemy words
	    for (int i = 0; i < entityManager.getEnemyList().size(); i++)
		{
	    	layout = new GlyphLayout(font, entityManager.getEnemyList().get(i).getEnemyWord());
	    	float width = layout.width;
	    	x = entityManager.getEnemyList().get(i).getX() + (entityManager.getEnemyList().get(i).getWidth() - width) / 2;
	    	y = entityManager.getEnemyList().get(i).getY() + entityManager.getEnemyList().get(i).getHeight() + 30;
	    	
	    	font.draw(batch, entityManager.getEnemyList().get(i).getEnemyWord(), x, y);
		}
	    
		// Render entities
		entityManager.renderPlayer(batch);
		entityManager.renderPlayerBullet(batch);
		entityManager.renderEnemies(batch);
		entityManager.renderEnemyBullet(batch);
		entityManager.renderHearts(batch);
		
		// Let IO Manager handle inputs
		ioManager.handleInput(entityManager.getPlayer());
		
		// Collisions
		collisionManager.collideBorder(entityManager.getPlayer(), -30, 30, 30, -30);
		collisionManager.collideAlien(entityManager.getPlayer(), game);
		collisionManager.collidePlayer(entityManager.getPlayer());
		
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void pause() {
		game.setScreen(new PauseScene(game));
	}
	
	@Override
	public void resume() {
		game.setScreen(new LevelScene(game));
	}
	
	@Override
	public void hide() {
		super.hide();
		playingSong.stop();
	}
	
	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
		background.dispose();
		
	}
}
