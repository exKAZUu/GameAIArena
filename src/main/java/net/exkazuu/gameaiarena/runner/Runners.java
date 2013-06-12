package net.exkazuu.gameaiarena.runner;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Runners {
  private Runners() {}

  public static <Arg, Result extends Serializable, ComputerPlayer> AbstractRunner<Arg, Result, ComputerPlayer> ignoringException(
      AbstractRunner<Arg, Result, ComputerPlayer> runner) {
    return new IgnoringExceptionRunner<Arg, Result, ComputerPlayer>(runner);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> AbstractRunner<Arg, Result, ComputerPlayer> limittingTime(
      AbstractRunner<Arg, Result, ComputerPlayer> runner, int maxMillisecond) {
    return new LimittingTimeRunner<Arg, Result, ComputerPlayer>(runner, maxMillisecond);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> AbstractRunner<Arg, Result, ComputerPlayer> limittingSumTime(
      AbstractRunner<Arg, Result, ComputerPlayer> runner, int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeRunner<Arg, Result, ComputerPlayer>(runner,
        maxSumExeededMillisecond, availableMillisecond);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> AbstractRunner<Arg, Result, ComputerPlayer> recordingStream(
      AbstractRunner<Arg, Result, ComputerPlayer> runner, ObjectOutputStream oos) {
    return new RecordingStreamRunner<Arg, Result, ComputerPlayer>(runner, oos);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> AbstractRunner<Arg, Result, ComputerPlayer> recordingMemory(
      AbstractRunner<Arg, Result, ComputerPlayer> runner, List<Result> oos) {
    return new RecordingMemoryRunner<Arg, Result, ComputerPlayer>(runner, oos);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> AbstractRunner<Arg, Result, ComputerPlayer> replayingStream(
      AbstractRunner<Arg, Result, ComputerPlayer> runner, ObjectInputStream ois) {
    return new ReplayingStreamRunner<Arg, Result, ComputerPlayer>(runner, ois);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> AbstractRunner<Arg, Result, ComputerPlayer> replayingMemory(
      AbstractRunner<Arg, Result, ComputerPlayer> runner, Iterable<Result> results) {
    return new ReplayingMemoryRunner<Arg, Result, ComputerPlayer>(runner, results);
  }
}
