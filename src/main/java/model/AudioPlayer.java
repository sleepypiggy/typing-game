package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

    private File file;
    private AudioInputStream audio;
    private Clip clip;
    private boolean loopContinuously;

    public AudioPlayer(String path, boolean loopContinuously) {
        this.loopContinuously = loopContinuously;

        file = new File(path);

        try {
            audio = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException uafe) {
            System.out.println("Unsupported audio file. ");
            uafe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Something went wrong with loading the audio file. ");
            ioe.printStackTrace();
        }

        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException lue) {
            System.out.println("Line unavailable. ");
            lue.printStackTrace();
        }

        try {
            clip.open(audio);
        } catch (IOException ioe) {
            System.out.println("Cannot find file. ");
            ioe.printStackTrace();
        } catch (LineUnavailableException lue) {
            System.out.println("Line unavailable. ");
            lue.printStackTrace();
        }
    }

    // EFFECTS: plays the audio of whatever audio file is located at the path given in the constructor
    public void playAudio() {
        clip.setFramePosition(0);
        clip.start();

        if (loopContinuously) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

}
