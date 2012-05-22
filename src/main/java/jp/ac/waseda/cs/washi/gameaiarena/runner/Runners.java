package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Runners {
  private Runners() {}

  public static <Arg, Result extends Serializable, Player> AbstractRunner<Arg, Result, Player> ignoringException(
      AbstractRunner<Arg, Result, Player> runner) {
    return new IgnoringExceptionRunner<Arg, Result, Player>(runner);
  }

  public static <Arg, Result extends Serializable, Player> AbstractRunner<Arg, Result, Player> limittingTime(
      AbstractRunner<Arg, Result, Player> runner, int maxMillisecond) {
    return new LimittingTimeRunner<Arg, Result, Player>(runner, maxMillisecond);
  }

  public static <Arg, Result extends Serializable, Player> AbstractRunner<Arg, Result, Player> limittingSumTime(
      AbstractRunner<Arg, Result, Player> runner, int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeRunner<Arg, Result, Player>(runner, maxSumExeededMillisecond,
        availableMillisecond);
  }

  public static <Arg, Result extends Serializable, Player> AbstractRunner<Arg, Result, Player> recordingStream(
      AbstractRunner<Arg, Result, Player> runner, ObjectOutputStream oos) {
    return new RecordingStreamRunner<Arg, Result, Player>(runner, oos);
  }

  public static <Arg, Result extends Serializable, Player> AbstractRunner<Arg, Result, Player> recordingMemory(
      AbstractRunner<Arg, Result, Player> runner, List<Result> oos) {
    return new RecordingMemoryRunner<Arg, Result, Player>(runner, oos);
  }

  public static <Arg, Result extends Serializable, Player> AbstractRunner<Arg, Result, Player> replayingStream(
      AbstractRunner<Arg, Result, Player> runner, ObjectInputStream ois) {
    return new ReplayingStreamRunner<Arg, Result, Player>(runner, ois);
  }

  public static <Arg, Result extends Serializable, Player> AbstractRunner<Arg, Result, Player> replayingMemory(
      AbstractRunner<Arg, Result, Player> runner, Iterable<Result> results) {
    return new ReplayingMemoryRunner<Arg, Result, Player>(runner, results);
  }
}
