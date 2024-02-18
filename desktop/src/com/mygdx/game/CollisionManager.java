package com.mygdx.game;

import java.util.List;

import com.mygdx.game.entities.Droplet;
import com.mygdx.game.entities.EntityManager;
import com.mygdx.game.entities.Player;

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
	
	public void collideDroplet(Player player) {
		List<Droplet> DropletList = entityManager.getDropletList();
		for (int i = 0; i < DropletList.size(); i++) {
			Droplet droplet = DropletList.get(i);
			if (player.isCollide(droplet)) {
				// Do something game related
				DropletList.remove(i);
				player.setScore(player.getScore() + 1);
				System.err.println(player.getScore());
			}
		}
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
