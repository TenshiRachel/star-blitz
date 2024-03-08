package com.mygdx.game.GameEngine.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameLayer.WordFactory;
import com.mygdx.game.GameLayer.Entities.*;

public class EntityManager {
	protected static EntityManager instance;
	private Player player;
	private SpriteBatch batch;
	private List<PlayerBullet> playerBulletList;
	private List<Enemy> enemyList;
	private List<Bullet> bulletList;
	private PlayerBullet playerbullet;
	private Bullet bullet;
	
	private int EnemySpawn;
	
	private int emptyWordCount = 15;
    private int spaceWordCount = 8;
    private int nonSpaceWordCount = 7;
    
    public WordFactory wordFactory = new WordFactory();
    
	Random random = new Random();
	
	public EntityManager() {
		playerBulletList = new ArrayList<>();
		enemyList = new ArrayList<>();
		bulletList = new ArrayList<>();
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
	
	public List<Bullet> getBulletList(){
		return bulletList;
	}
	
	public void create() {
		player = new Player(new Vector2(50, 0), 10, 64, 64,0);
	}
	
	public void renderPlayer(SpriteBatch batch) {
		player.render(batch);
	}

	public void spawnPlayerBullet()
	{
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
	            enemy2 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Green) enemy2);
	        } else if(enemyType2 == 1){
	            enemy2 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
	            enemyList.add((Yellow) enemy2);
	        } else {
	        	enemy2 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-270), 2, 64, 64, getRandomEnemyType());
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
	public void spawnBullet() {
		if(EnemySpawn == 1) {
			for (int i = 0; bulletList.size() < enemyList.size(); i++) {
				if (enemyList.get(i) instanceof Green) {
					bullet = new GreenBullet(new Vector2(enemyList.get(i).getX(),enemyList.get(i).getY()), 2, 50, 50);
					bulletList.add((GreenBullet) bullet);
				} else if (enemyList.get(i)instanceof Yellow) {
					bullet = new YellowBullet(new Vector2(enemyList.get(i).getX(),enemyList.get(i).getY()), 2, 50, 50);
					bulletList.add((YellowBullet) bullet);
				} else if (enemyList.get(i) instanceof Red) {
					bullet = new RedBullet(new Vector2(enemyList.get(i).getX(),enemyList.get(i).getY()), 2, 50, 50);
					bulletList.add((RedBullet) bullet);
				}
			}
		}
	}
	
	public void renderGreenBullet(SpriteBatch batch) {
	    for (Bullet bullet: bulletList) {
	        if (bullet instanceof GreenBullet) {
	            bullet.render(batch);
	        }
	    }
	}
	
	
	public void updateGreenBullet(float deltaTime) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Green) {
	            enemy.update(deltaTime);
	        }
	    }
	}
	
	public void renderYellowBullet(SpriteBatch batch) {
	    for (Bullet bullet: bulletList) {
	        if (bullet instanceof YellowBullet) {
	            bullet.render(batch);
	        }
	    }
	}
	
	
	public void updateYellowBullet(float deltaTime) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Yellow) {
	            enemy.update(deltaTime);
	        }
	    }
	}
	
	public void renderRedBullet(SpriteBatch batch) {
	    for (Bullet bullet: bulletList) {
	        if (bullet instanceof RedBullet) {
	            bullet.render(batch);
	        }
	    }
	}
	
	
	public void updateRedBullet(float deltaTime) {
	    for (Enemy enemy : enemyList) {
	        if (enemy instanceof Red) {
	            enemy.update(deltaTime);
	        }
	    }
	}

}
