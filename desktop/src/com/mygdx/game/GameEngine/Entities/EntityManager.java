package com.mygdx.game.GameEngine.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameLayer.WordFactory;
import com.mygdx.game.GameLayer.Entities.*;

public class EntityManager {
	protected static EntityManager instance;
	private Player player;
	private SpriteBatch batch;
	private List<PlayerBullet> playerBulletList;
	private List<Enemy> enemyList;
	private List<Bullet> EnemyBulletList;
	private PlayerBullet playerbullet;
	private Bullet bullet;
	public BehaviourManager behaviourManager = BehaviourManager.getInstance();
	
	private Music playerShootSound, alienShootSound;
	private AudioSettings audioSettings = new AudioSettings();
	
	private int EnemySpawn;
	
	private int emptyWordCount = 15;
    private int spaceWordCount = 8;
    private int nonSpaceWordCount = 7;
    
    public WordFactory wordFactory = new WordFactory();
    
	Random random = new Random();
	
	public EntityManager() {
		playerBulletList = new ArrayList<>();
		enemyList = new ArrayList<>();
		EnemyBulletList = new ArrayList<>();
		AssetsManager.queuePlayerShootMusic();
		AssetsManager.queueAlienShootMusic();
        AssetsManager.getManager().finishLoading();
        playerShootSound = AssetsManager.getManager().get(AssetsManager.playerShootSound);
        alienShootSound = AssetsManager.getManager().get(AssetsManager.alienShootSound);
	}
	
	public static EntityManager getInstance() {
		if (instance == null) {
			instance = new EntityManager();
		}
		return instance;
	}
	
	public Player getPlayer() {
		return player;
	}

	public List<PlayerBullet> getPlayerbulletList()
	{
		return playerBulletList;
	}
	
	public List<Enemy> getEnemyList(){
		return enemyList;
	}
	
	public List<Bullet> getEnemyBulletList(){
		return EnemyBulletList;
	}
	
	public void create() {
		player = new Player(new Vector2(50, 0), 10, 64, 64,0, 5);
	}
	
	public void renderPlayer(SpriteBatch batch) {
		player.render(batch);
	}

	public void spawnPlayerBullet()
	{
		playerShootSound.setVolume(audioSettings.getSoundVolume());
        if (audioSettings.isSoundEnabled()) {
        	playerShootSound.play();
        }
		playerbullet = new PlayerBullet(new Vector2(player.getX(), player.getY()), 10, 64, 64);
		playerBulletList.add(playerbullet);

	}

	public void renderPlayerBullet(SpriteBatch batch) {
		// Render player bullet
		for (int i = 0; i < playerBulletList.size(); i++)
		{
			playerBulletList.get(i).render(batch);
		}
	}
	
	
	public void updatePlayerBullet(float deltaTime) {
		// Update player bullet position
		for (int i = 0; i < playerBulletList.size(); i++)
		{
			playerBulletList.get(i).setY(playerBulletList.get(i).getY() + playerBulletList.get(i).getSpeed());
			if(playerBulletList.get(i).getY() + playerBulletList.get(i).getHeight() > Gdx.graphics.getHeight())
			{
				playerBulletList.remove(i);
			}
		}
	}

	private String getRandomEnemyType() {
		int randomNumber = random.nextInt(3);
		while 
		(
			(randomNumber == 0 && emptyWordCount == 0) || (randomNumber == 1 && spaceWordCount == 0) || (randomNumber == 2 && nonSpaceWordCount == 0)  
	    ) 
			{
				randomNumber = random.nextInt(3);
			}
		if (randomNumber == 1) 
		{
			spaceWordCount--;
			return "space";
		} 
		else if (randomNumber == 2) 
		{
			nonSpaceWordCount--;
			return "nonSpace";
		} 
		else 
		{
			emptyWordCount--;
			return "empty";
		}
	}
	
