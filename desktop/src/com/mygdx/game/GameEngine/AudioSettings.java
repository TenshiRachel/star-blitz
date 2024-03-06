package com.mygdx.game.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AudioSettings {

    // default music and volume put path once got music
    private static final String DEFAULT_AUDIO = "path";

    private static final String AUDIO_VOLUME = "volume";
    private static final String AUDIO_ENABLED = "audio.enabled";
    private static final String SOUND_ENABLED = "sound.enabled";
    private static final String SOUND_VOLUME = "sound";
    private static final String PREF_NAME = "starblitz";

    // protected to be run by current class or any class that extends it
    protected Preferences getPrefs() {

        return Gdx.app.getPreferences(PREF_NAME);
    }
    
    // flush preferences after changes are made
    public boolean isSoundEnabled(){

        return getPrefs().getBoolean(SOUND_ENABLED, true);
    }

    public void setSoundEnabled(boolean soundEnabled){
        getPrefs().putBoolean(SOUND_ENABLED, soundEnabled);
        getPrefs().flush();
    }

    public boolean isAudioEnabled(){

        return getPrefs().getBoolean(AUDIO_ENABLED, true);
    }

    public void setAudioEnabled(boolean audioEnabled){
        getPrefs().putBoolean(AUDIO_ENABLED, audioEnabled);
        getPrefs().flush();
    }

    public float getAudioVolume(){

        return getPrefs().getFloat(AUDIO_VOLUME, 0.5f);
    }

    public void setAudioVolume(float volume){
        getPrefs().putFloat(AUDIO_VOLUME, volume);
        getPrefs().flush();
    }

    public float getSoundVolume(){

        return getPrefs().getFloat(SOUND_VOLUME, 0.5f);
    }

    public void setSoundVolume(float volume){
        getPrefs().putFloat(SOUND_VOLUME, volume);
        getPrefs().flush();
    }
}
