package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.BehaviourManager;

public class Droplet extends Entity{
	private Texture dropletTexture;
	
	public Droplet(Vector2 pos, float speed, float width, float height) {
		super(pos.x, pos.y, speed, width, height);
		this.dropletTexture = new Texture(Gdx.files.internal("entities/droplet.png"));
	}
		
	public void update(float deltaTime) {
		BehaviourManager.getInstance().updateDroplets(this, deltaTime);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(dropletTexture, getX(), getY(), getWidth(), getHeight());
	}
	
	public Texture getNPC() {
		return dropletTexture;
	}

	public void setNPC(Texture nPC) {
		dropletTexture = nPC;
	}
	
	public void dispose() {
        dropletTexture.dispose();
    }
}