	// Enemy
	public void spawnEnemy() {
		for (int i = 0; enemyList.size() < 30; i++) {
			// 0 for Green, 1 for Yellow 2 for red
			int enemyType1 = random.nextInt(3);
			int enemyType2 = random.nextInt(3);
			int enemyType3 = random.nextInt(3);

	        Enemy enemy1 = null;
	        Enemy enemy2 = null;
	        Enemy enemy3 = null;

	        // Row 3 Enemy
	        if (enemyType1 == 0) {
	            enemy1 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i,Gdx.graphics.getHeight()-170 ), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Green) enemy1);
	        } else if(enemyType1 == 1){
	            enemy1 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-170), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Yellow) enemy1);
	        } else {
	        	enemy1 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-170), 2, 64, 64, getRandomEnemyType());
	        	enemyList.add((Red) enemy1);
	        }
	        
	        enemy1.setEnemyWord(wordFactory.getRandomWord(enemy1.getEnemyType()));
	        
	        // Row 2 Enemy
	        if (enemyType2 == 0) {
	            enemy2 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i+100, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Green) enemy2);
	        } else if(enemyType2 == 1){
	            enemy2 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i+100, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Yellow) enemy2);
	        } else {
	        	enemy2 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i+100, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
	        	enemyList.add((Red) enemy2);
	        }
	        
	        enemy2.setEnemyWord(wordFactory.getRandomWord(enemy2.getEnemyType()));

	        
	        // Row 1 Enemy
	        if (enemyType3 == 0) {
	            enemy3 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-370), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Green) enemy3);
	        } else if(enemyType3 == 1){
	            enemy3 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-370), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Yellow) enemy3);
	        } else {
	        	enemy3 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-370), 2, 64, 64, getRandomEnemyType());
	        	enemyList.add((Red) enemy3);
	        }
	        
	        enemy3.setEnemyWord(wordFactory.getRandomWord(enemy3.getEnemyType()));

	        EnemySpawn = 1;
			
		}

		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			// System.out.println("Enemy " + (i + 1) + ": Enemy Word - " + enemy.getEnemyWord());
		}
	}
	
	
	// Enemies
	public void renderGreen(SpriteBatch batch) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Green) {
	            enemy.render(batch);
	        }
	    }
	}
	
	public void updateGreen(float deltaTime) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Green) {
	            enemy.update(deltaTime);
	        }
	    }
	}
	

	public void renderYellow(SpriteBatch batch) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Yellow) {
	            enemy.render(batch);
	        }
	    }
	}
	
	public void updateYellow(float deltaTime) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Yellow) {
	            enemy.update(deltaTime);
	        }
	    }
	}
	

	public void renderRed(SpriteBatch batch) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Red) {
	            enemy.render(batch);
	        }
	    }
	}
	
	public void updateRed(float deltaTime) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Red) {
	            enemy.update(deltaTime);
	        }
	    }
	}
	
	// Bullets
	public void spawnEnemyBullet() {
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy alien = enemyList.get(i);
			if (behaviourManager.playerNearAlien(player, alien)) {
				alienShootSound.setVolume(audioSettings.getSoundVolume());
		        if (audioSettings.isSoundEnabled()) {
		        	alienShootSound.play();
		        }
				if (alien instanceof Yellow) {
					YellowBullet bullet = new YellowBullet(new Vector2(alien.getX(), alien.getY()), 10, 50, 50);
					EnemyBulletList.add(bullet);
				}
				
				if (alien instanceof Green) {
					GreenBullet bullet = new GreenBullet(new Vector2(alien.getX(), alien.getY()), 10, 50, 50);
					EnemyBulletList.add(bullet);
				}
				
				if (alien instanceof Red) {
					RedBullet bullet = new RedBullet(new Vector2(alien.getX(), alien.getY()), 10, 50, 50);
					EnemyBulletList.add(bullet);
				}
			}
		}
	}
	//still working on this - jake
//	public void spawnEnemyBullet() {
//        for (Enemy enemy : enemyList) {
//            if (behaviourManager.isPlayerDirectlyBelow(player, enemy)) {
//                Bullet bullet;
//                if (enemy instanceof Green) {
//                    bullet = new GreenBullet(new Vector2(enemy.getX(), enemy.getY()), 20, 50, 50);
//                } else if (enemy instanceof Yellow) {
//                    bullet = new YellowBullet(new Vector2(enemy.getX(), enemy.getY()), 20, 50, 50);
//                } else if (enemy instanceof Red) {
//                    bullet = new RedBullet(new Vector2(enemy.getX(), enemy.getY()), 20, 50, 50);
//                } else {
//                    // Default bullet type if enemy type is unknown
//                    bullet = new Bullet(new Vector2(enemy.getX(), enemy.getY()), 20, 50, 50);
//                }
//                bulletList.add(bullet);
//            }
//        }
//    }
	
	public void renderEnemyBullet(SpriteBatch batch) {
		for (int i = 0; i < EnemyBulletList.size(); i++) {
			EnemyBulletList.get(i).render(batch);;
		}
	}
	
	public void updateEnemyBullet(float deltaTime) {
		for (int i = 0; i < EnemyBulletList.size(); i++) {
			Bullet enemyBullet = EnemyBulletList.get(i);
			enemyBullet.setY(enemyBullet.getY() - enemyBullet.getSpeed());
			if (enemyBullet.getY() < 0) {
				EnemyBulletList.remove(i);
			}
		}
	}
	
	
}


