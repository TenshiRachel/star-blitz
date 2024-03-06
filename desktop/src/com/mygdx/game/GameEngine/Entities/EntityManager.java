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
	private Green green;
	private Yellow yellow;
	private Red red;
	private YellowBullet yellowBullet;
	private PlayerBullet playerbullet;
	Random random = new Random();
	
	public EntityManager() {
		DropletList = new ArrayList<>();
		playerBulletList = new ArrayList<>();
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
	
	
	// Green Enemy
	
	// Create 2 at Random stationary position for now
	public void spawnGreen(){
		for(int i = 0; greenList.size() < 2; i ++){
			green = new Green(new Vector2(random.nextInt(Gdx.graphics.getWidth()), 300), 2, 60, 60);
			greenList.add(green);
		}
	}
	
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
	
	// Create 2 at Random stationary position for now
	public void spawnYellow(){
		for(int i = 0; yellowList.size() < 2; i ++){
			yellow = new Yellow(new Vector2(random.nextInt(Gdx.graphics.getWidth()), 300), 2, 60, 60);
			yellowList.add(yellow);
		}
	}
	
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
	
	// Create 2 at Random stationary position for now
	public void spawnRed(){
		for(int i = 0; redList.size() < 2; i ++){
			red = new Red(new Vector2(random.nextInt(Gdx.graphics.getWidth()), 300), 2, 60, 60);
			redList.add(red);
		}
	}
	
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
