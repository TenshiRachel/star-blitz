package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.scenes.LevelScene;

public class StarBlitz extends Game {
	private Screen currScreen;
	private LevelScene level;
	public IOManager ioManager;
	public BehaviourManager behaviourManager;
	
	@Override
	public void create() {
		level = new LevelScene(this);
		setScreen(level);
		ioManager = IOManager.getInstance();
		ioManager.setGame(this);
		behaviourManager = BehaviourManager.getInstance();
	}
	
	@Override 
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		currScreen = screen;
	}
	
	public Screen getCurrScreen() {
		return currScreen;
	}
}
