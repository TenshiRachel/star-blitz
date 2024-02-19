package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.iCollidable;

public abstract class Entity {
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
	
}
