package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;

public class Enemy extends CollidableEntity{

	private String EnemyWord;
	
	public Enemy(Vector2 pos, float speed, float width, float height, String EnemyWord) {
		super(pos.x, pos.y, speed, width, height);
		this.EnemyWord = EnemyWord;
	}

	public String getEnemyWord() {
        return EnemyWord;
    }

    public void setEnemyWord(String EnemyWord) {
        this.EnemyWord = EnemyWord;
    }
}
