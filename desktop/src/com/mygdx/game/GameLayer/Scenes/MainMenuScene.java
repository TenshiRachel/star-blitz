package com.mygdx.game.GameLayer.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.Scene.SceneManager;

public class MainMenuScene extends SceneManager {
	private Stage stage;
	private Skin skin;
	private SpriteBatch batch;
	private Texture background;
	private Music music;
	private AudioSettings audioSettings = new AudioSettings();
	
	public MainMenuScene(Game game) {
		super(game);
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/menu.wav"));
	}
	
	@Override
	public void show() {
		music.setVolume(audioSettings.getAudioVolume());
		music.play();
		
		background = new Texture(Gdx.files.internal("background/space.png"));
		
		skin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		// Fill screen with table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(false);
		stage.addActor(table);
		
		// Create menu buttons
		TextButton startGame = new TextButton("Start", skin);
		TextButton exit = new TextButton("Quit", skin);
		TextButton options = new TextButton("Options", skin);
		
		// Add buttons to table
		table.add(startGame).fillX().uniformX();
		table.add(exit).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(options).fillX().uniformX();
		
		// Button event listeners
		startGame.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LevelScene(game));
				music.stop();
			}
		});
		
		exit.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		
		options.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new PreferencesScene(game));
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
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		music.dispose();
	}
}
