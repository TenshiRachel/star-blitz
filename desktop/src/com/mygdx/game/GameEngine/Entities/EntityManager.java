package com.mygdx.game.GameEngine.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameLayer.Entities.*;

public class EntityManager {
	protected static EntityManager instance;
	private Player player;
	private SpriteBatch batch;
	private List<Droplet> DropletList;
	private List<PlayerBullet> playerBulletList;
	private List<Green> greenList;
	private List<Yellow> yellowList;
	private List<Red> redList;
	private List<Enemy> enemyList;
	private List<YellowBullet> yellowBulletList;
	private Droplet droplet;
	private YellowBullet yellowBullet;
	private PlayerBullet playerbullet;
	Random random = new Random();
	
	public EntityManager() {
		DropletList = new ArrayList<>();
		playerBulletList = new ArrayList<>();
		enemyList = new ArrayList<>();
		greenList = new ArrayList<>();
		yellowList = new ArrayList<>();
		redList = new ArrayList<>();
		yellowBulletList = new ArrayList<>();
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
	
	public List<Droplet> getDropletList() {
		return DropletList;
	}

	public List<PlayerBullet> getPlayerbulletList()
	{
		return playerBulletList;
	}
	
	public List<Enemy> getEnemyList(){
		return enemyList;
	}
	
	public List<Yellow> getYellowList(){
		return yellowList;
	}
	
	public List<YellowBullet> getyellowBulletList(){
		return yellowBulletList;
	}
	
	public void create() {
		player = new Player(new Vector2(50, 0), 10, 64, 64,0);
	}
	
	public void renderPlayer(SpriteBatch batch) {
		player.render(batch);
	}
	
	public void spawnDroplets() {
		for (int i = 0; DropletList.size() < MathUtils.random(1, 5); i++)
		{
			droplet = new Droplet(new Vector2(random.nextInt(Gdx.graphics.getWidth()), Gdx.graphics.getHeight()), 2 + random.nextInt(10), 64,64);
			DropletList.add(droplet);
		}
	}

	public void renderDroplets(SpriteBatch batch){
		for (int i = 0; i  < DropletList.size(); i++){
			DropletList.get(i).render(batch);
		}
	}

	public void updateDroplets(float deltaTime) {
		// Iterate over all droplets in the dropletlist
		for (Droplet droplet : DropletList) {
			// Call the update method on each droplet, passing in deltatime
			droplet.update(deltaTime);
		}
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
	            enemy1 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i,Gdx.graphics.getHeight()-50 ), 2, 64, 64);
	            greenList.add((Green) enemy1);
	        } else if(enemyType1 == 1){
	            enemy1 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-50), 2, 64, 64);
	            yellowList.add((Yellow) enemy1);
	        } else {
	        	enemy1 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-50), 2, 64, 64);
	        	redList.add((Red) enemy1);
	        }
	        
	        enemyList.add(enemy1);
	        
	        // Row 2 Enemy
	        if (enemyType2 == 0) {
	            enemy2 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-150), 2, 64, 64);
	            greenList.add((Green) enemy2);
	        } else if(enemyType2 == 1){
	            enemy2 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-150), 2, 64, 64);
	            yellowList.add((Yellow) enemy2);
	        } else {
	        	enemy2 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-150), 2, 64, 64);
	        	redList.add((Red) enemy2);
	        }
	        enemyList.add(enemy2);
	        
	        // Row 1 Enemy
	        if (enemyType3 == 0) {
	            enemy3 = new Green(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-250), 2, 64, 64);
	            greenList.add((Green) enemy3);
	        } else if(enemyType3 == 1){
	            enemy3 = new Yellow(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-250), 2, 64, 64);
	            yellowList.add((Yellow) enemy3);
	        } else {
	        	enemy3 = new Red(new Vector2((Gdx.graphics.getWidth() / 10) * i, Gdx.graphics.getHeight()-250), 2, 64, 64);
	        	redList.add((Red) enemy3);
	        }
	        enemyList.add(enemy3);
		}
	}
	
	
	// Green Enemy
	public void renderGreen(SpriteBatch batch){
		// Render Yellow Enemy
		for (int i = 0; i < greenList.size(); i++){
			greenList.get(i).render(batch);
		}
	}
	
	public void updateGreen(float deltaTime) {
		for (Green green : greenList) {
			green.update(deltaTime);
		}
	}
	
	// Yellow Enemy
	public void renderYellow(SpriteBatch batch){
		// Render Yellow Enemy
		for (int i = 0; i < yellowList.size(); i++){
			yellowList.get(i).render(batch);
		}
	}
	
	public void updateYellow(float deltaTime) {
		for (Yellow yellow : yellowList) {
			yellow.update(deltaTime);
		}
	}
	
	// Red Enemy
	public void renderRed(SpriteBatch batch){
		// Render Red Enemy
		for (int i = 0; i < redList.size(); i++){
			redList.get(i).render(batch);
		}
	}
	
	public void updateRed(float deltaTime) {
		for (Yellow yellow : yellowList) {
			yellow.update(deltaTime);
		}
	}
	
	public void updateYellowBullet(float deltaTime) {
		
	}

	

}
