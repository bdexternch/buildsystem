package de.ios.util;

import de.ios.exceptions.NoSuchRecordingDeviceException;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.abs;

public class Record {
    private static final int THRESHOLD = 10000;
    private static final int RECORDING_TIME = 10000;

    private boolean stopped = false;

    public static AudioFormat getFormat(){
        return new AudioFormat(22050f, 8, 1, true, true);
    }

    public static void listDevices(){
        Mixer.Info[] infos = AudioSystem.getMixerInfo();

        System.out.println("List of available Audio devices:\n");

        for (int i = 0; i < infos.length; i++) {
            System.out.println(i + ": " + infos[i].getName());
        }
        System.out.flush();
    }

    private static TargetDataLine getLine(String audioDeviceName){
        TargetDataLine line = null;

        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class,
                getFormat());

        for (int i = 0; i < infos.length; i++) {
            try {
                if (infos[i].getName().toLowerCase().equals(audioDeviceName.toLowerCase())) {
                    Mixer mixer = AudioSystem.getMixer(infos[i]);
                    line = (TargetDataLine) mixer.getLine(dataLineInfo);
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(i + ": Failed to get Device: " + infos[i].getName().toLowerCase());
            }
        }

        return line;
    }

    public boolean record(Config config) throws NoSuchRecordingDeviceException {
        boolean soundPresent = false;
        AudioFormat format = getFormat();

        TargetDataLine line = getLine(config.getRecordingDevice());

        if (line == null) {
            throw new NoSuchRecordingDeviceException("Failed to find audio device: " + config.getRecordingDevice());
        }

        try {
            line.open(format);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            byte[] data = new byte[line.getBufferSize() / 5];

            // Begin audio capture.
            line.start();


            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    stopped = true;
                    timer.cancel();
                }
            }, RECORDING_TIME);

            // Here, stopped is a global boolean set by another thread.
            while (!stopped) {
                // Read the next chunk of data from the TargetDataLine.
                numBytesRead = line.read(data, 0, data.length);
                // Save this chunk of data.
                out.write(data, 0, numBytesRead);

                int sum = 0;
                for (int i = 0; i < data.length; i++) {
                    sum += abs(data[i]);
                }
//                System.out.println(sum);
                if (sum > THRESHOLD * config.getAccuracy()) {
                    timer.cancel();
                    line.stop();
                    out.close();
                    return true;
                }
            }

            line.stop();

            byte[]audio = out.toByteArray();

            if (config.isSave()) {
                FileOperations.saveAudio(audio);
            }

            out.close();

//            long sound = 0;
//            for (int i = 0; i < audio.length; i++) {
//                if (abs(audio[i]) > 4) {
//                    sound++;
//                }
//            }
//            if (sound >= audio.length * config.getAccuracy()) {
//                soundPresent = true;
//            } else {
//                soundPresent = false;
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return soundPresent;
    }

    public static void playAudio(byte[] audio) {
        try {
            AudioFormat format = getFormat();

            InputStream input =
                    new ByteArrayInputStream(audio);

            final AudioInputStream ais =
                    new AudioInputStream(input, format,
                            audio.length / format.getFrameSize());
            DataLine.Info info = new DataLine.Info(
                    SourceDataLine.class, format);
            final SourceDataLine line = (SourceDataLine)
                    AudioSystem.getLine(info);
            line.open(format);
            line.start();

            Runnable runner = new Runnable() {
                int bufferSize = (int) format.getSampleRate()
                        * format.getFrameSize();
                byte buffer[] = new byte[bufferSize];

                public void run() {
                    try {
                        int count;
                        while ((count = ais.read(
                                buffer, 0, buffer.length)) != -1) {
                            if (count > 0) {
                                line.write(buffer, 0, count);
                            }
                        }
                        line.drain();
                        line.close();
                    } catch (IOException e) {
                        System.err.println("I/O problems: " + e);
                        System.exit(-3);
                    }
                }
            };
            Thread playThread = new Thread(runner);
            playThread.start();
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e);
            System.exit(-4);
        }
    }
}
