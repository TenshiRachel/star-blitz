package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EntityManager {
	private Player player;
	private SpriteBatch batch;
	
	public EntityManager() {
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void create() {
		player = new Player(new Vector2(50, 50), 200);
	}
	
	public void renderPlayer(SpriteBatch batch) {
		player.render(batch);
	}
}
