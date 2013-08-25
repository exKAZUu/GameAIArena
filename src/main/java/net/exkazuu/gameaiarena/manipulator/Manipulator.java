package net.exkazuu.gameaiarena.manipulator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import net.exkazuu.gameaiarena.player.ExternalComputerPlayer;

public abstract class Manipulator<Arg, Result extends Serializable> {

  public final Result run(Arg input) {
    runPreProcessing(input);
    runProcessing();
    return runPostProcessing();
  }

  public ExternalComputerPlayer getExternalComputerPlayer() {
    return null;
  }

  protected abstract void runPreProcessing(Arg input);

  protected abstract void runProcessing();

  protected abstract Result runPostProcessing();

  public Manipulator<Arg, Result> ignoringException() {
    return new IgnoringExceptionManipulator<Arg, Result>(this);
  }

  public Manipulator<Arg, Result> limittingTime(int maxMillisecond) {
    return new LimittingTimeManipulator<Arg, Result>(this, maxMillisecond);
  }

  public Manipulator<Arg, Result> limittingSumTime(int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeManipulator<Arg, Result>(this,
        maxSumExeededMillisecond, availableMillisecond);
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

  public ThreadManipulator<Arg, Result> threading() {
    return new ThreadManipulator<Arg, Result>(this);
  }
}
