package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;

public class Enemy extends CollidableEntity{
	private String enemyType;
	private String enemyword;
	
	public Enemy(Vector2 pos, float speed, float width, float height, String enemyType) {
		super(pos.x, pos.y, speed, width, height);
		this.enemyword = "";
		this.enemyType = enemyType;
	}
	
	public String getEnemyType() {
		return enemyType;
	}

	public String getEnemyWord() {
        return enemyword;
    }

    public void setEnemyWord(String enemyword) {
        this.enemyword = enemyword;
    }
    
    public void setEnemyType(String enemyType) {
    	this.enemyType = enemyType;
    }
}
