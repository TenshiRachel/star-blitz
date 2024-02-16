package com.mygdx.game;

import com.mygdx.game.entities.Entity;

public interface iCollidable {
	public boolean isCollide(Entity object);
	public boolean detectBorder(Entity object, float os_left, float os_right, float os_top, float os_bottom);
}
