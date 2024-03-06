package com.mygdx.game.GameLayer.Scenes;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.Scene.SceneManager;

public class ScoreScene extends SceneManager{
	private Stage stage;
	private SpriteBatch batch;
	private Skin skin;
	private Texture background;
	private Label dateLabel;
	private Label scoreLabel;
	private Label dateValueLabel;
	private Label scoreValueLabel;
	private int score;
	private Music music;
	private AudioSettings audioSettings = new AudioSettings();

	public ScoreScene(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/score.wav"));
	}
	
	@Override
	public void show() {
		if (audioSettings.isAudioEnabled()) {
			music.setVolume(audioSettings.getAudioVolume());
			music.setLooping(true);
			music.play();
		}

		background = new Texture(Gdx.files.internal("background/space.png"));
		stage.clear();
		Gdx.input.setInputProcessor(stage);
		skin =  new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));
		
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		// Labels for Date and Score headers
		dateLabel = new Label("Date", skin);
		scoreLabel = new Label("Score", skin);
		
		// Labels for Date and Score values
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
		String dateString = formatter.format(new Date(TimeUtils.millis()));
		dateValueLabel = new Label(dateString, skin);
        scoreValueLabel = new Label(String.valueOf(score), skin);
		
		
		table.pad(100); // offset from corner of the screen
		table.top(); //align table at the top
		table.add(dateLabel).expandX().left();
		table.add(scoreLabel).expandX().right();
		table.row();
		table.add(dateValueLabel).expandX().left();
        table.add(scoreValueLabel).expandX().right();
		
		
		// On create
		Gdx.input.setInputProcessor(new InputAdapter() {
			// Return to menu upon pressing Escape button 
			@Override
			public boolean keyDown(int keyCode) {
				if (keyCode == Input.Keys.ESCAPE) {
					game.setScreen(new MainMenuScene(game));
				}
				return true;
			}
		});
	}
	
	@Override
	public void render(float deltatime) {
		Gdx.gl.glClearColor(0f, 0.2f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(background, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// Show Date and score
		
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
		super.hide();
		music.stop();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}

