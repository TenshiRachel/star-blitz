package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Droplet extends Entity{
	private Texture npcTexture;
	private NPCState state; //enum for different states
	private NPCAttributes attributes; //attributes for health, strength, etc
	
	public Droplet(Vector2 pos, float speed, float width, float height) {
		super(pos.x, pos.y, speed, width, height);
		this.npcTexture = new Texture(Gdx.files.internal("entities/droplet.png"));
		this.state = NPCState.IDLE;
		this.attributes = new NPCAttributes(100,10);
	}
	
	public enum NPCState {
	    IDLE,
	    WALKING,
	    ATTACKING,
	    RUNNING,
	    FLYING
	}
	
	private class NPCAttributes{
        private int health, strength;
        
        public NPCAttributes(int health, int strength) {
            this.health = health;
            this.strength = strength;
        }
        
        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }
        
        public int getStrength() {
            return strength;
        }

        public void setStrength(int strength) {
            this.strength = strength;
        }
    }
	
	public void update(float deltaTime) {
		
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(npcTexture, getX(), getY(), getWidth(), getHeight());
	}
	
	public NPCState getState() {
		return state;
	}
	
	public void setState(NPCState state) {
        this.state = state;
    }

	public Texture getNPC() {
		return npcTexture;
	}

	public void setNPC(Texture nPC) {
		npcTexture = nPC;
	}
	
	public void dispose() {
        npcTexture.dispose();
    }
}
