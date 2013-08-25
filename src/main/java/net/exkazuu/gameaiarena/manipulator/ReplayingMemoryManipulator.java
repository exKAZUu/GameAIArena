package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;
import java.util.Iterator;

public class ReplayingMemoryManipulator<Arg, Result extends Serializable>
    extends Manipulator<Arg, Result> {

  private final Manipulator<Arg, Result> manipulator;
  private final Iterator<Result> resultIterator;

  public ReplayingMemoryManipulator(Manipulator<Arg, Result> manipulator,
      Iterable<Result> results) {
    this.manipulator = manipulator;
    this.resultIterator = results.iterator();
  }

  @Override
  public void runPreProcessing(Arg input) {
    manipulator.runPreProcessing(input);
  }

  @Override
  public void runProcessing() {
    manipulator.runProcessing();
  }

  @Override
  public Result runPostProcessing() {
    return resultIterator.next();
  }

  @Override
  public String toString() {
    return manipulator.toString();
  }
}
