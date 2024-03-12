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
	private List<Enemy> RowOneEnemies;
	private List<Bullet> EnemyBulletList;
	private PlayerBullet playerbullet;
	private Bullet bullet;
	public BehaviourManager behaviourManager = BehaviourManager.getInstance();
	
	private Music playerShootSound, alienShootSound;
	private AudioSettings audioSettings = new AudioSettings();
	
	private boolean enemySpawned = false;
	
	private int emptyWordCount = 15;
    private int spaceWordCount = 8;
    private int nonSpaceWordCount = 7;
    
	Random random = new Random();
	
	public EntityManager() {
		playerBulletList = new ArrayList<>();
		enemyList = new ArrayList<>();
		RowOneEnemies = new ArrayList<>();
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
	
	public List<Enemy> getRowOne(){
		return RowOneEnemies;
	}
	
	public List<Bullet> getEnemyBulletList(){
		return EnemyBulletList;
	}
	
	public void create() {
		player = new Player(new Vector2(Gdx.graphics.getWidth() / 2, 0), 10, 64, 64,0, 5);
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
		WordFactory wordFactory = new WordFactory();
		if (!enemySpawned)
		{
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
		        } else if(enemyType1 == 1){
		            enemy1 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-170), 2, 64, 64, getRandomEnemyType());
		        } else {
		        	enemy1 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-170), 2, 64, 64, getRandomEnemyType());
		        }
		        
		        enemyList.add(enemy1);
		        enemy1.setEnemyWord(wordFactory.getRandomWord(enemy1.getEnemyType()));
		        
		        // Row 2 Enemy
		        if (enemyType2 == 0) {
		            enemy2 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i+100, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
		        } else if(enemyType2 == 1){
		            enemy2 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i+100, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
		        } else {
		        	enemy2 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i+100, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
		        }
		        
		        enemyList.add(enemy2);
		        enemy2.setEnemyWord(wordFactory.getRandomWord(enemy2.getEnemyType()));
	
		        
		        // Row 1 Enemy
		        if (enemyType3 == 0) {
		            enemy3 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-370), 2, 64, 64, getRandomEnemyType());
		        } else if(enemyType3 == 1){
		            enemy3 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-370), 2, 64, 64, getRandomEnemyType());
		        } else {
		        	enemy3 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-370), 2, 64, 64, getRandomEnemyType());
		        }
		        
		        enemyList.add(enemy3);
		        RowOneEnemies.add(enemy3);
		        enemy3.setEnemyWord(wordFactory.getRandomWord(enemy3.getEnemyType()));
		        
		        enemySpawned = true;
			}
		}
	}
	
	public void swapEnemyRow(float deltaTime)
	{
		for (int i = 0; i < enemyList.size(); i++)
		{
			enemyList.get(i).setY(enemyList.get(i).getY() - 100);
			if (enemyList.get(i).getY() < Gdx.graphics.getHeight() - 400)
			{
				enemyList.get(i).setY(Gdx.graphics.getHeight()-170);
				
			}
			
			if(enemyList.get(i).getY() == Gdx.graphics.getHeight() - 270)
			{
				enemyList.get(i).setX(enemyList.get(i).getX() + 100);
			}
			else if(enemyList.get(i).getY() == Gdx.graphics.getHeight() - 370)
			{
				enemyList.get(i).setX(enemyList.get(i).getX()- 100);
			}
			else
			{
				enemyList.get(i).setX(enemyList.get(i).getX()) ;
			}
		}
	}
	
	
	// Enemies
	public void renderEnemies(SpriteBatch batch) {
		for (int i = 0; i < enemyList.size(); i++)
		{
			enemyList.get(i).render(batch);
		}
	}
	
	public void updateEnemies(float deltaTime) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).update(deltaTime);
		}
	}
	
	// Bullets
	public void spawnEnemyBullet() {
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy alien = enemyList.get(i);
			if (enemyList.get(i).getY() == Gdx.graphics.getHeight() - 370) {
				if (behaviourManager.playerNearAlien(player, alien)) {
					if (!alien.getHasFired()) {
						alienShootSound.setVolume(audioSettings.getSoundVolume());
				        if (audioSettings.isSoundEnabled()) {
				        	alienShootSound.play();
				        }
				        Bullet bullet = null;
				        
						if (alien instanceof Yellow) {
							bullet = new YellowBullet(new Vector2(alien.getX(), alien.getY()), 10, 50, 50);
						}
						
						if (alien instanceof Green) {
							bullet = new GreenBullet(new Vector2(alien.getX(), alien.getY()), 10, 50, 50);
						}
						
						if (alien instanceof Red) {
							bullet = new RedBullet(new Vector2(alien.getX(), alien.getY()), 10, 50, 50);
						}
						
						EnemyBulletList.add(bullet);
						alien.setHasFired(true);
					}
				}
			}
		}
	}
	
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
				for (int j = 0; j < RowOneEnemies.size(); j++) {
					if (RowOneEnemies.get(j).getX() == enemyBullet.getX()) {
						RowOneEnemies.get(j).setHasFired(false);
					}
				}
				EnemyBulletList.remove(i);
				
			}
		}
	}

	public void resetEntities() {
		// Clear entities
		EnemyBulletList.clear();
		playerBulletList.clear();
		enemyList.clear();
		RowOneEnemies.clear();
		
		enemySpawned = false;
		emptyWordCount = 15;
		spaceWordCount = 8;
		nonSpaceWordCount = 7;
		
		// Reset player attributes
		if (player != null) {
			player.setPlayerHealth(5);
			player.setScore(0);
			player.setX(Gdx.graphics.getWidth() / 2);
		}
	}
}


