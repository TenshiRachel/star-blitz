package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	private List<Entity> entities;
	
	public EntityManager() {
		entities = new ArrayList<>();
	}
	
	public List<Entity> getEntities(){
		return entities;
	}
	
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void render(SpriteBatch batch) {
		for (Entity entity: entities) {
			entity.draw(batch);
		}
	}
	
	public void move() {
		for (Entity entity: entities) {
			entity.move();
		}
	}
}
