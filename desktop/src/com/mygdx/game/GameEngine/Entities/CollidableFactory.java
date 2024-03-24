package com.mygdx.game.GameEngine.Entities;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameLayer.Entities.*;

public class CollidableFactory {
	public static CollidableEntity getAlien(int num, float x, float y, String enemyType, int col, int row) {
		if (num == 1) {
			return new Yellow(new Vector2(x, y), 2, 64, 64, enemyType,col, row);
		}
		if (num == 0) {
			return new Green(new Vector2(x, y), 2, 64, 64, enemyType, col, row);
		}
		if (num == 2) {
			return new Red(new Vector2(x, y), 2, 64, 64, enemyType, col, row);
		}
		return null;
	}
	
	public static CollidableEntity getEnemyBullet(Enemy alien) {
		if (alien instanceof Yellow) {
			return new YellowBullet(new Vector2(alien.getX(), alien.getY()), 7, 64, 64, alien.getColumn());
		}
		if (alien instanceof Green) {
			return new GreenBullet(new Vector2(alien.getX(), alien.getY()), 7, 64, 64, alien.getColumn());
		}
		if (alien instanceof Red) {
			return new RedBullet(new Vector2(alien.getX(), alien.getY()), 14, 64, 64, alien.getColumn());
		}
		return null;
	}
	
	public static CollidableEntity getPlayerBullet(Player player) {
		return new PlayerBullet(new Vector2(player.getX(), player.getY()), 10, 64, 64);
	}
}
