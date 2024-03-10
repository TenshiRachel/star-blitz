package com.mygdx.game.GameEngine.Collision;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameLayer.WordFactory;
import com.mygdx.game.GameLayer.Entities.Bullet;
import com.mygdx.game.GameLayer.Entities.Enemy;
import com.mygdx.game.GameLayer.Entities.Player;
import com.mygdx.game.GameLayer.Entities.PlayerBullet;

public class CollisionManager {
	private static CollisionManager instance;
	private EntityManager entityManager = EntityManager.getInstance();
	private Music alienHitSound, playerHitSound;
	private AudioSettings audioSettings = new AudioSettings();
	private WordFactory wordFactory = new WordFactory();
	
	private CollisionManager() {
        AssetsManager.queueAlienHitMusic();
        // AssetsManager.queuePlayerHitMusic();
        AssetsManager.getManager().finishLoading();
        
        alienHitSound = AssetsManager.getManager().get(AssetsManager.alienHitSound);
        // playerHitSound = AssetsManager.getManager().get(AssetsManager.playerHitSound);
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
		
		
	    for (Iterator<Enemy> enemyIterator = EnemyList.iterator(); enemyIterator.hasNext();) {
	    	Enemy alien = enemyIterator.next();
	    	for (int j = 0; j < PlayerBulletList.size(); j++) {
	    		PlayerBullet bullet = PlayerBulletList.get(j);
	    		
	    		if (alien.isCollide(bullet)) {
	    			alienHitSound.setVolume(audioSettings.getSoundVolume());
	    	        if (audioSettings.isSoundEnabled()) {
	    	        	alienHitSound.play();
	    	        }
	    	        
	    			if (alien.getEnemyType() == "empty") {
	    				player.setScore(player.getScore() + 10);
	    			}
	    			
	    			if (alien.getEnemyType() == "space") {
	    				player.setScore(player.getScore() + 20);
	    			}
	    			
	    			if (alien.getEnemyType() == "nonSpace") {
	    				player.setScore(player.getScore() - 10);
	    				if (player.getScore() < 0) {
	    					player.setScore(0);
	    				}
	    			}
	    			enemyIterator.remove();
	    			PlayerBulletList.remove(j);
	    			
	    			break;
	    		}
	    	}
	    }
	}
	
	public void collidePlayer(Player player) {
		// get enemy bullet, loop through and check if bullet collide with player
		List<Bullet> EnemyBulletList = entityManager.getEnemyBulletList();
		
		for (int i = 0; i < EnemyBulletList.size(); i++) {
			if (player.isCollide(EnemyBulletList.get(i))) {
				player.setPlayerHealth(player.getPlayerHealth() - 1);
				if (player.getPlayerHealth() < 0) {
					player.setPlayerHealth(0);
				}
				
				player.setScore(player.getScore() - 20);
				if (player.getScore() < 0) {
					player.setScore(0);
				}
				
				EnemyBulletList.remove(i);
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
	
    public static void resetInstance() {
        instance = null;
    }
	

}
