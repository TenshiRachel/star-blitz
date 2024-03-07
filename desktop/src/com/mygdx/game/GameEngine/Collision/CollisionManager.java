package com.mygdx.game.GameEngine.Collision;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameLayer.Entities.Enemy;
import com.mygdx.game.GameLayer.Entities.Player;
import com.mygdx.game.GameLayer.Entities.PlayerBullet;

public class CollisionManager {
	private static CollisionManager instance;
	private EntityManager entityManager = EntityManager.getInstance();
	private Music music;
	private AudioSettings audioSettings = new AudioSettings();
	
	private CollisionManager() {
	}
	
	public static synchronized CollisionManager getInstance() {
		if (instance == null) {
			instance = new CollisionManager();
		}
		return instance;
	}
	
	public void collideAlien(Player player) {
		// get alien and bullet lists, loop through aliens and check if bullet collide
		// if collide, remove bullet and alien, play alien_hit sound
		List<Enemy> EnemyList = entityManager.getEnemyList();
		List<PlayerBullet> PlayerBulletList = entityManager.getPlayerbulletList();
		
	    for (int i = 0; i < EnemyList.size(); i++) {
	    	Enemy alien = EnemyList.get(i);
	    	for (int j = 0; j < PlayerBulletList.size(); j++) {
	    		PlayerBullet bullet = PlayerBulletList.get(j);
	    		
	    		if (bullet.isCollide(alien)) {
//	    			EnemyList.remove(i);
//	    			PlayerBulletList.remove(j);
	    			if (audioSettings.isSoundEnabled()) {
	    				music = Gdx.audio.newMusic(Gdx.files.internal("audio/alien_hit.wav"));
	    				music.setVolume(audioSettings.getSoundVolume());
	    				music.play();
	    			}
	    			
	    			player.setScore(player.getScore() + 1);
	    			
	    			break;
	    		}
	    	}
	    }
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
