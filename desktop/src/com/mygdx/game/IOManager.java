package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.entities.Player;

public class IOManager {
	public static IOManager instance;
	private StarBlitz game;
	
	public void setGame(StarBlitz game) {
		this.game = game;
	}
	
	public IOManager() {
		
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
