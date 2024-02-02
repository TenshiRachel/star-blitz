package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Movable;

public abstract class Entity implements Movable {
	private Texture tex;
	private int x;
	private int y;
	private int speed;
	
	public Entity(String imgName, int x, int y, int speed) {
		this.tex = new Texture(Gdx.files.internal(imgName));
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public Texture getTex() {
		return tex;
	}
	
	public void setTex(String imgName) {
		tex = new Texture(Gdx.files.internal(imgName));
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void draw(SpriteBatch batch) {
		batch.begin();
		batch.draw(tex, x, y, tex.getWidth(), tex.getHeight());
		batch.end();
	}
	
	public void move() {
		
	}
}
