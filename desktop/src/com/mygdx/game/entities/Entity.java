package com.mygdx.game.entities;

import com.mygdx.game.iCollidable;

public abstract class Entity implements iCollidable {
	private float x, y, speed, width, height;
	
	public Entity(float x, float y, float speed, float width, float height) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	
	public Entity() {
		
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getWidth() {
		return width;
	}
	
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	@Override 
	// Detect collision with other entities
	public boolean isCollide(Entity object) {
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
	public boolean detectBorder(Entity object, float os_left, float os_right, float os_top, float os_bottom) {
		// Get border of entity
		float left = object.getX() - (object.getWidth() / 2);
		float right = object.getX() + (object.getWidth() / 2);
		float top = object.getY() + (object.getHeight() / 2);
		float bottom = object.getY() - (object.getHeight() / 2);
		
		// Check if does not hit border of screen
		if (left >= 0 + os_left) {
			if (right <= 800 - os_right) {
				if (top <= 600 - os_top) {
					if (bottom >= 0 + os_bottom) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
}
