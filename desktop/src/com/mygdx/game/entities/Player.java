package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.IOManager;
import com.mygdx.game.PlayerControl;

public class Player extends Entity implements PlayerControl {
	private IOManager ioManager;

	public Player(String imgName, int x, int y, int speed) {
		super(imgName, x, y, speed);
		ioManager = new IOManager();
	}
	
	@Override
	public void move() {
		if (ioManager.isKeyPressed(Keys.A) | ioManager.isKeyPressed(Keys.LEFT)) {
			moveLeft();
		}
		if (ioManager.isKeyPressed(Keys.D) | ioManager.isKeyPressed(Keys.RIGHT)) {
			moveRight();
		}
	}

	@Override
	public void moveLeft() {
		int currX = getX();
		setX((int) (currX - getSpeed() * Gdx.graphics.getDeltaTime()));
	}

	@Override
	public void moveRight() {
		int currX = getX();
		setX((int) (currX + getSpeed() * Gdx.graphics.getDeltaTime()));
	}

}
