package net.exkazuu.gameaiarena.manipulator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Manipulators {
  private Manipulators() {}

  public static <Arg, Result extends Serializable, ComputerPlayer> Manipulator<Arg, Result, ComputerPlayer> ignoringException(
      Manipulator<Arg, Result, ComputerPlayer> runner) {
    return new IgnoringExceptionManipulator<Arg, Result, ComputerPlayer>(runner);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> Manipulator<Arg, Result, ComputerPlayer> limittingTime(
      Manipulator<Arg, Result, ComputerPlayer> runner, int maxMillisecond) {
    return new LimittingTimeManipulator<Arg, Result, ComputerPlayer>(runner, maxMillisecond);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> Manipulator<Arg, Result, ComputerPlayer> limittingSumTime(
      Manipulator<Arg, Result, ComputerPlayer> runner, int availableMillisecond,
      int maxSumExeededMillisecond) {
    return new LimittingSumTimeManipulator<Arg, Result, ComputerPlayer>(runner,
        maxSumExeededMillisecond, availableMillisecond);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> Manipulator<Arg, Result, ComputerPlayer> recordingStream(
      Manipulator<Arg, Result, ComputerPlayer> runner, ObjectOutputStream oos) {
    return new RecordingStreamManipulator<Arg, Result, ComputerPlayer>(runner, oos);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> Manipulator<Arg, Result, ComputerPlayer> recordingMemory(
      Manipulator<Arg, Result, ComputerPlayer> runner, List<Result> oos) {
    return new RecordingMemoryManipulator<Arg, Result, ComputerPlayer>(runner, oos);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> Manipulator<Arg, Result, ComputerPlayer> replayingStream(
      Manipulator<Arg, Result, ComputerPlayer> runner, ObjectInputStream ois) {
    return new ReplayingStreamManipulator<Arg, Result, ComputerPlayer>(runner, ois);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> Manipulator<Arg, Result, ComputerPlayer> replayingMemory(
      Manipulator<Arg, Result, ComputerPlayer> runner, Iterable<Result> results) {
    return new ReplayingMemoryManipulator<Arg, Result, ComputerPlayer>(runner, results);
  }

  public static <Arg, Result extends Serializable, ComputerPlayer> ThreadManipulator<Arg, Result, ComputerPlayer> therad(
      Manipulator<Arg, Result, ComputerPlayer> runner) {
    return new ThreadManipulator<Arg, Result, ComputerPlayer>(runner);
  }
}
