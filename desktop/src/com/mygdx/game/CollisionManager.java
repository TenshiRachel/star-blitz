package com.mygdx.game;

import com.mygdx.game.entities.EntityManager;

public class CollisionManager {
	public static CollisionManager instance;
	public EntityManager entityManager;
	
	public CollisionManager() {
		
	}
	
	public static CollisionManager getInstance() {
		if (instance == null) {
			instance = new CollisionManager();
		}
		return instance;
	}
	
	public void collide() {
		
	}
}
