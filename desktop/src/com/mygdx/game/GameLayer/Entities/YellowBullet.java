package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.EntityManager;

public class YellowBullet extends Bullet{
    private Texture bullet;

    public YellowBullet(Vector2 pos, float speed, float width, float height,int column) {	
		super(pos, speed, width, height, column);
		bullet = new Texture(Gdx.files.internal("entities/yellowBullet.png"));
	}

    public void render(SpriteBatch batch) {
		batch.draw(bullet, getX(), getY(), getWidth(), getHeight());
	}
    
}