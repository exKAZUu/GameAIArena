package net.exkazuu.gameaiarena.runner;

import java.io.Serializable;
import java.util.Iterator;

public class ReplayingMemoryRunner<Arg, Result extends Serializable, Controller>
    extends AbstractRunner<Arg, Result, Controller> {

  private final AbstractRunner<Arg, Result, Controller> player;
  private final Iterator<Result> resultIterator;

  public ReplayingMemoryRunner(AbstractRunner<Arg, Result, Controller> controller,
      Iterable<Result> results) {
    this.player = controller;
    this.resultIterator = results.iterator();
  }

  @Override
  public Controller getComputerPlayer() {
    return player.getComputerPlayer();
  }

  @Override
  public void runPreProcessing(Arg input) {
    player.runPreProcessing(input);
  }

  @Override
  public void runProcessing() {
    player.runProcessing();
  }

  @Override
  public Result runPostProcessing() {
    return resultIterator.next();
  }

  @Override
  public String toString() {
    return player.toString();
  }
}
