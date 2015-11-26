package net.exkazuu.gameaiarena.manipulator;

import java.io.Serializable;
import java.util.Iterator;

public class ReplayingMemoryManipulator<Arg, Result extends Serializable>
    extends DefaultManipulator<Arg, Result> {
  private final Iterator<Result> resultIterator;

  public ReplayingMemoryManipulator(Manipulator<Arg, Result> manipulator,
      Iterable<Result> results) {
    super(manipulator);
    this.resultIterator = results.iterator();
  }

  @Override
  protected void sendDataToAI(Arg input) {}

  @Override
  protected void receiveDataFromAI() {}

  @Override
  protected Result runPostProcessing() {
    return resultIterator.next();
  }
}
