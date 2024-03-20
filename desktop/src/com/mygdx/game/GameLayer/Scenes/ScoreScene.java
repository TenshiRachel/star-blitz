package com.mygdx.game.GameLayer.Scenes;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
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
import com.mygdx.game.GameEngine.AssetsManager;
import com.mygdx.game.GameEngine.AudioSettings;
import com.mygdx.game.GameEngine.Entities.EntityManager;
import com.mygdx.game.GameEngine.Scene.SceneManager;
import com.mygdx.game.GameLayer.Entities.Player;

public class ScoreScene extends SceneManager {
    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private Texture background;
    private Label detailLabel;
    private Label scoreLabel;
    private Label currentScoreLabel;
    private Label highScoreLabel;
    private Label currentScoreValueLabel;
    private Label highScoreValueLabel;
    private Music playingSong;
    private AudioSettings audioSettings = new AudioSettings();
    private int highScore, currentScore;

    public EntityManager entityManager = EntityManager.getInstance();
    
 // Constructor
    public ScoreScene(Game game) {
        super(game);
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        // Load assets
        AssetsManager assetsManager = getAssetManager();
        assetsManager.queueAddSkin();
        assetsManager.getManager().finishLoading();
    }

    // Constructor
    public ScoreScene(Game game, int currentScore, int highScore) {
        super(game);
        this.highScore = highScore;
        this.currentScore = currentScore;
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        // Load assets
        AssetsManager assetsManager = getAssetManager();
        assetsManager.queueAddSkin();
        assetsManager.getManager().finishLoading();
    }

    @Override
    public void show() {
        // Load music
        AssetsManager.queueScoreMusic();
        AssetsManager.getManager().finishLoading();
        playingSong = AssetsManager.getManager().get(AssetsManager.scoreSongPath);
        playingSong.setVolume(audioSettings.getAudioVolume());
        playingSong.setLooping(true);
        if (audioSettings.isAudioEnabled()) {
            playingSong.play();
        }

        // Load background texture
        background = new Texture(Gdx.files.internal("background/space.png"));

        stage.clear();
        Gdx.input.setInputProcessor(stage);

        skin = getAssetManager().getSkin();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Labels for Date and Score headers
        detailLabel = new Label("Details", skin);
        scoreLabel = new Label("Score", skin);

        // Labels for Details and Score values
        currentScoreLabel = new Label("Current Score", skin);
        currentScoreValueLabel = new Label(String.valueOf(currentScore), skin);
        highScoreLabel = new Label("High Score", skin);
        highScoreValueLabel = new Label(String.valueOf(highScore), skin);

        // Table setup
        table.pad(100); // offset from corner of the screen
        table.top(); // align table at the top
        table.add(detailLabel).expandX().left();
        table.add(scoreLabel).expandX().right();
        table.row();
        table.add(currentScoreLabel).expandX().left();
        table.add(currentScoreValueLabel).expandX().right();
        table.row();
        table.add(highScoreLabel).expandX().left();
        table.add(highScoreValueLabel).expandX().right();

        // Input processing
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
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0f, 0.2f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
        playingSong.stop();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        playingSong.dispose();
    }
}
