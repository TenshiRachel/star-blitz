package com.mygdx.game.GameEngine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.LifeCycleManager;
import com.mygdx.game.GameLayer.Entities.Player;

public class IOManager {
	private static IOManager instance;
	private LifeCycleManager game;
	
	public void setGame(LifeCycleManager game) {
		this.game = game;
	}
	
	private IOManager() {
	}
	
	public static IOManager getInstance() {
		if (instance == null) {
			instance = new IOManager();
		}
		return instance;
	}
	
	public void handleInput(Player player) {
//		if (Gdx.input.isKeyPressed(Keys.W)) {
//			game.behaviourManager.moveUp(player);
//		}
//		
//		if (Gdx.input.isKeyPressed(Keys.S)) {
//			game.behaviourManager.moveDown(player);
//		}
		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			game.behaviourManager.moveRight(player);
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			game.behaviourManager.moveLeft(player);
		}
	}
}
