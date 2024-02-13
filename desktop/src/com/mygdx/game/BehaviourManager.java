package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityManager;

public class BehaviourManager {
	private static BehaviourManager instance;
	private Vector2 pos;
	
	private BehaviourManager() {
		
	}
	
	public static synchronized BehaviourManager getInstance() {
		if (instance == null) {
			instance = new BehaviourManager();
		}
		return instance;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public void moveUp(Entity entity1) {
		entity1.setY(entity1.getY() + entity1.getSpeed());
	}
	
	public void moveDown(Entity entity1) {
		entity1.setY(entity1.getY() - entity1.getSpeed());
	}
	
	public void moveLeft(Entity entity1) {
		entity1.setX(entity1.getX() - entity1.getSpeed());
	}
	
	public void moveRight(Entity entity1) {
		entity1.setX(entity1.getX() + entity1.getSpeed());
	}
	
	public void handleCollision(Entity entity1, Entity entity2) {
		
	}
}
