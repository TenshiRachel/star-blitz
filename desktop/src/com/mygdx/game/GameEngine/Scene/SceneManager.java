package com.mygdx.game.GameEngine.Scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.LifeCycleManager;
import com.mygdx.game.GameEngine.AssetsManager;

public abstract class SceneManager implements Screen {
	protected Game game;
	private AssetsManager assetsManager = new AssetsManager();
	
	public SceneManager(Game game) {
		this.game = game;
	}
	
	public AssetsManager getAssetManager(){
		if (assetsManager == null) {
			assetsManager = new AssetsManager();
		}
		return assetsManager;
	}
	
	@Override
	public void show() {
	}
	
	@Override 
	public void render(float deltatime) {
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
	}
	
	@Override
	public void dispose() {
	}
}