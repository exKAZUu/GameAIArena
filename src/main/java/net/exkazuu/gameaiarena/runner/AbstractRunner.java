package net.exkazuu.gameaiarena.runner;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractRunner<Arg, Result extends Serializable, ComputerPlayer> {

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

  public AbstractRunner<Arg, Result, ComputerPlayer> ignoringException() {
    return new IgnoringExceptionRunner<Arg, Result, ComputerPlayer>(this);
  }

  public AbstractRunner<Arg, Result, ComputerPlayer> limittingTime(int maxMillisecond) {
    return new LimittingTimeRunner<Arg, Result, ComputerPlayer>(this, maxMillisecond);
  }

  public AbstractRunner<Arg, Result, ComputerPlayer> limittingSumTime(int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeRunner<Arg, Result, ComputerPlayer>(this, maxSumExeededMillisecond,
        availableMillisecond);
  }

  public AbstractRunner<Arg, Result, ComputerPlayer> recordingStream(ObjectOutputStream oos) {
    return new RecordingStreamRunner<Arg, Result, ComputerPlayer>(this, oos);
  }

  public AbstractRunner<Arg, Result, ComputerPlayer> recordingMemory(List<Result> oos) {
    return new RecordingMemoryRunner<Arg, Result, ComputerPlayer>(this, oos);
  }

  public AbstractRunner<Arg, Result, ComputerPlayer> replayingStream(ObjectInputStream ois) {
    return new ReplayingStreamRunner<Arg, Result, ComputerPlayer>(this, ois);
  }

  public AbstractRunner<Arg, Result, ComputerPlayer> replayingMemory(Iterable<Result> results) {
    return new ReplayingMemoryRunner<Arg, Result, ComputerPlayer>(this, results);
  }
}
