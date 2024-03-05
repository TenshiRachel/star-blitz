package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;

public class playerbullet extends CollidableEntity{

    private Texture bullet;

    public playerbullet(Vector2 pos, float speed, float width, float height) {
        
		super(pos.x, pos.y, speed, width, height);
		bullet = new Texture(Gdx.files.internal("entities/bullet.png"));
	}

    public void render(SpriteBatch batch) {
		batch.draw(bullet, getX(), getY(), getWidth(), getHeight());
	}

	public void update(float deltaTime) {
		BehaviourManager.getInstance().updateplayerbullet(this, deltaTime);
	}
    
}
