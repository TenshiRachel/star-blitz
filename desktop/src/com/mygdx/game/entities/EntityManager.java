package com.mygdx.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EntityManager {
	private static EntityManager instance;
	private Player player;
	private SpriteBatch batch;
	
	public EntityManager() {
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
	
	public void create() {
		player = new Player(new Vector2(50, 50), 10, 64, 64);
	}
	
	public void renderPlayer(SpriteBatch batch) {
		player.render(batch);
	}
}
