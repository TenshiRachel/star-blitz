package com.mygdx.game.GameEngine.Collision;

import java.util.List;

import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameLayer.Entities.Player;

public class CollisionManager {
	private static CollisionManager instance;
	private EntityManager entityManager = EntityManager.getInstance();
	
	private CollisionManager() {
	}
	
	public static synchronized CollisionManager getInstance() {
		if (instance == null) {
			instance = new CollisionManager();
		}
		return instance;
	}

	
	public void collideAlien() {
		// get alien and bullet lists, loop through aliens and check if bullet collide
		// if collide, remove bullet and alien, play alien_hit sound
	}
	
	public void collidePlayer(Player player) {
		
	}
	
	public void collideBorder(Player player) {
		if (player.detectBorder(player, 0, 0, 0, 0)) {
			BehaviourManager.handleBorder(player, 0, 0, 0, 0);
		}
	}
	
	public void collideBorder(Player player, float os_left, float os_right, float os_top, float os_bottom) {
		if (player.detectBorder(player, os_left, os_right, os_top, os_bottom)) {
			BehaviourManager.handleBorder(player, os_left, os_right, os_top, os_bottom);
		}
	}
}
