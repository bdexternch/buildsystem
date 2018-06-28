package de.ios.util;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOperations {

    private static boolean createFolder(String path) {
        File folder = new File(path);
        if (!folder.isDirectory() && !folder.exists()) {
            return folder.mkdir();
        }
        return true;
    }

    public static boolean saveAudio(byte[] audio) throws IOException {
        if (FileOperations.createFolder("audio_test" + File.separator)) {

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy__HH_mm_ss");
            File file = new File("audio_test" + File.separator + dateFormat.format(date) + "_audio_test.wav");
            if (!file.exists()) {
                AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(audio), Record.getFormat(),
                        audio.length);
                AudioSystem.write(stream, AudioFileFormat.Type.WAVE, file);
                return true;
            }
        }
        return false;
    }

    public static Config loadConfig(String mic, double accuracy, boolean save) {
        Config config = new Config(mic, accuracy, save);
        return config;
    }
}
