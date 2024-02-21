package com.mygdx.game.entities;

public abstract class NonCollidableEntity extends Entity {
	
	public NonCollidableEntity(float x, float y, float speed, float width, float height, EntityManager instance) {
		super(x, y, speed, width, height, instance);
	}
}
