package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class IOManager {
	public IOManager() {
		
	}
	
	public boolean isKeyPressed(int key) {
		return Gdx.input.isKeyPressed(key);
	}
}
