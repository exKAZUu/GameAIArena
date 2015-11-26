package net.exkazuu.gameaiarena.manipulator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Manipulators {
  private Manipulators() {}

  public static <Arg, Result extends Serializable> Manipulator<Arg, Result> ignoringException(
      Manipulator<Arg, Result> runner) {
    return new IgnoringExceptionManipulator<Arg, Result>(runner);
  }

  public static <Arg, Result extends Serializable> Manipulator<Arg, Result> limittingTime(
      Manipulator<Arg, Result> runner, int maxMillisecond) {
    return new LimittingTimeManipulator<Arg, Result>(runner, maxMillisecond);
  }

  public static <Arg, Result extends Serializable> Manipulator<Arg, Result> limittingSumTime(
      Manipulator<Arg, Result> runner, int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeManipulator<Arg, Result>(runner,
        maxSumExeededMillisecond, availableMillisecond);
  }

  public static <Arg, Result extends Serializable> Manipulator<Arg, Result> recordingStream(
      Manipulator<Arg, Result> runner, ObjectOutputStream oos) {
    return new RecordingStreamManipulator<Arg, Result>(runner, oos);
  }

  public static <Arg, Result extends Serializable> Manipulator<Arg, Result> recordingMemory(
      Manipulator<Arg, Result> runner, List<Result> oos) {
    return new RecordingMemoryManipulator<Arg, Result>(runner, oos);
  }

  public static <Arg, Result extends Serializable> Manipulator<Arg, Result> replayingStream(
      Manipulator<Arg, Result> runner, ObjectInputStream ois) {
    return new ReplayingStreamManipulator<Arg, Result>(runner, ois);
  }

  public static <Arg, Result extends Serializable> Manipulator<Arg, Result> replayingMemory(
      Manipulator<Arg, Result> runner, Iterable<Result> results) {
    return new ReplayingMemoryManipulator<Arg, Result>(runner, results);
  }

  public static <Arg, Result extends Serializable> ThreadManipulator<Arg, Result> therading(
      Manipulator<Arg, Result> runner) {
    return new ThreadManipulator<Arg, Result>(runner);
  }

  public static <Arg, Result extends Serializable> PauseUnpauseManipulator<Arg, Result> pauseUnpause(
      Manipulator<Arg, Result> runner, String[] pauseCommand, String[] unpauseCommand) {
    return new PauseUnpauseManipulator<Arg, Result>(runner, pauseCommand, unpauseCommand);
  }
}
