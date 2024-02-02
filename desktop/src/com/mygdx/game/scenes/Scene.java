package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene {
	private Texture tex;
	private int x;
	private int y;
	
	public Scene(String imgName, int x, int y) {
		tex = new Texture(Gdx.files.internal(imgName));
		this.x = x;
		this.y = y;
	}
	
	public Texture getTexure() {
		return tex;
	}
	
	public void setTexture(String imgName) {
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
	
	public void draw(SpriteBatch batch) {
		batch.begin();
		batch.draw(tex, x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
	}
}
