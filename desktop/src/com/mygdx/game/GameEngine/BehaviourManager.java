package com.mygdx.game.GameEngine;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.Entity;
import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.mygdx.game.GameLayer.Entities.Player;
import com.mygdx.game.GameLayer.Entities.PlayerBullet;
import com.mygdx.game.GameLayer.Entities.Bullet;
import com.mygdx.game.GameLayer.Entities.Enemy;
import com.mygdx.game.GameLayer.Entities.Green;
import com.mygdx.game.GameLayer.Entities.Yellow;
import com.mygdx.game.GameLayer.Entities.Red;

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
	//still working on this - jake
	public boolean checkAndSpawnBullet(Player player, List<Enemy> enemies, EntityManager entityManager) {
		boolean spawnedBullet = false;
	    for (Enemy enemy : enemies) {
	        if (isPlayerDirectlyBelow(player, enemy)) {
	            entityManager.spawnEnemyBullet(); 
	            spawnedBullet = true;
	        }
	    }
	    return spawnedBullet;
    }
	//still working on this - jake
	public boolean isPlayerDirectlyBelow (Player player, Enemy enemy) {
		float enemyCenterX = enemy.getX() + enemy.getWidth() / 2;
	    float playerCenterX = player.getX() + player.getWidth() / 2;
	    float threshold = 5; 
	    return Math.abs(enemyCenterX - playerCenterX) <= threshold;
	}
	
	public boolean playerNearAlien(Player player, Enemy enemy) {
		float playerX = player.getX();
		float alienX = enemy.getX();
		
		int threshold = 50;
		
		return (playerX + threshold == alienX || playerX - threshold == alienX);
	}
	
	public void updateYellow(Yellow yellow, float deltaTime) {
		// Increase x position till border
		
	}
	
	public void updateRed(Red red, float deltaTime) {
		// Increase x position till border
		
	}
	
	public void updateGreen(Green green, float deltaTime) {
		// Increase x position till border
		
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
	
	public static void handleCollision(CollidableEntity entity1, CollidableEntity entity2) {
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
