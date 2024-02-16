package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityManager;
import com.mygdx.game.entities.Player;

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
	
	public static void handleCollision(Entity entity1, Entity entity2) {
		// Get pos of both obj
		float entity1_x = entity1.getX();
		float entity1_y = entity1.getY();
		float entity2_x = entity2.getX();
		float entity2_y = entity2.getY();
		
		// Make entity recoil back on collision
		if (entity1_x >= entity2_x) {
			entity1.setX(entity1.getX() + entity1.getSpeed());
		}
		
		if (entity1_x < entity2_x) {
			entity1.setX(entity1.getX() + entity1.getSpeed());
		}
		
		if (entity1_y >= entity2_y) {
			entity1.setY(entity1.getY() + entity1.getSpeed());
		}
		
		if (entity1_y < entity2_y) {
			entity1.setY(entity1.getY() - entity1.getSpeed());
		}
	}
	
	public static void handleBorder(Player player, float os_left, float os_right, float os_top, float os_bottom) {
		// If at border, set X or Y to edge of border
		if (player.getX() < player.getWidth() / 2 + os_left) {
			player.setX((player.getWidth()) / 2 + os_left);
		}
		
		if (player.getX() > 800 - player.getWidth() / 2 - os_right) {
			player.setX(800 - player.getWidth() / 2 - os_right);
		}
		
		if (player.getY() > 600 - player.getHeight() / 2 - os_top) {
			player.setY(600 - player.getHeight() / 2 - os_top);
		}
		
		if (player.getY() < player.getHeight() / 2 + os_bottom) {
			player.setY((player.getHeight() / 2) + os_bottom);
		}
	}
}
