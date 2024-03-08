package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.EntityManager;

public class RedBullet extends Bullet{
    private Texture bullet;

    public RedBullet(Vector2 pos, float speed, float width, float height) {	
		super(pos, speed, width, height);
		bullet = new Texture(Gdx.files.internal("entities/bullet.png"));
	}

    public void render(SpriteBatch batch) {
		batch.draw(bullet, getX(), getY(), getWidth(), getHeight());
	}

	public void update(float deltaTime) {
		EntityManager.getInstance().updateRedBullet(deltaTime);
	}
    
}