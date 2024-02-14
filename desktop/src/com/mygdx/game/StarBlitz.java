package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.scenes.MainMenuScene;

public class StarBlitz extends Game {
	private Screen currScreen;
	private MainMenuScene menu;
	public IOManager ioManager;
	public BehaviourManager behaviourManager;
	
	@Override
	public void create() {
		menu = new MainMenuScene(this);
		setScreen(menu);
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
