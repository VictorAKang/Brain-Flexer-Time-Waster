package ui.audioPlayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URI;

public class AudioPlayer {

    public void playAudio(String audioPath) {
        File file = new File("./src/Bomb.mp3");
        URI uri = file.toURI();
        String media = uri.toString();
        System.out.println(media);
        Media sound = new Media(media);
        //Media sound = new Media("file:///C:/Users/Cliente/IdeaProjects/Brain-Flexer-Time-Waster/data/audio/Bomb.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
