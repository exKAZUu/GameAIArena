package net.exkazuu.gameaiarena.manipulator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public abstract class Manipulator<Arg, Result extends Serializable, ComputerPlayer> {

  /**
   * Gets an actual an instance of an AI class.
   * 
   * @return an actual an instance of an AI class
   */
  public abstract ComputerPlayer getComputerPlayer();

  public final Result run(Arg input) {
    runPreProcessing(input);
    runProcessing();
    return runPostProcessing();
  }

  protected abstract void runPreProcessing(Arg input);

  protected abstract void runProcessing();

  protected abstract Result runPostProcessing();

  public Manipulator<Arg, Result, ComputerPlayer> ignoringException() {
    return new IgnoringExceptionManipulator<Arg, Result, ComputerPlayer>(this);
  }

  public Manipulator<Arg, Result, ComputerPlayer> limittingTime(int maxMillisecond) {
    return new LimittingTimeManipulator<Arg, Result, ComputerPlayer>(this, maxMillisecond);
  }

  public Manipulator<Arg, Result, ComputerPlayer> limittingSumTime(int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeManipulator<Arg, Result, ComputerPlayer>(this,
        maxSumExeededMillisecond, availableMillisecond);
  }

  public Manipulator<Arg, Result, ComputerPlayer> recordingStream(ObjectOutputStream oos) {
    return new RecordingStreamManipulator<Arg, Result, ComputerPlayer>(this, oos);
  }

  public Manipulator<Arg, Result, ComputerPlayer> recordingMemory(List<Result> oos) {
    return new RecordingMemoryManipulator<Arg, Result, ComputerPlayer>(this, oos);
  }

  public Manipulator<Arg, Result, ComputerPlayer> replayingStream(ObjectInputStream ois) {
    return new ReplayingStreamManipulator<Arg, Result, ComputerPlayer>(this, ois);
  }

  public Manipulator<Arg, Result, ComputerPlayer> replayingMemory(Iterable<Result> results) {
    return new ReplayingMemoryManipulator<Arg, Result, ComputerPlayer>(this, results);
  }

  public ThreadManipulator<Arg, Result, ComputerPlayer> threading() {
    return new ThreadManipulator<Arg, Result, ComputerPlayer>(this);
  }
}
