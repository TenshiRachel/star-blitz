package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.EntityManager;
import com.mygdx.game.entities.Player;
import com.mygdx.game.scenes.SceneManager;

public class Game extends ApplicationAdapter {
	private SceneManager sceneManager;
	private EntityManager entityManager;
	private SpriteBatch batch;
	private Color color;
	
	@Override
	public void create() {
		entityManager = new EntityManager();

		Player player = new Player("bucket.png", 300, 20, 200);
		entityManager.addEntity(player);
		
		sceneManager = new SceneManager(entityManager);
		
		batch = new SpriteBatch();
	}
	
	@Override 
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		sceneManager.renderLevel(batch);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
