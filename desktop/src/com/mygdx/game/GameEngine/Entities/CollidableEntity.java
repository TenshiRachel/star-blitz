package com.mygdx.game.GameEngine.Entities;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameEngine.Collision.iCollidable;

public abstract class CollidableEntity extends Entity implements iCollidable {

	public CollidableEntity(float x, float y, float speed, float width, float height) {
		super(x, y, speed, width, height);
	}
	
	public CollidableEntity() {
		
	}
	
	@Override 
	// Detect collision with other entities
	public boolean isCollide(CollidableEntity object) {
		// Get border of both entities
		float obj1_left = this.getX() - (this.getWidth() / 2);
		float obj2_left = object.getX() - (object.getWidth() / 2);
		float obj1_right = this.getX() + (this.getX() / 2);
		float obj2_right = object.getX() + (object.getX() / 2);
		float obj1_top = this.getY() + (this.getY() / 2);
		float obj2_top = object.getY() + (object.getY() / 2);
		float obj1_bottom = this.getY() - (this.getY() / 2);
		float obj2_bottom = object.getY() - (object.getY() / 2);
		
		// If collided, set true
		if (obj1_left < obj2_right) {
			if (obj2_left < obj1_right) {
				if (obj1_top > obj2_bottom) {
					if (obj2_top > obj1_bottom) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	@Override
	// Detect collision with border
	public boolean detectBorder(CollidableEntity object, float os_left, float os_right, float os_top, float os_bottom) {
		// Get border of entity
		float left = object.getX() - (object.getWidth() / 2);
		float right = object.getX() + (object.getWidth() / 2);
		float top = object.getY() + (object.getHeight() / 2);
		float bottom = object.getY() - (object.getHeight() / 2);
		
		// Check if does not hit border of screen
		if (left >= 0 + os_left) {
			if (right <= Gdx.graphics.getWidth() - os_right) {
				if (top <= Gdx.graphics.getHeight() - os_top) {
					if (bottom >= 0 + os_bottom) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
