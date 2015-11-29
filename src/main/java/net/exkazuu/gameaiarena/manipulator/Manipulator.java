package net.exkazuu.gameaiarena.manipulator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import net.exkazuu.gameaiarena.player.ExternalComputerPlayer;

public abstract class Manipulator<Arg, Result extends Serializable> {

  public final Result run(Arg input) {
    runPreProcessing(input);
    sendDataToAI(input);
    receiveDataFromAI(input);
    return runPostProcessing(input);
  }

  protected abstract void runPreProcessing(Arg input);

  protected abstract void sendDataToAI(Arg input);

  protected abstract void receiveDataFromAI(Arg input);

  protected abstract Result runPostProcessing(Arg input);

  public abstract boolean released();

  protected abstract void beforeRelease();

  protected abstract void afterRelease();

  public abstract void release();

  public abstract ExternalComputerPlayer getExternalComputerPlayer();

  public Manipulator<Arg, Result> ignoringException() {
    return new IgnoringExceptionManipulator<Arg, Result>(this);
  }

  public Manipulator<Arg, Result> limittingTime(int maxMillisecond) {
    return new LimittingTimeManipulator<Arg, Result>(this, maxMillisecond);
  }

  public Manipulator<Arg, Result> limittingSumTime(int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeManipulator<Arg, Result>(this, maxSumExeededMillisecond,
        availableMillisecond);
  }

  public Manipulator<Arg, Result> recordingStream(ObjectOutputStream oos) {
    return new RecordingStreamManipulator<Arg, Result>(this, oos);
  }

  public Manipulator<Arg, Result> recordingMemory(List<Result> oos) {
    return new RecordingMemoryManipulator<Arg, Result>(this, oos);
  }

  public Manipulator<Arg, Result> replayingStream(ObjectInputStream ois) {
    return new ReplayingStreamManipulator<Arg, Result>(this, ois);
  }

  public Manipulator<Arg, Result> replayingMemory(Iterable<Result> results) {
    return new ReplayingMemoryManipulator<Arg, Result>(this, results);
  }

  public PauseUnpauseManipulator<Arg, Result> pauseUnpause(String[] pauseCommand,
      String[] unpauseCommand) {
    return new PauseUnpauseManipulator<Arg, Result>(this, pauseCommand, unpauseCommand);
  }
}
