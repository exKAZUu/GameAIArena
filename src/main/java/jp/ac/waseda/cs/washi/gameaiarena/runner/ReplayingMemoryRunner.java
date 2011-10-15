package jp.ac.waseda.cs.washi.gameaiarena.runner;

import java.io.Serializable;
import java.util.Iterator;

public class ReplayingMemoryRunner<Arg, Result extends Serializable, Plyaer>
		extends AbstractRunner<Arg, Result, Plyaer> {

	private final AbstractRunner<Arg, Result, Plyaer> player;
	private final Iterator<Result> resultIterator;

	public ReplayingMemoryRunner(AbstractRunner<Arg, Result, Plyaer> player,
			Iterable<Result> results) {
		this.player = player;
		this.resultIterator = results.iterator();
	}

	@Override
	public Plyaer getPlyaer() {
		return player.getPlyaer();
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
}
