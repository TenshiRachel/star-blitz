package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EntityManager {
	private static EntityManager instance;
	private Player player;
	private SpriteBatch batch;
	private List<Droplet> DropletList;
	private Droplet droplet;
	Random random = new Random();
	
	public EntityManager() {
		DropletList = new ArrayList<>();
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
	
	public void create() {
		player = new Player(new Vector2(50, 50), 10, 64, 64,0);
	}
	
	public void renderPlayer(SpriteBatch batch) {
		player.render(batch);
	}
	
	public void spawnDroplets() {
		for (int i = 0; DropletList.size() < MathUtils.random(1, 10); i++)
		{
			droplet = new Droplet(new Vector2(random.nextInt(Gdx.graphics.getWidth()), Gdx.graphics.getHeight()), 2 + random.nextInt(10), 64,64);
			DropletList.add(droplet);
		}
	}

	public void renderDroplets(SpriteBatch batch){
		for (int i = 0; i  < DropletList.size(); i++){
			DropletList.get(i).render(batch);
			//DropletList.remove(i);
		}
	}

	public void updateDroplets(float deltaTime) {
		// Iterate over all droplets in the dropletlist
		for (Droplet droplet : DropletList) {
			// Call the update method on each droplet, passing in deltatime
			droplet.update(deltaTime);
		}
	}

}
