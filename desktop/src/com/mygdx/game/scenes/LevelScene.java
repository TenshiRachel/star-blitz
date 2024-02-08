package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StarBlitz;

public class LevelScene extends SceneManager {
	private Stage stage;
	private SpriteBatch batch;
	private Texture background;
	
	public LevelScene(StarBlitz game) {
		super(game);
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
	}
	
	@Override
	public void show() {
		// When scene running
		background = new Texture(Gdx.files.internal(""));
		
		stage.clear();
		
		batch.begin();
		
		batch.end();
	}
	
	@Override
	public void render(float deltatime) {
		// On create
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.draw(background, 0, 0, 800, 600);
		
		batch.end();
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
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
