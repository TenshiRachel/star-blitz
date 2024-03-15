package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameLayer.Entities.Enemy;

public class Yellow extends Enemy{
	private Texture yellow;

	public Yellow(Vector2 pos, float speed, float width, float height, String enemyword, int column, int row) {
		super(pos, speed, width, height, enemyword, column, row);
		yellow = new Texture(Gdx.files.internal("entities/yellow.png"));
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(yellow, getX(), getY(), getWidth(), getHeight());
	}
	
}