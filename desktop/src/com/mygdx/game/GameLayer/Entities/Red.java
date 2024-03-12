package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;

public class Red extends Enemy{
	private Texture red;

	public Red(Vector2 pos, float speed, float width, float height, String enemyword) {
		super(pos, speed, width, height, enemyword);
		red = new Texture(Gdx.files.internal("entities/red.png"));
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(red, getX(), getY(), getWidth(), getHeight());
	}
	
}