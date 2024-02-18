package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Droplet;
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
	
	public void updateDroplets(Droplet droplet, float deltaTime) {
		// Decrease droplet y position based on its speed
		droplet.setY(droplet.getY() - droplet.getSpeed());
		
		// Check if droplet has reached the bottom of the screen
		if (droplet.getY() + droplet.getHeight() < 0) {
			// Reset droplet y position to top of screen
			droplet.setY(Gdx.graphics.getHeight());
			// Set droplet x position at random, - droplet.getwidth is so that the droplets will not be cut off from the screen
			droplet.setX(MathUtils.random(0, Gdx.graphics.getWidth() - droplet.getWidth()));
			// If speed less than 10, + 2
			if (droplet.getSpeed() < 10) {
				droplet.setSpeed(Math.min(droplet.getSpeed()+ 2f , 10f));
			}
		}
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
		
		if (player.getX() > Gdx.graphics.getWidth() - player.getWidth() / 2 - os_right) {
			player.setX(Gdx.graphics.getWidth() - player.getWidth() / 2 - os_right);
		}
		
		if (player.getY() > Gdx.graphics.getHeight() - player.getHeight() / 2 - os_top) {
			player.setY(Gdx.graphics.getHeight() - player.getHeight() / 2 - os_top);
		}
		
		if (player.getY() < player.getHeight() / 2 + os_bottom) {
			player.setY((player.getHeight() / 2) + os_bottom);
		}
	}
	
}
