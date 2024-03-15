package com.mygdx.game.GameLayer.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.BehaviourManager;
import com.mygdx.game.GameEngine.Entities.CollidableEntity;
import com.mygdx.game.GameEngine.Entities.EntityManager;

public class GreenBullet extends Bullet{
    private Texture bullet;

    public GreenBullet(Vector2 pos, float speed, float width, float height, int colomn) {	
		super(pos, speed, width, height, colomn);
		bullet = new Texture(Gdx.files.internal("entities/greenBullet.png"));
	}

    public void render(SpriteBatch batch) {
		batch.draw(bullet, getX(), getY(), getWidth(), getHeight());
	}
    
}