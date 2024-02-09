package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
	private Texture player;

	public Player(Vector2 pos, float speed, float width, float height) {
		super(pos.x, pos.y, speed, width, height);
		player = new Texture(Gdx.files.internal("entities/bucket.png"));
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(player, getX(), getY(), getWidth(), getHeight());
	}

}
