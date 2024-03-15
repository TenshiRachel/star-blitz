package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine.Entities.NonCollidableEntity;

public class Hearts extends NonCollidableEntity {

	private Texture hearts;

	public Hearts(float x, float y, float speed, float width, float height) {
		super(x, y, speed, width, height);
		// TODO Auto-generated constructor stub
		hearts = new Texture(Gdx.files.internal("entities/heart.png"));
	}

	public void render(SpriteBatch batch) {
		batch.draw(hearts, getX(), getY(), getWidth(), getHeight());
	}

	public void update(float deltaTime) {

	}

}
