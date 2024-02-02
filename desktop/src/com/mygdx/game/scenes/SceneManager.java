package com.mygdx.game.scenes;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.EntityManager;

public class SceneManager {
	private List<Scene> scenes;
	private EntityManager entityManager;
	
	public SceneManager(EntityManager entityManager) {
		scenes = new ArrayList<>();
		this.entityManager = entityManager;
	}
	
	public void renderLevel(SpriteBatch batch) {		
		entityManager.render(batch);
		entityManager.move();
	}
	
	public void transition() {
		
	}
	
	public void resize(int width, int height) {
		
	}
}
