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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.Scene.SceneManager;
import com.mygdx.game.GameLayer.Scenes.MainMenuScene;

public class InstructionsScene extends SceneManager {
	private Stage stage;
	private SpriteBatch batch;
	private Texture instructions[];
	private int instructions_num = 4;
	private int page;
	private Skin skin;
	
	public InstructionsScene(Game game) {
		super(game);
		page = 1;
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		
		AssetsManager assetsManager = getAssetManager();
		assetsManager.queueAddSkin();
		assetsManager.getManager().finishLoading();
	}
	
	@Override
	public void show() {
		instructions = new Texture[instructions_num];
		instructions[0] = new Texture(Gdx.files.internal("instructions/move.png"));
		instructions[1] = new Texture(Gdx.files.internal("instructions/shoot.png"));
		instructions[2] = new Texture(Gdx.files.internal("instructions/dodge.png"));
		instructions[3] = new Texture(Gdx.files.internal("instructions/aliens.png"));
		
		skin = getAssetManager().getSkin();
		
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		// Fill screen with table
		Table table = new Table();
		table.setFillParent(true);
		table.setDebug(false);
		stage.addActor(table);
		
		// Create menu buttons
		TextButton backButton = new TextButton("Back", skin);
		TextButton homeButton = new TextButton("Home", skin);
		TextButton nextButton = new TextButton("Next", skin);
		
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (page <= 1) {
					page = instructions_num;
				}
				else {
					page -= 1;
				}
			}
		});
		
		nextButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (page >= instructions_num) {
					page = 1;
				}
				else {
					page += 1;
				}
			}
		});
		
		homeButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScene(game));
			}
		});
		
		// Add buttons to table
		table.row().padTop(600);
		table.add(backButton);
		table.add(homeButton);
		table.add(nextButton);
        
		
	}
	
	@Override
	public void render(float deltatime) {
		Gdx.gl.glClearColor(0f, 0.2f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(instructions[page - 1], 0, 0 , 2000, 1300);
		
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
	}
}
