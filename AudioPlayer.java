
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

    public static void play(String filename) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    AudioPlayer.class.getResource("/resources/" + filename)
            );

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (Exception e) {
            System.out.println("Erro ao tocar áudio: " + e.getMessage());
        }
    }
}

