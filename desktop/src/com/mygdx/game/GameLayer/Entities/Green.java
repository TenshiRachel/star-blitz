package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;

public class Green extends Enemy{
	private Texture green;

	public Green(Vector2 pos, float speed, float width, float height) {
		super(pos, speed, width, height);
		green = new Texture(Gdx.files.internal("entities/green.png"));
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(green, getX(), getY(), getWidth(), getHeight());
	}
	
	public void update(float deltaTime) {
		BehaviourManager.getInstance().updateGreen(this, deltaTime);
	}
	
}