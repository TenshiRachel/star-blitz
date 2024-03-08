package com.mygdx.game.GameLayer.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.Scene.SceneManager;

public class SettingsScene extends SceneManager{
	private Stage stage;
	private SpriteBatch batch;
	private Skin skin;
	private Texture background;
	private Label titleLabel;
	private Label musicEnLabel;
	private Label soundEnLabel;
	private Label musicVolLabel;
	private Label soundVolLabel;
	private AudioSettings audioSettings = new AudioSettings();
	
	public SettingsScene(Game game) {
		super(game);
		
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		
		AssetsManager assetsManager = getAssetManager();
		assetsManager.queueAddSkin();
		assetsManager.getManager().finishLoading();
	}
	
	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("background/space.png"));
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		
		Table table = new Table();
		table.setFillParent(true);
		
		stage.addActor(table);
		
		skin =  getAssetManager().getSkin();
		
		Slider musicVolSlider = new Slider(0f, 1f, 0.1f, false, skin);
        musicVolSlider.setValue(audioSettings.getAudioVolume());
        musicVolSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
            	audioSettings.setAudioVolume(musicVolSlider.getValue());
                return false;
            }
        });
        
        Slider soundVolSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundVolSlider.setValue(audioSettings.getSoundVolume());
        soundVolSlider.addListener(new EventListener() {
        	@Override
        	public boolean handle(Event event) {
        		audioSettings.setSoundVolume(soundVolSlider.getValue());
        		return false;
        	}
        });
	        
        CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked(audioSettings.isAudioEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                audioSettings.setAudioEnabled(enabled);
                return false;
            }
        });
        
        CheckBox soundsCheckbox = new CheckBox(null, skin);
        soundsCheckbox.setChecked(audioSettings.isSoundEnabled());
        soundsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundsCheckbox.isChecked();
                audioSettings.setSoundEnabled(enabled);
                return false;
            }
        });
        
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	game.setScreen(new PauseScene(game));
            }
        });
		
        // Labels for sliders and checkboxes
        titleLabel = new Label( "Settings", skin );
        musicVolLabel = new Label( "Music Volume", skin );
        soundVolLabel = new Label( "Sound Volume", skin );
        musicEnLabel = new Label( "Music", skin );
        soundEnLabel = new Label( "Sound Effects", skin );

        table.add(titleLabel);
        table.row().pad(5, 0, 5, 0);;
        table.add(musicVolLabel);
        table.add(musicVolSlider);
        table.row().pad(5, 0, 5, 0);;
        table.add(musicEnLabel);
        table.add(musicCheckbox);
        table.row().pad(5, 0, 5, 0);;
        table.add(soundVolLabel);
        table.add(soundVolSlider);
        table.row().pad(5, 0, 5, 0);;
        table.add(soundEnLabel);
        table.add(soundsCheckbox);
        table.row().pad(5, 0, 5, 0);;
        table.add(backButton);
	}

	
	@Override
	public void render(float deltatime) {
		Gdx.gl.glClearColor(0f, 0.2f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(background, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		batch.end();
		
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
		stage.draw();
	}
	
	@Override 
	public void resize(int width, int height) {
		// Resize stage
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void pause() {
	}
	
	@Override
	public void resume() {
	}
	
	@Override
	public void hide() {
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
