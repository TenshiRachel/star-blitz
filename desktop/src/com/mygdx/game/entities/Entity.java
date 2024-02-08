package com.mygdx.game.entities;


public abstract class Entity {
	private float x, y, speed;
	
	public Entity(float x, float y, float speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
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
	
}
