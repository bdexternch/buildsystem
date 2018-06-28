package de.concepthero.exceptions;

public class NoSuchRecordingDeviceException extends Exception {
  public NoSuchRecordingDeviceException() {
    super();
  }

  public NoSuchRecordingDeviceException(String message) {
    super(message);
  }
}
