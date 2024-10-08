package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;

public class Player extends CollidableEntity {
	private Texture player;
	private int score = 0;
	private int playerhealth = 5;

	public Player(Vector2 pos, float speed, float width, float height, int score, int playerhealth) {
		super(pos.x, pos.y, speed, width, height);
		this.score = score;
		player = new Texture(Gdx.files.internal("entities/player.png"));
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(player, getX(), getY(), getWidth(), getHeight());
	}

	public int getScore(){
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}
	
	public int getPlayerHealth()
	{
		return playerhealth;
	}
	
	public void setPlayerHealth(int playerhealth)
	{
		this.playerhealth = playerhealth;
	}

}
