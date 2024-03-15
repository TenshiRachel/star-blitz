package com.mygdx.game.GameLayer.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.Scene.SceneManager;

public class InfoScene extends SceneManager {
	private Stage stage;
	private Image test;
	private String word;
	private SpriteBatch batch;
	private Skin skin;
	private Texture background;
	private Label pauseLabel;

	public InfoScene(Game game, String enemy_word) {
		super(game);
		// TODO Auto-generated constructor stub
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		word = enemy_word;

		//		Gdx.input.setInputProcessor(stage);

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

		test = new Image(new Texture(Gdx.files.internal("background/"+word+".png")));
		//        System.err.println(word);

		// Resume button
		TextButton resumeButton = new TextButton("Continue", skin);
		resumeButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// Call function to resume the game
				resume();
			}
		});

		//Input processing
//		Gdx.input.setInputProcessor(new InputAdapter() {
//			// Return to menu upon pressing Escape button
//			@Override
//			public boolean keyDown(int keyCode) {
//				if (keyCode == Input.Keys.ESCAPE) {
//					resume();
//				}
//				return true;
//			}
//		});

		table.row().pad(60, 250, 0, 250);;
		table.add(test);
		table.row().pad(90, 0, 100, 0);;
		table.add(resumeButton);
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
