package sonidos;

import javax.sound.sampled.*;
import java.io.*;

public class ReproductorSonidos {
    private Clip clip;

    public ReproductorSonidos(String ruta) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ruta).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void detener() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void reproducirInfi() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(-1);
        }
    }

}
