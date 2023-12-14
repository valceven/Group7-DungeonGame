package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import javax.sound.sampled.*;

public class Sound {

    Clip clip;

    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/MainMenuScreen/SoundEffect/pokemonSoundEffect.wav");
        soundURL[1] = getClass().getResource("/MainMenuScreen/SoundEffect/Title Theme.wav");
        soundURL[2] = getClass().getResource("/MainMenuScreen/SoundEffect/BattleSong.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public void stopAndReset(int i) {
        stop();
        if (clip != null) {
            clip.close();
        }
        setFile(i);
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        }
    }
}