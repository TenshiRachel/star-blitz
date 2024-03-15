package com.mygdx.game.GameLayer.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.mygdx.game.GameEngine.Scene.SceneManager;

public class PauseScene extends SceneManager {
	private Stage stage;
	private SpriteBatch batch;
	private Skin skin;
	private Texture background;
	private int currentScore;
	
	public PauseScene(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		
		AssetsManager assetsManager = getAssetManager();
		assetsManager.queueAddSkin();
		assetsManager.getManager().finishLoading();
	}
	
	
	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("background/space.png"));
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setFillParent(true);
		
		stage.addActor(table);
		
		skin = getAssetManager().getSkin();
		
        
		// Resume button
        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	// Call function to resume the game
            	resume();
            }
        });
        
        // Settings button
        TextButton settingsButton = new TextButton("Settings", skin);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	game.setScreen(new SettingsScene(game));
            }
        });
        
        // Main Menu Button
        TextButton mainMenuButton = new TextButton("Main Menu", skin);
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	game.setScreen(new MainMenuScene(game));
            }
        });
        
		
        table.row().pad(5, 0, 5, 0);;
        table.add(resumeButton);
        table.row().pad(5, 0, 5, 0);;
        table.add(settingsButton);
        table.row().pad(5, 0, 5, 0);;
        table.add(mainMenuButton);
	}

	
	@Override
	public void render(float deltatime) {
		Gdx.gl.glClearColor(0f, 0.2f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(background, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
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
		game.setScreen(new LevelScene(game));
	}
	
	@Override
	public void hide() {
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
