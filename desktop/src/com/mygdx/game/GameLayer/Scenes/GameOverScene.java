package com.mygdx.game.GameLayer.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.mygdx.game.GameEngine.Scene.SceneManager;
import com.badlogic.gdx.Preferences;

public class GameOverScene extends SceneManager{
	private Stage stage;
	private Skin skin;
	private SpriteBatch batch;
	private Texture background;
	private Label titleLabel;
	private Label currentScoreLabel;
	private Label highScoreLabel;
	private Label currentScoreValueLabel;
	private Label highScoreValueLabel;
	private Music playingSong;
	private AudioSettings audioSettings = new AudioSettings();
	public int currentScore, highScore;
	
	public EntityManager entityManager = EntityManager.getInstance();

	public GameOverScene(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
	}
	
	@Override
	public void show() {
        AssetsManager.queueEndMusic();
        AssetsManager.getManager().finishLoading();
        playingSong = AssetsManager.getManager().get(AssetsManager.gameOverPath);
        playingSong.setVolume(audioSettings.getAudioVolume());
        playingSong.setLooping(true);
        if (audioSettings.isAudioEnabled()) {
            playingSong.play();
        }
		
		background = new Texture(Gdx.files.internal("background/space.png"));
		
		skin = getAssetManager().getSkin();
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		// Fill screen with table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(false);
		stage.addActor(table);
		
		//Get high score
		Preferences prefs = Gdx.app.getPreferences("spacegame");
		this.highScore = prefs.getInteger("highscore", entityManager.getPlayer().getScore());
		
		if (currentScore > highScore) {
			prefs.putInteger("highscore", currentScore);
			prefs.flush();
		}
		
		titleLabel = new Label( "Game Over", skin );
		currentScoreLabel = new Label ("Score:", skin);
		highScoreLabel = new Label ("Highest Score:", skin);
		currentScoreValueLabel = new Label(String.valueOf(currentScore), skin);
		highScoreValueLabel = new Label(String.valueOf(highScore), skin);
		
		
		// Create menu buttons
		TextButton retry = new TextButton("Try Again", skin);
		TextButton mainMenu = new TextButton("Main Menu", skin);
		
		// Add labels
		table.add(titleLabel);
        table.row().pad(5, 0, 5, 0);;
        table.add(currentScoreLabel);
        table.add(currentScoreValueLabel);
        table.row().pad(5, 0, 5, 0);;
        table.add(highScoreLabel);
        table.add(highScoreValueLabel);
        table.row().pad(5, 0, 5, 0);;
		
		// Add buttons to table
		table.add(retry).fillX().uniformX();
		table.add(mainMenu).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        
		
		// Button event listeners
		retry.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LevelScene(game));
			}
		});
		
		mainMenu.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScene(game));
			}
		});
	}
	
	@Override
	public void render(float deltatime) {
		Gdx.gl.glClearColor(0f, 0.2f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(background, 0, 0 , 2000, 1300);
		
		batch.end();
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		stage.draw();
	}
	
	@Override 
	public void resize(int width, int height) {
		// Resize stage
		stage.getViewport().update(width, height, true);
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
		playingSong.stop();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

}
