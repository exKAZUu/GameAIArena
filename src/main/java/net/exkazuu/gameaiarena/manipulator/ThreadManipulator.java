package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManipulator<Arg, Result extends Serializable, Controller> {
  protected static ExecutorService executorService = Executors.newCachedThreadPool();
  private final Manipulator<Arg, Result, Controller> manipulator;

  public ThreadManipulator(Manipulator<Arg, Result, Controller> manipulator) {
    this.manipulator = manipulator;
    if (executorService.isShutdown()) {
      executorService = Executors.newCachedThreadPool();
    }
  }

  public static void shutdownExecutorService() {
    executorService.shutdown();
  }

  public Controller getComputerPlayer() {
    return manipulator.getComputerPlayer();
  }

  public final ManipulatorResult<Result> run(final Arg input) {
    final ManipulatorResult<Result> result = new ManipulatorResult<Result>();
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        manipulator.runPreProcessing(input);
        manipulator.runProcessing();
        result.setResult(manipulator.runPostProcessing());
      }
    });
    return result;
  }
}
