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
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.IOManager;
import com.mygdx.game.GameEngine.Collision.CollisionManager;
import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.mygdx.game.GameEngine.Scene.SceneManager;

public class MainMenuScene extends SceneManager {
	private Stage stage;
	private Skin skin;
	private SpriteBatch batch;
	private Texture background;
	private Music playingSong;
	private AudioSettings audioSettings = new AudioSettings();
	
	public MainMenuScene(Game game) {
		super(game);
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		
		AssetsManager assetsManager = getAssetManager();
		assetsManager.queueAddSkin();
		assetsManager.getManager().finishLoading();
		EntityManager.resetInstance();
		//BehaviourManager.resetInstance();
		CollisionManager.resetInstance();
		//IOManager.resetInstance();
	}
	
	@Override
	public void show() {
        AssetsManager.queueMenuMusic();
        AssetsManager.getManager().finishLoading();
        playingSong = AssetsManager.getManager().get(AssetsManager.menuSongPath);
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
		
		// Create menu buttons
		TextButton startGame = new TextButton("Start", skin);
		TextButton exit = new TextButton("Quit", skin);
		TextButton options = new TextButton("Options", skin);
		TextButton records = new TextButton("Records", skin);
		
		// Add buttons to table
		table.add(startGame).fillX().uniformX();
		table.add(exit).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(options).fillX().uniformX();
        table.add(records).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        
		
		// Button event listeners
		startGame.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LevelScene(game));
				playingSong.stop();
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
		
		records.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new ScoreScene(game));
				playingSong.stop();
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
		AssetsManager.getManager().dispose();
	}
	
	
}
