package com.mygdx.game.GameEngine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.LifeCycleManager;
import com.mygdx.game.GameLayer.Entities.Player;
import com.mygdx.game.GameEngine.Entities.EntityManager;

public class IOManager {
	private static IOManager instance;
	private LifeCycleManager game;
	private EntityManager entitymanager = EntityManager.getInstance();
	
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

		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			entitymanager.spawnPlayerBullet();
		}
	}
	
    public static void resetInstance() {
        instance = null;
    }
}
