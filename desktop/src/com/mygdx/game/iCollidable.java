package com.mygdx.game;

import com.mygdx.game.entities.CollidableEntity;

public interface iCollidable {
	public boolean isCollide(CollidableEntity object);
	public boolean detectBorder(CollidableEntity object, float os_left, float os_right, float os_top, float os_bottom);
}
