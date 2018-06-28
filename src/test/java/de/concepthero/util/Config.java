package de.concepthero.util;

public class Config {

    private String recordingDevice;
    private double accuracy;
    private boolean save;

    public Config(String recordingDevice, double accuracy, boolean save) {
        this.recordingDevice = recordingDevice;
        this.accuracy = accuracy;
        this.save = save;
    }

    public String getRecordingDevice(){
        return recordingDevice;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public boolean isSave() {
        return save;
    }
}
