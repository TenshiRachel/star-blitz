package com.mygdx.game.GameEngine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager {
	private static final AssetManager manager = new AssetManager();
	private Skin skin;
	private Music playingSong;
	
    public static final String skinPath = "skin/star-soldier-ui.json";
    public static final String playingSongPath = "audio/level.wav";
    public static final String gameOverPath = "audio/game_over.wav";
    public static final String scoreSongPath = "audio/score.wav";
    public static final String menuSongPath = "audio/menu.wav";
    public static final String alienHitSound = "audio/alien_hit.wav";
    public static final String playerHitSound = "audio/player_hit.wav";
    public static final String alienShootSound = "audio/alien_shoot.wav";
    public static final String playerShootSound = "audio/player_shoot.wav";
    public static final String playerDieSound = "audio/die.wav";

    public static void queueAddSkin(){
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/star-soldier-ui.atlas");
        manager.load(skinPath, Skin.class, params);
    }

    public static void queueLevelMusic(){
        manager.load(playingSongPath, Music.class);
    }
    
    public static void queueMenuMusic(){
    	manager.load(menuSongPath, Music.class);
    }
    
    public static void queueEndMusic(){
        manager.load(gameOverPath, Music.class);
    }
    
    public static void queueScoreMusic(){
        manager.load(scoreSongPath, Music.class);
    }
    
    public static void queueAlienHitMusic(){
        manager.load(alienHitSound, Music.class);
    }
    
    public static void queuePlayerHitMusic(){manager.load(playerHitSound, Music.class);}
    
    public static void queueAlienShootMusic(){manager.load(alienShootSound, Music.class);}
    
    public static void queuePlayerShootMusic(){manager.load(playerShootSound, Music.class);}
    
    public static void queuePlayerDieMusic() {manager.load(playerDieSound, Music.class);}
    
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
    
    public Skin getSkin() {
        if (skin == null) {
            // Load the skin from the asset manager if it hasn't been loaded yet
            skin = manager.get(skinPath, Skin.class);
        }
        return skin;
    }
    
    public void setPlayingSong(Music playingSong) {
        this.playingSong = playingSong;
    }

    public Music getPlayingSong() {
        return playingSong;
    }

    public static AssetManager getManager() {
        return manager;
    }
}
